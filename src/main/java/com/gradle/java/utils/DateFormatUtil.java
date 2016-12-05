package com.gradle.java.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 
 * <p>
 * 时间转换工具类
 * </p>
 */
public class DateFormatUtil {

	/** 格式：yyyy-MM-dd */
	public static SimpleDateFormat yyyy_MM_dd = new SimpleDateFormat(
			"yyyy-MM-dd");
	/** 格式：yyyyMMdd */
	public static SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	/** 格式：yyyy/MM/dd */
	public static SimpleDateFormat _yyyy_MM_dd = new SimpleDateFormat(
			"yyyy/MM/dd");

	/** 格式：yyyy-MM-dd HH:mm:ss */
	public static SimpleDateFormat yMd_Hms = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	/** 格式：yyyy/MM/dd HH:mm:ss */
	public static SimpleDateFormat _yMd_Hms = new SimpleDateFormat(
			"yyyy/MM/dd HH:mm:ss");
	/** 格式：yyyyMMddHHmmss */
	public static SimpleDateFormat yMdHms = new SimpleDateFormat(
			"yyyyMMddHHmmss");

	/** 格式：yyyyMM */
	public static SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyyMM");
	/** 格式：HH:mm:ss */
	public static SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss");

	/**
	 * <p>
	 * 获取当前系统时间
	 * </p>
	 * 通过java.util.Date类获取
	 * 
	 * @return 返回java.util.Date类型对象
	 * @see #getCurrentDate()
	 */
	public static Date getDate() {
		return new Date();
	}

	/**
	 * <p>
	 * 获取当前系统时间
	 * </p>
	 * 通过java.util.Calendar类获取
	 * 
	 * @return 返回java.util.Date类型对象
	 * @see Calendar
	 */
	public static Date getCalendarDate() {
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}

	/**
	 * <p>
	 * 获取当前系统日期，返回字符串格式
	 * </p>
	 * 格式：yyyy-MM-dd
	 *
	 * @return 返回日期字符串格式：yyyy-MM-dd
	 * @see #getDateStr(Date)
	 */
	public static String getDateStr() {
		return getDateStr(new Date());
	}

	/**
	 * <p>
	 * 获取格式化字符串日期，返回字符串格式
	 * </p>
	 * 格式：yyyy-MM-dd
	 *
	 * @param date
	 *            指定日期对象
	 * @return 返回日期字符串格式：yyyy-MM-dd
	 */
	public static String getDateStr(Date date) {
		return yyyy_MM_dd.format(date);
	}

	/**
	 * <p>
	 * <b>[默认]</b> 获取当前系统日期时间，返回字符串格式
	 * </p>
	 * 格式：yyyy-MM-dd HH:mm:ss<br>
	 *
	 * @return 返回字符串对象：yyyy-MM-dd HH:mm:ss
	 * @see #getDateTimeStr(Date)
	 */
	public static String getDateTime() {
		return yMd_Hms.format(new Date());
	}

	/**
	 * <p>
	 * 获取当前系统日期时间，返回字符串格式
	 * </p>
	 * 格式：yyyy-MM-dd HH:mm:ss
	 *
	 * @return 返回字符串对象：yyyy-MM-dd HH:mm:ss
	 * @see #getDateTimeStr(Date)
	 */
	public static String getDateTimeStr() {
		return getDateTimeStr(new Date());
	}

	/**
	 * <p>
	 * 获取格式化字符串日期时间，返回字符串格式
	 * </p>
	 * 格式：yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 *            指定日期对象
	 * @return 返回日期时间字符串格式：yyyy-MM-dd HH:mm:ss
	 */
	public static String getDateTimeStr(Date date) {
		return yMd_Hms.format(date);
	}

