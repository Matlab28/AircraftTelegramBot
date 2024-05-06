package com.example.telegrambook.dto.aircraftDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Root {
    private ArrayList<Datum> data;
    private Boolean status;
    private String message;
}
