package tn.esprit.pidev.views;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.Reservation;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.services.UserService;
import tn.esprit.pidev.utils.LoginSession;

import java.util.Collections;


public class LoginForm extends Form {
    Form current;
    UserService userService = new UserService();
    public LoginForm(){
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Login");
        setLayout(BoxLayout.yCenter());
        /* *** *YOUR CODE GOES HERE* *** */
        Label emailLabel = new Label("E-mail");
        Label passwordLabel = new Label("Password");
        TextField emailTextField = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
        TextField passwordTextField = new TextField("", "Password", 20, TextArea.PASSWORD);
        Button loginButton = new Button("Login");
        loginButton.addActionListener(evt -> {
            if (!emailTextField.getText().equals("")&&!passwordTextField.getText().equals("")){
                User user = new User(emailTextField.getText(), passwordTextField.getText());
               if( userService.login(user)){
                 LoginSession.loggedUser = userService.findUser(emailTextField.getText()).getId();
                 LoginSession.emailUser = emailTextField.getText();
                 new HomeForm().show();
               }else {
                   Dialog.show("Not Exist", "User doesn't exist", null, "OK");
                   new SignupForm().show();
               }
            }else{
                Dialog.show("Invalid Input", "Please check your inputs", null, "OK");
            }
        });
        addAll(emailLabel, emailTextField, passwordLabel, passwordTextField, loginButton);
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Signup", null, (evt) -> {
            new SignupForm().show();
        });
    }
}
