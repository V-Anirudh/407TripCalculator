package tollcalculation;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONString;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder; 
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class TollCalculator {
	public static void main(String[] args) {
        try {
        	 FileReader reader = new FileReader("src/main/resources/interchanges.json");
        	 Gson gson = new Gson();
             JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
             System.out.println(jsonObject.toString());
           //JSONObject jsonObject = new JSONObject(new FileReader("src/main/resources/interchanges.json"));
            HashMap<Integer, Interchange> interchanges = new HashMap<Integer, Interchange>();
            System.out.println(jsonObject.get("locations").toString());
            JsonObject locationsObject = (JsonObject) jsonObject.get("locations");
            System.out.println(locationsObject.get("1"));
            
			/*
			 * for (String key : jsonObject.get("locations").keySet()) { JSONObject
			 * interchangeJson = jsonObject.get("locations").getJSONObject(key); int id =
			 * Integer.parseInt(key); String name = interchangeJson.getString("name");
			 * double lat = interchangeJson.getDouble("lat"); double lng =
			 * interchangeJson.getDouble("lng"); Interchange interchange = new
			 * Interchange(id, name, lat, lng); interchanges.put(id, interchange); for (int
			 * i = 0; i < interchangeJson.getJSONArray("routes").length(); i++) { JSONObject
			 * routeJson = interchangeJson.getJSONArray("routes").getJSONObject(i); int toId
			 * = routeJson.getInt("toId"); double distance =
			 * routeJson.getDouble("distance"); interchange.addRoute(toId, distance); } }
			 */
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter the starting interchange id: ");
            int fromId = scanner.nextInt();
            System.out.print("Enter the ending interchange id: ");
            int toId = scanner.nextInt();
            double distance = interchanges.get(fromId).getDistance(toId);
            double toll = distance * 0.25;
            System.out.printf("The toll for driving from interchange %d to interchange %d is $%.2f\n", fromId, toId, toll);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
