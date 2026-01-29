package com.example.spring_boot_lab.config;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneOffset;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

//正常時鐘
//@Configuration
//public class ClockConfig {
//	@Bean
//	public Clock clock() {
//		return Clock.systemDefaultZone();
//	}
//}

//Mock時鐘
@Configuration
public class MockClockConfig {
	@Bean
	@Primary
	public Clock mockClock() {
		return Clock.fixed(
				Instant.parse("2025-10-31T10:00:00Z"),
				ZoneOffset.systemDefault());
	}
}