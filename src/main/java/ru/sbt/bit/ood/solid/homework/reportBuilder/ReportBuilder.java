package ru.sbt.bit.ood.solid.homework.reportBuilder;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportBuilder {
    private final String resultHtml;

    public ReportBuilder(ResultSet results) {
        this.resultHtml = createResultHtml(results);
    }

    public String getResultHtml() {
        return resultHtml;
    }

    private String createResultHtml(ResultSet results) {
        StringBuilder resultingHtml = new StringBuilder();
        resultingHtml.append("<html><body><table><tr><td>Employee</td><td>Salary</td></tr>");
        double totals = 0;
        try {
            while (results.next()) {
                resultingHtml.append("<tr>");
                resultingHtml.append("<td>").append(results.getString("emp_name")).append("</td>");
                resultingHtml.append("<td>").append(results.getDouble("salary")).append("</td>");
                resultingHtml.append("</tr>");
                totals += results.getDouble("salary");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Exception with load from data set", e);
        }
        resultingHtml.append("<tr><td>Total</td><td>").append(totals).append("</td></tr>");
        resultingHtml.append("</table></body></html>");
        return resultingHtml.toString();
    }
}
