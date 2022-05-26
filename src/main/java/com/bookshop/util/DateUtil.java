package com.bookshop.util;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.TimeZone;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DateUtil {

	public static Date converToDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(TimeZone.getTimeZone("Turkey").toZoneId()).toInstant());
	}

}
