package tollcalculation;

import java.util.HashMap;

public class Interchange {

	    private int id;
	    private String name;
	    private double lat;
	    private double lng;
	    private HashMap<Integer, Double> routes;

	    public Interchange(int id, String name, double lat, double lng) {
	        this.id = id;
	        this.name = name;
	        this.lat = lat;
	        this.lng = lng;
	        this.routes = new HashMap<Integer, Double>();
	    }

	    public void addRoute(int toId, double distance) {
	        routes.put(toId, distance);
	    }

	    public double getDistance(int toId) {
	        return routes.get(toId);
	    }
	
}
