package com.baiytfp.hf.faceguardiot.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class DateUtil {

	/**
	 * 年月日缺省分隔符
	 */
	private static char DAY_DELIMITER = '-';
	public static final SimpleDateFormat yyyy_MM_dd_sdf = new SimpleDateFormat("yyyy-MM-dd");
	public static final SimpleDateFormat HH_mm_ss_sdf = new SimpleDateFormat("HH:mm:ss");
	public static final SimpleDateFormat yyMMdd_sdf = new SimpleDateFormat("yyMMdd");
	public static final SimpleDateFormat yyMM_sdf = new SimpleDateFormat("yyMM");
	public static final SimpleDateFormat HHmmss_sdf = new SimpleDateFormat("HHmmss");

	public static final SimpleDateFormat standard_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static final SimpleDateFormat yyMMddHHmmss_sdf = new SimpleDateFormat("yyMMddHHmmss");

	// 取得年龄
	public static int getAge(String dateString) {
		String[] arr = StringUtil.split(dateString, '-');
		Calendar cal = new GregorianCalendar(Integer.parseInt(arr[0]),
				Integer.parseInt(arr[1]), Integer.parseInt(arr[2]));
		Calendar now = new GregorianCalendar();
		int res = now.get(Calendar.YEAR) - cal.get(Calendar.YEAR);
		if ((cal.get(Calendar.MONTH) > now.get(Calendar.MONTH))
				|| (cal.get(Calendar.MONTH) == now.get(Calendar.MONTH) && cal
						.get(Calendar.DAY_OF_MONTH) > now
						.get(Calendar.DAY_OF_MONTH))) {
			res--;
		}
		// System.out.println(res);
		return res;
	}

	// 传入格式 YYYY-MM-DD HH:mm:ss;
	public static String getTimestamp(String aTimestamp) {
		String date = StringUtil.getField(aTimestamp, " ", 0L);
		if ((date != null) && (!(date.equals("")))) {
			String time = StringUtil.getField(aTimestamp, " ", 1L);
			int pos = time.lastIndexOf(".");
			if (pos != -1)
				time = time.substring(0, pos);

			int year = Integer.parseInt(StringUtil.getField(date, "-", 0L));
			int month = Integer.parseInt(StringUtil.getField(date, "-", 1L));
			int day = Integer.parseInt(StringUtil.getField(date, "-", 2L));
			int hour = Integer.parseInt(StringUtil.getField(time, ":", 0L));
			int minute = Integer.parseInt(StringUtil.getField(time, ":", 1L));
			int second = Integer.parseInt(StringUtil.getField(time, ":", 2L));
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month - 1, day, hour, minute, second);
			return String.valueOf(calendar.getTime().getTime());
		}

		return "";
	}

	/**
	 * 常用的格式化日期
	 * 
	 * @param date
	 *            Date
	 * @return String
	 */
	public static String formatDate(Date date) {
		return formatDateByFormat(date, "yyyy-MM-dd");
	}

	/**
	 * 以指定的格式来格式化日期
	 *
	 * @param date
	 *            Date
	 * @param format
	 *            String
	 * @return String
	 */
	public static String formatDateByFormat(Date date, String format) {
		String result = "";
		if (date != null) {
			try {
				SimpleDateFormat sdf = new SimpleDateFormat(format);
				result = sdf.format(date);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 格式化日期
	 *
	 * @param dateStr
	 *            字符型日期
	 * @param format
	 *            格式
	 * @return 返回日期
	 */
	public static Date parseDate(String dateStr, String format) {
		Date date = null;
		try {
			java.text.DateFormat df = new SimpleDateFormat(format);
			String dt = dateStr; // Normal.parse(dateStr).replaceAll("-", "/");
			if ((!dt.equals("")) && (dt.length() < format.length())) {
				dt += format.substring(dt.length()).replaceAll("[YyMmDdHhSs]",
						"0");
			}
			date = (Date) df.parse(dt);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	// 字符串转日期
	public static Date parseDate(String dateStr) {
		return parseDate(dateStr, "yyyy/MM/dd");
	}

	// 数据库日期转普通日期
	public static Date parseDate(java.sql.Date date) {
		return date;
	}

	// 普通日期转数据库日期
	public static java.sql.Date parseSqlDate(Date date) {
		if (date != null)
			return new java.sql.Date(date.getTime());
		else
			return null;
	}

	// 字符串转数据库日期
	public static java.sql.Date parseSqlDate(String dateStr, String format) {
		Date date = parseDate(dateStr, format);
		return parseSqlDate(date);
	}

	// 字符串转数据库日期
	public static java.sql.Date parseSqlDate(String dateStr) {
		return parseSqlDate(dateStr, "yyyy/MM/dd");
	}

	// 字符串转数据库Timestamp
	public static Timestamp parseTimestamp(String dateStr,
			String format) {
		Date date = parseDate(dateStr, format);
		if (date != null) {
			long t = date.getTime();
			return new Timestamp(t);
		} else
			return null;
	}

	// 字符串转数据库Timestamp
	public static Timestamp parseTimestamp(String dateStr) {
		return parseTimestamp(dateStr, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 格式化输出日期
	 *
	 * @param date
	 *            日期
	 * @param format
	 *            格式
	 * @return 返回字符型日期
	 */
	public static String format(Date date, String format) {
		String result = "";
		try {
			if (date != null) {
				java.text.DateFormat df = new SimpleDateFormat(format);
				result = df.format(date);
			}
		} catch (Exception e) {
		}
		return result;
	}

	public static String format(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 返回年份
	 *
	 * @param date
	 *            日期
	 * @return 返回年份
	 */
	public static int getYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * 返回月份
	 *
	 * @param date
	 *            日期
	 * @return 返回月份
	 */
	public static int getMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * 返回日份
	 *
	 * @param date
	 *            日期
	 * @return 返回日份
	 */
	public static int getDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 返回日份(周)
	 *
	 * @param date
	 *            日期
	 * @return 返回日份
	 */
	public static int getWeekDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 返回小时
	 *
	 * @param date
	 *            日期
	 * @return 返回小时
	 */
	public static int getHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 返回分钟
	 *
	 * @param date
	 *            日期
	 * @return 返回分钟
	 */
	public static int getMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * 返回秒钟
	 *
	 * @param date
	 *            日期
	 * @return 返回秒钟
	 */
	public static int getSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/**
	 * 返回毫秒
	 *
	 * @param date
	 *            日期
	 * @return 返回毫秒
	 */
	public static long getMillis(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.getTimeInMillis();
	}

	/**
	 * 返回字符型日期
	 *
	 * @param date
	 *            日期
	 * @return 返回字符型日期
	 */
	public static String getDate(Date date) {
		return format(date, "yyyy/MM/dd");
	}

	/**
	 * 返回字符型时间
	 *
	 * @param date
	 *            日期
	 * @return 返回字符型时间
	 */
	public static String getTime(Date date) {
		return format(date, "HH:mm:ss");
	}

	/**
	 * 返回字符型日期时间
	 *
	 * @param date
	 *            日期
	 * @return 返回字符型日期时间
	 */
	public static String getDateTime(Date date) {
		return format(date, "yyyy/MM/dd HH:mm:ss");
	}

	/**
	 * 日期相加
	 *
	 * @param date
	 *            日期
	 * @param day
	 *            天数
	 * @return 返回相加后的日期
	 */
	public static Date addDate(Date date, int day) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
		return c.getTime();
	}

	/**
	 * 日期相减
	 *
	 * @param date
	 *            日期
	 * @param date1
	 *            日期
	 * @return 返回相减后的日期
	 */
	public static int diffDate(Date endDate, Date startDate) {
		return (int) ((getMillis(endDate) - getMillis(startDate)) / (24 * 3600 * 1000));
	}


	// 判断两个日期是否在同一周
	boolean isSameWeekDates(Date date1, Date date2) {
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();

		cal1.setTime(date1);
		cal2.setTime(date2);
		int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
		if (0 == subYear) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (1 == subYear && 11 == cal2.get(Calendar.MONTH)) {
			// 如果12月的最后一周横跨来年第一周的话则最后一周即算做来年的第一周
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		} else if (-1 == subYear && 11 == cal1.get(Calendar.MONTH)) {
			if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2
					.get(Calendar.WEEK_OF_YEAR))
				return true;
		}
		return false;
	}

	// 产生周序列
	public static String getSeqWeek() {
		Calendar c = Calendar.getInstance(Locale.CHINA);
		String week = Integer.toString(c.get(Calendar.WEEK_OF_YEAR));
		if (week.length() == 1)
			week = "0" + week;
		String year = Integer.toString(c.get(Calendar.YEAR));
		return year + week;

	}

	// 获得周一的日期
	public static String getMonday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
	}

	// 获得周五的日期
	public static String getFriday(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		c.set(Calendar.DAY_OF_WEEK, Calendar.FRIDAY);
		return new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());

	}

	// 保持某一周周一的日期  //n为推迟的周数，0本周，-1向前推迟一周，1下周，依次类推
	public static String getWeekMonday(int n) {
		Calendar cal = Calendar.getInstance();
		String monday;
		cal.add(Calendar.DATE, n*7);
		//想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		cal.set(Calendar.DAY_OF_WEEK,Calendar.MONDAY);
		monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

		return monday;
	}

	// 保持某一周周一的日期  //n为推迟的周数，0本周，-1向前推迟一周，1下周，依次类推
	public static String getWeekSunday(int n) {
		Calendar cal = Calendar.getInstance();
		String monday;
		cal.add(Calendar.DATE, n*7);
		//想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

		return monday;
	}

	// 取得某一天(相对于今天)
	public static String getOneDay(int n) {
		Calendar cal = Calendar.getInstance();
		String monday;
		cal.add(Calendar.DATE, n);
		//想周几，这里就传几Calendar.MONDAY（TUESDAY...）
		//cal.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
		monday = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

		return monday;
	}

	// 判断日期是否合法
	public static boolean checkDate(String date,String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		Date d = null;
        try{
            d = df.parse(date);
        }catch(ParseException e){
            //如果不能转换,肯定是错误格式
            return false;
        }
        String s1 = df.format(d);
        // 转换后的日期再转换回String,如果不等,逻辑错误.如format为"yyyy-MM-dd",date为
        // "2006-02-31",转换为日期后再转换回字符串为"2006-03-03",说明格式虽然对,但日期
        // 逻辑上不对.
        return date.equals(s1);
    }
	
	/**
	 * 取得当前时间
	 * 
	 * @param len
	 * @return
	 */
	public static String getCurrentDT(int len) {
		String dataTime = new Timestamp(System.currentTimeMillis()) + "";
		if (dataTime.length() > len) {
			dataTime = dataTime.substring(0, len);
		}
		return dataTime;
	}
	
	/**
	 * 取得当前时间
	 * 
	 * @return
	 */
	public static Timestamp getCurrentDT() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		SimpleDateFormat formattxt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String temp = formattxt.format(timestamp);
		return Timestamp.valueOf(temp);
	}

	/**
	 *
	 * @param date
	 * @param format
	 * @return
	 */
	public static String toString(Date date, String format)
	{
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}

	/**
	 *
	 * @param date
	 * @param offset
	 * @param format
	 * @return
	 */
	public static String getMonthOffset(Date date, int offset, String format)
	{
		Calendar calendar = Calendar.getInstance(Locale.CHINA);
		calendar.setTime(date);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + offset);
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(calendar.getTime());
	}
}
