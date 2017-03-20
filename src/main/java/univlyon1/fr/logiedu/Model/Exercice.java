/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.Model;

import univlyon1.fr.logiedu.Model.GameModel.Game;

/**
 *
 * @author dyavil
 */
public class Exercice {
    private int id;
    private Course correspondingCourse;
    private String name;
    private String content;
    private int difficulty;
    private Game game;
    private Progress progress;
    
    public Exercice(String n, String content, Course c, int id){
        this.name = n;
        this.correspondingCourse = c;
        this.difficulty = 0;
        this.id = id;
        this.content = content;
    }

    /**
     * @return the correspondingCourse
     */
    public Course getCorrespondingCourse() {
        return correspondingCourse;
    }

    /**
     * @param correspondingCourse the correspondingCourse to set
     */
    public void setCorrespondingCourse(Course correspondingCourse) {
        this.correspondingCourse = correspondingCourse;
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
     * @return the difficulty
     */
    public int getDifficulty() {
        return difficulty;
    }

    /**
     * @param difficulty the difficulty to set
     */
    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return game;
    }
}
