/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.View;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

/**
 *
 * @author dyavil
 */
public class CourseListItem extends HBox {
    private Label lab;
    private String css;
    private Boolean isCourse;
    private int themeId;
    private int courseId;

    public CourseListItem(String cours) {
        this.lab = new Label(cours);
        this.css = "";
        this.isCourse = false;
    }
    public CourseListItem(String cours, String css) {
        this.lab = new Label(cours);
        this.css = css;
        this.isCourse = false;
    }
    public CourseListItem(String cours, String css, boolean isco, int thId, int coId) {
        this.lab = new Label(cours);
        this.css = css;
        this.isCourse = isco;
        this.themeId=thId;
        this.courseId = coId;
    }

    /**
     * @return the lab
     */
    public Label getLab() {
        return lab;
    }

    /**
     * @param lab the lab to set
     */
    public void setLab(Label lab) {
        this.lab = lab;
    }

    /**
     * @return the css
     */
    public String getCss() {
        return css;
    }

    /**
     * @param css the css to set
     */
    public void setCss(String css) {
        this.css = css;
    }

    /**
     * @return the isCourse
     */
    public Boolean getIsCourse() {
        return isCourse;
    }

    /**
     * @param isCourse the isCourse to set
     */
    public void setIsCourse(Boolean isCourse) {
        this.isCourse = isCourse;
    }

    /**
     * @return the themeId
     */
    public int getThemeId() {
        return themeId;
    }

    /**
     * @return the courseId
     */
    public int getCourseId() {
        return courseId;
    }
    
}
