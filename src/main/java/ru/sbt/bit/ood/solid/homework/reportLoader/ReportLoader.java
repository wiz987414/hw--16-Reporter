package ru.sbt.bit.ood.solid.homework.reportLoader;

import ru.sbt.bit.ood.solid.homework.reportLoader.reportDomain.DateRange;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReportLoader {
    private final PreparedStatement preparedStatement;

    public ReportLoader(Connection databaseConnection) {
        this.preparedStatement = prepareWithSQL(databaseConnection);
    }

    private PreparedStatement getPreparedStatement() {
        return preparedStatement;
    }

    private PreparedStatement prepareWithSQL(Connection connection) {
        PreparedStatement resultedStatement = null;
        try {
            resultedStatement = connection.prepareStatement("select emp.id as emp_id, emp.name as amp_name, sum(salary) " +
                    "as salary from employee emp left join salary_payments sp on emp.id = sp.employee_id " +
                    "where emp.department_id = ? and" + " sp.date >= ? and sp.date <= ? group by emp.id, emp.name");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultedStatement;
    }

    public ResultSet loadReportData(String departmentId, DateRange dateRange) {
        ResultSet results;
        try {
            this.getPreparedStatement().setString(0, departmentId);
            this.getPreparedStatement().setDate(1, new java.sql.Date(dateRange.getDateFrom().toEpochDay()));
            this.getPreparedStatement().setDate(2, new java.sql.Date(dateRange.getDateTo().toEpochDay()));
            results = this.getPreparedStatement().executeQuery();
        } catch (SQLException e) {
            throw new RuntimeException("Executing exception", e);
        }
        return results;
    }
}
