package com.github.lexya.minispring;

public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
