/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 *
 * @author dyavil
 */
public class LoggedPane extends GridPane{
    private Button coursesButton;
    private Button exercicesButton;
    private Label userLabel;
    
    public LoggedPane(String loggedUser, int parentWidth, int parentHeight){
        this.coursesButton = new Button("Cours");
        this.exercicesButton = new Button("Exercices");
        this.coursesButton.setId("green");
        this.coursesButton.setPrefSize(100, 50);
        this.coursesButton.setAlignment(Pos.CENTER);
        this.coursesButton.getStyleClass().add("bigg");
        this.exercicesButton.setId("green");
        this.exercicesButton.setPrefSize(100, 50);
        this.exercicesButton.setAlignment(Pos.CENTER);
        this.exercicesButton.getStyleClass().add("bigg");
        this.userLabel = new Label(loggedUser);
        
        this.getColumnConstraints().add(new ColumnConstraints(40));
        ColumnConstraints cocol = new ColumnConstraints(((parentWidth-80)/2));
        cocol.setHalignment(HPos.CENTER);
        this.getColumnConstraints().add(cocol);
        this.getColumnConstraints().add(cocol);
        this.getColumnConstraints().add(new ColumnConstraints(40));
        this.add(this.coursesButton, 1, 0);
        this.add(this.exercicesButton, 2, 0);
        
    }

    /**
     * @return the coursesButton
     */
    public Button getCoursesButton() {
        return coursesButton;
    }

    /**
     * @return the exercicesButton
     */
    public Button getExercicesButton() {
        return exercicesButton;
    }

    /**
     * @return the userLabel
     */
    public Label getUserLabel() {
        return userLabel;
    }

    /**
     * @param userLabel the userLabel to set
     */
    public void setUserLabel(String label) {
        this.userLabel.setText(label);
    }
    
}
