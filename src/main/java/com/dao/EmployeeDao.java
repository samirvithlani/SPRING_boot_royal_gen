package com.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.bean.EmployeeBean;

@Repository
public class EmployeeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	private final static class EmployeeMapper implements RowMapper<EmployeeBean>{

		@Override
		public EmployeeBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			EmployeeBean employeeBean = new EmployeeBean();
			
			employeeBean.seteId(rs.getInt("eid"));
			employeeBean.seteName(rs.getString("ename"));
			employeeBean.seteAge(rs.getInt("eage"));
			employeeBean.seteEmail(rs.getString("eemail"));
			return employeeBean;
		}
		
		
	}
	
	public EmployeeBean getEmployeeById(int eId) {
		
		return jdbcTemplate.queryForObject("select * from employee where eid = "+eId+"", new EmployeeMapper());
		
	}

	public int addEmployee(EmployeeBean employeeBean) {

		return jdbcTemplate.update("insert into employee (ename,eemail,eage,epassword)values(?,?,?,?)",
				employeeBean.geteName(), employeeBean.geteEmail(), employeeBean.geteAge(), employeeBean.getePassword());
	}

	public int deleteEmployee(int eId) {
		
		return jdbcTemplate.update("delete from employee where eid =?",eId);
		
	}
}
