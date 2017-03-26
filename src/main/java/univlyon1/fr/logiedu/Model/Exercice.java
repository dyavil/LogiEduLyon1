/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.Model;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import univlyon1.fr.logiedu.Model.GameModel.Game;
import univlyon1.fr.logiedu.Utility.ExecUtility;

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
    private Boolean mandatory;
    private Boolean gotSources;
    
    public Exercice(String n, String content, Course c, int id, int diff, Boolean mand, Boolean src){
        this.name = n;
        this.correspondingCourse = c;
        this.difficulty = 0;
        this.id = id;
        this.content = content;
        this.difficulty = diff;
        this.progress = null;
        this.mandatory = mand;
        this.gotSources = src;
    }
    
    public Exercice(String n, String content, Course c, int id, int diff, Progress prog, Boolean mand, Boolean src){
        this(n, content, c, id, diff, mand, src);
        this.progress = prog;
    }
    
    public void CompileCode(){
       
        if(this.gotSources){
            try {
                File errFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                       +this.correspondingCourse.getReferingTheme().getId()+"/"
                       +this.correspondingCourse.getId()+"/"+this.getId()+"/errC.txt");
                errFile.createNewFile();
                OutputStream out = new FileOutputStream(errFile);
                ExecUtility.runProcess("javac "+System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/Main.java", out, out);
            } catch (Exception ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }
    public void ExecuteCode(){
        if(this.gotSources){
            try {
                File errFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                       +this.correspondingCourse.getReferingTheme().getId()+"/"
                       +this.correspondingCourse.getId()+"/"+this.getId()+"/errC.txt");
                errFile.createNewFile();
                OutputStream out = new FileOutputStream(errFile);
                ExecUtility.runProcess("java -cp "+System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+" Main", out, out);
            } catch (Exception ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
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

    /**
     * @return the progress
     */
    public Progress getProgress() {
        return progress;
    }

    /**
     * @param progress the progress to set
     */
    public void setProgress(Progress progress) {
        this.progress = progress;
    }

    /**
     * @return the mandatory
     */
    public Boolean getMandatory() {
        return mandatory;
    }
}
