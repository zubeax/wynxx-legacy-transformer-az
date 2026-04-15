package com.example.demo.enums;

public enum DataSourceType {

    DATABASE("Database"),
    REST_API("REST API"),
    FILE("File"),
    STREAM("Stream"),
    WEBHOOK("Webhook"),
    GRAPHQL("GraphQL"),
    SOAP("SOAP Web Service"),
    MESSAGE_QUEUE("Message Queue");

    private final String displayName;

    DataSourceType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public boolean supportsRealTime() {
        return this == STREAM || this == WEBHOOK || this == MESSAGE_QUEUE;
    }

    public boolean requiresPolling() {
        return this == DATABASE || this == REST_API || this == FILE || this == GRAPHQL || this == SOAP;
    }
}
