/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
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
    private Boolean isTheme;
    private Boolean isExpanded;
    private int themeId;
    private int courseId;

    public CourseListItem(String cours) {
        this.lab = new Label(cours);
        this.css = "";
        this.isCourse = false;
        this.isTheme = false;
        this.isExpanded=false;
    }
    public CourseListItem(String cours, String css) {
        this.lab = new Label(cours);
        this.css = css;
        this.isCourse = false;
        this.isTheme = false;
        this.isExpanded=false;
    }
    
    public CourseListItem(String cours, String css, boolean isTh, int thId){
        this(cours, css);
        this.isTheme = isTh;
        this.themeId = thId;
    }
    
    public CourseListItem(String cours, String css, boolean isco, int thId, int coId) {
        this(cours, css);
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

    /**
     * @return the isTheme
     */
    public Boolean getIsTheme() {
        return isTheme;
    }

    /**
     * @return the isExpanded
     */
    public Boolean getIsExpanded() {
        return isExpanded;
    }

    /**
     * @param isExpanded the isExpanded to set
     */
    public void setIsExpanded(Boolean isExpanded) {
        this.isExpanded = isExpanded;
    }
    
}
