package com.fordeal.search.sort;

import com.fordeal.search.base.BaseSort;
import com.fordeal.search.base.Maps2;

import java.util.Map;

/**
 * 脚本排序
 * Created by maoxiajun on 2018/6/11.
 */
public class ScriptSort extends BaseSort<ScriptSort> {

    private String inlineScript;
    private Map<String, Object> params;

    @Override
    public String name() {
        return "_script";
    }

    /**
     * 设置内嵌脚本
     * @param inline 脚本
     * @return self
     */
    public ScriptSort script(String inline) {
        this.inlineScript = inline;
        return this;
    }

    /**
     * 设置内嵌脚本中需要用到的参数，如果没有脚本参数，可以不设置
     * @param params 脚本参数
     * @return self
     */
    public ScriptSort params(Map<String, Object> params) {
        this.params = params;
        return this;
    }

    @Override
    public Map<String, Object> output() {
        Map<String, Object> output = super.output();
        output.put("type", "number");
        // 拼装脚本
        Map<String, Object> script = Maps2.of("lang", "painless");
        script.put("inline", this.inlineScript);
        if (null != this.params) {
            script.put("params", this.params);
        }
        output.put("script", script);
        return output;
    }

}
