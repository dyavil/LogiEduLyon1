/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.Model;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    private JSONObject base;
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
                base = new JSONObject();
                userList = new JSONArray();
                base.put("users", userList);
                out.write(base.toJSONString());
                out.flush();
                out.close();
            }
            else{
                JSONParser parser = new JSONParser();
                in = new FileReader(configFile);
                Object obj = parser.parse(in);
                base = (JSONObject) obj;
                userList = (JSONArray) base.get("users");
                in.close();
            }
        } catch (IOException | ParseException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
            out.write(base.toJSONString());
            out.flush();
            out.close();
        } catch (IOException ex) {
            Logger.getLogger(LoadData.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
