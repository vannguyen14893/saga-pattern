package com.saga.admin.dto.request.languages;

public record CreateLanguagesRequest(String languageCode, String languageName, String languageFlag,
                                     String languageDescription, boolean active) {

}
