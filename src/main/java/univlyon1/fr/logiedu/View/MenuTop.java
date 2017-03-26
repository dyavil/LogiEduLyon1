/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.View;

import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

/**
 *
 * @author dyavil
 */
public class MenuTop extends MenuBar {
    private Menu fileMenu;
    private MenuItem addUser;
    private MenuItem exerciceLink;
    private MenuItem coursesLink;
    private Menu logout;
    private Label logoutLabel;
    
    public MenuTop(){
        this.fileMenu = new Menu("Fichier");
        this.addUser = new MenuItem("Ajouter utilisateur...");
        this.logout = new Menu();
        this.logoutLabel = new Label("Déconnexion");
        logout.setGraphic(this.logoutLabel);
        this.coursesLink = new MenuItem("Cours");
        this.exerciceLink = new MenuItem("Exercices");
        fileMenu.getItems().add(addUser);
        this.getMenus().add(fileMenu);
    }
    
    public void displayLoggedMenu(){
        this.getMenus().clear();
        fileMenu.getItems().clear();
        fileMenu.getItems().add(coursesLink);
        fileMenu.getItems().add(exerciceLink);
        this.getMenus().add(fileMenu);
        this.getMenus().add(logout);
    }
    
    public void displayStartMenu(){
        this.getMenus().clear();
        fileMenu.getItems().clear();
        fileMenu.getItems().add(addUser);
        this.getMenus().add(fileMenu);
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
     * @return the exerciceLink
     */
    public MenuItem getExerciceLink() {
        return exerciceLink;
    }

    /**
     * @return the coursesLink
     */
    public MenuItem getCoursesLink() {
        return coursesLink;
    }

    /**
     * @return the logout
     */
    public MenuItem getLogout() {
        return logout;
    }

    /**
     * @return the logoutLabel
     */
    public Label getLogoutLabel() {
        return logoutLabel;
    }
   
}
