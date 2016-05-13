

public class RegExpressionForNotify {
	public static void main(String args[])
	{
		String rat_values = "thingTronics|thingSocket|v1.0:v1.0|configure|HALL|switch|TV|14|STATUS:ON";
		
		Boolean configurechecking=rat_values.contains("configure");
		if(configurechecking==true)
		{
		System.out.println(configurechecking);
		}
		else
		{
		String[] value_split = rat_values.split("\\|");
	    String[] status = rat_values.split("\\STATUS:");
	    
	    String zone=value_split[3];
	    System.out.println(zone);
	    
	    String ApplianceType=value_split[4];
	    System.out.println(ApplianceType);
	    
	    String ApplianceName=value_split[5];
	    System.out.println(ApplianceName);
	    
	    String statusvar=status[1];
	    System.out.println(statusvar);
		}
	    //System.out.println("select label from IP where (Zone,ResourceType) IN (('HALL','TV'),('Dining','FAN'),('Dining','FAN'));");
	}

}
