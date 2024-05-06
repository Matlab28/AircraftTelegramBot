package com.example.telegrambook.controller;

import com.example.telegrambook.dto.response.RootResponseDto;
import com.example.telegrambook.service.AircraftService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/flight")
public class AircraftController {
    private final AircraftService service;

    @GetMapping
    public RootResponseDto msgResponse() {
        return service.sendResponse();
    }
}
