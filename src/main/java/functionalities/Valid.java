package functionalities;

import java.util.*;

public class Valid 
{
	Map<String,String> map=new HashMap<String, String>();
	
	public Map<String,String> dict(List<String> s)
	{
		for(String s1:s)
		{
//			20-MAY-2023 To 26-MAY-2023
			
		    String substr1 = s1.substring(0,11);
		    if(substr1.contains("MAY"))
		    {
		    	String a=substr1.replace("MAY","05");
		    	map.put(a,"SATURDAY");
		    }
		    else
		    {
		    	String a=substr1.replace("JUN","06");
			    map.put(a,"SATURDAY");
		    }
		    
		    
		    String substr2=s1.substring(15,26);
		    if(substr2.contains("MAY"))
		    {
		    	String b=substr2.replace("MAY","05");
		    	 map.put(b,"FRIDAY");
		    }
		    else
		    {
		    	String b=substr2.replace("JUN","06");
		    	 map.put(b,"FRIDAY");
		    }
		}
		//System.out.println(map);
		return map;
	}
}
