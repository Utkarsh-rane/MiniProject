package utils;

import java.time.LocalDate;
import java.time.Period;
import java.util.Iterator;
import java.util.List;

import com.app.customer.Customer;
import com.app.customer.ServicePlan;

import cust_exception.InvalidCustomerException;

public class CustomerValidationRule {
	//All validations
	public static Customer ValidateAll(String firstname, String lastname, String email, String passwd, Double regAmt,
			String dob,String plan,String subscriptionPaidDate,List<Customer> cust) throws InvalidCustomerException
	{
		validateEmail(email,cust);
		
		LocalDate d=LocalDate.parse(dob);
		LocalDate d1=LocalDate.parse(subscriptionPaidDate);
		ServicePlan sp= validatePlanandAmt(plan,regAmt);
		validateage(dob);
		return new Customer( firstname,  lastname,  email , passwd, regAmt,d, sp,d1);
	}
	
	//Chacking Service plan validating and parse
	private static ServicePlan validatePlanandAmt(String plan,double regAmt) throws InvalidCustomerException 
	{
		
		ServicePlan sp= ServicePlan.valueOf(plan.toUpperCase());
		if(regAmt==sp.getPlanCost())
			return sp;
		throw new InvalidCustomerException("invalid regestration Amount for plan");
	}
	
     //email dublication checking
	private static void validateEmail(String email,List<Customer> cust) throws InvalidCustomerException
	{
		
		Customer cd=new Customer(email);
		
			if(cust.contains(cd))
			{
				throw new InvalidCustomerException("email is already used");
			}   
			System.out.println("no dublicte email found");
	}
	//cust age must be >21 use period  class 
	private static void validateage(String dob) throws InvalidCustomerException
	{
		LocalDate d1=LocalDate.parse(dob);
	     int age =Period.between(d1,LocalDate.now()).getYears();
		if(age<21)
			throw new InvalidCustomerException("you are under age");
		System.out.println("you can proced");
	}
	
	//parseandValidateSubscriptionDate
	public static LocalDate parseandValidateSubscriptionDate(String subscriptionPaidDate) throws InvalidCustomerException
	{
		LocalDate sd1=LocalDate.parse(subscriptionPaidDate);
		long period=Period.between(sd1,LocalDate.now() ).toTotalMonths();
		if(period>3)
			throw new InvalidCustomerException("subscription is over ");
		return sd1;
	}
	
   // add a static method for customer login
	public static Customer customerlogin(String email,String passwd,List<Customer> cust) throws InvalidCustomerException
	{
		int index= cust.indexOf(new Customer(email));
        
        if(index==-1)
        	throw new InvalidCustomerException("invalid email ");
       
            Customer ci=cust.get(index);
            if(!passwd.equals(ci.getPasswd()))
        	throw new InvalidCustomerException("invalid password ");
        	
        return ci;
	}
	
}
/*Iterator<Customer> itr=cust.iterator();
						while (itr.hasNext())
							
						 customer=itr.next();
						Long period=Period.between(customer.getsubscriptionPaidDate(), LocalDate.now()).toTotalMonths();
							if(period>3)
							{
						itr.remove();
						break;
							}*/
