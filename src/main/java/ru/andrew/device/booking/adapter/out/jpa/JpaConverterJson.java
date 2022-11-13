package ru.andrew.device.booking.adapter.out.jpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Map;

@Converter(autoApply = true)
public class JpaConverterJson implements AttributeConverter<Map<String, String>, String> {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Override
    @SneakyThrows
    public String convertToDatabaseColumn(Map<String, String> meta) {
        return objectMapper.writeValueAsString(meta);
    }

    @Override
    @SneakyThrows
    public Map<String, String> convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return objectMapper.readValue(dbData, Map.class);
    }
}