package tn.esprit.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Location;
import tn.esprit.pidev.utils.Database;
import tn.esprit.pidev.utils.LoginSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocationService {
    public ArrayList<Location> locationArrayList;
public Location location = new Location();
    public static LocationService instance = null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;

    public LocationService() {
        connectionRequest = new ConnectionRequest();
    }

    public static LocationService getInstance() {
        if (instance == null) {
            instance = new LocationService();
        }
        return instance;
    }

    public ArrayList<Location> parseLocation(String jsonText) {
        try {
            locationArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> locationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) locationListJson.get("root");
            for (Map<String, Object> obj : list) {
                Location location = new Location();
                location.setId((int) Float.parseFloat(obj.get("id").toString()));
                location.setDescription(obj.get("description").toString());
                location.setLocalisation(obj.get("localisation").toString());
                location.setType(obj.get("type").toString());
                location.setPlace((int) Float.parseFloat(obj.get("placesDispo").toString()));
                location.setPrix(Double.parseDouble(obj.get("prix").toString()));
            locationArrayList.add(location);
            }
        } catch (IOException ex) {
        }
        return locationArrayList;
    }

    public ArrayList<Location> showAll() {
        String url = Database.BASE_URL + "home/location/all"; // Add Symfony URL Here
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                locationArrayList = parseLocation(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return locationArrayList;
    }


}
