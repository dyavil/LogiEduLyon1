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
public class Theme {
    private String name;
    private ArrayList<Theme> neededThemes;
    private ArrayList<Course> courseList;
    private ArrayList<Exercice> exerciceList;
    
    public Theme(String n){
        this.name = n;
        this.courseList = new ArrayList();
        this.neededThemes = new ArrayList();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the neededThemes
     */
    public ArrayList<Theme> getNeededThemes() {
        return neededThemes;
    }

    /**
     * @param neededThemes the neededThemes to set
     */
    public void setNeededThemes(ArrayList<Theme> neededThemes) {
        this.neededThemes = neededThemes;
    }

    /**
     * @return the courseList
     */
    public ArrayList<Course> getCourseList() {
        return courseList;
    }

    /**
     * @param courseList the courseList to set
     */
    public void setCourseList(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    /**
     * @return the exerciceList
     */
    public ArrayList<Exercice> getExerciceList() {
        return exerciceList;
    }

    /**
     * @param exerciceList the exerciceList to set
     */
    public void setExerciceList(ArrayList<Exercice> exerciceList) {
        this.exerciceList = exerciceList;
    }
}
