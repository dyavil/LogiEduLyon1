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
        for (Iterator iterator = themeList.iterator(); iterator.hasNext();) {
            JSONObject currentTheme = (JSONObject) iterator.next();
            Theme th = new Theme((String) currentTheme.get("name"));
            resList.add(th);
        }
        return resList;
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
        userList.add(userObj);
        write();
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
