package com.saga.admin.dto.request.languages;

public record UpdateLanguagesRequest(Long id, String languageCode, String languageName, String languageFlag,
                                     String languageDescription, boolean active) {
}
