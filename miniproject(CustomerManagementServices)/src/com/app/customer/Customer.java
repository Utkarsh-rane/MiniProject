package com.app.customer;

import java.time.LocalDate;
import java.time.Period;

public class Customer implements Comparable<Customer> {
	private  int custId;
	private String firstname;
	private String lastname;
	private String email;
	private String passwd;
	private Double regAmt;
	private LocalDate dob;
	private static int counter;
	private ServicePlan plan;
	private LocalDate subscriptionPaidDate; 
	static
	{
		counter=100;
	}
	
	public Customer( String firstname, String lastname, String email, String passwd, Double regAmt,
			LocalDate dob,ServicePlan plan,LocalDate subscriptionPaidDate) {
		super();
		this.custId = counter++;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.passwd = passwd;
		this.regAmt = regAmt;
		this.dob = dob;
		this.plan=plan;
		this.subscriptionPaidDate=subscriptionPaidDate;
	}
	public Customer(String email) {
		super();
		this.email = email;
	
	}

	

	public Customer(String email, String passwd) {
		super();
		this.email = email;
		this.passwd = passwd;
	}



	public Double getRegAmt() {
		return regAmt;
	}
	public LocalDate getDob() {
		return dob;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}



	public LocalDate getsubscriptionPaidDate() {
		return subscriptionPaidDate;
	}
	public void setsubscriptionPaidDate(LocalDate subscriptionPaidDate) {
		this.subscriptionPaidDate = subscriptionPaidDate;
	}
	
	
	
	@Override
	public String toString() {
		return "Customer [custId=" + custId + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", passwd=" + passwd + ", regAmt=" + regAmt + ", dob=" + dob + ", plan=" + plan
				+ ", subscriptionPaidDate=" + subscriptionPaidDate + "]";
	}
	@Override
	public boolean equals(Object o)
	{
		
		if(o instanceof Customer)
			return this.email.equals(((Customer) o).email) ;
					//(this.email.equals(((Customer) o).email) && this.passwd.equals(((Customer)o).passwd));
		return false;
     }
	@Override
	public int compareTo(Customer o) {
		System.out.println("inside compareto");
		return this.email.compareTo(o.email);
	}
	public int period(LocalDate subscriptionPaidDate)
	{
		int d1=Period.between(getsubscriptionPaidDate(), LocalDate.now()).getMonths();
		return d1;
		
	}
	
}
