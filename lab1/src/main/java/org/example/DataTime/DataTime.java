package org.example.DataTime;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/**
 * Класс, описывающий объект "DataTime".
 */
@Getter
@Setter(AccessLevel.PRIVATE)
public class DataTime {
    private int monthDay;
    private int numberOfMonth;
    public DataTime(int monthDay, int numberOfMonth) {
        this.monthDay = monthDay;
        this.numberOfMonth = numberOfMonth;
    }
}
