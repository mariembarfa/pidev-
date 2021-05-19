package tn.esprit.pidev.entities;

import java.util.Comparator;

public class Location {
    private int id;
    private String description;
    private String localisation;
    private String type;
    private Double prix;
    private int place;
   // private String url;


    public Location() {
    }

    public Location(String description, String localisation, Double prix, String type, int place) {
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.type = type;
        this.place = place;
    }

    public Location(int id, String description, String localisation, Double prix, String type, int place) {
        this.id = id;
        this.description = description;
        this.localisation = localisation;
        this.prix = prix;
        this.type = type;
        this.place = place;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public static Comparator<Location> localisationComparator = new Comparator<Location>() {
        @Override
        public int compare(Location o1, Location o2) {
            return (int) (o1.getLocalisation().toLowerCase().compareTo(o2.getLocalisation().toLowerCase()));
        }
    };
    public static Comparator<Location> typeComparator = new Comparator<Location>() {
        @Override
        public int compare(Location o1, Location o2) {
            return (int) (o1.getType().toLowerCase().compareTo(o2.getType().toLowerCase()));
        }
    };
}
