package tn.esprit.pidev.views;

import com.codename1.ui.*;
import com.codename1.ui.layouts.BoxLayout;
import tn.esprit.pidev.entities.User;
import tn.esprit.pidev.services.UserService;

public class SignupForm extends Form {
    Form current;

    UserService userService = new UserService();

    public SignupForm() {
        /* *** *CONFIG SCREEN* *** */
        current = this;
        setTitle("Login");
        setLayout(BoxLayout.yCenter());
        /* *** *YOUR CODE GOES HERE* *** */
        Label emailLabel = new Label("E-mail");
        Label passwordLabel = new Label("Password");
        Label rePasswordLabel = new Label("Re-type Password");
        Label firstNameLabel = new Label("First Name");
        Label lastNameLabel = new Label("Last Name");
        Label phoneLabel = new Label("Phone");
        TextField emailTextField = new TextField("", "E-Mail", 20, TextArea.EMAILADDR);
        TextField passwordTextField = new TextField("", "Password", 20, TextArea.PASSWORD);
        TextField rePasswordTextField = new TextField("", "Re-type Password", 20, TextArea.PASSWORD);
        TextField firstNameTextField = new TextField("", "First Name", 20, TextArea.ANY);
        TextField lastNameTextField = new TextField("", "Last Name", 20, TextArea.ANY);
        TextField phoneTextField = new TextField("", "Phone", 20, TextArea.PHONENUMBER);
        Button registerButton = new Button("Register");
        registerButton.addActionListener(evt -> {
            if (passwordTextField.getText().equals(rePasswordTextField.getText())) {
                if (!emailTextField.getText().equals("") && !firstNameTextField.getText().equals("") && !lastNameTextField.getText().equals("") && !phoneTextField.getText().equals("")) {
                    User user = new User(emailTextField.getText(), passwordTextField.getText(), firstNameTextField.getText(), lastNameTextField.getText(), Integer.parseInt(phoneTextField.getText()));
                   // if (!userService.login(user)) {
                        userService.register(user);
                   /* } else {
                        Dialog.show("Exist", "User exists", null, "OK");

                    }*/
                    new LoginForm().show();
                } else {
                    Dialog.show("Invalid Input", "Please check your inputs", null, "OK");
                }
            } else {
                Dialog.show("Invalid Input", "Password doesnt match", null, "OK");
            }
        });
        addAll(firstNameLabel, firstNameTextField, lastNameLabel, lastNameTextField, phoneLabel, phoneTextField, emailLabel, emailTextField, passwordLabel, passwordTextField, rePasswordLabel, rePasswordTextField, registerButton);
        /* *** *OVERFLOW MENU* *** */
        getToolbar().addCommandToOverflowMenu("Signup", null, (evt) -> {
            new LoginForm().show();
        });

    }
}
