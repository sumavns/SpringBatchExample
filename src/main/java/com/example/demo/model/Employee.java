package com.example.demo.model;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@XmlRootElement(name = "employee")
public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;

	private int empid;
	private String ename;
	private double sal;
	private List<Address> addresslist;
	private List<Department> deptlist;

	public int getEmpid() {
		return empid;
	}

	public void setEmpid(int empid) {
		this.empid = empid;
	}

	public String getEname() {
		return ename;
	}

	public void setEname(String ename) {
		this.ename = ename;
	}

	public double getSal() {
		return sal;
	}

	public void setSal(double sal) {
		this.sal = sal;
	}

	public List<Address> getAddresslist() {
		return addresslist;
	}

	public void setAddresslist(List<Address> addresslist) {
		this.addresslist = addresslist;
	}

	public List<Department> getDeptlist() {
		return deptlist;
	}

	public void setDeptlist(List<Department> deptlist) {
		this.deptlist = deptlist;
	}

	@Override
	public String toString() {
		return "Employee [empid=" + empid + ", ename=" + ename + ", sal=" + sal + ", addresslist=" + addresslist
				+ ", deptlist=" + deptlist + "]";
	}

}
