import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularExpressions 
{
	

	public static void main(String args[])
	{
	String data="</test>;rt=test;ct=0,</validate>;rt=validate;ct=0,</hello>;rt=Type1;ct=0;if=If1,</bl%C3%A5b%C3%A6rsyltet%C3%B8y>;rt=blåbærsyltetøy;ct=0,</sink>;rt=sink;ct=0,</separate>;rt=separate;ct=0,</large>;rt=Type1 Type2;ct=0;sz=1700;if=If2,</secret>;rt=secret;ct=0,</broken>;rt=Type2 Type1;ct=0;if=If2 If1,</weird33>;rt=weird33;ct=0,</weird44>;rt=weird44;ct=0,</weird55>;rt=weird55;ct=0,</weird333>;rt=weird333;ct=0,</weird3333>;rt=weird3333;ct=0,</weird33333>;rt=weird33333;ct=0,</123412341234123412341234>;rt=123412341234123412341234;ct=0,</location-query>;rt=location-query;ct=0,</create1>;rt=create1;ct=0,</large-update>;rt=large-update;ct=0,</large-create>;rt=large-create;ct=0,</query>;rt=query;ct=0,</seg1>;rt=seg1;ct=40,</path>;rt=path;ct=40,</location1>;rt=location1;ct=40,</multi-format>;rt=multi-format;ct=0,</3>;rt=3;ct=50,</4>;rt=4;ct=50,</5>;rt=5;ct=50";
	String regex="\\<\\(.*?)\\>";
	Pattern pattern = Pattern.compile("\\<.*?\\>");

	Matcher matcher = pattern.matcher(data);
	String[] resources_list=new String[100];
	int i=0;
	int j=0;
	
	for(i=0;matcher.find();i++)
	{
		String temp=matcher.group();
		
				String resource_list=temp.replaceAll("\\<|\\>", "");
				resources_list[i]=resource_list;
				//System.out.println(resource_list);
				j=j+1;
	}
	System.out.println("Total "+j+" services are present");
	i=j;
	for(j=0;j<i;j++)
	{
		String resource=resources_list[j];
		System.out.println(resource);
	}
    


}
}