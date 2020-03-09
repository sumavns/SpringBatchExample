package com.example.demo.statementsetter;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

import com.example.demo.model.Employee;

/**
 * This class is used to set the actual parameter values of a prepared
 * statement.
 */
final class EmployeePreparedStatementSetter implements ItemPreparedStatementSetter<Employee> {

	@Override
	public void setValues(Employee employee, PreparedStatement preparedStatement) throws SQLException {
		preparedStatement.setInt(1, employee.getEmpid());
		preparedStatement.setString(2, employee.getEname());
		preparedStatement.setDouble(3, employee.getSal());
	}
}
