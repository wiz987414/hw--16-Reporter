package ru.sbt.bit.ood.solid.homework.reportLoader.reportDomain;

import java.time.LocalDate;

public class DateRange {
    private final LocalDate dateFrom;
    private final LocalDate dateTo;

    public DateRange(LocalDate dateFrom, LocalDate dateTo) {
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    public LocalDate getDateFrom() {
        return dateFrom;
    }

    public LocalDate getDateTo() {
        return dateTo;
    }
}
