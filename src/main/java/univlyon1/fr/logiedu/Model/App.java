/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.Model;

import java.util.ArrayList;

/**
 * Class which represent the model
 * @author dyavil
 */
public class App {
    private ArrayList<User> users;
    private LoadData data;
    private ArrayList<Theme> themes;
    private User LoggedUser;
    
    
    /**
     * Basic constructor
     * Init attributes
     */
    public App(){
        this.data = new LoadData();
        this.users = this.data.loadUserList();
        this.themes = this.data.loadThemeList();
    }
    
    
    
    /**
     * Get the content of a course as a string
     * @param c course searched
     * @return content as String
     */
    public String getCourseContent(Course c){
        return this.data.getCourseContent(c);
    }
    
    /**
     * Load the content (slides) of a specific course
     * @param c course to be loaded
     */
    public void loadCourseContent(Course c){
        c.setSlides(data.getCourseSlides(c));
    }
    
    /**
     * If a user is logged, load it's progress
     */
    public void loadProgress(){
        if(this.LoggedUser != null){
            this.data.loadUserProgress(LoggedUser, themes);
        }
    }

    /**
     * Persist the course data
     * @param c course to be saved
     */
    public void updateCourse(Course c){
        this.data.saveCourseProgress(c, LoggedUser);
    }
    
    /**
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }
    
    /**
     * Get the User with the corresponding id
     * @param id id of the looked user
     * @return searched user (null if not found)
     */
    public User getUser(int id){
        for(User us : users){
            if(us.getId() == id) return us;
        }
        return null;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
    
    /**
     * Add a new user to th model
     * @param ps username of the user to be added
     * @return the created User
     */
    public User addUser(String ps){
        User us = new User(ps);
        this.data.saveNewUser(us);
        this.users.add(us);
        return us;
    }

    /**
     * @return the data
     */
    public LoadData getData() {
        return data;
    }

    /**
     * @param data the data to set
     */
    public void setData(LoadData data) {
        this.data = data;
    }

    /**
     * @return the themes
     */
    public ArrayList<Theme> getThemes() {
        return themes;
    }

    /**
     * @param themes the themes to set
     */
    public void setThemes(ArrayList<Theme> themes) {
        this.themes = themes;
    }

    /**
     * @return the LoggedUser
     */
    public User getLoggedUser() {
        return LoggedUser;
    }

    /**
     * @param LoggedUser the LoggedUser to set
     */
    public void setLoggedUser(User LoggedUser) {
        this.LoggedUser = LoggedUser;
        this.loadProgress();
    }
    
    /**
     * @param id of the user to be set
     */
    public void setLoggedUser(int id) {
        this.LoggedUser = this.getUser(id);
        this.loadProgress();
    }
}
