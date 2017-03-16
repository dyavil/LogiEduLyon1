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
class Course {
    protected String title;
    protected Theme referingTheme;
    protected ArrayList<Exercice> exercices;
    
    public Course(String t, Theme th){
        this.title = t;
        this.referingTheme = th;
        this.exercices = new ArrayList();
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the referingTheme
     */
    public Theme getReferingTheme() {
        return referingTheme;
    }

    /**
     * @param referingTheme the referingTheme to set
     */
    public void setReferingTheme(Theme referingTheme) {
        this.referingTheme = referingTheme;
    }

    /**
     * @return the exercices
     */
    public ArrayList<Exercice> getExercices() {
        return exercices;
    }

    /**
     * @param exercices the exercices to set
     */
    public void setExercices(ArrayList<Exercice> exercices) {
        this.exercices = exercices;
    }
}
