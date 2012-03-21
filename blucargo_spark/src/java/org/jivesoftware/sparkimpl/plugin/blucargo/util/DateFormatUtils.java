package org.jivesoftware.sparkimpl.plugin.blucargo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatUtils {
	
	private static final SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
	
	public static String formatDate(Date date)
	{
		return formatter.format(date);
	}
}
