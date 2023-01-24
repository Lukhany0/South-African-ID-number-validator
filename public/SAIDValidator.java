//Lukhanyo Mhlifili 
//South Africa ID number validator
import java.time.*;
import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;

//Driver class
class SAIDValidator{
	
	String dob, year;
	String genderCheck;
	String gender;
	String citizen;
	int age;
	Scanner sc = new Scanner(System.in);
	boolean isValid;
	
	//Calculating the checksum using Luhn Algorithm
	boolean isSA_IDno(String idno) {
		/*Using Luhn Algorithm
		 * 
		 * Take the length of the ID number of which is suppose to be 13
		 * EXCLUDE/Remove the right most digit is it the checksum that is being verified 
		 * Start from the right most digit to moving to the left -> multiply the right most digit by 2 skip 1 digit and multiply by 2 until the left most is reached
		 * If the digt multiplies is greater than 9 sum their digits
		 * Sum all the digits 
		 * if the sum + checksum equals zero it is a valid id number
		 */
		boolean alt = false;
		
		int len = idno.length();
		int count = 0;
		int sum = 0;
		
		
		for(int i=len - 1; i>=0; i--) {	
			int n = Integer.parseInt(idno.substring(i, i+1));
			if(alt) {
				n*=2;		//multiply the nth digit by 2
				if(n>9) {
					n = n%10 + 1;	//sum the digits of a number if it is greater than 9
				}
			}
			sum+=n;	//sum all the digit value
			alt = !alt;
		}
		
		return (sum % 10 == 0);
	}
	
	//Method TO display IF id number is valid or in valid
	void Validate(String id) {
		
		boolean vid = validID(id); //id no format
		
		if(vid) { 		//if id number format is correct
			
			isValid = isSA_IDno(id); //id no results
		}
	}
	//Method to check if the id number enter is in the correct format 
	//returns true is format is correct else false
	boolean validID(String id) {
		
		DateTimeFormatter fmt;
		LocalDate date;
		
		if((id.length()!=13)) { 			//check if id  no has 13 digits	
			return false;
		}
		genderCheck = id.substring(6,10);
		citizen = id.substring(10,11);
		if((!citizen.equals("1")) && (!citizen.equals("0")) && (id.substring(11,12)!="8" && id.substring(11,12)!="9")) {			//check is the citizenship number is valid is between integer [0,1]; checks if 12 digit is 8 or 9
			return false;
		}
		
		try {
			long intID = Long.parseLong(id); //check if id number is only valid digits
		}catch(Exception e) {
			return false;
		}
		
		try {
			 
			dob = id.substring(0, 6);							//date of birth -> 6 digits
			fmt = DateTimeFormatter.ofPattern("uuMMdd"); 	     //Format is yyMMdd
			date = LocalDate.parse(dob,fmt.withResolverStyle(ResolverStyle.STRICT));
			
			return true;
			
		}catch(Exception e) {
			 return false;
		}	
	}
	//get date of birth in the format dd month yyyy
	public String getDob() {
		//months
		String [] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "Novemember", "December"};
		
		String day, mon, yr;
		yr = dob.substring(0,2);
		mon = dob.substring(2,4);
		day = dob.substring(4,6);

		char yr0 = yr.charAt(0); //temp var to store yr[0]
		
		//year in yyyy format 
		if(yr0 == '0') {
			year = 20 + yr;
		}
		else
			year = 19 + yr;
		
		//parse mon to as int to get month index for mon
		int m = Integer.parseInt(mon);
		mon = months[m-1];
		
		return day + " " + mon + " " + year;
	}
	
	//calculate age
	public int getAge() {
		
		int temp_year = Integer.parseInt(year);
		int thisyear = LocalDate.now().getYear();
		return thisyear - temp_year;
	}
	//check citizen status
	public String getCitizen() {
		
		int pcitizen = Integer.parseInt(citizen);
		if(pcitizen == 0) {
			return "South African born";
		}
		else{
			return "permanent resident";
		}
	}
	//check gender
	public String getGender() {
		
		int genderC = Integer.parseInt(genderCheck); //gender checker as integer
		
		//Female gender in SA id is identified by the 5th number; if less than 5, then = female else male
		if(genderC < 5000) {
			return "Female";
		}
		else
			return "male";
	}
}