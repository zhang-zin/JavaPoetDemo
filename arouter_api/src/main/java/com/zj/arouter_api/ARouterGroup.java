package com.zj.arouter_api;

import java.util.Map;

public interface ARouterGroup {

    Map<String, Class<? extends ARouterPath>> getGroupMap();
}
