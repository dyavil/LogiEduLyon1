/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.Model;

import java.util.Date;

/**
 *
 * @author dyavil
 */
public class User implements Comparable<User> {
    private int id;
    private String userName;
    private Date lastLog;
    
    public User(String usn, int id, Date log){
        this.userName = usn;
        this.id = id;
        this.lastLog = log;
    }
    
    public User(String usn){
        this.userName = usn;
        this.id = -1;
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
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public int compareTo(User o) {
        return -getLastLog().compareTo(o.getLastLog());
    }

    /**
     * @return the lastLog
     */
    public Date getLastLog() {
        return lastLog;
    }

    /**
     * @param lastLog the lastLog to set
     */
    public void setLastLog(Date lastLog) {
        this.lastLog = lastLog;
    }
    
    @Override
    public String toString(){
        return this.userName;
    }
}
