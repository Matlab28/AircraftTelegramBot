package com.example.telegrambook.client;

import com.example.telegrambook.dto.aircraftDto.Root;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "aircraftApi", url = "https://sky-scanner3.p.rapidapi.com/get-config")
public interface AircraftClient {
    @GetMapping
    Root getData(@RequestHeader("x-rapidapi-host") String host,
                 @RequestHeader("x-rapidapi-key") String apiKey);
}
