/*
 * Quintard LivaI
 * Project for Logiciel Educatif
 * Universite lyon 1
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
public class HomeView extends GridPane {
    private TreeView<HBox> coursesList;
    private TreeView<HBox> exercicesList;
    private TreeItem<HBox> courseRootNode;
    private TreeItem<HBox> exerciceRootNode;
    
    public HomeView(int parentWidth){
        CourseListItem tbox = new CourseListItem("Cours", "no-change");
        ExerciceListItem item = new ExerciceListItem("Exercices", "no-change");
        this.courseRootNode = new TreeItem<>(tbox);
        this.exerciceRootNode = new TreeItem<>(item);
        this.coursesList = new TreeView<>(this.getCourseRootNode());
        this.exercicesList = new TreeView<>(this.getExerciceRootNode());
        this.getColumnConstraints().add(new ColumnConstraints(40));
        ColumnConstraints colc = new ColumnConstraints((parentWidth-80)/2);
        colc.setHalignment(HPos.RIGHT);
        this.getColumnConstraints().add(colc);
        this.getColumnConstraints().add(colc);
        this.add(coursesList, 1, 0);
        this.add(exercicesList, 2, 0);
        this.getColumnConstraints().add(new ColumnConstraints(40));
    }

    /**
     * @return the coursesList
     */
    public TreeView<HBox> getCoursesList() {
        return coursesList;
    }

    /**
     * @return the exercicesList
     */
    public TreeView<HBox> getExercicesList() {
        return exercicesList;
    }

    /**
     * @param exercicesList the exercicesList to set
     */
    public void setExercicesList(TreeView<HBox> exercicesList) {
        this.exercicesList = exercicesList;
    }

    /**
     * @return the courseRootNode
     */
    public TreeItem<HBox> getCourseRootNode() {
        return courseRootNode;
    }

    /**
     * @return the exerciceRootNode
     */
    public TreeItem<HBox> getExerciceRootNode() {
        return exerciceRootNode;
    }


}
