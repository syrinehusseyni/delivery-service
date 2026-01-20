package tn.enis.fooddelivery.delivery.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class GpsService {

    public Map<String, String> getRoute(String from, String to) {
        Map<String, String> route = new HashMap<>();
        route.put("distance", "4.2 km");
        route.put("duration", "12 min");
        route.put("path", "mock-polyline");
        return route;
    }
}
