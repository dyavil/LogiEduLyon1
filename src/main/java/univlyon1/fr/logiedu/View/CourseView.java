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
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.web.WebView;

/**
 *
 * @author dyavil
 */
public class CourseView extends GridPane {
    private GridPane topPane;
    private GridPane bottomPane;
    private GridPane bottomPaneTop;
    private GridPane bottomPaneBottom;
    private GridPane middlePane;
    private WebView contentHtml;
    private Label themeName;
    private Label courseName;
    private Label courseContent;
    private Button homeButton;
    private Button nextCourse;
    private Button nextSlide;
    private Button prevCourse;
    private Button prevSlide;
    private Button exercices;
    private Boolean nextShowned;
    private Boolean prevShowned;
    private Boolean nextSlideShowned;
    private Boolean prevSlideShowned;
    private ImageView image;
    
    public CourseView(String theme, String name, String content, int parentWidth){
        this.topPane = new GridPane();
        this.middlePane = new GridPane();
        this.image = null;
        this.bottomPane = new GridPane();
        this.bottomPaneTop = new GridPane();
        this.bottomPaneBottom = new GridPane();
        this.courseName = new Label(name);
        this.themeName = new Label(theme);
        this.themeName.getStyleClass().add("theme-label");
        this.courseName.getStyleClass().add("course-label");
        this.courseContent = new Label(content);
        this.courseContent.getStyleClass().add("course-content");
        this.courseContent.setWrapText(true);
        contentHtml = new WebView();
        contentHtml.getEngine().loadContent("<body style='font-size:15px; font-family:\"Helvetica\";'>"+content+"</body>", "text/html");
        this.homeButton = new Button();
        this.nextCourse = new Button("Cours suivant");
        this.nextSlide = new Button("Suivant");
        this.prevCourse = new Button("Cours precedent");
        this.prevSlide = new Button("Precedent");
        this.exercices = new Button("Exercices");
        this.homeButton.getStyleClass().add("toggle-button");
        this.nextCourse.getStyleClass().add("dark-blue");
        this.prevCourse.getStyleClass().add("dark-blue");
        this.nextSlide.getStyleClass().add("bevel-grey");
        this.prevSlide.getStyleClass().add("bevel-grey");
        this.homeButton.setAlignment(Pos.BASELINE_RIGHT);
        this.getRowConstraints().add(new RowConstraints(20));
        this.getRowConstraints().add(new RowConstraints(60));
        this.getRowConstraints().add(new RowConstraints(50));
        this.getRowConstraints().add(new RowConstraints(340));
        this.getColumnConstraints().add(new ColumnConstraints(30));
        ColumnConstraints colc = new ColumnConstraints(parentWidth-60);
        colc.setHalignment(HPos.RIGHT);
        this.getColumnConstraints().add(colc);
        this.getColumnConstraints().add(new ColumnConstraints(30));
        ColumnConstraints topCol1 = new ColumnConstraints((parentWidth-60)/2);
        ColumnConstraints topCol2 = new ColumnConstraints((parentWidth-60)/2);
        topCol1.setHalignment(HPos.LEFT);
        topCol2.setHalignment(HPos.RIGHT);
        
        ColumnConstraints topCol13 = new ColumnConstraints((parentWidth-60)/3);
        ColumnConstraints topCol23 = new ColumnConstraints((parentWidth-60)/3);
        ColumnConstraints topCol33 = new ColumnConstraints((parentWidth-60)/3);
        topCol13.setHalignment(HPos.LEFT);
        topCol23.setHalignment(HPos.CENTER);
        topCol33.setHalignment(HPos.RIGHT);
        
        this.topPane.getColumnConstraints().add(topCol13);
        this.bottomPaneTop.getColumnConstraints().add(topCol13);
        this.bottomPaneBottom.getColumnConstraints().add(topCol1);
        this.topPane.getColumnConstraints().add(topCol23);
        this.topPane.getColumnConstraints().add(topCol33);
        this.bottomPaneTop.getColumnConstraints().add(topCol23);
        this.bottomPaneTop.getColumnConstraints().add(topCol33);
        this.bottomPaneBottom.getColumnConstraints().add(topCol2);
        this.topPane.add(this.themeName, 1, 0);
        this.topPane.add(this.homeButton, 2, 0);
        
        this.middlePane.getColumnConstraints().add(new ColumnConstraints(parentWidth-60));
        this.middlePane.add(this.contentHtml, 0, 0);
        this.bottomPaneTop.add(this.exercices, 1, 0);
        this.bottomPane.getRowConstraints().add(new RowConstraints(40));
        this.bottomPane.getRowConstraints().add(new RowConstraints(10));
        this.bottomPane.add(this.bottomPaneTop, 0, 2);
        this.bottomPane.add(this.bottomPaneBottom, 0, 0);
        this.nextShowned = false;
        this.prevShowned = false;
        this.nextSlideShowned = false;
        this.prevSlideShowned = false;
        this.add(this.topPane, 1, 1);
        this.add(this.courseName, 1, 2);
        this.add(this.middlePane, 1, 3);
        this.add(this.bottomPane, 1, 4);
    }
    
