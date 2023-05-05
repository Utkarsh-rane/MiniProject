package utils;


import java.util.ArrayList;
import java.util.List;
//import  utils.CustomerValidationRule.*;
import static utils.CustomerValidationRule.ValidateAll;
import com.app.customer.Customer;



public class Customerutils {
	
    public static List<Customer> customer(){	
	//SILVER(1000),GOLD(2000),DIAMOND(5000),PLATINUM(10000);
    	try {
		List<Customer> cust= new ArrayList<>();
 
 
	 cust.add( ValidateAll("utkarsh","rane","utkarsh@gmail","utks",2000.00,"2001-03-02","gold","2022-12-02",cust));
     cust.add(ValidateAll("ram","Girhe","ram@","ram",10000.00, "2001-02-17","platinum","2023-01-01",cust));
     cust.add(ValidateAll("aniket","gonajare","aniket@","aniket",1000.00, "2000-12-12","silver","2023-02-02",cust));
     cust.add(ValidateAll("darshan","patil","darshan@","darshan",10000.00,  "2001-02-12","platinum","2023-02-22",cust));
     cust.add(ValidateAll("nikhil","bhosale","nikhil@","nikhil",2000.00, "2000-12-12","gold","2023-03-19",cust));
     cust.add(ValidateAll("balaji","gaphat","balaji@","balaji",2000.00, "2000-05-14","gold","2023-04-19",cust));
     return cust;
	}catch (Exception e) {
		e.printStackTrace();
	}
		return null;

}
    }
