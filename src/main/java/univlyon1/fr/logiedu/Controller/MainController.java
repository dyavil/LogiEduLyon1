/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.Controller;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;
import univlyon1.fr.logiedu.Model.App;
import univlyon1.fr.logiedu.Model.User;
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
        
        this.view.getUsersPane().stream().forEach((usp) -> {
            usp.getLogButton().setOnAction((ActionEvent e) -> {
                System.out.println(usp.getID());
            });
        });
        
        this.view.getAddUser().setOnAction((ActionEvent e) -> {
                TextInputDialog dialog = new TextInputDialog("pseudo");
                dialog.setTitle("Pseudo : ");
                dialog.setHeaderText("");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    User us = this.model.addUser(result.get());
                    System.out.println("Pseudo: " + result.get());
                    this.view.addUserPane(new UserPane(us.getUserName(), us.getId()));
                    this.view.displayUsersPane();
                }
            });
        
    }
}
