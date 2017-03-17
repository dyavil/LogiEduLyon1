/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.Controller;

import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import univlyon1.fr.logiedu.Model.App;
import univlyon1.fr.logiedu.Model.Theme;
import univlyon1.fr.logiedu.Model.User;
import univlyon1.fr.logiedu.View.CoursesPane;
import univlyon1.fr.logiedu.View.MainView;
import univlyon1.fr.logiedu.View.UserPane;

/**
 *
 * @author dyavil
 */
public class MainController {
    private MainView view;
    private App model;
    private Stage stage;
    
    public MainController(final MainView v, final App m, final Stage s){
        this.model = m;
        this.view = v;
        this.stage = s;
        for(User us : this.model.getUsers()){
            this.view.addUserPane(new UserPane(us.getUserName(), us.getId()));
        }
        this.view.displayUsersPane();
        this.stage.setScene(this.view.getScene());
        
        this.refreshUsersPane();
        
        this.view.getHead().getAddUser().setOnAction((ActionEvent e) -> {
                TextInputDialog dialog = new TextInputDialog("pseudo");
                dialog.setTitle("Pseudo : ");
                dialog.setHeaderText("");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    User us = this.model.addUser(result.get());
                    System.out.println("Pseudo: " + result.get());
                    this.view.addUserPane(new UserPane(us.getUserName(), us.getId()));
                    this.view.displayUsersPane();
                    this.refreshUsersPane();
                }
            });
        this.logoutHandler();
        
    }
    
    
    private void refreshUsersPane(){
        this.view.getUsersPane().stream().forEach((usp) -> {
            usp.getLogButton().setOnAction((ActionEvent e) -> {
                this.model.setLoggedUser(usp.getID());
                this.loadHomePane();
            });
        });
    }
    
    private void loadHomePane(){
        this.view.displayHomePane(this.model.getLoggedUser().getUserName());
        logoutHandler();
        this.view.getHomePane().getCoursesButton().setOnAction((ActionEvent e) -> {
                this.loadCourseList();
            });
    }

    private void loadCourseList() {
        ObservableList<String> data = FXCollections.observableArrayList();
        for(Theme th : this.model.getThemes()){
            data.add(th.getName());
        }
        this.view.getCoursePane().getCoursesList().setItems(data);
        this.view.getCoursePane().getCoursesList().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.setSelectCourseEventHandler();
        this.view.getCoursePane().getHomeButton().setOnAction((ActionEvent e) -> {
                this.loadHomePane();
            });
        this.view.displayCoursesPane();
    }
    
    private void logoutHandler(){
        this.view.getHead().getLogoutLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    view.displayUsersPane();
                    model.setLoggedUser(null);
            }

        });
    }

    private void setSelectCourseEventHandler() {
        
    }
}
