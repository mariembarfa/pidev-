package tn.esprit.pidev.views;

import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import tn.esprit.pidev.entities.Location;
import tn.esprit.pidev.entities.Reservation;
import tn.esprit.pidev.services.LocationService;
import tn.esprit.pidev.services.ReservationService;
import tn.esprit.pidev.utils.LoginSession;


public class LocationDetailForm extends Form {
    Form current;
LocationService locationService = new LocationService();
ReservationService reservationService = new ReservationService();
    public LocationDetailForm(Form previous, Location location) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Location");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        Label localisationLabel = new Label("Localisation: " + location.getLocalisation());
        Label typeLabel = new Label("Type: " + location.getType());
        Label prixLabel = new Label("Prix: " + location.getPrix()+"DT");
        Label placeLabel = new Label("Places: " + location.getPlace());
        SpanLabel descriptionLabel = new SpanLabel(location.getDescription());
        Button reservationButton = new Button("Reserver");
        reservationButton.addActionListener(e -> {
            Reservation reservation = new Reservation();
            reservation.setDestination(location.getLocalisation());
            reservation.setPlace(1);
            reservation.setIdUser(LoginSession.loggedUser);
            reservation.setPrix(location.getPrix());
            reservationService.reserver(reservation);
        });
        Container horizontalContainer = new Container(new BorderLayout());
        horizontalContainer.add(BorderLayout.WEST, placeLabel);
        horizontalContainer.add(BorderLayout.EAST, prixLabel);
        addAll(localisationLabel, typeLabel, horizontalContainer, descriptionLabel, reservationButton);
        /* *** *BACK BUTTON* *** */
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Share", FontImage.createMaterial(FontImage.MATERIAL_SHARE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            //SENDING EMAIL
            Display.getInstance().sendMessage(new String[]{""}, "Let's participate!", new Message("Check out this localisation: " + location.getLocalisation() + " it's for: " + location.getPrix() + " DT"));
        });
    }
}
