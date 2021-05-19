package tn.esprit.pidev.views;

import com.codename1.components.MultiButton;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Reservation;
import tn.esprit.pidev.services.EventService;
import tn.esprit.pidev.utils.Database;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class EventsForm extends Form {
    Form current;
    EventService eventService = new EventService();
    ArrayList<Event> eventArrayList = new ArrayList<>();

    public EventsForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("All Events");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        eventArrayList = eventService.showAll();
        Collections.reverse(eventArrayList);
        ShowEvents();
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
        getToolbar().addCommandToOverflowMenu("Sort by Name", null, (evt) -> {
            removeAll();
            Collections.sort(eventArrayList, Event.titleComparator);
            ShowEvents();
        });
        getToolbar().addCommandToOverflowMenu("Sort by Category", null, (evt) -> {
            removeAll();
            Collections.sort(eventArrayList, Event.categoryComparator);
            ShowEvents();
        });
        getToolbar().addCommandToOverflowMenu("Sort by Localisation", null, (evt) -> {
            removeAll();
            Collections.sort(eventArrayList, Event.localisationComparator);
            ShowEvents();
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

    private void ShowEvents() {
        for (Event event : eventArrayList) {
            MultiButton multiButton = new MultiButton();
            multiButton.setTextLine1(event.getTitre());
            multiButton.setTextLine2("Prix: " + event.getPrix() + "DT");
            multiButton.setTextLine3("Category: " + event.getCategory());
            multiButton.setTextLine4("Location: " + event.getLocalisation());
            multiButton.setUIID(event.getId() + "");
            multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
            multiButton.addActionListener(l -> new EventDetailForm(current, event, 0).show());
            add(multiButton);
        }
    }
}
