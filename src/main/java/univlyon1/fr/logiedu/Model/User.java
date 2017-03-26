/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.Model;

/**
 *
 * @author dyavil
 */
public class User {
    private int id;
    private String userName;
    
    public User(String usn, int id){
        this.userName = usn;
        this.id = id;
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
}
