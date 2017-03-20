/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author dyavil
 */
public class ExercicesPane extends GridPane {
    private TreeView<HBox> exercicesList;
    private TreeItem<HBox> rootNode;
    private Button homeButton;
    
    public ExercicesPane(int parentWidth){
        ExerciceListItem item = new ExerciceListItem("Exercices", "no-change");
        this.rootNode = new TreeItem<>(item);
        this.exercicesList = new TreeView<>(this.getRootNode());
        this.homeButton = new Button();
        this.homeButton.getStyleClass().add("toggle-button");
        this.homeButton.setAlignment(Pos.BASELINE_RIGHT);
        this.getColumnConstraints().add(new ColumnConstraints(40));
        ColumnConstraints colc = new ColumnConstraints(parentWidth-80);
        colc.setHalignment(HPos.RIGHT);
        this.getColumnConstraints().add(colc);
        this.getRowConstraints().add(new RowConstraints(100));
        this.add(exercicesList, 1, 1);
        this.add(homeButton, 1, 0);
        this.getColumnConstraints().add(new ColumnConstraints(40));
    }

    /**
     * @return the exercicesList
     */
    public TreeView<HBox> getExercicesList() {
        return exercicesList;
    }

    /**
     * @return the rootNode
     */
    public TreeItem<HBox> getRootNode() {
        return rootNode;
    }

    /**
     * @return the homeButton
     */
    public Button getHomeButton() {
        return homeButton;
    }
}
