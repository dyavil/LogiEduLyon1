/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.View;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author dyavil
 */
public class UserPane extends GridPane {
    private Label userNameLabel;
    private Button logButton;
    private int id;
    
    public UserPane(String usn, int id){
        userNameLabel = new Label(usn);
        this.id = id;
        logButton = new Button("Go !");
        logButton.setPrefWidth(80);
        userNameLabel.setPrefWidth(80);
        logButton.setAlignment(Pos.CENTER);
        userNameLabel.setAlignment(Pos.CENTER);
        
        logButton.setId("green");
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

    /**
     * @return the id
     */
    public int getID() {
        return id;
    }
}
