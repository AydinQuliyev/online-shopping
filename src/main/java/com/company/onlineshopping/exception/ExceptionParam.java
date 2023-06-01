package com.company.onlineshopping.exception;

import java.util.HashMap;
import java.util.Map;

public class ExceptionParam {

    private final Map<String, Object> params;

    private ExceptionParam(Map<String, Object> params) {
        this.params = params;
    }

    public static ExceptionParam collect() {
        return new ExceptionParam(new HashMap<>());
    }

    public ExceptionParam param(String key, Object value) {
        this.params.put(key, value);
        return this;
    }

    protected Map<String, Object> build() {
        return this.params;
    }
}
