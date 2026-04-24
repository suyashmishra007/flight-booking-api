package io.github.suyashmishra007.flight.application.service;

import io.github.suyashmishra007.flight.application.dto.BookingResponse;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class IdempotencyService {

    private final Map<String, BookingResponse> store = new ConcurrentHashMap<>();

    public BookingResponse get(String key) {
        return store.get(key);
    }

    public boolean exists(String key) {
        return store.containsKey(key);
    }

    public void put(String key, BookingResponse response) {
        store.put(key, response);
    }

    // To avoid race-condition
    public BookingResponse putIfAbsent(String key, BookingResponse response) {
        return store.putIfAbsent(key, response);
    }
}
