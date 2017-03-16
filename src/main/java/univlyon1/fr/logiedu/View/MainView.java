/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.View;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

/**
 *
 * @author dyavil
 */
public class MainView extends GridPane implements Observer {
    
    private Group root;
    private MenuBar head;
    private Menu fileMenu;
    private MenuItem addUser;
    private GridPane center;
    private int width;
    private int height;
    private ArrayList<UserPane> usersPane;
    
    public MainView(int w, int h){
        this.width = w;
        this.height = h;
        this.setPrefSize(w, h);
        root = new Group();
        Scene scene = new Scene(getRoot());
        scene.getStylesheets().add("/styles/Styles.css");
        head = new MenuBar();
        fileMenu = new Menu("Fichier");
        addUser = new MenuItem("Ajouter Utilisateur...");
        fileMenu.getItems().add(addUser);
        head.getMenus().add(fileMenu);
        center = new GridPane();
        usersPane = new ArrayList<>();
        this.setFocusTraversable(true);
        root.getChildren().add(this);
        this.add(head, 0, 0);
        this.add(center, 0, 2);
        this.getRowConstraints().add(new RowConstraints());
        this.getRowConstraints().add(new RowConstraints(100));
    }
    
    public void addUserPane(UserPane usp){
        usp.setAlignment(Pos.CENTER);
        getUsersPane().add(usp);
    }
    
    public void displayUsersPane(){
        int line = 1;
        getCenter().getChildren().clear();
        getCenter().getColumnConstraints().clear();
        getCenter().getColumnConstraints().add(new ColumnConstraints(10));
        for(UserPane usp : getUsersPane()){
            getCenter().getColumnConstraints().add(new ColumnConstraints(((this.width-20)/this.usersPane.size())));
            getCenter().add(usp, line, 0);
            line++;
        }
        getCenter().getColumnConstraints().add(new ColumnConstraints(10));
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
    public MenuBar getHead() {
        return head;
    }

    /**
     * @return the usersPane
     */
    public ArrayList<UserPane> getUsersPane() {
        return usersPane;
    }

    /**
     * @return the fileMenu
     */
    public Menu getFileMenu() {
        return fileMenu;
    }

    /**
     * @return the addUser
     */
    public MenuItem getAddUser() {
        return addUser;
    }

    /**
     * @return the center
     */
    public GridPane getCenter() {
        return center;
    }
    
}
