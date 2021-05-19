package tn.esprit.pidev.entities;

public class User {
    private int id;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private int phone;

    public User() {
    }

    public User(int id, String email, String nom, String prenom, int phone) {
        this.id = id;
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
    }

    public User(String email, String nom, String prenom, int phone) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String email, String password, String nom, String prenom, int phone) {
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
    }

    public User(int id, String email, String password, String nom, String prenom, int phone) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.nom = nom;
        this.prenom = prenom;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
}
