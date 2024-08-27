package com.bookstoreapi.bookstoreapi;

import io.micrometer.core.instrument.Timer;

import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;

@Component
public class CustomMetrics {
    private final MeterRegistry meterRegistry;

    public CustomMetrics(MeterRegistry registry) {
        this.meterRegistry = registry;
    }

    @PostConstruct
    public void init() {
        meterRegistry.counter("custom.metric.counter", "type", "example").increment();
        Timer timer = meterRegistry.timer("custom.metric.timer");
        timer.record(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch(InterruptedException exception) {
                Thread.currentThread().interrupt();
            }
        });
    }
}
