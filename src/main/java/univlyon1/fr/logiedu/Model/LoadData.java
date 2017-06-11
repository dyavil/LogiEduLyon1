/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import univlyon1.fr.logiedu.Utility.UnzipUtility;

/**
 * Class which handle courses and config json files
 * Transform raw data into class objects
 * @author dyavil
 */
public class LoadData {
    
    private File configFile;
    private JSONObject baseConfig;
    
    //static content
    private JSONObject baseThemes;
    private JSONArray themeList;
    
    //progress content
    private JSONArray userList;
    
    /**
     * Constructor,
     * Set up the files on first launch
     * Load them otherwise
     */
    public LoadData(){
        FileReader in;
        FileWriter out;
        try {
            File configDir = new File(System.getProperty("user.home")+"/LogiEdu");
            configFile = new File(System.getProperty("user.home")+"/LogiEdu/config.json");
            if(!configDir.exists()) configDir.mkdirs();
            if(!configFile.exists()){
                
                InputStream f = getClass().getResourceAsStream("/exo.zip");
                File tempFile = new File(System.getProperty("user.home")+"/LogiEdu/exo.zip");
                Files.copy(f, tempFile.toPath(), REPLACE_EXISTING);
                UnzipUtility util = new UnzipUtility();
                util.unzip(System.getProperty("user.home")+"/LogiEdu/exo.zip", System.getProperty("user.home")+"/LogiEdu/ExercicesSources");
                

                out = new FileWriter(configFile);
                configFile.createNewFile();
                baseConfig = new JSONObject();
                userList = new JSONArray();
                baseConfig.put("users", userList);
                out.write(baseConfig.toJSONString());
                out.flush();
                out.close();
                
            }
            else{
                JSONParser parser = new JSONParser();
                in = new FileReader(configFile);
                Object obj = parser.parse(in);
                baseConfig = (JSONObject) obj;
                userList = (JSONArray) baseConfig.get("users");
                in.close();
            }
        } catch (IOException | ParseException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        }
        initThemes();
        //initExerciceFolder();
    }
    
    
    private void initExerciceFolder(){
        File configDir = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources");
        if(!configDir.exists()) {
            configDir.mkdirs();
            int i = 0;
            for (Iterator iterator = themeList.iterator(); iterator.hasNext();) {
                JSONObject currentTheme = (JSONObject) iterator.next();
                File thDir = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"+i);
                thDir.mkdir();
                int j = 0;
                JSONArray courses = (JSONArray) currentTheme.get("courses");
                for (Iterator iterator1 = courses.iterator(); iterator1.hasNext();) {
                    JSONObject currentCourse = (JSONObject) iterator1.next();
                    File thCo = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"+i+"/"+j);
                    thCo.mkdir();
                    JSONArray exercices = (JSONArray) currentCourse.get("exercices");
                    int k = 0;
                    for (Iterator iterator2 = exercices.iterator(); iterator2.hasNext();) {
                        JSONObject ex = (JSONObject) iterator2.next();
                        File thEx = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"+i+"/"+j+"/"+k+"/base");
                        File thEx2 = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"+i+"/"+j+"/"+k+"/user");
                        Number nmand = (Number) ex.get("sourceFiles");
                        if(nmand.intValue() > 0) {
                            thEx.mkdirs();
                            thEx2.mkdirs();
                        }                       
                        k++;
                    }
                    j++;
                }
                i++;
            }
        }
    }
    
    private void updateExerciceFolder(User us){
        int i = 0;
        for (Iterator iterator = themeList.iterator(); iterator.hasNext();) {
            JSONObject currentTheme = (JSONObject) iterator.next();
            int j = 0;
            JSONArray courses = (JSONArray) currentTheme.get("courses");
            for (Iterator iterator1 = courses.iterator(); iterator1.hasNext();) {
                JSONObject currentCourse = (JSONObject) iterator1.next();
                JSONArray exercices = (JSONArray) currentCourse.get("exercices");
                int k = 0;
                for (Iterator iterator2 = exercices.iterator(); iterator2.hasNext();) {
                    JSONObject ex = (JSONObject) iterator2.next();
                    File thEx = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"+i+"/"+j+"/"+k+"/base/Main.java");
                    Number nmand = (Number) ex.get("sourceFiles");
                    if(nmand.intValue() > 0) {
                        try {
                            File exUs = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"+i+"/"+j+"/"+k+"/user/"+us.getUserName());
                            exUs.mkdir();
                            File exUsM = new File(System.getProperty("user.home")+"/LogiEdu/ExercicesSources/"+i+"/"+j+"/"+k+"/user/"+us.getUserName()+"/Main.java");
                            Files.copy(thEx.toPath(), exUsM.toPath(), REPLACE_EXISTING);
                        } catch (IOException ex1) {
                            //Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex1);
                        }
                    }                       
                    k++;
                }
                j++;
            }
            i++;
        }
    }
    
