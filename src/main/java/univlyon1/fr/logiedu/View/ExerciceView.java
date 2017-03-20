/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.View;

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
public class ExerciceView extends GridPane{
    
    private GridPane topPane;
    private GridPane middlePane;
    private GridPane bottomPane;
    
    private Label courseName;
    private Label exerciceName;
    private Label exerciceContent;
    private Button nextExercice;
    private Button prevExercice;
    private Button backButton;
    private Button courseButton;
    private Button homeButton;
    private Boolean nextShowned;
    private Boolean prevShowned;
    
    public ExerciceView(String course, String name, String content, int parentWidth){
        this.topPane = new GridPane();
        this.middlePane = new GridPane();
        this.bottomPane = new GridPane();
        
        this.courseName = new Label(course);
        this.exerciceName = new Label(name);
        this.exerciceContent = new Label(content);
        this.nextExercice = new Button("Exercice suivant");
        this.nextExercice.getStyleClass().add("bevel-grey");
        this.prevExercice = new Button("Exercice précédent");
        this.prevExercice.getStyleClass().add("bevel-grey");
        this.nextShowned = false;
        this.prevShowned = false;
        this.homeButton = new Button();
        this.backButton = new Button("Retour");
        this.courseButton = new Button("Cours");
        this.courseButton.getStyleClass().add("green");
        this.homeButton.getStyleClass().add("toggle-button");
        this.exerciceContent.getStyleClass().add("course-content");
        this.exerciceContent.setWrapText(true);
        
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
        this.topPane.add(this.courseName, 1, 0);
        this.topPane.add(this.homeButton, 2, 0);
        this.middlePane.add(this.exerciceName, 0, 0);
        this.middlePane.add(this.exerciceContent, 0, 2);
        this.bottomPane.getColumnConstraints().add(third1);
        this.bottomPane.getColumnConstraints().add(third2);
        this.bottomPane.getColumnConstraints().add(third3);
        this.bottomPane.add(this.courseButton, 1, 0);
        
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

    public void showNextButton(){
        if(!this.getNextShowned()) {
            this.bottomPane.add(this.nextExercice, 2, 0);
            this.setNextShowned((Boolean) true);
        }
    }
    
    public void hideNextButton(){
        if(getNextShowned()){
            this.bottomPane.getChildren().remove(this.nextExercice);
            this.setNextShowned((Boolean) false);
        }
        
    }
    
    public void showPrevButton(){
        if(!this.getPrevShowned()) {
            this.bottomPane.add(this.getPrevExercice(), 0, 0);
            this.setPrevShowned((Boolean) true);
        }
    }
    
    public void hidePrevButton(){
        if(getPrevShowned()){
            this.bottomPane.getChildren().remove(this.getPrevExercice());
            this.setPrevShowned((Boolean) false);
        }
        
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

    /**
     * @return the nextExercice
     */
    public Button getNextExercice() {
        return nextExercice;
    }

    /**
     * @return the prevExercice
     */
    public Button getPrevExercice() {
        return prevExercice;
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

    /**
     * @return the nextShowned
     */
    public Boolean getNextShowned() {
        return nextShowned;
    }

    /**
     * @return the prevShowned
     */
    public Boolean getPrevShowned() {
        return prevShowned;
    }

    /**
     * @param nextShowned the nextShowned to set
     */
    public void setNextShowned(Boolean nextShowned) {
        this.nextShowned = nextShowned;
    }

    /**
     * @param prevShowned the prevShowned to set
     */
    public void setPrevShowned(Boolean prevShowned) {
        this.prevShowned = prevShowned;
    }
    
}
