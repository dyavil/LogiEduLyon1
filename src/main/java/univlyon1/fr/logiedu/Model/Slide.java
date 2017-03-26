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
public class Slide {
    private int id;
    private Course correspondingCourse;
    private String imagePath;
    private String content;
    
    public Slide(int id, Course c, String cont){
        this.id = id;
        this.correspondingCourse = c;
        this.content = cont;
        this.imagePath = null;
    }
    
    public Slide(int id, Course c, String cont, String imP){
        this(id, c, cont);
        this.imagePath = imP;
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
     * @return the imagePath
     */
    public String getImagePath() {
        return imagePath;
    }

    /**
     * @param imagePath the imagePath to set
     */
    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    /**
     * @return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
}
