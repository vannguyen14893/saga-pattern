package com.saga.admin.dto.response.languages;

public record LanguagesResponse(Long id,
                                String languageCode,
                                String languageName,
                                String languageFlag,
                                String languageDescription,
                                boolean active) {
}
