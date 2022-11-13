package ru.andrew.device.booking.adapter.out.fonoapi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import ru.andrew.device.booking.application.DetailsUnavailableException;
import ru.andrew.device.booking.application.out.GetDetailsPort;

import java.util.List;
import java.util.Map;

@Component
public class FonoapiDetailsPort implements GetDetailsPort {

    private final RestTemplate restTemplate = new RestTemplate();


    private final String token;

    public FonoapiDetailsPort(@Value("${fonoapi.token}") String token) {
        this.token = token;
    }

    @Override
    public Map<String, String> getDetails(String deviceName) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(List.of(MediaType.APPLICATION_JSON));

            MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
            map.add("token", token);
            map.add("device", deviceName);

            HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

            ResponseEntity<Map> response =
                    restTemplate.exchange("https://fonoapi.freshpixl.com/v1/getdevice",
                            HttpMethod.POST,
                            entity,
                            Map.class);

            return response.getBody();

        } catch (Exception e) {
            throw new DetailsUnavailableException(e);
        }
    }
}
