package tollcalculation;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import org.json.JSONObject;
import com.google.gson.Gson; 
import com.google.gson.GsonBuilder; 
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
public class TollCalculator {
    private static final double TOLL_RATE = 0.25; // toll rate of $0.25/km
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the starting interchange: ");
        String start = scanner.nextLine();
        System.out.print("Enter the destination interchange: ");
        String end = scanner.nextLine();
        scanner.close();
        costOfTrip(start, end);
        // System.out.println("Trip 1: QEW to Highway 400");
        // costOfTrip("QEW", "Highway 400");
        
        // System.out.println("\nTrip 2: Salem Road to QEW");
        // costOfTrip("Salem Road", "QEW");
        
        // System.out.println("\nTrip 3: QEW to Salem Road");
        // costOfTrip("QEW", "Salem Road");
    }
    
    public static void costOfTrip(String start, String end) {
        System.out.print(start+' '+end);
        double distance = 0.0;
        double cost = 0.0;
        
        try {
            // Load the interchanges.json file
            FileReader reader = new FileReader("src/main/resources/interchanges.json");
        	 Gson gson = new Gson();
             JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
             System.out.println(jsonObject.toString());

            // File file = new File("D:/407ETR/407TripCalculator/src/main/resources/interchanges.json");
            // System.out.println("the json data"+file);
            Scanner scanner = new Scanner(System.in);
            String jsonStr = scanner.useDelimiter("\\Z").next();
            scanner.close();
            
            // Parse the JSON string
            JSONObject jsonObj = new JSONObject(jsonStr);
            
            // Find the start and end interchanges
            JSONObject startInterchange = null;
            JSONObject endInterchange = null;
            
            for (Object key : jsonObj.getJSONObject("locations").keySet()) {
                JSONObject interchange = jsonObj.getJSONObject("locations").getJSONObject((String) key);
                
                if (interchange.getString("name").equalsIgnoreCase(start)) {
                    startInterchange = interchange;
                }
                
                if (interchange.getString("name").equalsIgnoreCase(end)) {
                    endInterchange = interchange;
                }
                
                if (startInterchange != null && endInterchange != null) {
                    break;
                }
            }
            
            // Calculate the distance between the start and end interchanges
            for (int i = 0; i < startInterchange.getJSONArray("routes").length(); i++) {
                JSONObject route = startInterchange.getJSONArray("routes").getJSONObject(i);
                
                if (route.getInt("toId") == endInterchange.getInt("id")) {
                    distance = route.getDouble("distance");
                    break;
                }
            }
            
            // Calculate the cost of the trip
            cost = distance * TOLL_RATE;
            
            // Print the results
            System.out.printf("Distance: %.3f km\n", distance);
            System.out.printf("Cost: $%.2f\n", cost);
        } catch (FileNotFoundException e) {
            System.out.println("interchanges.json file not found!");
            e.printStackTrace();
        }
    }
}
