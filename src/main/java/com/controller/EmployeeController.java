package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bean.EmployeeBean;
import com.dao.EmployeeDao;

@RestController
public class EmployeeController {

//	@RequestMapping(value = "/home", method = RequestMethod.POST)
//	public String home() {
//
//		System.out.println("home called....");
//
//		return "api called...";
//	}
//
//	@PostMapping(value = "/addemployee")
//	public String addEmployee(String ename,int age) {
//
//		System.out.println("ename = " + ename+ " - "+age);
//
//		return ename;
//	}

	@Autowired
	EmployeeDao employeeDao;

	
	@PostMapping(value = "/addemployee")
	public ResponseEntity<String> addEmployee(EmployeeBean employeeBean) {

		employeeDao.addEmployee(employeeBean);

		return new ResponseEntity<String>("Record Added", HttpStatus.CREATED);
	}
	
	
	
	@PostMapping(value = "/addemployee1",consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> addEmployee1(@RequestBody EmployeeBean employeeBean) {

		System.out.println("empbean"+employeeBean.geteName());
		employeeDao.addEmployee(employeeBean);

		return new ResponseEntity<String>("Record Added", HttpStatus.CREATED);
	}
	

	@DeleteMapping(value = "/deleteemployee/{eid}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("eid") int eId) {

		int res = employeeDao.deleteEmployee(eId);
		if (res > 0) {

			return new ResponseEntity<String>("Data Deleted", HttpStatus.OK);
		}

		return new ResponseEntity<String>("Data not Deleted", HttpStatus.CONFLICT);

	}
	
	

	@GetMapping(value = "/getemployee/{eId}")
	public ResponseEntity<EmployeeBean> getEmployeeById(@PathVariable("eId") int eId) {

		EmployeeBean employeeBean = employeeDao.getEmployeeById(eId);

		return new ResponseEntity<EmployeeBean>(employeeBean, HttpStatus.OK);

	}

}
