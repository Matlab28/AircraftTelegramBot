package com.example.telegrambook.client;

import com.example.telegrambook.dto.request.RootRequestDto;
import com.example.telegrambook.dto.request.TelegramSendDto;
import com.example.telegrambook.dto.response.RootResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "telegramApi", url = "https://api.telegram.org/botYOUR_BOT_TOKEN")
public interface TelegramAircraftClient {
    @GetMapping("/getUpdates?offset={value}")
    RootRequestDto getUpdates(@PathVariable Long value);

    @PostMapping("/sendMessage")
    RootResponseDto sendMessage(@RequestBody TelegramSendDto dto);
}

