package com.example.telegrambook.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResultRequestDto {
    @JsonProperty("update_id")
    public Integer updateId;
    public MessageRequestDto message;
}
