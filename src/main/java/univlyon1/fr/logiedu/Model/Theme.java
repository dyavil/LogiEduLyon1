/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.Model;

import java.util.ArrayList;

/**
 *
 * @author dyavil
 */
public class Theme {
    private int id;
    private String name;
    private ArrayList<Theme> neededThemes;
    private ArrayList<Course> courseList;
    private ArrayList<Exercice> exerciceList;
    private Progress themeProgress;
    
    public Theme(String n, int i){
        this.id = i;
        this.name = n;
        this.courseList = new ArrayList();
        this.neededThemes = new ArrayList();
        this.themeProgress = null;
    }
    
    public Theme(String n, int i, Progress p){
        this.id = i;
        this.name = n;
        this.courseList = new ArrayList();
        this.neededThemes = new ArrayList();
        this.themeProgress = p;
    }
    
    
    public void addCourse(Course c){
        this.courseList.add(c);
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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the themeProgress
     */
    public Progress getThemeProgress() {
        return themeProgress;
    }

    /**
     * @param themeProgress the themeProgress to set
     */
    public void setThemeProgress(Progress themeProgress) {
        this.themeProgress = themeProgress;
    }
}
