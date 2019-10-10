package com.java.loan.app.loan_app_api;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "applications")
public class LoanForm {
	@Id
	@GeneratedValue
	private int formId;
	private String personalId;
	private String first_name;
	private String last_name;
	private Date birth_date;
	private String employer;
	private int salary;
	private int monthly_liability;
	private int requested_amount;
	private String result;
	
	public int getFormId() {
		return formId;
	}
	public void setFormId(int formId) {
		this.formId = formId;
	}
	public LoanForm() {
		
	}
	public String getResult() {
		return result;
	}
	public int getRequested_amount() {
		return requested_amount;
	}
	public void setRequested_amount(int requested_amount) {
		this.requested_amount = requested_amount;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getPersonalId() {
		return personalId;
	}
	public void setPersonalId(String personalId) {
		this.personalId = personalId;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmployer() {
		return employer;
	}
	public void setEmployer(String employer) {
		this.employer = employer;
	}
	public Date getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Date birth_date) {
		this.birth_date = birth_date;
	}
	public int getSalary() {
		return salary;
	}
	public void setSalary(int salary) {
		this.salary = salary;
	}
	public int getMonthly_liability() {
		return monthly_liability;
	}
	public void setMonthly_liability(int monthly_liability) {
		this.monthly_liability = monthly_liability;
	}
	@Override
	public String toString() {
		return "{personalId=" + personalId + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", birth_date=" + birth_date + ", employer=" + employer + ", salary=" + salary
				+ ", monthly_liability=" + monthly_liability + ", requested_amount=" + requested_amount + ", result="
				+ result + "}";
	}
	public void calculateResult() {
		
		/*
		 * Score = (Sum of first name letter positions in the alphabet (a = 1, z = 26)) + Salary * 1.5 - MonthlyLiability * 3 + (year of birth) - 
		 * (month of birth) - (Julian day of the year of birth (1st Feb = 32, etc.))
		 * When scoring is applied, if score is < 2500, application should be rejected, if it is > 3500, application should be approved
		 */
		this.result = "approved";
	}
	
}
