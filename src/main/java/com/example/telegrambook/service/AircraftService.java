package com.example.telegrambook.service;

import com.example.telegrambook.client.AircraftClient;
import com.example.telegrambook.client.TelegramAircraftClient;
import com.example.telegrambook.dto.aircraftDto.Datum;
import com.example.telegrambook.dto.aircraftDto.Root;
import com.example.telegrambook.dto.request.RootRequestDto;
import com.example.telegrambook.dto.request.TelegramSendDto;
import com.example.telegrambook.dto.response.RootResponseDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class AircraftService {
    private final AircraftClient client;
    private final TelegramAircraftClient telegramClient;
    private final String host = "YOUR_RAPID_API_HOST";
    private final String key = "YOUR_RAPID_API_KEY";
    private Random random = new Random();
    private Long lastUpdateId = 0L;

    public RootRequestDto getUpdateService() {
        RootRequestDto updates = telegramClient.getUpdates(0L);
        Integer updateId = updates.getResult().get(updates.getResult().size() - 1).getUpdateId();
        log.info("Message got from - " + updates.getResult().get(0).getMessage().getFrom().getFirstName());
        return telegramClient.getUpdates(Long.valueOf(updateId));
    }


    public Root getUpdates() {
        return client.getData(host, key);
    }

    public RootResponseDto sendMessage(TelegramSendDto dto) {
        return telegramClient.sendMessage(dto);
    }

    public String formatFlightInfo(Datum datum) {
        return String.format("Flight Information:\n" +
                        "- Country: %s\n" +
                        "- Market Code: %s\n" +
                        "- Locale: %s\n" +
                        "- Currency: %s\n" +
                        "- Site: %s",
                datum.getCountry(),
                datum.getMarket(),
                datum.getLocale(),
                datum.getCurrency(),
                datum.getSite());
    }


    public RootResponseDto sendResponse() {
        RootRequestDto updateService = getUpdateService();
        String text = updateService.getResult().get(0).getMessage().getText();
        Long id = updateService.getResult().get(0).getMessage().getChat().getId();
        TelegramSendDto dto = new TelegramSendDto();
        dto.setChatId(String.valueOf(id));

        Root updates = getUpdates();

        if (text.equals("/start")) {
            String msg = "Hi " + updateService.getResult().get(0).getMessage().getFrom().getFirstName() +
                    ", welcome to  Aircraft bot!\n\nHow can I help you?";

            dto.setText(msg);
            sendMessage(dto);
            log.info("Message sent to - " + updateService.getResult().get(0).getMessage().getFrom().getFirstName());
        } else if (text.toLowerCase().contains("air")
                || text.toLowerCase().contains("aircraft")
                || text.toLowerCase().contains("plane")) {
            log.info("Message got from - " + updateService.getResult().get(0).getMessage().getFrom().getFirstName());

            Datum randomDatum = updates.getData().get(random.nextInt(updates.getData().size()));

            dto.setText(formatFlightInfo(randomDatum));
            log.info("Message sent to - " + updateService.getResult().get(0).getMessage().getFrom().getFirstName());
            return sendMessage(dto);
        } else {
            boolean countryMatch = false;
            for (Datum datum : updates.getData()) {
                if (text.toLowerCase().contains(datum.getCountry().toLowerCase())) {
                    countryMatch = true;
                    String msg = "Here is, you can check '" + datum.getCountry() + "':\n\n";
                    dto.setText(msg + formatFlightInfo(datum));
                    log.info("Message sent to - " +
                            updateService.getResult().get(0).getMessage().getFrom().getFirstName());
                    return sendMessage(dto);
                }
            }
            if (!countryMatch) {
                log.info("No matching country found. Msg got from - " +
                        updateService.getResult().get(0).getMessage().getFrom().getFirstName());
                dto.setText("Sorry, I couldn't find any flight information for the country '" + text + "'.");
                sendMessage(dto);
            }
        }
        return null;
    }

    @Scheduled(fixedDelay = 1000)
    public void refresh() {
        RootRequestDto updateService = getUpdateService();
        if (!updateService.getResult().isEmpty()) {
            Integer latestUpdateId = updateService.getResult().get(updateService.getResult().size() - 1).getUpdateId();
            if (latestUpdateId > lastUpdateId) {
                lastUpdateId = Long.valueOf(latestUpdateId);
                sendResponse();
            }
        }
    }
}
