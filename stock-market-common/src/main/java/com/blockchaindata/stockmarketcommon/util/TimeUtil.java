package com.blockchaindata.stockmarketcommon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Andon
 * @date 2019/2/19
 */
public class TimeUtil {

    private static final Logger LOG = LoggerFactory.getLogger(TimeUtil.class);
    public static final ThreadLocal<SimpleDateFormat> FORMAT = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    public static final ThreadLocal<SimpleDateFormat> FORMAT_DAY = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd"));
    public static final ThreadLocal<SimpleDateFormat> FORMAT_HOUR = ThreadLocal.withInitial(() -> new SimpleDateFormat("HH"));
    public static final ThreadLocal<SimpleDateFormat> FORMAT_MINUTE = ThreadLocal.withInitial(() -> new SimpleDateFormat("mm"));
    public static final ThreadLocal<SimpleDateFormat> FORMAT_SECOND = ThreadLocal.withInitial(() -> new SimpleDateFormat("ss"));

    public static long getTimeId(String time) {
        long timeId = 0;
        try {
            Date date = FORMAT.get().parse(time);
            timeId = date.getTime() / 1000;
        } catch (ParseException e) {
            LOG.error("FORMAT parse time failure!! error={}", e.getMessage());
        }
        return timeId;
    }
    
    public static Date parseTime(String time) {
        Date parse = null;
        try {
            parse = TimeUtil.FORMAT.get().parse(time);
        } catch (ParseException e) {
            LOG.error("FORMAT parse time failure!! error={}", e.getMessage());
        }
        return parse;
    }
    
    public static void sleep(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            LOG.error("Thread sleep failure!! error={}", e.getMessage());
        }
    }
}
