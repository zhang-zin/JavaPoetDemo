package com.zj.javapoetdemo_compiler;

//@Override
//public void getParameter(Object targetParameter) {
//    Personal_MainActivity t = (Personal_MainActivity) targetParameter;
//    t.name = t.getIntent().getStringExtra("name");
//    t.sex = t.getIntent().getStringExtra("sex");
//    t.age = t.getIntent().getIntExtra("age", t.age);
//}

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterSpec;
import com.squareup.javapoet.TypeName;
import com.zj.javapoetdemo_annotations.Parameter;
import com.zj.javapoetdemo_compiler.utils.ProcessorConfig;

import java.util.List;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import javax.annotation.processing.Messager;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;
import javax.tools.Diagnostic;

/**
 * 生成注解的生成类中的方法
 *
 * @author zhangjin
 */
public class ParameterMethodFactory {


    private final Messager messager;
    private final ClassName className;
    private MethodSpec.Builder methodBuilder;

    private ParameterMethodFactory(Builder builder) {
        className = builder.className;
        messager = builder.messager;
        addMethodName(builder.parameterSpec);
        addFirstStatement();
    }

    /**
     * @Override public void getParameter(Object targetParameter)
     */
    private void addMethodName(ParameterSpec parameterSpec) {
        methodBuilder = MethodSpec.methodBuilder(ProcessorConfig.PARAMETER_METHOD_NAME)
                .addAnnotation(ClassName.get(Override.class))
                .addModifiers(Modifier.PUBLIC)
                .returns(TypeName.VOID)
                .addParameter(parameterSpec);
    }

    /**
     * Personal_MainActivity t = (Personal_MainActivity) targetParameter;
     */
    private void addFirstStatement() {
        methodBuilder.addStatement("$T t = ($T) $N", className, className, ProcessorConfig.PARAMETER_NAME);
    }

    /**
     * t.name = t.getIntent().getStringExtra("name");
     * t.sex = t.getIntent().getStringExtra("sex");
     * t.age = t.getIntent().getIntExtra("age", t.age);
     * <p>
     * element ==  String name
     *
     * @param elements 被@Parameter 注解的参数列表
     * @return 方法生成工厂，
     */
    public ParameterMethodFactory buildStatement(List<Element> elements) {
        if (elements.isEmpty()) {
            throw new RuntimeException("被@Parameter 注解的参数列表不能为空");
        }

        for (Element element : elements) {
            TypeMirror typeMirror = element.asType();

            // 获取被@Parameters注解属性的类型
            TypeKind fieldType = typeMirror.getKind();

            // 属性的名称
            String fieldName = element.getSimpleName().toString();
            // 注解的值
            String annotationValue = element.getAnnotation(Parameter.class).name();
            annotationValue = annotationValue.isEmpty() ? fieldName : annotationValue;

            String finalValue = "t." + fieldName;
            String methodContent = finalValue + " = t.getIntent().";
            switch (fieldType) {
                case INT:
                    methodContent += "getIntExtra($S," + finalValue + ")";
                    break;
                case BOOLEAN:
                    methodContent += "getBooleanExtra($S," + finalValue + ")";
                    break;
                default:
                    if (typeMirror.toString().equalsIgnoreCase(ProcessorConfig.STRING)) {
                        methodContent += "getStringExtra($S)";
                    }
                    break;
            }

            if (methodContent.endsWith(")")) {
                methodBuilder.addStatement(methodContent, annotationValue);
            } else {
                messager.printMessage(Diagnostic.Kind.ERROR, typeMirror.toString() + "不支持此参数类型");
            }
        }
        return this;
    }

    /**
     * @return 最终生成的注解处理器生成类的方法
     */
    public MethodSpec buildMethodSpec() {
        return methodBuilder.build();
    }

    public static class Builder {

        private ClassName className;
        private Messager messager;
        private ParameterSpec parameterSpec;

        public Builder(ParameterSpec parameterSpec) {
            this.parameterSpec = parameterSpec;
        }

        public Builder setClassName(ClassName className) {
            this.className = className;
            return this;
        }

        public Builder setMessager(Messager messager) {
            this.messager = messager;
            return this;
        }

        public ParameterMethodFactory build() {
            if (parameterSpec == null) {
                throw new RuntimeException("parameterSpec不能为空");
            }

            if (className == null) {
                throw new RuntimeException("className不能为空");
            }

            if (messager == null) {
                throw new RuntimeException("messager不能为空");
            }

            return new ParameterMethodFactory(this);
        }

    }
}
