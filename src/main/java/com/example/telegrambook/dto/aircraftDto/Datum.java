package com.example.telegrambook.dto.aircraftDto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Datum {
    private String country;
    private String market;
    private String locale;
    @JsonProperty("currency_title")
    private Object currencyTitle;
    private String currency;
    @JsonProperty("currency_symbol")
    private String currencySymbol;
    private String site;
}
