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
public class ExerciceListItem extends HBox {
    private Label lab;
    private String css;
    private Boolean isExercice;
    private Boolean isCourse;
    private int themeId;
    private int courseId;
    private int exerciceId;

    public ExerciceListItem(String exercice) {
        this.lab = new Label(exercice);
        this.css = "";
        this.isExercice = false;
        this.isCourse = false;
    }
    public ExerciceListItem(String exercice, String css) {
        this.lab = new Label(exercice);
        this.css = css;
        this.isExercice = false;
        this.isCourse = false;
    }
    public ExerciceListItem(String exercice, String css, boolean isco, int thId, int coId) {
        this.lab = new Label(exercice);
        this.css = css;
        this.isCourse = isco;
        this.isExercice = false;
        this.themeId=thId;
        this.courseId = coId;
    }
    public ExerciceListItem(String exercice, String css, boolean isex, int thId, int coId, int exId) {
        this.lab = new Label(exercice);
        this.css = css;
        this.isExercice = isex;
        this.isCourse = false;
        this.themeId=thId;
        this.exerciceId = exId;
        this.courseId = coId;
    }

    /**
     * @return the lab
     */
    public Label getLab() {
        return lab;
    }

    /**
     * @return the css
     */
    public String getCss() {
        return css;
    }

    /**
     * @return the isExercice
     */
    public Boolean getIsExercice() {
        return isExercice;
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

    /**
     * @return the exerciceId
     */
    public int getExerciceId() {
        return exerciceId;
    }

    /**
     * @return the isCourse
     */
    public Boolean getIsCourse() {
        return isCourse;
    }
}
