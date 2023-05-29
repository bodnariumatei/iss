package com.iss.theatre.gui;

import com.iss.theatre.model.User;
import com.iss.theatre.model.UserType;
import com.iss.theatre.service.AdminService;
import com.iss.theatre.service.UserService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {
    @FXML
    TextField usernameTextField;
    @FXML
    PasswordField passwordTextField;
    @FXML
    Label errorLabel;
    @FXML
    Button loginButton;

    private UserService srv;

    public void setService(UserService userService) {
        this.srv = userService;
    }

    @FXML
    public void login(ActionEvent e){
        String username = usernameTextField.getText();
        String password = passwordTextField.getText();
        if(username.equals("") || password.equals("")){
            errorLabel.setText("Username and Password can't be empty!");
            return;
        }
        User user = srv.getUserByUsernameAndPassword(username, password);
        if(user == null){
            errorLabel.setText("Username or password incorrect!");
            return;
        }
        errorLabel.setText("");

        // change scene
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        changeScene(stage, user.getType());
    }

    private void changeScene(Stage stage, UserType type){
        if(type.equals(UserType.ADMIN)){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("adminScene.fxml"));
                Parent root = loader.load();
                AdminController adminController = loader.getController();
                adminController.setSrv(new AdminService());
                adminController.loadScene();
                stage.setScene(new Scene(root));
            } catch (IOException ex) {
                System.err.println(ex.getMessage());
            }
        }
    }

}