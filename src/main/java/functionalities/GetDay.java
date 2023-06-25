package functionalities;

import java.time.LocalDate;

public class GetDay 
{
	public String day(String a)
	{
		 String dateParts[] = a.split("-");
		 int date = Integer.parseInt(dateParts[0]);
	     int month = Integer.parseInt(dateParts[1]);
	     int year = Integer.parseInt(dateParts[2]);
	     
	     LocalDate ld=LocalDate.of(year, month, date);
	     String a1=(String)ld.getDayOfWeek().toString();
	    
	     return a1;
	}
}
