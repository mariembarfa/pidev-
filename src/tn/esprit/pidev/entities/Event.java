package tn.esprit.pidev.entities;

import java.util.Comparator;

public class Event {
    private int id;
    private String titre;
    private String category;
    private String date;
    private String localisation;
    private float prix;
    private int places;
    private String description;

    public Event() {
    }

    public Event(String titre, String description, float prix, String localisation, String date, String category, int places) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.localisation = localisation;
        this.date = date;
        this.category = category;
        this.places = places;
    }

    public Event(int id, String titre, String description, float prix, String localisation, String date, String category, int places) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.localisation = localisation;
        this.date = date;
        this.category = category;
        this.places = places;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getLocalisation() {
        return localisation;
    }

    public void setLocalisation(String localisation) {
        this.localisation = localisation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPlaces() {
        return places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }
    public static Comparator<Event> titleComparator = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return (int) (o1.getTitre().toLowerCase().compareTo(o2.getTitre().toLowerCase()));
        }
    };
    public static Comparator<Event> categoryComparator = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return (int) (o1.getCategory().toLowerCase().compareTo(o2.getCategory().toLowerCase()));
        }
    };
    public static Comparator<Event> localisationComparator = new Comparator<Event>() {
        @Override
        public int compare(Event o1, Event o2) {
            return (int) (o1.getLocalisation().toLowerCase().compareTo(o2.getLocalisation().toLowerCase()));
        }
    };
}
