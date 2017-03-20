/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.View;

import java.util.ArrayList;
import javafx.geometry.HPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author dyavil
 */
public class ExerciceGridView extends GridPane{
    
    private ArrayList<ExerciceGridSample> exercicesGrid;
    private GridPane topPane;
    private GridPane middlePane;
    private GridPane bottomPane;
    
    private Button nextExercices;
    private Button prevExercices;
    private Button backButton;
    private Button courseButton;
    private Button homeButton;
    
    public ExerciceGridView(int parentWidth){
        this.topPane = new GridPane();
        this.middlePane = new GridPane();
        this.bottomPane = new GridPane();
        this.exercicesGrid = new ArrayList<>();
        this.homeButton = new Button();
        this.backButton = new Button("Retour");
        this.courseButton = new Button("Cours");
        this.homeButton.getStyleClass().add("toggle-button");
        this.nextExercices = new Button("Exercices suivants");
        this.prevExercices = new Button("Exercices précédents");
        
        ColumnConstraints third1 = new ColumnConstraints((parentWidth-80)/3);
        third1.setHalignment(HPos.LEFT);
        ColumnConstraints third2 = new ColumnConstraints((parentWidth-80)/3);
        third2.setHalignment(HPos.CENTER);
        ColumnConstraints third3 = new ColumnConstraints((parentWidth-80)/3);
        third3.setHalignment(HPos.RIGHT);
        this.topPane.getColumnConstraints().add(third1);
        this.topPane.getColumnConstraints().add(third2);
        this.topPane.getColumnConstraints().add(third3);
        this.topPane.add(this.backButton, 0, 0);
        this.topPane.add(this.courseButton, 1, 0);
        this.topPane.add(this.homeButton, 2, 0);
        
        ColumnConstraints quart = new ColumnConstraints((parentWidth-95)/4);
        quart.setHalignment(HPos.CENTER);
        this.middlePane.getColumnConstraints().add(quart);
        this.middlePane.getColumnConstraints().add( new ColumnConstraints(5));
        this.middlePane.getColumnConstraints().add(quart);
        this.middlePane.getColumnConstraints().add( new ColumnConstraints(5));
        this.middlePane.getColumnConstraints().add(quart);
        this.middlePane.getColumnConstraints().add( new ColumnConstraints(5));
        this.middlePane.getColumnConstraints().add(quart);
        
        ColumnConstraints half1 = new ColumnConstraints((parentWidth-80)/2);
        half1.setHalignment(HPos.LEFT);
        ColumnConstraints half2 = new ColumnConstraints((parentWidth-80)/2);
        half2.setHalignment(HPos.RIGHT);
        this.bottomPane.getColumnConstraints().add(half1);
        this.bottomPane.getColumnConstraints().add(half2);
        
        ColumnConstraints marge = new ColumnConstraints(40);
        ColumnConstraints midCol = new ColumnConstraints(parentWidth-80);
        this.getColumnConstraints().add(marge);
        this.getColumnConstraints().add(midCol);
        this.getColumnConstraints().add(marge);
        this.getRowConstraints().add(new RowConstraints(20));
        this.getRowConstraints().add(new RowConstraints(60));
        this.getRowConstraints().add(new RowConstraints(300));
        this.add(this.topPane, 1, 1);
        this.add(this.middlePane, 1, 2);
        this.add(this.bottomPane, 1, 3);
        
    }
    
    public ExerciceGridSample addExerciceElem(String name, String content){
        ExerciceGridSample ng = new ExerciceGridSample(name, content);
        this.exercicesGrid.add(ng);
        return ng;
    }
    
    public void displayExerciceGrid(int parentWidth){
        int col = 0;
        int line = 0;
        int count = 0;
        for(ExerciceGridSample samp : this.exercicesGrid){
            count++;
            if(count%4 == 0) {
                line++;
                col = 0;
            }
            this.middlePane.add(samp, col, line);
            col+=2;
        }
    }

    /**
     * @return the exercicesGrid
     */
    public ArrayList<ExerciceGridSample> getExercicesGrid() {
        return exercicesGrid;
    }

    /**
     * @return the topPane
     */
    public GridPane getTopPane() {
        return topPane;
    }

    /**
     * @return the middlePane
     */
    public GridPane getMiddlePane() {
        return middlePane;
    }

    /**
     * @return the bottomPane
     */
    public GridPane getBottomPane() {
        return bottomPane;
    }

    /**
     * @return the nextExercices
     */
    public Button getNextExercices() {
        return nextExercices;
    }

    /**
     * @return the prevExercices
     */
    public Button getPrevExercices() {
        return prevExercices;
    }

    /**
     * @return the backButton
     */
    public Button getBackButton() {
        return backButton;
    }

    /**
     * @return the courseButton
     */
    public Button getCourseButton() {
        return courseButton;
    }

    /**
     * @return the homeButton
     */
    public Button getHomeButton() {
        return homeButton;
    }

    public static class ExerciceGridSample extends GridPane {
        
        private Label exerciceName;
        private Label exerciceContent;
        public ExerciceGridSample(String name, String content) {
            this.exerciceName = new Label(name);
            this.exerciceContent = new Label(content);
            this.add(exerciceName, 0, 0);
            this.add(exerciceContent, 0, 1);
            this.getStyleClass().add("exercice-grid-elem");
        }

        /**
         * @return the exerciceName
         */
        public Label getExerciceName() {
            return exerciceName;
        }

        /**
         * @return the exerciceContent
         */
        public Label getExerciceContent() {
            return exerciceContent;
        }
    }
}
