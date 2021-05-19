package tn.esprit.pidev.entities;

import java.util.Comparator;

public class Reservation {
private int id;
private String destination;
private int idUser;
private String dateDebut;
private String dateFin;
private int place;
private Double prix;

    public Reservation() {
    }

    public Reservation(String destination, int idUser, String dateDebut, String dateFin, int place, Double prix) {
        this.destination = destination;
        this.idUser = idUser;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.place = place;
        this.prix = prix;
    }

    public Reservation(int id, String destination, int idUser, String dateDebut, String dateFin, int place, Double prix) {
        this.id = id;
        this.destination = destination;
        this.idUser = idUser;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.place = place;
        this.prix = prix;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateFin() {
        return dateFin;
    }

    public void setDateFin(String dateFin) {
        this.dateFin = dateFin;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }
    public static Comparator<Reservation> destinationComparator = new Comparator<Reservation>() {
        @Override
        public int compare(Reservation o1, Reservation o2) {
            return (int) (o1.getDestination().toLowerCase().compareTo(o2.getDestination().toLowerCase()));
        }
    };
}
