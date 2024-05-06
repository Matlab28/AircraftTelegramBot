package com.example.telegrambook.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FromRequestDto {
    public Long id;
    @JsonProperty("is_bot")
    public Boolean isBot;
    @JsonProperty("first_name")
    public String firstName;
    @JsonProperty("language_code")
    public String languageCode;
}
