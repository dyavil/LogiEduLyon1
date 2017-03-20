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
public class Course {
    private int id;
    private String title;
    private String imagePath;
    private Theme referingTheme;
    private ArrayList<Exercice> exercices;
    private ArrayList<Slide> slides;
    private Progress courseProgress;
    
    public Course(String t, Theme th, int d){
        this.title = t;
        this.id = d;
        this.referingTheme = th;
        this.exercices = new ArrayList();
        this.slides = new ArrayList<>();
        this.courseProgress = null;
        this.imagePath = null;
    }
    
    public Course(String t, Theme th, int d, Progress p){
        this(t, th, d);
        this.courseProgress = p;
    }
    
    public Course(String t, Theme th, int d, String imp){
        this(t, th, d);
        this.imagePath = imp;
    }
    
    public void addSlide(Slide s){
        this.slides.add(s);
    }
    
    public void addExercice(Exercice ex){
        this.exercices.add(ex);
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
     * @return the courseProgress
     */
    public Progress getCourseProgress() {
        return courseProgress;
    }

    /**
     * @param courseProgress the courseProgress to set
     */
    public void setCourseProgress(Progress courseProgress) {
        this.courseProgress = courseProgress;
    }

    /**
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the slides
     */
    public ArrayList<Slide> getSlides() {
        return slides;
    }

    /**
     * @param slides the slides to set
     */
    public void setSlides(ArrayList<Slide> slides) {
        this.slides = slides;
    }
}
