/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.Model;

import java.util.ArrayList;

/**
 *
 * @author dyavil
 */
public class App {
    private ArrayList<User> users;
    private LoadData data;
    private ArrayList<Theme> themes;
    private User LoggedUser;
    
    public App(){
        this.data = new LoadData();
        this.users = this.data.loadUserList();
        this.themes = this.data.loadThemeList();
    }

    /**
     * @return the users
     */
    public ArrayList<User> getUsers() {
        return users;
    }
    
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
    }
    
    /**
     * @param LoggedUser the LoggedUser to set
     */
    public void setLoggedUser(int id) {
        this.LoggedUser = this.getUser(id);
    }
}
