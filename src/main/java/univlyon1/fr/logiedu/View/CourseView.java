/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.View;

import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author dyavil
 */
public class CourseView extends GridPane {
    private GridPane topPane;
    private GridPane bottomPane;
    private Label courseName;
    private Label courseContent;
    private Button homeButton;
    private Button backToList;
    private Button nextCourse;
    private Button prevCourse;
    private Boolean nextShowned;
    private Boolean prevShowned;
    
    public CourseView(String name, String content, int parentWidth){
        this.topPane = new GridPane();
        this.bottomPane = new GridPane();
        this.courseName = new Label(name);
        this.courseName.getStyleClass().add("course-label");
        this.courseContent = new Label(content);
        this.courseContent.getStyleClass().add("course-content");
        this.courseContent.setWrapText(true);
        this.homeButton = new Button();
        this.backToList = new Button("Liste de cours");
        this.nextCourse = new Button("Cours suivant");
        this.prevCourse = new Button("Cours precedent");
        this.homeButton.getStyleClass().add("toggle-button");
        this.nextCourse.getStyleClass().add("dark-blue");
        this.prevCourse.getStyleClass().add("dark-blue");
        this.homeButton.setAlignment(Pos.BASELINE_RIGHT);
        this.getRowConstraints().add(new RowConstraints(20));
        this.getRowConstraints().add(new RowConstraints(60));
        this.getRowConstraints().add(new RowConstraints(50));
        this.getRowConstraints().add(new RowConstraints(300));
        this.getColumnConstraints().add(new ColumnConstraints(40));
        ColumnConstraints colc = new ColumnConstraints(parentWidth-80);
        colc.setHalignment(HPos.RIGHT);
        this.getColumnConstraints().add(colc);
        this.getColumnConstraints().add(new ColumnConstraints(40));
        ColumnConstraints topCol1 = new ColumnConstraints((parentWidth-80)/2);
        ColumnConstraints topCol2 = new ColumnConstraints((parentWidth-80)/2);
        topCol1.setHalignment(HPos.LEFT);
        topCol2.setHalignment(HPos.RIGHT);
        this.topPane.getColumnConstraints().add(topCol1);
        this.bottomPane.getColumnConstraints().add(topCol1);
        this.topPane.getColumnConstraints().add(topCol2);
        this.bottomPane.getColumnConstraints().add(topCol2);
        this.topPane.add(this.backToList, 0, 0);
        this.topPane.add(this.homeButton, 1, 0);
        this.nextShowned = false;
        this.prevShowned = false;
        this.add(this.topPane, 1, 1);
        this.add(this.courseName, 1, 2);
        this.add(this.courseContent, 1, 3);
        this.add(this.bottomPane, 1, 4);
    }

    public void showNextButton(){
        if(!this.nextShowned) {
            this.getBottomPane().add(this.nextCourse, 1, 0);
            this.nextShowned = true;
        }
    }
    
    public void hideNextButton(){
        if(nextShowned){
            this.getBottomPane().getChildren().remove(this.nextCourse);
            this.nextShowned = false;
        }
        
    }
    
    public void showPrevButton(){
        if(!this.prevShowned) {
            this.getBottomPane().add(this.getPrevCourse(), 0, 0);
            this.prevShowned = true;
        }
    }
    
    public void hidePrevButton(){
        if(prevShowned){
            this.getBottomPane().getChildren().remove(this.getPrevCourse());
            this.prevShowned = false;
        }
        
    }
    
    /**
     * @return the courseName
     */
    public Label getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(Label courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the courseContent
     */
    public Label getCourseContent() {
        return courseContent;
    }

    /**
     * @param courseContent the courseContent to set
     */
    public void setCourseContent(Label courseContent) {
        this.courseContent = courseContent;
    }

    /**
     * @return the homeButton
     */
    public Button getHomeButton() {
        return homeButton;
    }

    /**
     * @param homeButton the homeButton to set
     */
    public void setHomeButton(Button homeButton) {
        this.homeButton = homeButton;
    }

    /**
     * @return the backToList
     */
    public Button getBackToList() {
        return backToList;
    }

    /**
     * @param backToList the backToList to set
     */
    public void setBackToList(Button backToList) {
        this.backToList = backToList;
    }

    /**
     * @return the nextCourse
     */
    public Button getNextCourse() {
        return nextCourse;
    }

    /**
     * @param nextCourse the nextCourse to set
     */
    public void setNextCourse(Button nextCourse) {
        this.nextCourse = nextCourse;
    }

    /**
     * @return the topPane
     */
    public GridPane getTopPane() {
        return topPane;
    }

    /**
     * @return the nextShowned
     */
    public Boolean getIsLast() {
        return nextShowned;
    }

    /**
     * @param isLast the nextShowned to set
     */
    public void setIsLast(Boolean isLast) {
        this.nextShowned = isLast;
    }

    /**
     * @return the bottomPane
     */
    public GridPane getBottomPane() {
        return bottomPane;
    }

    /**
     * @return the prevCourse
     */
    public Button getPrevCourse() {
        return prevCourse;
    }
}
