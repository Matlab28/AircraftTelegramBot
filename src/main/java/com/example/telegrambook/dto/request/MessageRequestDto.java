package com.example.telegrambook.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MessageRequestDto {
    @JsonProperty("message_id")
    public Integer messageId;
    public FromRequestDto from;
    public ChatRequestDto chat;
    public Integer date;
    public String text;
}
