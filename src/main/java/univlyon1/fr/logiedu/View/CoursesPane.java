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
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
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
public class CoursesPane extends GridPane {
    private TreeView<HBox> coursesList;
    private TreeItem<HBox> rootNode;
    private Button homeButton;
    private Button goButton;
    private boolean go;
    
    public CoursesPane(int parentWidth){
        CourseListItem tbox = new CourseListItem("Cours", "no-change");
        this.rootNode = new TreeItem<>(tbox);
        this.coursesList = new TreeView<>(this.rootNode);
        this.homeButton = new Button();
        this.goButton = new Button("Go !");
        this.goButton.setId("green");
        this.homeButton.getStyleClass().add("toggle-button");
        this.go = false;
        this.goButton.setAlignment(Pos.BASELINE_RIGHT);
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
    
    public void showgoButton(){
        if(!go){
            this.add(this.getGoButton(), 1, 2);
            go=true;
        }
    }
    
    public void hidegoButton(){
        this.getChildren().remove(this.getGoButton());
        go=false;
    }

    /**
     * @return the coursesList
     */
    public TreeView<HBox> getCoursesList() {
        return coursesList;
    }

    /**
     * @return the homeButton
     */
    public Button getHomeButton() {
        return homeButton;
    }

    /**
     * @return the rootNode
     */
    public TreeItem<HBox> getRootNode() {
        return rootNode;
    }

    /**
     * @return the goButton
     */
    public Button getGoButton() {
        return goButton;
    }
}
