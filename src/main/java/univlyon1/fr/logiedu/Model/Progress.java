/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package univlyon1.fr.logiedu.Model;

import java.util.ArrayList;

/**
 *
 * @author dyavil
 */
public class Progress {
    private int state;
    private String comment;
    private ArrayList<String> correspondingNames;
    private ArrayList<String> correspondingCSS;
    
    public Progress(){
        this.state = 0;
        this.comment = "";
        this.correspondingNames = new ArrayList<>();
        this.correspondingCSS = new ArrayList<>();
    }
    
    public void addProgressStep(String step){
        this.getCorrespondingNames().add(step);
    }
    
    public String getStringState(int st){
        if(st<this.getCorrespondingNames().size()) return this.getStringState(st);
        return this.getStringState(this.getState());
    }
    
    public void nextStep(){
        if(this.getState() < this.getCorrespondingNames().size()-1) this.setState(this.getState() + 1);
    }
    
    public void previousStep(){
        if(this.getState() > 0) this.setState(this.getState() - 1);
    }

    /**
     * @return the state
     */
    public int getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(int state) {
        this.state = state;
    }

    /**
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * @param comment the comment to set
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * @return the correspondingNames
     */
    public ArrayList<String> getCorrespondingNames() {
        return correspondingNames;
    }

    /**
     * @param correspondingNames the correspondingNames to set
     */
    public void setCorrespondingNames(ArrayList<String> correspondingNames) {
        this.correspondingNames = correspondingNames;
    }

    /**
     * @return the correspondingCSS
     */
    public ArrayList<String> getCorrespondingCSS() {
        return correspondingCSS;
    }

    /**
     * @param correspondingCSS the correspondingCSS to set
     */
    public void setCorrespondingCSS(ArrayList<String> correspondingCSS) {
        this.correspondingCSS = correspondingCSS;
    }
    
}
