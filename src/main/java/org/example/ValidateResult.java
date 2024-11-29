package org.example;

/**
 * validateUserの結果を表現するDataClass
 */
public record ValidateResult(
    boolean success,
    String errorMessage,
    String generatedId,
    String logMessage
) {}
