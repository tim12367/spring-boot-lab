package com.example.spring_boot_lab;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.annotation.PostConstruct;

@SpringBootTest
class DateTests {

	@Autowired
	private Clock clock;

	LocalDateTime now;

	@PostConstruct
	public void init() {
		// 使用mock時鐘建立的時間
		now = LocalDateTime.now(clock);
		System.out.println("現在mock時間：" + now.toString());
	}

	/*
	 * "2024-07-15T11:17:31.754+08:00” 帶時區字串 → LocalDateTime
	 */
	@Test
	void testZoneDateTimeToLocalDateTime() {
		String dateStr = "2024-07-15T11:17:31.754+08:00";
		ZonedDateTime zonedDateTime = ZonedDateTime.parse(dateStr);
		LocalDateTime localDateTime = zonedDateTime.toLocalDateTime();
		assertEquals("2024-07-15T11:17:31.754", localDateTime.toString());
	}

	/*
	 * "2024-12-10T13:46:47.305006” 無時區字串 → LocalDateTime(S代表幾分之幾秒，目前最長9位數)
	 */
	@Test
	void testStringToLocalDateTime() {
		String dateStr = "2024-12-10T13:46:47.305006";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
		LocalDateTime localDateTime = LocalDateTime.parse(dateStr, formatter);
		assertEquals("2024-12-10T13:46:47.305006", localDateTime.toString());
	}

	/*
	 * LocalDateTime → "yyyy年MM月dd日 HH:mm:ss"
	 */
	@Test
	void testLocalDateTimeToString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
		String localDateTime = now.format(formatter);
		assertEquals("2025年10月31日 18:00:00", localDateTime);
	}

	/*
	 * LocalDateTime 換時區，台北 to UTC
	 * 原始mock時間 2025-10-31T18:00 -> 2025-10-31T10:00:00
	 */
	@Test
	void testChangeZone() {
		ZonedDateTime nowUtc = now.atZone(ZoneId.of("Asia/Taipei")).withZoneSameInstant(ZoneId.of("UTC"));
		assertEquals("2025-10-31T10:00", nowUtc.toLocalDateTime().toString());
	}

}