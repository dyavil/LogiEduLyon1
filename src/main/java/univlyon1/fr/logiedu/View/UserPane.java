/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.View;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author dyavil
 */
public class UserPane extends GridPane {
    protected Label userNameLabel;
    protected Button logButton;
    
    public UserPane(String usn){
        userNameLabel = new Label(usn);
        logButton = new Button("Go !");
        this.add(userNameLabel, 0, 0);
        this.getRowConstraints().add(new RowConstraints(30));
        this.add(logButton, 0, 1);
    }

    /**
     * @return the userNameLabel
     */
    public Label getUserNameLabel() {
        return userNameLabel;
    }

    /**
     * @return the logButton
     */
    public Button getLogButton() {
        return logButton;
    }
}
