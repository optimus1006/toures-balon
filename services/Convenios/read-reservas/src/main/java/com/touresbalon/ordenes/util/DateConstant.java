package com.touresbalon.ordenes.util;

import java.time.format.DateTimeFormatter;

public interface DateConstant {

	String DATE_TIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DateConstant.DATE_TIME_FORMAT);

}