    /**
     * Method whic load the themes content
     * in a JSONObject which will be dealt with after
     */
    private void initThemes(){
        try {
            InputStream f = getClass().getResourceAsStream("/courses.json");
            BufferedReader rd = new BufferedReader(new InputStreamReader(f));
            String content = "";
            String t;
            while((t = rd.readLine()) != null) content += t;
            JSONParser parser = new JSONParser();
            baseThemes = (JSONObject) parser.parse(content);
            themeList = (JSONArray) baseThemes.get("themes");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException | ParseException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Transform the JSONArray containing the theme list
     * into a Theme class list
     * @return the list oh themes
     */
    public ArrayList<Theme> loadThemeList(){
        ArrayList<Theme> resList = new ArrayList<>();
        int i = 0;
        for (Iterator iterator = themeList.iterator(); iterator.hasNext();) {
            JSONObject currentTheme = (JSONObject) iterator.next();
            JSONArray courses = (JSONArray) currentTheme.get("courses");
            Theme th = new Theme((String) currentTheme.get("name"), i);
            int j = 0;
            for (Iterator it2 = courses.iterator(); it2.hasNext();) {
                JSONObject obj = (JSONObject) it2.next();
                Course nco = new Course((String) obj.get("title"), th, j, (String) obj.get("imagePath"));
                th.addCourse(nco);
                this.loadExercices(nco, obj);
                j++;
            }
            resList.add(th);
            i++;
        }
        return resList;
    }
    
    /**
     * Extract exercices information from the json
     * and update the course object accordingly
     * @param co course to be updated
     * @param course source jsonobject
     */
    private void loadExercices(Course co, JSONObject course){
        JSONArray exercices = (JSONArray) course.get("exercices");
        int i = 0;
        for (Iterator iterator1 = exercices.iterator(); iterator1.hasNext();) {
            JSONObject ex = (JSONObject) iterator1.next();
            Number diff = (Number) ex.get("difficulty");
            Number nmand = (Number) ex.get("mandatory");
            Boolean mand = false;
            if(nmand.intValue() > 0) mand = true;
            Number nsrc = (Number) ex.get("sourceFiles");
            Boolean src = false;
            if(nsrc.intValue() > 0) src = true;
            Exercice temp = new Exercice((String) ex.get("title"), (String)ex.get("content"), co, i, diff.intValue(), mand, src, (String) ex.get("result"));
            co.addExercice(temp);
            i++;
        }
    }
    
    
    /**
     * Get the content of a course as a String
     * @param c course searched
     * @return course content
     */
    public String getCourseContent(Course c){
        return (String) ((JSONObject)(((JSONArray)(((JSONObject)(themeList.get(c.getReferingTheme().getId()))).get("courses"))).get(c.getId()))).get("content");
    }
    
    /**
     * Extract a course content of the JSON
     * as a list of Slides
     * @param c course searched
     * @return the list of slides
     */
    public ArrayList<Slide> getCourseSlides(Course c){
        ArrayList<Slide> res = new ArrayList<>();
        JSONObject jsoncourse = ((JSONObject)(((JSONArray)(((JSONObject)(themeList.get(c.getReferingTheme().getId()))).get("courses"))).get(c.getId())));
        JSONArray jsonSlides = (JSONArray) jsoncourse.get("slides");
        int i = 0;
        for (Iterator iterator = jsonSlides.iterator(); iterator.hasNext();) {
            JSONObject next = (JSONObject) iterator.next();
            Slide temp = new Slide(i, c, (String) next.get("content"), (String) next.get("imagePath"));
            res.add(temp);
            i++;
        }
        return res; 
    }
    
    /**
     * Transform the JSONArray containing the user list
     * into a User class list
     * @return 
     */
    public ArrayList<User> loadUserList(){
        ArrayList<User> resList = new ArrayList<>();
        for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
            JSONObject currentUser = (JSONObject) iterator.next();
            Number id = (Number) currentUser.get("id");
            String date = (String) currentUser.get("lastlogged");
            Date trueDate = new Date(date);
            User u = new User((String)currentUser.get("usn"), id.intValue(), trueDate);
            resList.add(u);
        }
        return resList;
    }
    
    /**
     * Store the data concerning a new User
     * into the json file
     * @param us User to be added
     */
    public void saveNewUser(User us){
        JSONObject userObj = new JSONObject();
        userObj.put("id", userList.size());
        us.setId(userList.size());
        userObj.put("usn", us.getUserName());
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        us.setLastLog(date);
        userObj.put("lastlogged", dateFormat.format(date));
        userObj.put("progress", new JSONArray());
        userList.add(userObj);
        this.initThemeProgress(userObj);
        this.updateExerciceFolder(us);
        write();
    }
    
    /**
     * Extract the progress info from the json file
     * regarding a specific user
     * @param us user searched
     * @param ths list to be updated with the user data
     */
    public void loadUserProgress(User us, ArrayList<Theme> ths){
        for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
            JSONObject currentUser = (JSONObject) iterator.next();
            Number id = (Number) currentUser.get("id");
            String date = (String) currentUser.get("lastlogged");
            Date trueDate = new Date(date);
            User u = new User((String)currentUser.get("usn"), id.intValue(), trueDate);
            if(us.getId() == u.getId()){
                for(Theme th : ths){
                    JSONObject jsonTheme = this.getThemeProgress(currentUser, th);
                    JSONArray jsonCourses = (JSONArray) jsonTheme.get("coursesProgress");
                    this.loadCoursesProgress(jsonCourses, th);
                }
            }
        }
    }
    
    
    
