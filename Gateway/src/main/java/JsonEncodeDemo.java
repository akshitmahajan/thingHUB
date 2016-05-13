import org.json.JSONException;
import org.json.JSONObject;

public class JsonEncodeDemo 
{
	   @SuppressWarnings("unchecked")
	public static void main(String[] args) 
	   {
		   
		   JSONObject obj = new JSONObject();
           String Zone="HALL";
           String ApplianceType="TV";
           String ApplianceName="HALLTV";
           String ResourceNo="10";
           try {
           obj.put("Zone", Zone);
	      
			obj.put("ApplianceType", ApplianceType);
		
	      obj.put("ApplianceName", ApplianceName);
	      obj.put("ResourceNo", ResourceNo);
	      System.out.println(obj);
	      JSONObject obj1=obj;
	      System.out.println("obj1 \t"+obj1);
	      String DbZone=obj.get("Zone").toString();
	      System.out.println(DbZone);
	      
	      String DbAppType=obj.get("ApplianceType").toString();
	      System.out.println(DbAppType);
	      
	      String DbAppName=obj.get("ApplianceName").toString();
	      System.out.println(DbAppName);
	      
	      String DbResourceNo=obj.get("ResourceNo").toString();
	      System.out.println(DbResourceNo);
	      } catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      }
	}