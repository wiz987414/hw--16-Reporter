package ru.sbt.bit.ood.solid.homework;

import org.springframework.mail.javamail.JavaMailSenderImpl;
import ru.sbt.bit.ood.solid.homework.reportBuilder.ReportBuilder;
import ru.sbt.bit.ood.solid.homework.reportLoader.ReportLoader;
import ru.sbt.bit.ood.solid.homework.reportLoader.reportDomain.DateRange;
import ru.sbt.bit.ood.solid.homework.reportSender.ReportSender;

import java.sql.Connection;
import java.sql.ResultSet;

public class SalaryHtmlReportNotifier {
    private final ReportLoader reportLoader;
    private final ReportSender reportSender;

    public SalaryHtmlReportNotifier(Connection databaseConnection) {
        this.reportLoader = new ReportLoader(databaseConnection);
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        this.reportSender = new ReportSender(mailSender);
    }

    private ReportLoader getReportLoader() {
        return reportLoader;
    }

    private ReportSender getReportSender() {
        return reportSender;
    }

    public void generateAndSendHtmlSalaryReport(String departmentId, DateRange dateRange, String recipients) {
        ResultSet results = getReportLoader().loadReportData(departmentId, dateRange);
        getReportSender().sendReport(new ReportBuilder(results).getResultHtml(), recipients);
    }
}
