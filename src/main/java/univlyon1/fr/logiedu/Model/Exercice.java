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
import univlyon1.fr.logiedu.Utility.ErrParser;
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
    private String compileLog;
    private String executeLog;
    private String expectedOutput;
    
    public Exercice(String n, String content, Course c, int id, int diff, Boolean mand, Boolean src, String expOut){
        this.name = n;
        this.correspondingCourse = c;
        this.difficulty = 0;
        this.id = id;
        this.content = content;
        this.difficulty = diff;
        this.progress = null;
        this.mandatory = mand;
        this.gotSources = src;
        this.compileLog = "";
        this.expectedOutput = expOut;
    }
    
    public Exercice(String n, String content, Course c, int id, int diff, Progress prog, Boolean mand, Boolean src, String expOut){
        this(n, content, c, id, diff, mand, src, expOut);
        this.progress = prog;
    }
    
    public String getSourceContent(User us){
        if(this.gotSources){
            String folder = "base";
            try {
                File srcFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/user/"+us.getUserName()+"/Main.java");
                if(srcFile.exists()) folder = "user/"+us.getUserName();
                String content = new Scanner(new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/"+folder+"/Main.java")).useDelimiter("\\Z").next();
                return content;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        return null;
    }
    
    public Boolean CompileCode(User us){
       
        if(this.getGotSources()){
            try {
                File stdFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                       +this.correspondingCourse.getReferingTheme().getId()+"/"
                       +this.correspondingCourse.getId()+"/"+this.getId()+"/user/"+us.getUserName()+"/stdC.txt");
                stdFile.createNewFile();
                OutputStream out = new FileOutputStream(stdFile);
                File errFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                       +this.correspondingCourse.getReferingTheme().getId()+"/"
                       +this.correspondingCourse.getId()+"/"+this.getId()+"/user/"+us.getUserName()+"/errC.txt");
                errFile.createNewFile();
                OutputStream errout = new FileOutputStream(errFile);
                Boolean res = ExecUtility.runProcess("javac "+System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/user/"+us.getUserName()+"/Main.java", errout, out);
                return res;
            } catch (Exception ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    public void ExecuteCode(User us){
        if(this.getGotSources()){
            try {
                File stdFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                       +this.correspondingCourse.getReferingTheme().getId()+"/"
                       +this.correspondingCourse.getId()+"/"+this.getId()+"/user/"+us.getUserName()+"/stdC.txt");
                stdFile.createNewFile();
                OutputStream out = new FileOutputStream(stdFile);
                File errFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                       +this.correspondingCourse.getReferingTheme().getId()+"/"
                       +this.correspondingCourse.getId()+"/"+this.getId()+"/user/"+us.getUserName()+"/errC.txt");
                errFile.createNewFile();
                OutputStream errout = new FileOutputStream(errFile);
                ExecUtility.runProcess("java -cp "+System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/user/"+us.getUserName()+"/ Main", errout, out);
            } catch (Exception ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public String gotStdExecutionRes(User us){
        if(this.getGotSources()){
            String content2 = "";
            try {
                content2 = new Scanner(new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/user/"+us.getUserName()+"/errC.txt")).useDelimiter("\\Z").next();
            } catch (Exception ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.SEVERE, null, ex);
            }
                executeLog = ErrParser.getExecuteTypeOutput(content2);
            try {
                String content = new Scanner(new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/user/"+us.getUserName()+"/stdC.txt")).useDelimiter("\\Z").next();
                
                return content;
            } catch (Exception ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.WARNING, null, ex);
                return "";
            }
        }
        return null;
    }
    
    public String gotErrExecutionRes(int type, User us){
        if(this.getGotSources()){
            try {
                String content = new Scanner(new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/user/"+us.getUserName()+"/errC.txt")).useDelimiter("\\Z").next();
                compileLog = ErrParser.getCompileTypeOutput(content);
                if(type == 1) return content.split("Main.java")[1];
                return content;
            } catch (Exception ex) {
                Logger.getLogger(Exercice.class.getName()).log(Level.WARNING, null, ex);
                return "no error";
            }
        }
        return null;
    }
    
    public void writeToFile(String content, User us){
        if(this.getGotSources()){
            FileWriter fw = null;
            try {
                File srcFile = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"
                        +this.correspondingCourse.getReferingTheme().getId()+"/"
                        +this.correspondingCourse.getId()+"/"+this.getId()+"/user/"+us.getUserName()+"/Main.java");
                if(! srcFile.exists()) srcFile.createNewFile();
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
    
    
    public boolean compareResult(String in){
        if(in.toLowerCase().replaceAll("\n", "").replaceAll(" ", "").equals(this.expectedOutput.replaceAll("\n", "").replaceAll(" ", "").toLowerCase())){
            return true;
        }
        if(in.toLowerCase().replaceAll("\n", "").replaceAll(" ", "").contains(this.expectedOutput.replaceAll("\n", "").replaceAll(" ", "").toLowerCase())) return true;
        return false;
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

    /**
     * @return the compileLog
     */
    public String getCompileLog() {
        return compileLog;
    }

    /**
     * @return the executeLog
     */
    public String getExecuteLog() {
        return executeLog;
    }

    /**
     * @return the expectedOutput
     */
    public String getExpectedOutput() {
        return expectedOutput;
    }
}
