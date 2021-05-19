package tn.esprit.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.events.ActionListener;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.utils.Database;
import tn.esprit.pidev.utils.LoginSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EventService {
    public ArrayList<Event> eventArrayList;
    public Event event = new Event();
    public static EventService instance = null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;

    public EventService() {
        connectionRequest = new ConnectionRequest();
    }

    public static EventService getInstance() {
        if (instance == null) {
            instance = new EventService();
        }
        return instance;
    }

    public ArrayList<Event> parseEvent(String jsonText) {
        try {
            eventArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> eventListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) eventListJson.get("root");
            for (Map<String, Object> obj : list) {
                Event event = new Event();
                event.setCategory(obj.get("categorie").toString());
                event.setPrix(Float.parseFloat(obj.get("prix").toString()));
                event.setId((int) Float.parseFloat(obj.get("id").toString()));
                event.setDate(obj.get("date").toString());
                event.setDescription(obj.get("description").toString());
                event.setPlaces((int) Float.parseFloat(obj.get("nombrePlace").toString()));
                event.setTitre(obj.get("title").toString());
                event.setLocalisation(obj.get("localisation").toString());
                eventArrayList.add(event);
            }
        } catch (IOException ex) {
        }
        return eventArrayList;
    }

    public ArrayList<Event> showAll() {
        String url = Database.BASE_URL + "evenements/showevent"; // Add Symfony URL Here
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                eventArrayList = parseEvent(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return eventArrayList;
    }

    public ArrayList<Event> showMyEvent() {
        String url = Database.BASE_URL + "evenements/showMyEvent?idUser=" + LoginSession.loggedUser; // Add Symfony URL Here
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                eventArrayList = parseEvent(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return eventArrayList;
    }

    public void reserver(Event event) {
    }

    public void annuler(Event event) {
    }
}
