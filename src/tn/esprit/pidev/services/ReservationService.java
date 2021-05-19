package tn.esprit.pidev.services;

import com.codename1.io.*;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionListener;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Location;
import tn.esprit.pidev.entities.Reservation;
import tn.esprit.pidev.utils.Database;
import tn.esprit.pidev.utils.LoginSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationService {
    public ArrayList<Reservation> reservationArrayList;
    public Event event = new Event();
    public static ReservationService instance = null;
    public boolean resultOK;
    private ConnectionRequest connectionRequest;

    public ReservationService() {
        connectionRequest = new ConnectionRequest();
    }

    public static ReservationService getInstance() {
        if (instance == null) {
            instance = new ReservationService();
        }
        return instance;
    }

    public ArrayList<Reservation> parseReservation(String jsonText) {
        try {
            reservationArrayList = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> reservationListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            List<Map<String, Object>> list = (List<Map<String, Object>>) reservationListJson.get("root");
            for (Map<String, Object> obj : list) {
                Reservation reservation = new Reservation();
                reservation.setId((int) Float.parseFloat(obj.get("id").toString()));
                reservation.setPlace((int) Float.parseFloat(obj.get("nombresPersonnes").toString()));
                reservation.setPrix( Double.parseDouble(obj.get("prix").toString()));
                reservation.setDateFin(obj.get("dateFin").toString());
                reservation.setDateDebut(obj.get("dateDebut").toString());
                reservation.setDestination(obj.get("destination").toString());
                reservationArrayList.add(reservation);
            }
        } catch (IOException ex) {
        }
        return reservationArrayList;
    }

    public ArrayList<Reservation> showAll() {
       // String url = Database.BASE_URL + "resevation/api/show/idUser="+LoginSession.loggedUser; // Add Symfony URL Here
        String url = Database.BASE_URL + "home/reservation/api/show"; // Add Symfony URL Here
        connectionRequest.setUrl(url);
        connectionRequest.setPost(false);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                reservationArrayList = parseReservation(new String(connectionRequest.getResponseData()));
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return reservationArrayList;
    }

    public boolean annuler(Reservation reservation) {
            String url = Database.BASE_URL  + "reservation/api/delete/"+reservation.getId(); // Add Symfony URL here
        connectionRequest.setUrl(url);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
                @Override
                public void actionPerformed(NetworkEvent evt) {
                    resultOK = connectionRequest.getResponseCode() == 200; //Code HTTP 200 OK
                    connectionRequest.removeResponseListener(this);
                }
            });
            NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
            return resultOK;

    }
    public boolean reserver(Reservation reservation) {
        String url = Database.BASE_URL + "reservation/api/reserver?destination=" + reservation.getDestination() +
                "&idUser=" + reservation.getIdUser() +
                "&nombrePersonne="+reservation.getPlace()+
                "&prix=" + reservation.getPrix() ;// Add Symfony URL here
        connectionRequest.setUrl(url);
        connectionRequest.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = connectionRequest.getResponseCode() == 200; //Code HTTP 200 OK
                connectionRequest.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(connectionRequest);
        return resultOK;

    }
}
