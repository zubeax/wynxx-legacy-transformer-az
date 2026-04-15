package com.example.demo.enums;

public enum OutputFormat {

    JSON("JSON"),
    CSV("CSV"),
    XML("XML"),
    PARQUET("Apache Parquet"),
    AVRO("Apache Avro"),
    ORC("ORC"),
    EXCEL("Excel (XLSX)"),
    TSV("Tab-Separated Values");

    private final String displayName;

    OutputFormat(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getFileExtension() {
        return switch (this) {
            case JSON -> ".json";
            case CSV -> ".csv";
            case XML -> ".xml";
            case PARQUET -> ".parquet";
            case AVRO -> ".avro";
            case ORC -> ".orc";
            case EXCEL -> ".xlsx";
            case TSV -> ".tsv";
        };
    }

    public String getMimeType() {
        return switch (this) {
            case JSON -> "application/json";
            case CSV -> "text/csv";
            case XML -> "application/xml";
            case PARQUET -> "application/octet-stream";
            case AVRO -> "application/octet-stream";
            case ORC -> "application/octet-stream";
            case EXCEL -> "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
            case TSV -> "text/tab-separated-values";
        };
    }

    public boolean isTextBased() {
        return this == JSON || this == CSV || this == XML || this == TSV;
    }
}
