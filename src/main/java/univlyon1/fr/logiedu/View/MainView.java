/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.View;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author dyavil
 */
public class MainView extends GridPane implements Observer {
    
    private Group root;
    private MenuTop head;
    private GridPane headPane;
    private GridPane center;
    private GridPane bottomPane;
    private Button addButton;
    private LoggedPane homePane;
    private CoursesPane coursePane;
    private ExercicesPane exercicePane;
    private int width;
    private int height;
    private ArrayList<UserPane> usersPane;
    private ChoiceBox otherUsersList;
    private Button otherUsersListLog;
    private Label nameLabel;
    
    public MainView(int w, int h){
        this.width = w;
        this.height = h;
        this.setPrefSize(w, h);
        this.setWidth(w);
        this.setHeight(h);
        homePane = new LoggedPane("", this.getTWidth(), this.getTHeight());
        coursePane = new CoursesPane(this.getTWidth());
        exercicePane = new ExercicesPane(this.getTWidth());
        root = new Group();
        Scene scene = new Scene(getRoot());
        scene.getStylesheets().add("/styles/Styles.css");
        head = new MenuTop();
        this.nameLabel = new Label("");
        this.nameLabel.getStyleClass().add("top-name-label");
        center = new GridPane();
        headPane = new GridPane();
        bottomPane = new GridPane();
        addButton = new Button();
        addButton.getStyleClass().add("plus-button");
        otherUsersListLog = new Button("Go !");
        otherUsersListLog.setId("green");
        otherUsersListLog.setPrefWidth(80);
        otherUsersListLog.setAlignment(Pos.CENTER);
        ColumnConstraints colCo = new ColumnConstraints(this.width);
        colCo.setHalignment(HPos.CENTER);
        headPane.getColumnConstraints().add(colCo);
        usersPane = new ArrayList<>();
        otherUsersList = new ChoiceBox();
        otherUsersList.getStyleClass().add("older-user");
        otherUsersList.setPrefWidth(80);
        this.setFocusTraversable(true);
        root.getChildren().add(this);
        this.headPane.add(head, 0, 0);
        this.headPane.add(nameLabel, 0, 1);
        this.add(headPane, 0, 0);
        this.add(center, 0, 2);
        this.add(bottomPane, 0, 3);
        this.getRowConstraints().add(new RowConstraints());
        
        bottomPane.getColumnConstraints().add(new ColumnConstraints(10));
        bottomPane.getRowConstraints().add(new RowConstraints(40));
        ColumnConstraints col = new ColumnConstraints((this.getWidth()-20));
        col.setHalignment(HPos.CENTER);
        bottomPane.getColumnConstraints().add(col);
    }
    
    public void addUserPane(UserPane usp){
        usp.setAlignment(Pos.CENTER);
        getUsersPane().add(usp);
    }
    
    public void displayUsersPane(Boolean more){
        int col = 1;
        clearPanes();
        getCenter().getRowConstraints().add(new RowConstraints(100));
        getCenter().getColumnConstraints().add(new ColumnConstraints(10));
        int size = this.usersPane.size();
        if(more) size++;
        for(UserPane usp : getUsersPane()){
            getCenter().getColumnConstraints().add(new ColumnConstraints(((this.getWidth()-20)/size)));
            getCenter().add(usp, col, 1);
            col++;
        }
        if(more){
            GridPane oldUs = new GridPane();
            oldUs.add(this.otherUsersList, 1, 0);
            oldUs.getRowConstraints().add(new RowConstraints(30));
            oldUs.getColumnConstraints().add(new ColumnConstraints(20));
            oldUs.add(this.getOtherUsersListLog(), 1, 1);
            getCenter().getColumnConstraints().add(new ColumnConstraints(((this.getWidth()-20)/size)));
            getCenter().add(oldUs, col, 1);
        }
        
        
        bottomPane.add(getAddButton(), 1, 1);
        getCenter().getColumnConstraints().add(new ColumnConstraints(10));
        this.getNameLabel().setText("");
        this.head.displayStartMenu();
    }
    
    public void displayHomePane(String loggedUser){
        clearPanes();
        getCenter().getRowConstraints().add(new RowConstraints(100));
        this.center.add(this.homePane, 0, 1);
        this.getNameLabel().setText(loggedUser);
        this.head.displayLoggedMenu();
    }
    
    public void displayCoursesPane(){
        clearPanes();
        this.center.add(this.coursePane, 0, 0);
        this.head.displayLoggedMenu();
    }
    
    public void displayExercicesPane(){
        clearPanes();
        this.center.add(this.exercicePane, 0, 0);
        this.head.displayLoggedMenu();
    }
    
    public void displayCourseView(CourseView cv){
        clearPanes();
        this.center.add(cv, 0, 0);
        this.head.displayLoggedMenu();
    }
    public void displayExerciceView(ExerciceView exv){
        clearPanes();
        this.center.add(exv, 0, 0);
        this.head.displayLoggedMenu();
    }
    
    public void displayExerciceGridView(ExerciceGridView exv){
        clearPanes();
        this.center.add(exv, 0, 0);
        this.head.displayLoggedMenu();
    }
    
    private void clearPanes(){
        getCenter().getChildren().clear();
        bottomPane.getChildren().clear();
        getCenter().getColumnConstraints().clear();
        getCenter().getRowConstraints().clear();
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @return the root
     */
    public Group getRoot() {
        return root;
    }

    /**
     * @return the head
     */
    public MenuTop getHead() {
        return head;
    }

    /**
     * @return the usersPane
     */
    public ArrayList<UserPane> getUsersPane() {
        return usersPane;
    }


    /**
     * @return the center
     */
    public GridPane getCenter() {
        return center;
    }

    /**
     * @return the homePane
     */
    public LoggedPane getHomePane() {
        return homePane;
    }

    /**
     * @return the coursePane
     */
    public CoursesPane getCoursePane() {
        return coursePane;
    }

    /**
     * @return the exercicePane
     */
    public ExercicesPane getExercicePane() {
        return exercicePane;
    }

    /**
     * @return the width
     */
    public int getTWidth() {
        return width;
    }

    /**
     * @return the height
     */
    public int getTHeight() {
        return height;
    }

    /**
     * @return the addButton
     */
    public Button getAddButton() {
        return addButton;
    }

    /**
     * @return the nameLabel
     */
    public Label getNameLabel() {
        return nameLabel;
    }

    /**
     * @return the otherUsersList
     */
    public ChoiceBox getOtherUsersList() {
        return otherUsersList;
    }

    /**
     * @return the otherUsersListLog
     */
    public Button getOtherUsersListLog() {
        return otherUsersListLog;
    }
    
}
