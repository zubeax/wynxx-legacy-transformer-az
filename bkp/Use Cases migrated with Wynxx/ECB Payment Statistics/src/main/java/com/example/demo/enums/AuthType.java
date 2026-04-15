package com.example.demo.enums;

public enum AuthType {

    NONE("No Authentication"),
    API_KEY("API Key"),
    BASIC_AUTH("Basic Authentication"),
    OAUTH2("OAuth 2.0"),
    JWT("JWT Token"),
    CERTIFICATE("Certificate"),
    CUSTOM("Custom");

    private final String displayName;

    AuthType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean requiresCredentials() {
        return this != NONE;
    }

    public boolean isTokenBased() {
        return this == OAUTH2 || this == JWT || this == API_KEY;
    }
}
