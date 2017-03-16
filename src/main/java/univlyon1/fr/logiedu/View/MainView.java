/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.View;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 *
 * @author dyavil
 */
public class MainView extends GridPane implements Observer {
    
    private Group root;
    private GridPane head;
    private ArrayList<UserPane> usersPane;
    
    public MainView(int w, int h){
        this.setPrefSize(w, h+60);
        root = new Group();
        Scene scene = new Scene(root);
        head = new GridPane();
        usersPane = new ArrayList<>();
        this.setFocusTraversable(true);
        root.getChildren().add(this);
        this.getColumnConstraints().add(new ColumnConstraints(10));
    }
    
    public void addUserPane(UserPane usp){
        usersPane.add(usp);
    }
    
    public void displayUsersPane(){
        int line = 1;
        this.getChildren().clear();
        this.getColumnConstraints().add(new ColumnConstraints(80));
        for(UserPane usp : usersPane){
            this.add(usp, line, 0);
            line++;
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
