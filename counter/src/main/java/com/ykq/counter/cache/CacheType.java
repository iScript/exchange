package com.ykq.counter.cache;

public enum CacheType {

    CAPTCHA("captcha:"),

    ACCOUNT("account:"),

    ORDER("order1:"),

    TRADE("trade:"),

    POSI("posi:"),
    ;

    private String type;

    CacheType(String type) {
        this.type = type;
    }

    public String type() {
        return this.type;
    }


}

