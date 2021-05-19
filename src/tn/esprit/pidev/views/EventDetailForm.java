package tn.esprit.pidev.views;

import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.*;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import tn.esprit.pidev.entities.Event;
import tn.esprit.pidev.services.EventService;


public class EventDetailForm extends Form{
    Form current;
    EventService eventService = new EventService();
    public EventDetailForm(Form previous, Event event, int action) {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle(event.getTitre());
        setLayout(BoxLayout.y());
        /* *** *YOUR CODE GOES HERE* *** */
        Label titreLabel = new Label("Titre: "+event.getTitre());
        Label categoryLabel = new Label("Category: "+event.getCategory());
        Label dateLabel = new Label("Date: "+event.getDate());
        Label localisationLabel = new Label("Location: "+event.getLocalisation());
        Label prixLabel = new Label("Prix: "+event.getPrix()+"DT");
        Label placeLabel = new Label("Place:"+ event.getPlaces());
        SpanLabel descriptionLabel = new SpanLabel("Description: "+event.getDescription());
        Button actionButton = new Button();
        if(action == 0 ){
            actionButton.setText("Reserver");
        }else {
            actionButton.setText("Annuler Reservation");
        }
        actionButton.addActionListener(evt -> {
            if(action == 0 ){
                System.out.println("0");
                eventService.reserver(event);
            }else {
                System.out.println("1");
                eventService.annuler(event);
            }
        });
        Container horizontalContainer = new Container(new BorderLayout());
        horizontalContainer.add(BorderLayout.WEST, placeLabel);
        horizontalContainer.add(BorderLayout.EAST, prixLabel);
        addAll(titreLabel, categoryLabel, dateLabel, localisationLabel, horizontalContainer, descriptionLabel, actionButton);
        /* *** *BACK BUTTON* *** */
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Share", FontImage.createMaterial(FontImage.MATERIAL_SHARE, UIManager.getInstance().getComponentStyle("TitleCommand")), (evt) -> {
            //SENDING EMAIL
            Display.getInstance().sendMessage(new String[]{""}, "Let's participate!", new Message("Check out this event: " + event.getTitre() + " it's for: " + event.getPrix() + " DT"));
        });
    }
}