	/**
	 * <p>
	 * 获取当前年
	 * </p>
	 *
	 * @return 返回int类型的整数
	 */
	public static int getCurrentYear() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}

	/**
	 *
	 * <p>
	 * 获取当前月
	 * </p>
	 *
	 * @return 返回int类型的整数，一位或两位数，范围是：1-12
	 */
	public static int getCurrentMonth() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH) + 1;
	}

	// 获得本月第一天0点时间
	public static Date getTimesMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
				cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH,
				cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return cal.getTime();
	}

	public static Date getLastMonthStartMorning() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getTimesMonthmorning());
		cal.add(Calendar.MONTH, -1);
		return cal.getTime();
	}

	/**
	 * <p>
	 * 获取当前日
	 * </p>
	 *
	 * @return 返回int类型的整数，一位或两位数，范围是：1-31
	 */
	public static int getCurrentDay() {
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * <p>
	 * 获得指定的年，int格式
	 * </p>
	 *
	 * @param date
	 *            指定日期对象
	 * @return 返回int类型的整数
	 */
	public static int getCustomYear(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.YEAR);
	}

	/**
	 * <p>
	 * 获得指定的月，int格式
	 * </p>
	 *
	 * @param date
	 *            指定日期对象
	 * @return 返回int类型的整数，一位或两位数，范围是：1-12
	 */
	public static int getCustomMonth(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MONTH) + 1;
	}

	/**
	 * <p>
	 * 获得指定的日，int格式
	 * </p>
	 *
	 * @param date
	 *            指定日期对象
	 * @return 返回int类型的整数，一位或两位数，范围是：1-31
	 */
	public static int getCustomDay(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * <p>
	 * 获取当前系统时间的小时数
	 * </p>
	 * 通过java.util.Calendar获取
	 *
	 * @return 返回小时数
	 */
	public static int getCurrentHour() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * <p>
	 * 获取当前系统时间的分钟数
	 * </p>
	 * 通过java.util.Calendar获取
	 *
	 * @return 返回分钟数
	 */
	public static int getCurrentMinute() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * <p>
	 * 获取当前系统时间的秒数
	 * </p>
	 * 通过java.util.Calendar获取
	 *
	 * @return 返回秒数
	 */
	public static int getCurrentSecond() {
		Calendar calendar = new GregorianCalendar();
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * <p>
	 * 获得指定的小时(日中的)，int格式
	 * </p>
	 * <br>
	 * 
	 * @return
	 */
	public static int getCustomHour(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * <p>
	 * 获得指定的分钟，int格式
	 * </p>
	 * <br>
	 * 
	 * @return
	 */
	public static int getCustomMinute(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.MINUTE);
	}

	/**
	 * <p>
	 * 获得指定的秒，int格式
	 * </p>
	 * <br>
	 * 
	 * @return
	 */
	public static int getCustomSecond(Date date) {
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c.get(Calendar.SECOND);
	}

	/********************** 我是华丽丽的分割线 ***************************************************/

	/**
	 * <p>
	 * 获取本月第一天日期（格式如YYYYMMDD）,如果当前日为当月1日,则返回上月第一日
	 * </p>
	 *
	 * @return
	 */
	public static String getMonthFirstDay() {
		Calendar calendar = new GregorianCalendar();
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int month = 0;
		if (day == 1)// 当月第一日
		{
			calendar.add(Calendar.MONTH, -1);
		}
		month = calendar.get(Calendar.MONTH);
		if (month < 10) {
			return "" + calendar.get(Calendar.YEAR) + "0" + (month + 1) + "01";
		} else {
			return "" + calendar.get(Calendar.YEAR) + month + "01";
		}
	}

	/**
	 * <p>
	 * 获取当前时间前几天或后几天的日期
	 * </p>
	 *
	 * @return
	 */
	public static Date getAddDays(int days) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DAY_OF_YEAR, days);
		return calendar.getTime();
	}

	/**
	 * <p>
	 * 获取某个月后的日期格式（yyyyMMdd）
	 * </p>
	 *
	 * @return
	 */
	public static String getAfterMonth(int monthNum) {
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.MONTH, monthNum);
		return yyyyMMdd.format(calendar.getTime());
	}

	/**
	 * <p>
	 * 返回日期（格式yyyyMMdd）
	 * </p>
	 *
	 * @param timeMillis
	 * @return
	 */
	public static String getFormatDate(long timeMillis) {
		return yMd_Hms.format(new Date(timeMillis));
	}

	/**
	 * 获取当前系统时间距离传入时间的毫秒数
	 *
	 * @param strTime
	 *            格式[ DD:00:00]
	 * @return
	 * @throws ParseException
	 */
	public static long getSleepTime(String strTime) throws ParseException {
		long p = 1;
		long l_date = System.currentTimeMillis();
		Date date_now = new Date(l_date);
		String strDate = yyyy_MM_dd.format(date_now) + strTime;
		if (date_now.before(yMd_Hms.parse(strDate)))
			p = (yMd_Hms.parse(strDate)).getTime() - l_date;
		else {
			Calendar calendar = new GregorianCalendar();
			calendar.setTime(date_now);
			calendar.add(Calendar.DAY_OF_YEAR, 1);
			Date date = calendar.getTime();
			strDate = yyyy_MM_dd.format(date) + strTime;
			p = (yMd_Hms.parse(strDate)).getTime() - l_date;
		}
		return p;
	}

	public static String getPredate() {
		Date nowDate = new Date();
		String nowdates = yyyy_MM_dd.format(nowDate);
		String[] dates = nowdates.split("-");
		int year = Integer.parseInt(dates[0]);
		int month = Integer.parseInt(dates[1]);
		int day = Integer.parseInt(dates[2]) - 1;
		if (day == 0) {
			switch (month - 1) {
			case 1:
				day = 31;
				break;
			case 3:
				day = 31;
				break;
			case 5:
				day = 31;
				break;
			case 7:
				day = 31;
				break;
			case 8:
				day = 31;
				break;
			case 10:
				day = 31;
				break;
			case 0:
				month = 13;
				year = year - 1;
				day = 31;
				break;
			case 4:
				day = 30;
				break;
			case 6:
				day = 30;
				break;
			case 9:
				day = 30;
				break;
			case 11:
				day = 30;
				break;
			case 2:
				if (year % 4 == 0) {
					day = 29;
				} else {
					day = 28;
				}
				break;
			default:
				break;
			}
			month = month - 1;
		}
		String predate = Integer.toString(year) + "-"
				+ (month < 10 ? "0" + month : month) + "-"
				+ (day < 10 ? "0" + day : day);
		return predate;
	}

	/**
	 * <p>
	 * 获取中文日期格式
	 * </p>
	 * 格式：xxxx年xx月xx日
	 * 
	 * @param date
	 * @return
	 */
	/*
	 * public static String getChinaDateFormat(Date date) { //
	 * 获取yyyy-mm-dd格式日期格式 String dateStr = getDateTime_I(date); StringBuffer sb
	 * = new StringBuffer(); if (dateStr != null && dateStr.length() > 0) {
	 * String[] newStr = dateStr.split("-"); // 获取月 int month =
	 * Integer.valueOf(newStr[1]); // 获取日 int day = Integer.valueOf(newStr[2]);
	 * sb.append(newStr[0]).append("年");
	 * sb.append(month).append("月").append(day).append("日"); } return
	 * sb.toString(); }
	 */

	/**
	 * <p>
	 * 获取中文日期时间格式
	 * </p>
	 * 格式：xxxx年xx月xx日<br>
	 * 
	 * @param date
	 *            指定日期对象，为null时获取当前系统时间
	 * @return 返回诸如“xxxx年xx月xx日”格式的日期
	 */
	public static String getChineseDate(Date date) {
		if (date == null)
			date = new Date();
		int yyyy = getCustomYear(date);
		int MM = getCustomMonth(date);
		int dd = getCustomDay(date);

		StringBuilder sb = new StringBuilder();
		sb.append(yyyy + "年");
		sb.append(MM + "月");
		sb.append(dd + "日");
		return sb.toString();
	}

	/**
	 * <p>
	 * 获取中文日期时间格式
	 * </p>
	 * 格式：xxxx年xx月xx日 xx时xx分xx秒
	 * 
	 * @param date
	 *            指定日期对象，为null时获取当前系统时间
	 * @return 返回诸如“xxxx年xx月xx日 xx时xx分xx秒”格式的日期
	 */
	public static String getChineseDateTime(Date date) {
		if (date == null)
			date = new Date();
		int yyyy = getCustomYear(date);
		int MM = getCustomMonth(date);
		int dd = getCustomDay(date);

		int HH = getCustomHour(date);
		int mm = getCustomMinute(date);
		int ss = getCustomSecond(date);

		StringBuilder sb = new StringBuilder();
		sb.append(yyyy + "年");
		sb.append(MM + "月");
		sb.append(dd + "日");
		sb.append(" ");
		sb.append(HH + "时");
		sb.append(mm + "分");
		sb.append(ss + "秒");

		return sb.toString();
	}

	/**
	 * add by lipp
	 * <p>
	 * 获取xxxx年xx月xx日 日期格式。
	 * </p>
	 * 
	 * @param date
	 *            格式必须是2009-03-21字符串
	 * @return
	 */
	public static String getChinaDateFormat(String date) {
		// 获取yyyy-mm-dd格式日期格式
		StringBuffer sb = new StringBuffer();
		if (date != null && date.length() > 0) {
			String[] newStr = date.split("-");
			// 获取月
			int month = Integer.valueOf(newStr[1]);
			// 获取日
			int day = Integer.valueOf(newStr[2]);
			sb.append(newStr[0]).append("年");
			sb.append(month).append("月").append(day).append("日");
		}
		return sb.toString();
	}

	/**
	 * 判断一个日期字符串是否合法
	 * 
	 * @param date
	 * @param format
	 * @return
	 * @author liufengyu
	 */
	public static boolean isDateStringCorrect(String date, String format) {
		SimpleDateFormat df = new SimpleDateFormat(format);

		try {
			df.setLenient(false);
			df.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * add by gongtao
	 * <P>
	 * 将字符串类型的日期格式 转换为 符合要求的日期格式
	 * </P>
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getStrDate4String(String date, String format) {
		if (date == null || date.trim().equals("")) {
			return "";
		} else {
			SimpleDateFormat df = new SimpleDateFormat(format);
			try {
				Date d = df.parse(date);
				return df.format(d);
			} catch (ParseException e) {
				System.out.println(e);
				return "";
			}
		}
	}

	/**
	 * add by gongtao
	 * <P>
	 * 将Date类型的日期格式 转换为 符合要求的 String日期格式
	 * </P>
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static String getStrDate4Date(Date date, String format) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat df = new SimpleDateFormat(format);
			return df.format(date);
		}
	}

	/**
	 * add by gongtao
	 * <P>
	 * 将字符串类型的日期格式 转换为 符合要求的 Date类型的日期格式
	 * </P>
	 * 
	 * @param date
	 * @param format
	 * @return
	 */
	public static Date getDate4StrDate(String date, String format) {
		if (date == null || date.trim().equals("")) {
			return null;
		} else {
			SimpleDateFormat df = new SimpleDateFormat(format);
			try {
				return df.parse(date);
			} catch (ParseException e) {
				return null;
			}
		}
	}

	/**
	 * add by gongtao 计算指定日期时间之间的时间差
	 * 
	 * @param beginStr
	 *            开始日期字符串
	 * @param endStr
	 *            结束日期字符串
	 * @param f
	 *            时间差的形式0-秒,1-分种,2-小时,3--天 日期时间字符串格式:yyyyMMddHHmmss
	 * */
	public static int getInterval(String beginStr, String endStr, int f) {
		int hours = 0;
		try {
			Date beginDate = yMd_Hms.parse(beginStr);
			Date endDate = yMd_Hms.parse(endStr);
			long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减获取日期差X(单位:毫秒)
			/**
			 * Math.abs((int)(millisecond/1000)); 绝对值 1秒 = 1000毫秒
			 * millisecond/1000 --> 秒 millisecond/1000*60 - > 分钟
			 * millisecond/(1000*60*60) -- > 小时 millisecond/(1000*60*60*24) -->
			 * 天
			 * */
			switch (f) {
			case 0: // second
				return (int) (millisecond / 1000);
			case 1: // minute
				return (int) (millisecond / (1000 * 60));
			case 2: // hour
				return (int) (millisecond / (1000 * 60 * 60));
			case 3: // day
				return (int) (millisecond / (1000 * 60 * 60 * 24));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hours;
	}

	/**
	 * add by lipp
	 * <P>
	 * 获取起始日期前或后天数的日期
	 * </P>
	 * 
	 * @param starttime
	 *            起始日期 格式：yyyy-MM-dd
	 * @param days
	 * @return
	 * @throws ParseException
	 */
	public static Date getStartDateInterval(String starttime, int days) {
		// 格式化起始时间 yyyyMMdd
		Date startDate = null;
		try {
			startDate = yyyy_MM_dd.parse(starttime);

		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar startTime = Calendar.getInstance();
		startTime.clear();
		startTime.setTime(startDate);

		startTime.add(Calendar.DAY_OF_YEAR, days);
		return startTime.getTime();
	}

	/**
	 * add by lipp
	 * <P>
	 * 获取起始日期和结束日期之间的天数
	 * </P>
	 * 
	 * @param beginStr
	 *            起始日期
	 * @param endStr
	 *            结束日期
	 * @param format
	 *            根据 日期参数的格式，传对应的SimpleDateFormat格式
	 * @return 天数
	 */
	public static int getDaysInterval(String beginStr, String endStr,
			SimpleDateFormat format) {

		try {
			Date beginDate = format.parse(beginStr);
			Date endDate = format.parse(endStr);
			long millisecond = endDate.getTime() - beginDate.getTime(); // 日期相减获取日期差X(单位:毫秒)
			return (int) (millisecond / (1000 * 60 * 60 * 24));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;
	}

	/**
	 * 获取本月第一天
	 * 
	 * @return
	 */
	public static Date getFristDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		return calendar.getTime();

	}

	/**
	 * 获取本月最后一天
	 * 
	 * @return
	 */
	public static Date getLastDayOfMonth() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + 1);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MINUTE, 0);
		return calendar.getTime();

	}

	/**
	 * 获得本周一的日期
	 * 
	 * @return
	 * @throws ParseException
	 */
	/*
	 * public static Date getMondayOFWeek() throws ParseException { int
	 * mondayPlus = getMondayPlus(); GregorianCalendar currentDate = new
	 * GregorianCalendar(); currentDate.add(Calendar.DATE, mondayPlus); Date
	 * monday = currentDate.getTime(); String dateStr = getDateTime_I(monday);
	 * StringBuffer sb = new StringBuffer(dateStr);
	 * sb.append(" ").append("00:00:00"); return
	 * parseDateStrToDate(sb.toString()); }
	 */

	/**
	 * 获得本周星期日的日期
	 * 
	 * @return
	 * @throws ParseException
	 */
	/*
	 * public static Date getCurrentWeekday() throws ParseException { int
	 * mondayPlus = getMondayPlus(); GregorianCalendar currentDate = new
	 * GregorianCalendar(); currentDate.add(Calendar.DATE, mondayPlus + 6); Date
	 * monday = currentDate.getTime(); String dateStr = getDateTime_I(monday);
	 * StringBuffer sb = new StringBuffer(dateStr);
	 * sb.append(" ").append("00:00:00"); return
	 * parseDateStrToDate(sb.toString()); }
	 */

	/**
	 * 字符串时间格式转换为 Date
	 * 
	 * @param date
	 *            此格式 yyyy-MM-dd HH:mm:ss
	 * @return
	 * @throws ParseException
	 */
	/*
	 * public static Date parseDateStrToDate(String date) throws ParseException{
	 * Date date_time = null; if(!StringUtil.isEmpty(date)){ date_time =
	 * yMd_Hms.parse(date); } return date_time; }
	 */
	/**
	 * 字符串时间格式转换为 Date
	 * 
	 * @param date
	 *            此格式 yyyy-MM-dd
	 * @return
	 * @throws ParseException
	 */
	/*
	 * public static Date parseDateFromStr(String date) throws ParseException{
	 * Date date_time = null; if(!StringUtil.isEmpty(date)){ date_time =
	 * yyyy_MM_dd.parse(date); } return date_time; }
	 */

	public static int getMondayPlus() {
		Calendar cd = Calendar.getInstance();
		// 因为按中国礼拜一作为第一天所以这里减1
		int dayOfWeek = cd.get(Calendar.DAY_OF_WEEK) - 1;
		if (dayOfWeek == 1) {
			return 0;
		} else if (dayOfWeek == 0) {
			return 1 - 7;
		} else {
			return 1 - dayOfWeek;
		}
	}

	/**
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param f
	 *            时间差的形式0:秒,1:分种,2:小时,3:天
	 * @return
	 */
	public static int getDifferenceNum(Date beginDate, Date endDate, int f) {
		int result = 0;
		if (beginDate == null || endDate == null) {
			return 0;
		}
		try {
			// 日期相减获取日期差X(单位:毫秒)
			long millisecond = endDate.getTime() - beginDate.getTime();
			/**
			 * Math.abs((int)(millisecond/1000)); 绝对值 1秒 = 1000毫秒
			 * millisecond/1000 --> 秒 millisecond/1000*60 - > 分钟
			 * millisecond/(1000*60*60) -- > 小时 millisecond/(1000*60*60*24) -->
			 * 天
			 * */
			switch (f) {
			case 0: // second
				return (int) (millisecond / 1000);
			case 1: // minute
				return (int) (millisecond / (1000 * 60));
			case 2: // hour
				return (int) (millisecond / (1000 * 60 * 60));
			case 3: // day
				return (int) (millisecond / (1000 * 60 * 60 * 24));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * @param beginDate
	 * @param endDate
	 * @param f
	 *            时间差的形式0:秒,1:分种,2:小时,3:天
	 * @return
	 */
	public static long getDifference(Date beginDate, Date endDate, int f) {
		long result = 0;
		if (beginDate == null || endDate == null) {
			return 0;
		}
		try {
			// 日期相减获取日期差X(单位:毫秒)
			long millisecond = endDate.getTime() - beginDate.getTime();
			/**
			 * Math.abs((int)(millisecond/1000)); 绝对值 1秒 = 1000毫秒
			 * millisecond/1000 --> 秒 millisecond/1000*60 - > 分钟
			 * millisecond/(1000*60*60) -- > 小时 millisecond/(1000*60*60*24) -->
			 * 天
			 * */
			switch (f) {
			case 0: // second
				return (millisecond / 1000);
			case 1: // minute
				return (millisecond / (1000 * 60));
			case 2: // hour
				return (millisecond / (1000 * 60 * 60));
			case 3: // day
				return (millisecond / (1000 * 60 * 60 * 24));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	
	
	/**
	 * 时间差--按照 xx天xx时xx分xx秒的格式输出
	 * @param millisecond
	 * @param f
	 * @return
	 */
	public static String parseMillisecone(long millisecond, int f) {
		String time = null;
		try {
			switch (f) {
			case 0: // second
				return (millisecond / 1000) + "秒";
			case 1: // minute
				return (millisecond / (1000 * 60)) + "分";
			case 2: // hour
				return (millisecond / (1000 * 60 * 60)) + "时";
			case 3: // day
				long yushu_day = millisecond % (1000 * 60 * 60 * 24);
				long yushu_hour = (millisecond % (1000 * 60 * 60 * 24))
						% (1000 * 60 * 60);
				long yushu_minute = millisecond % (1000 * 60 * 60 * 24)
						% (1000 * 60 * 60) % (1000 * 60);
				long yushu_second = millisecond % (1000 * 60 * 60 * 24)
						% (1000 * 60 * 60) % (1000 * 60) %1000;
//				System.out.println(yushu_day);
//				System.out.println(yushu_hour);
//				System.out.println(yushu_minute);
//				System.out.println(yushu_second);
				if (yushu_day == 0) {
					return (millisecond / (1000 * 60 * 60 * 24)) + "天";
				} else {
					if (yushu_hour == 0) {
						return (millisecond / (1000 * 60 * 60 * 24)) + "天"
								+ (yushu_day / (1000 * 60 * 60)) + "时";
					} else {
						if (yushu_minute == 0) {
							return (millisecond / (1000 * 60 * 60 * 24)) + "天"
									+ (yushu_day / (1000 * 60 * 60)) + "时"
									+ (yushu_hour / (1000 * 60)) + "分";
						} else {
							if(yushu_second==0){
								return (millisecond / (1000 * 60 * 60 * 24)) + "天"
										+ (yushu_day / (1000 * 60 * 60)) + "时"
										+ (yushu_hour / (1000 * 60)) + "分"+(yushu_minute/1000)+"秒";
							}else{
								return (millisecond / (1000 * 60 * 60 * 24)) + "天"
										+ (yushu_day / (1000 * 60 * 60)) + "时"
										+ (yushu_hour / (1000 * 60)) + "分"+(yushu_minute/1000)+"秒";
							}

						}

					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return time;
	}

	/**
	 * <p>
	 * 比较两个日期的大小,精确到秒
	 * </p>
	 * 
	 * @param d1
	 * @param d2
	 * @author lipp@icloud-edu.com
	 * @date 2014-06-03
	 * @return 返回一个long类型的整数，若大于0表示第一个日期晚于第二个日期，小于0表示第一个日期早于第二个日期，否则相等
	 */
	public static long compareEachOther(Date d1, Date d2) {
		if (d1 == null || d2 == null)
			return -1;
		String d1Str = d1.getTime() + "";
		String d2Str = d2.getTime() + "";
		int l1 = d1Str.length();
		int l2 = d2Str.length();
		d1Str = d1Str.substring(0, l1 - 3) + "000";
		d2Str = d2Str.substring(0, l2 - 3) + "000";
		long long1 = Long.parseLong(d1Str);
		long long2 = Long.parseLong(d2Str);
		return long1 - long2;
	}

	public static Date getPreYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, -1);
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
				cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.YEAR));
		return cal.getTime();
	}

	public static Date getCurrentYearStartTime() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY),
				cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.YEAR));
		return cal.getTime();
	}

	/**
	 * 当前季度的结束时间，即2012-03-31 23:59:59
	 *
	 * @return
	 */
	public static Date getCurrentQuarterEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getCurrentQuarterStartTime());
		cal.add(Calendar.MONTH, 3);
		return cal.getTime();
	}

	/**
	 * 前季度的结束时间，即2012-03-31 23:59:59
	 *
	 * @return
	 */
	public static Date getPreQuarterEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(getPreQuarterStartTime());
		cal.add(Calendar.MONTH, 3);
		return cal.getTime();
	}

	/**
	 * 当前季度的开始时间，即2012-03-31 23:59:59
	 *
	 * @return
	 */
	public static Date getCurrentQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		// c.add(Calendar.MONTH, -3);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 6);
				System.out.println(new SimpleDateFormat("yyyyMM").format(c
						.getTime()));
			} else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 前季度的开始时间，即2012-03-31 23:59:59
	 *
	 * @return
	 */
	public static Date getPreQuarterStartTime() {
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, -3);
		int currentMonth = c.get(Calendar.MONTH) + 1;
		SimpleDateFormat longSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat shortSdf = new SimpleDateFormat("yyyy-MM-dd");
		Date now = null;
		try {
			if (currentMonth >= 1 && currentMonth <= 3)
				c.set(Calendar.MONTH, 0);
			else if (currentMonth >= 4 && currentMonth <= 6)
				c.set(Calendar.MONTH, 3);
			else if (currentMonth >= 7 && currentMonth <= 9) {
				c.set(Calendar.MONTH, 6);
				System.out.println(new SimpleDateFormat("yyyyMM").format(c
						.getTime()));
			} else if (currentMonth >= 10 && currentMonth <= 12)
				c.set(Calendar.MONTH, 9);
			c.set(Calendar.DATE, 1);
			now = longSdf.parse(shortSdf.format(c.getTime()) + " 00:00:00");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 根据指定日期,来运算加减乘
	 * @param date
	 * @param format
	 * @param value
	 *            add(new Date(),"yyyy-MM-dd HH:mm:ss",-1 * 1 * 60 * 60 * 1000);
	 */
	public static String add(Date date, String format, long value) {
		SimpleDateFormat df = new SimpleDateFormat(format);
		long newValue = date.getTime() + value;
		return df.format(new Date(newValue));
	}
	
	
	
   
	/**
	 * @param args main函数
	 * @throws ParseException
	 */
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ParseException {
		
		Date startDate=getDate4StrDate("2017-02-13 08:30:00", "yyyy-MM-dd HH:mm:ss");
		Date endDate = getDate4StrDate("2017-02-13 08:30:00", "yyyy-MM-dd HH:mm:ss");
		
		System.out.println("天："+getDifference(startDate, endDate, 3));// 天---时间差
		System.out.println("时："+getDifference(startDate, endDate, 2));// 时---时间差
		System.out.println("分："+getDifference(startDate, endDate, 1));//分---时间差
		System.out.println("秒："+getDifference(startDate, endDate, 0));//秒---时间差
		
		
		//求时间差  时间差求余
	
		long time = getDifference(startDate, endDate, 0) * 1000 + 1000;// 加一秒
//		System.err.println(parseMillisecone(time, 3));//时间差 ---用户可理解的方式展示
//		System.out.println("求余："+(float)getDifference(startDate, endDate, 1)/60);
		
		//加减操作
//		add(startDate, "yyyy-MM-dd HH:mm:ss", time);
//      add(startDate,"yyyy-MM-dd HH:mm:ss",-1 * 1 * 60 * 60 * 1000);
		
		//比较大小
//		String startDate="2016-12-16 08:30:00";
//		String endDate="2016-12-18 18:30:00";
//		System.out.println(endDate.compareTo(startDate));
		
		//熟悉subsring函数
		String date="2014-12-12 09:08:12";
//		System.out.println(date.substring(0, 4));
//		System.out.println(date.substring(5, 7));
//		System.out.println(date.substring(8, 10));
//		System.out.println(date.substring(11, 13));
//		System.out.println(date.substring(14, 16));
		
		 //获取当前时间根据Calendar
//		 System.out.println(Calendar.getInstance().get(Calendar.YEAR));
//		 System.out.println(Calendar.getInstance().get(Calendar.MONTH)+1);
//		 System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
//		 System.out.println(Calendar.getInstance().get(Calendar.HOUR_OF_DAY));
//		 System.out.println(Calendar.getInstance().get(Calendar.MINUTE));
		 
		 Calendar cdate= Calendar.getInstance();
		 cdate.setTime(getDate4StrDate("2017-02-13 23:35:00", "yyyy-MM-dd HH:mm:ss"));
		 System.out.println(cdate.get(Calendar.YEAR));
		 System.out.println(cdate.get(Calendar.MONTH)+1);
		 System.out.println(cdate.get(Calendar.DAY_OF_MONTH));
		 System.out.println(cdate.get(Calendar.HOUR_OF_DAY));
		 System.out.println(cdate.get(Calendar.MINUTE));
		
	}
}
