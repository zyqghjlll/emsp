package com.ethan.emsp.domain.model.location;

import cn.hutool.core.util.ObjectUtil;
import com.ethan.emsp.core.result.exception.InvalidArgumentException;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public record BusinessHours(LocalTime openTime, LocalTime closeTime) {

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    public BusinessHours(LocalTime openTime, LocalTime closeTime) {
        if (openTime.isAfter(closeTime)) {
            throw new InvalidArgumentException("Opening time must be before or equal to closing time");
        }
        this.openTime = openTime;
        this.closeTime = closeTime;
    }

    public static BusinessHours of(String openTimeStr, String closeTimeStr) {
        LocalTime openTime;
        LocalTime closeTime;
        if (ObjectUtil.isEmpty(openTimeStr)) {
            openTime = LocalTime.of(8, 0);
        } else {
            openTime = parseTime(openTimeStr);
        }

        if (ObjectUtil.isEmpty(closeTimeStr)) {
            closeTime = LocalTime.of(22, 0);
        } else {
            closeTime = parseTime(closeTimeStr);
        }

        return new BusinessHours(openTime, closeTime);
    }

    private static LocalTime parseTime(String time) {
        try {
            return LocalTime.parse(time, TIME_FORMATTER);
        } catch (Exception e) {
            throw new InvalidArgumentException("Invalid time format: " + time + ", expect: HH:mm");
        }
    }

    @Override
    public String toString() {
        return "(" + openTime + "-" + closeTime + ")";
    }
}
