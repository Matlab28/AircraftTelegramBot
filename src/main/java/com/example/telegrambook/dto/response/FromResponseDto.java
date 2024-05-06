package com.example.telegrambook.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FromResponseDto {
    public Long id;
    @JsonProperty("is_bot")
    public Boolean isBot;
    @JsonProperty("first_name")
    public String firstName;
    public String username;
}
