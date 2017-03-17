/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author dyavil
 */
public class CoursesPane extends GridPane {
    private ListView<String> coursesList;
    private Button homeButton;
    
    public CoursesPane(int parentWidth){
        this.coursesList = new ListView<>();
        this.homeButton = new Button();
        this.homeButton.getStyleClass().add("toggle-button");
        this.homeButton.setAlignment(Pos.BASELINE_RIGHT);
        this.getColumnConstraints().add(new ColumnConstraints(40));
        ColumnConstraints colc = new ColumnConstraints(parentWidth-80);
        colc.setHalignment(HPos.RIGHT);
        this.getColumnConstraints().add(colc);
        this.getRowConstraints().add(new RowConstraints(100));
        this.add(coursesList, 1, 1);
        this.add(homeButton, 1, 0);
        this.getColumnConstraints().add(new ColumnConstraints(40));
    }

    /**
     * @return the coursesList
     */
    public ListView<String> getCoursesList() {
        return coursesList;
    }

    /**
     * @return the homeButton
     */
    public Button getHomeButton() {
        return homeButton;
    }
}