    public CourseView(String th, String name, String content, int parentWidth, String imPath){
        this(th, name, content, parentWidth);
        this.middlePane.getChildren().clear();
        this.middlePane.getColumnConstraints().clear();
        this.image = new ImageView(imPath);
        this.image.setFitHeight(340);
        this.image.setPreserveRatio(true);
        ColumnConstraints topCol1 = new ColumnConstraints((parentWidth-60)/2-100);
        topCol1.setHalignment(HPos.CENTER);
        ColumnConstraints topCol2 = new ColumnConstraints((parentWidth-60)/2+100);
        this.middlePane.getColumnConstraints().add(topCol1);
        this.middlePane.getColumnConstraints().add(topCol2);
        this.middlePane.add(this.image, 0, 0);
        this.middlePane.add(this.contentHtml, 1, 0);
    }
    
    public void switchToNextTheme(){
        this.nextCourse.getStyleClass().clear();
        this.nextCourse.getStyleClass().add("dark-blue-th");
        this.nextCourse.setText("Sujet suivant");
    }
    
    public void switchToNextCours(){
        this.nextCourse.getStyleClass().clear();
        this.nextCourse.getStyleClass().add("dark-blue");
        this.nextCourse.setText("Cours suivant");
    }
    
    public void switchToPrevTheme(){
        this.prevCourse.getStyleClass().clear();
        this.prevCourse.getStyleClass().add("dark-blue-th");
        this.prevCourse.setText("Sujet precedent");
    }
    
    public void switchToPrevCours(){
        this.prevCourse.getStyleClass().clear();
        this.prevCourse.getStyleClass().add("dark-blue");
        this.prevCourse.setText("Cours precedent");
    }

    public void showNextButton(){
        if(!this.getNextShowned()) {
            this.bottomPaneTop.add(this.nextCourse, 2, 0);
            this.setNextShowned((Boolean) true);
        }
    }
    
    public void hideNextButton(){
        if(getNextShowned()){
            this.bottomPaneTop.getChildren().remove(this.nextCourse);
            this.setNextShowned((Boolean) false);
        }
        
    }
    
    public void showPrevButton(){
        if(!this.getPrevShowned()) {
            this.bottomPaneTop.add(this.getPrevCourse(), 0, 0);
            this.setPrevShowned((Boolean) true);
        }
    }
    
    public void hidePrevButton(){
        if(getPrevShowned()){
            this.bottomPaneTop.getChildren().remove(this.getPrevCourse());
            this.setPrevShowned((Boolean) false);
        }
        
    }
    
    public void showNextSlideButton(){
        if(!this.getNextSlideShowned()) {
            this.bottomPaneBottom.add(this.getNextSlide(), 1, 0);
            this.nextSlideShowned = true;
        }
    }
    
    public void hideNextSlideButton(){
        if(getNextSlideShowned()){
            this.bottomPaneBottom.getChildren().remove(this.getNextSlide());
            this.nextSlideShowned = false;
        }
        
    }
    
    public void showPrevSlideButton(){
        if(!this.getPrevSlideShowned()) {
            this.bottomPaneBottom.add(this.getPrevSlide(), 0, 0);
            this.prevSlideShowned = true;
        }
    }
    
    public void hidePrevSlideButton(){
        if(getPrevSlideShowned()){
            this.bottomPaneBottom.getChildren().remove(this.getPrevSlide());
            this.prevSlideShowned = false;
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
        return getNextShowned();
    }

    /**
     * @param isLast the nextShowned to set
     */
    public void setIsLast(Boolean isLast) {
        this.setNextShowned(isLast);
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

    /**
     * @return the middlePane
     */
    public GridPane getMiddlePane() {
        return middlePane;
    }

    /**
     * @return the nextSlide
     */
    public Button getNextSlide() {
        return nextSlide;
    }

    /**
     * @return the prevSlide
     */
    public Button getPrevSlide() {
        return prevSlide;
    }

    /**
     * @return the nextShowned
     */
    public Boolean getNextShowned() {
        return nextShowned;
    }

    /**
     * @param nextShowned the nextShowned to set
     */
    public void setNextShowned(Boolean nextShowned) {
        this.nextShowned = nextShowned;
    }

    /**
     * @return the prevShowned
     */
    public Boolean getPrevShowned() {
        return prevShowned;
    }

    /**
     * @param prevShowned the prevShowned to set
     */
    public void setPrevShowned(Boolean prevShowned) {
        this.prevShowned = prevShowned;
    }

    /**
     * @return the nextSlideShowned
     */
    public Boolean getNextSlideShowned() {
        return nextSlideShowned;
    }

    /**
     * @return the prevSlideShowned
     */
    public Boolean getPrevSlideShowned() {
        return prevSlideShowned;
    }

    /**
     * @return the image
     */
    public ImageView getImage() {
        return image;
    }

    /**
     * @return the themeName
     */
    public Label getThemeName() {
        return themeName;
    }

    /**
     * @return the exercices
     */
    public Button getExercices() {
        return exercices;
    }
}
