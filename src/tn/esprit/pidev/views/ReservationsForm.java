package tn.esprit.pidev.views;

import com.codename1.components.MultiButton;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.entities.Reservation;
import tn.esprit.pidev.services.EventService;
import tn.esprit.pidev.services.ReservationService;

import java.util.ArrayList;
import java.util.Collections;

public class ReservationsForm extends Form {
    Form current;
    ReservationService reservationService = new ReservationService();
    ArrayList<Reservation> reservationArrayList = new ArrayList<>();

    public ReservationsForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("My Reservation");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        reservationArrayList = reservationService.showAll();
        Collections.reverse(reservationArrayList);
        ShowReservation();
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
        getToolbar().addCommandToOverflowMenu("Sort by Destination", null, (evt) -> {
            removeAll();
            Collections.sort(reservationArrayList, Reservation.destinationComparator);
            ShowReservation();
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
        });    }

    private void ShowReservation() {
        if (reservationArrayList.size()>0){
        for (Reservation reservation : reservationArrayList) {
            MultiButton multiButton = new MultiButton();
            multiButton.setTextLine1(reservation.getDestination());
            multiButton.setTextLine2("Prix: " + reservation.getPrix() + "DT");
            multiButton.setTextLine3("Places: " + reservation.getPlace());
            multiButton.setUIID(reservation.getId() + "");
            multiButton.setEmblem(FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_RIGHT, "", 10.0f));
            multiButton.addActionListener(l -> new ReservationDetailForm(current, reservation).show());
            add(multiButton);
        }
        }
        else {
            add(new Label("No events"));
        }
    }
}