    /**
     * Store the data concerning a specific course progress
     * and a specific user
     * @param co course concerned
     * @param us user concerned
     */
    public void saveCourseProgress(Course co, User us){
        for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
            JSONObject currentUser = (JSONObject) iterator.next();
            Number id = (Number) currentUser.get("id");
            String date = (String) currentUser.get("lastlogged");
            Date trueDate = new Date(date);
            User u = new User((String)currentUser.get("usn"), id.intValue(), trueDate);
            if(us.getId() == u.getId()){
                for(Object prog : ((JSONArray) currentUser.get("progress")).toArray()){
                    JSONObject pr = (JSONObject) prog;
                    Number idth = (Number) pr.get("themeid");
                    if(idth.intValue() == co.getReferingTheme().getId()) {
                        JSONArray courses = (JSONArray) pr.get("coursesProgress");
                        for (Iterator iterator2 = courses.iterator(); iterator2.hasNext();){
                            JSONObject currentCourse = (JSONObject) iterator2.next();
                            Number idco = (Number) currentCourse.get("idCourse");
                                if(idco.intValue() == co.getId()){
                                    JSONObject coProg = (JSONObject) currentCourse.get("courseProgress");
                                    coProg.replace("progressState", co.getCourseProgress().getState());
                                    write();
                                }
                        }
                    }
                }
            }
        }
    }
    
    
    /**
     * init the theme part of a user to be
     * written in a json
     * @param jsonUser jsonobject to be updated
     */
    private void initThemeProgress(JSONObject jsonUser){
        int i = 0;
        JSONArray jsonProgress = (JSONArray) jsonUser.get("progress");
        for (Iterator iterator = themeList.iterator(); iterator.hasNext();) {
            JSONObject currentTheme = (JSONObject) iterator.next();
            JSONObject jsonP = new JSONObject();
            jsonP.put("themeid", i);
            jsonP.put("progressState", 0);
            jsonP.put("coursesProgress", new JSONArray());
            this.initCoursesProgress((JSONArray) jsonP.get("coursesProgress"), (JSONArray) currentTheme.get("courses"));
            jsonProgress.add(jsonP);
            i++;
        }
    }
    
    /**
     * init the courses part of a user to be
     * written in a json
     * @param jsonToSet
     * @param jsonCourses 
     */
    private void initCoursesProgress(JSONArray jsonToSet, JSONArray jsonCourses){
        int i =0;
        for (Iterator iterator = jsonCourses.iterator(); iterator.hasNext();) {
            JSONObject next = (JSONObject) iterator.next();
            JSONObject newCourse = new JSONObject();
            JSONObject newCourseProgress = new JSONObject();
            newCourse.put("idCourse", i);
            newCourseProgress.put("progressState", 0);
            newCourseProgress.put("progressComment", "");
            newCourse.put("courseProgress", newCourseProgress);
            newCourse.put("exercicesProgress", new JSONArray());
            initExercicesProgress((JSONArray) newCourse.get("exercicesProgress"), (JSONArray) next.get("exercices"));
            jsonToSet.add(newCourse);
            i++;
        }
    }
    
    /**
     * init the exercices part of a user to be
     * written in a json
     * @param jsonToSet
     * @param jsonExercices 
     */
    private void initExercicesProgress(JSONArray jsonToSet, JSONArray jsonExercices){
        int i = 0;
        for (Iterator iterator = jsonExercices.iterator(); iterator.hasNext();) {
            JSONObject exo = (JSONObject) iterator.next();
            JSONObject exoProg = new JSONObject();
            JSONObject newExoProgress = new JSONObject();
            exoProg.put("idExercice", i);
            newExoProgress.put("progressState", 0);
            newExoProgress.put("progressComment", "");
            exoProg.put("exerciceProgress", newExoProgress);
            jsonToSet.add(exoProg);
            i++;
        }
    }
    
    
    
    
    /**
     * Extract courses progress information from the json
     * and update the course object accordingly
     * @param courses
     * @param refTheme 
     */
    private void loadCoursesProgress(JSONArray courses, Theme refTheme){
        for (Iterator iterator = courses.iterator(); iterator.hasNext();){
            JSONObject currentCourse = (JSONObject) iterator.next();
            Number id = (Number) currentCourse.get("idCourse");
            for(Course c : refTheme.getCourseList()){
                if(id.intValue() == c.getId()){
                    JSONObject jsonProgress = (JSONObject) currentCourse.get("courseProgress");
                    Number state = (Number) jsonProgress.get("progressState");
                    String progressComment = (String) jsonProgress.get("progressComment");
                    c.setCourseProgress(new FourStepProgress(state.intValue(), progressComment));
                    loadExercicesProgress((JSONArray) currentCourse.get("exercicesProgress"), c);
                }
            }
        }
    }
    
    /**
     * Extract exercices progress information from the json
     * and update the course object accordingly
     * @param exercices
     * @param refCo 
     */
    private void loadExercicesProgress(JSONArray exercices, Course refCo){
        for (Iterator iterator = exercices.iterator(); iterator.hasNext();) {
            JSONObject currentExercice = (JSONObject) iterator.next();
            Number id = (Number) currentExercice.get("idExercice");
            for(Exercice e : refCo.getExercices()){
                if(e.getId() == id.intValue()){
                    JSONObject exerciceProg = (JSONObject) currentExercice.get("exerciceProgress");
                    Number state = (Number) exerciceProg.get("progressState");
                    String progressComment = (String) exerciceProg.get("progressComment");
                    e.setProgress(new FourStepProgress(state.intValue(), progressComment));
                }
            }
        }
    }
    
    public void saveExercicesProgress(User us, Course co, Exercice ex){
        //System.out.println(userList);
        for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
            JSONObject currentUser = (JSONObject) iterator.next();
            Number id = (Number) currentUser.get("id");
            String date = (String) currentUser.get("lastlogged");
            Date trueDate = new Date(date);
            User u = new User((String)currentUser.get("usn"), id.intValue(), trueDate);
            if(us.getId() == u.getId()){
                for(Object prog : ((JSONArray) currentUser.get("progress")).toArray()){
                    JSONObject pr = (JSONObject) prog;
                    Number idth = (Number) pr.get("themeid");
                    if(idth.intValue() == co.getReferingTheme().getId()) {
                        JSONArray courses = (JSONArray) pr.get("coursesProgress");
                        for (Iterator iterator2 = courses.iterator(); iterator2.hasNext();){
                            JSONObject currentCourse = (JSONObject) iterator2.next();
                            Number idco = (Number) currentCourse.get("idCourse");
                            if(idco.intValue() == co.getId()){
                                JSONArray exercicess = (JSONArray) currentCourse.get("exercicesProgress");
                                for (Iterator iterator3 = exercicess.iterator(); iterator3.hasNext();){
                                    JSONObject currentExo = (JSONObject) iterator3.next();
                                    Number idexo = (Number) currentExo.get("idExercice");
                                    
                                    if(idexo.intValue() == ex.getId()){
                                        JSONObject exProg = (JSONObject) currentExo.get("exerciceProgress");
                                        exProg.replace("progressState", ex.getProgress().getState());
                                        write();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Extract the progress info from the json file
     * regarding a specific user and theme
     * @param user searched
     * @param th theme searched
     * @return corresponding JsonObject
     */
    private JSONObject getThemeProgress(JSONObject user, Theme th){
        for(Object prog : ((JSONArray) user.get("progress")).toArray()){
            JSONObject pr = (JSONObject) prog;
            Number id = (Number) pr.get("themeid");
            if(id.intValue() == th.getId()) return pr;
        }
        return null;
    }

    
    
    /**
     * Write the modifications into the json
     */
    private void write(){
        try {
            FileWriter out = new FileWriter(configFile);
            out.write(baseConfig.toJSONString());
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
