package com.example.telegrambook.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Setter
@Getter
public class RootRequestDto {
    public Boolean ok;
    public ArrayList<ResultRequestDto> result;
}
