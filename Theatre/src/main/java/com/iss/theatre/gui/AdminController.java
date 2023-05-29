package com.iss.theatre.gui;

import com.iss.theatre.model.Show;
import com.iss.theatre.service.AdminService;
import com.iss.theatre.utils.ValidationException;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class AdminController {
    private AdminService srv;

    public void setSrv(AdminService srv) {
        this.srv = srv;
    }

    @FXML
    private ListView<Show> showsListView;

    @FXML
    private TextField nameTextField;

    @FXML
    private TextField durationTextField;

    public void loadScene(){
        showsListView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        loadShowsListView();
    }

    private void loadShowsListView(){
        showsListView.getItems().clear();
        Iterable<Show> shows = srv.getAllShows();
        for(Show s: shows){
            showsListView.getItems().add(s);
        }
    }

    public void addShow(){
        String name = nameTextField.getText();
        String durationString = durationTextField.getText();
        try{
            if(name.isEmpty() || durationString.isEmpty()){
                throw new ValidationException();
            }
            int duration = Integer.parseInt(durationString);
            Show show = srv.addShow(name, duration);
            printStatus(Alert.AlertType.CONFIRMATION,
                    "Show added successfully",
                    "Show: " + show.toString() + " added!");
            updateScene();
            nameTextField.setText("");
            durationTextField.setText("");
        } catch (ValidationException e) {
            printStatus(Alert.AlertType.ERROR,
                    "Empty name or duration" ,
                    "Name and duration must be completed first!");
        } catch (NumberFormatException nfe){
            printStatus(Alert.AlertType.ERROR,
                    "Invalid duration" ,
                    "Duration must be an integer!");
        }
    }

    public void removeShow(){
        try{
            Show show = showsListView.getSelectionModel().getSelectedItem();
            if(show == null){
                throw new ValidationException();
            }
            Show rShow = srv.removeShow(show.getId());
            printStatus(Alert.AlertType.CONFIRMATION,
                    "Show removed",
                    "The show: " + rShow.toString() + " was removed");
            updateScene();
        } catch (ValidationException e) {
            printStatus(Alert.AlertType.ERROR,
                    "No selection made!",
                    "Select a show first.");
        }
    }

    private void printStatus(Alert.AlertType type, String header , String message){
        Alert alert = new Alert(type);
        alert.setHeaderText(header);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void updateScene(){
        loadShowsListView();
    }
}
