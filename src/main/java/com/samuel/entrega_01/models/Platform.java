package com.samuel.entrega_01.models;

public enum Platform {
    PC("PC"), PS5("PS5"), XBOX("Xbox"), SWITCH_2("Switch 2"), ANDROID("Android"), IOS("iOS");

    private final String displayName;

    Platform(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
