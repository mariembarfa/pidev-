package tn.esprit.pidev.views;

import com.codename1.messaging.Message;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import tn.esprit.pidev.entities.Reservation;
import tn.esprit.pidev.services.ReservationService;

public class ReservationDetailForm extends Form{
    Form current;
    ReservationService reservationService = new ReservationService();
    public ReservationDetailForm(Form previous, Reservation reservation) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Reservation");
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        Label destinationLabel = new Label("Destination: "+reservation.getDestination());
        Label dateDebutLabel = new Label("Debut: "+reservation.getDateDebut());
        Label dateFinLabel = new Label("Fin: "+reservation.getDateFin());
        Label prixLabel = new Label("Prix: " + reservation.getPrix()+"DT");
        Label placeLabel = new Label("Places: " + reservation.getPlace());
        Button annulerButton = new Button("Annuler Reservation");
        annulerButton.addActionListener(evt -> reservationService.annuler(reservation));
        Container horizontalContainer = new Container(new BorderLayout());
        horizontalContainer.add(BorderLayout.WEST, placeLabel);
        horizontalContainer.add(BorderLayout.EAST, prixLabel);
        addAll(destinationLabel, dateDebutLabel, dateFinLabel, horizontalContainer, annulerButton);
        /* *** *BACK BUTTON* *** */
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Share", FontImage.createMaterial(FontImage.MATERIAL_SHARE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            //SENDING EMAIL
            Display.getInstance().sendMessage(new String[]{""}, "Let's participate!", new Message("I am going to: " + reservation.getDestination() + " it's for: " + reservation.getPrix() + " DT"));
        });
    }
}
