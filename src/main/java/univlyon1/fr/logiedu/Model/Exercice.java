/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.Model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;
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
    
    public String getSourceContent(){
        if(this.gotSources){
            try {
                String content = new Scanner(new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/Main.java")).useDelimiter("\\Z").next();
                return content;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return null;
    }
    
    public Boolean CompileCode(){
       
        if(this.getGotSources()){
            try {
                File stdFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                       +this.correspondingCourse.getReferingTheme().getId()+"/"
                       +this.correspondingCourse.getId()+"/"+this.getId()+"/stdC.txt");
                stdFile.createNewFile();
                OutputStream out = new FileOutputStream(stdFile);
                File errFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                       +this.correspondingCourse.getReferingTheme().getId()+"/"
                       +this.correspondingCourse.getId()+"/"+this.getId()+"/errC.txt");
                errFile.createNewFile();
                OutputStream errout = new FileOutputStream(errFile);
                Boolean res = ExecUtility.runProcess("javac "+System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/Main.java", errout, out);
                return res;
            } catch (Exception ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public void ExecuteCode(){
        if(this.getGotSources()){
            try {
                File stdFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                       +this.correspondingCourse.getReferingTheme().getId()+"/"
                       +this.correspondingCourse.getId()+"/"+this.getId()+"/stdC.txt");
                stdFile.createNewFile();
                OutputStream out = new FileOutputStream(stdFile);
                File errFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                       +this.correspondingCourse.getReferingTheme().getId()+"/"
                       +this.correspondingCourse.getId()+"/"+this.getId()+"/errC.txt");
                errFile.createNewFile();
                OutputStream errout = new FileOutputStream(errFile);
                ExecUtility.runProcess("java -cp "+System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+" Main", errout, out);
            } catch (Exception ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String gotStdExecutionRes(){
        if(this.getGotSources()){
            try {
                String content = new Scanner(new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/stdC.txt")).useDelimiter("\\Z").next();
                return content;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.WARNING, null, ex);
                return "no output";
            }
        }
        return null;
    }
    
    public String gotErrExecutionRes(){
        if(this.getGotSources()){
            try {
                String content = new Scanner(new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/errC.txt")).useDelimiter("\\Z").next();
                return content;
            } catch (Exception ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.WARNING, null, ex);
                return "no error";
            }
        }
        return null;
    }
    
    public void writeToFile(String content){
        if(this.getGotSources()){
            FileWriter fw = null;
            try {
                File srcFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/Main.java");
                fw = new FileWriter(srcFile);
                fw.write(content);
            } catch (IOException ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fw.close();
                } catch (IOException ex) {
                    Logger.getLogger(Exercice.class.getName()).log(Level.SEVERE, null, ex);
                }
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

    /**
     * @return the gotSources
     */
    public Boolean getGotSources() {
        return gotSources;
    }
}
