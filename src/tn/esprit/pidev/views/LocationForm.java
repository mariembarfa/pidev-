package tn.esprit.pidev.views;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Location;
import tn.esprit.pidev.services.LocationService;

import java.util.ArrayList;
import java.util.Collections;

public class LocationForm extends Form {
    Form current;
    LocationService locationService = new LocationService();
    ArrayList<Location> locationArrayList = new ArrayList<>();

    public LocationForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Locations");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        locationArrayList = locationService.showAll();
        Collections.reverse(locationArrayList);
        ShowLocation();
        /* *** *SEARCHBAR* *** */
        getToolbar().addSearchCommand(e -> {
            String text = (String) e.getSource();
            if (text == null || text.length() == 0) {
                // clear search
                for (Component cmp : getContentPane()) {
                    cmp.setHidden(false);
                    cmp.setVisible(true);
                }
                getContentPane().animateLayout(150);
            } else {
                text = text.toLowerCase();
                for (Component cmp : getContentPane()) {
                    MultiButton mb = (MultiButton) cmp;
                    String line1 = mb.getTextLine1();
                    String line2 = mb.getTextLine2();
                    boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(text) > -1;
                    mb.setHidden(!show);
                    mb.setVisible(show);

                }
                getContentPane().animateLayout(150);
            }
        }, 4);
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Sort by Type", null, (evt) -> {
            removeAll();
            Collections.sort(locationArrayList, Location.typeComparator);
            ShowLocation();
        });
        getToolbar().addCommandToOverflowMenu("Sort by Localisation", null, (evt) -> {
            removeAll();
            Collections.sort(locationArrayList, Location.localisationComparator);
            ShowLocation();
        });
        /* *** *SIDE MENU* *** */
        getToolbar().addCommandToLeftSideMenu("", null, (evt) -> {
        });
        getToolbar().addCommandToLeftSideMenu("Home", null, (evt) -> {
            new HomeForm().show();
        });
        getToolbar().addCommandToLeftSideMenu("All Events", null, (evt) -> {
            new EventsForm().show();
        });
        getToolbar().addCommandToLeftSideMenu("Location", null, (evt) -> {
            new LocationForm().show();
        });
        getToolbar().addCommandToLeftSideMenu("My Events", null, (evt) -> {
            new MyEventsForm().show();
        });
        getToolbar().addCommandToLeftSideMenu("My Reservations", null, (evt) -> {
            new ReservationsForm().show();
        });
        getToolbar().addCommandToLeftSideMenu("Profil", null, (evt) -> {
            new ProfilForm().show();
        });
    }

    private void ShowLocation() {
        for (Location location : locationArrayList) {
            MultiButton multiButton = new MultiButton();
            multiButton.setTextLine1(location.getLocalisation());
            multiButton.setTextLine2("Prix: " + location.getPrix() + "DT");
            multiButton.setTextLine3("Category: " + location.getType());
            multiButton.setUIID(location.getId() + "");
            multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
            multiButton.addActionListener(l -> new LocationDetailForm(current, location).show());
            add(multiButton);
        }
    }
}
