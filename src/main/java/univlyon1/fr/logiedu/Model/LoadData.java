/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/**
 *
 * @author dyavil
 */
public class LoadData {
    
    private File configFile;
    private JSONObject baseConfig;
    private JSONObject baseThemes;
    private JSONArray themeList;
    private JSONArray userList;
    
    public LoadData(){
        FileReader in;
        FileWriter out;
        try {
            File configDir = new File(System.getProperty("user.home")+"/LogiEdu");
            configFile = new File(System.getProperty("user.home")+"/LogiEdu/config.json");
            if(!configDir.exists()) configDir.mkdirs();
            if(!configFile.exists()){
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
    }
    
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
    
    public ArrayList<Theme> loadThemeList(){
        ArrayList<Theme> resList = new ArrayList<>();
        int i = 0;
        for (Iterator iterator = themeList.iterator(); iterator.hasNext();) {
            JSONObject currentTheme = (JSONObject) iterator.next();
            JSONArray courses = (JSONArray) currentTheme.get("courses");
            Theme th = new Theme((String) currentTheme.get("name"), i);
            int j = 0;
            for (Iterator it2 = courses.iterator(); it2.hasNext();) {
                th.addCourse(new Course((String) ((JSONObject) it2.next()).get("title"), th, j));
                j++;
            }
            resList.add(th);
            i++;
        }
        return resList;
    }
    
    public String getCourseContent(Course c){
        return (String) ((JSONObject)(((JSONArray)(((JSONObject)(themeList.get(c.getReferingTheme().getId()))).get("courses"))).get(c.getId()))).get("content");
    }
    
    public ArrayList<User> loadUserList(){
        ArrayList<User> resList = new ArrayList<>();
        for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
            JSONObject currentUser = (JSONObject) iterator.next();
            Number id = (Number) currentUser.get("id");
            User u = new User((String)currentUser.get("usn"), id.intValue());
            resList.add(u);
        }
        return resList;
    }
    
    public void saveNewUser(User us){
        JSONObject userObj = new JSONObject();
        userObj.put("id", userList.size());
        us.setId(userList.size());
        userObj.put("usn", us.getUserName());
        userObj.put("progress", new JSONArray());
        userList.add(userObj);
        this.initThemeProgress(userObj);
        write();
    }
    
    public void loadUserProgress(User us, ArrayList<Theme> ths){
        for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
            JSONObject currentUser = (JSONObject) iterator.next();
            Number id = (Number) currentUser.get("id");
            User u = new User((String)currentUser.get("usn"), id.intValue());
            if(us.getId() == u.getId()){
                for(Theme th : ths){
                    JSONObject jsonTheme = this.getThemeProgress(currentUser, th);
                    JSONArray jsonCourses = (JSONArray) jsonTheme.get("coursesProgress");
                    this.loadCoursesProgress(jsonCourses, th);
                }
            }
        }
    }
    
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
            jsonToSet.add(newCourse);
            i++;
        }
    }
    
    private void loadCoursesProgress(JSONArray courses, Theme refTheme){
        for (Iterator iterator = courses.iterator(); iterator.hasNext();){
            JSONObject currentCourse = (JSONObject) iterator.next();
            Number id = (Number) currentCourse.get("idCourse");
            for(Course c : refTheme.getCourseList()){
                if(id.intValue() == c.getId()){
                    JSONObject jsonProgress = (JSONObject) currentCourse.get("courseProgress");
                    Number state = (Number) jsonProgress.get("progressState");
                    String progressComment = (String) jsonProgress.get("progressComment");
                    c.setCourseProgress(new ThreeStepProgress(state.intValue(), progressComment));
                }
            }
        }
    }
    
    
    public void saveCourseProgress(Course co, User us){
        for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
            JSONObject currentUser = (JSONObject) iterator.next();
            Number id = (Number) currentUser.get("id");
            User u = new User((String)currentUser.get("usn"), id.intValue());
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
    
    private JSONObject getThemeProgress(JSONObject user, Theme th){
        for(Object prog : ((JSONArray) user.get("progress")).toArray()){
            JSONObject pr = (JSONObject) prog;
            Number id = (Number) pr.get("themeid");
            if(id.intValue() == th.getId()) return pr;
        }
        return null;
    }
    
    public void updateUser(User us){
        for (Iterator iterator = userList.iterator(); iterator.hasNext();) {
            JSONObject currentUser = (JSONObject) iterator.next();
            Number id = (Number) currentUser.get("id");
            User u = new User((String)currentUser.get("usn"), id.intValue());
            if(us.getId() == u.getId()){
                //update
            }
        }
    }
    
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