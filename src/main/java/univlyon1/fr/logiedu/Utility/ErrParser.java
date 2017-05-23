/*
 * Quintard LivaI
 * Project for Logiciel Educatif
 * Universite lyon 1
 */
package univlyon1.fr.logiedu.Utility;

/**
 *
 * @author dyavil
 */
public class ErrParser{

    public static String getCompileTypeOutput(String in){
        String[] keyWords = new String[]{"expected","end of file", "cannot find symbol", "not a statement"};
        String res = "Erreur de compilation";
        if(in.contains(keyWords[0])){
            res += "\nVérifiez la syntaxe(point virgule, parenthèses, accolades...)";
        }
        if(in.contains(keyWords[1])){
            res += "\nVérifiez que vos fonctions, if, for sont biens fermées !";
        }
        if(in.contains(keyWords[2])){
            res += "\nVérifiez vos déclarations/assignements de variable !";
        }
        if(in.contains(keyWords[3])){
            res += "\nVérifiez vos déclarations (typage, nommage) !";
        }
        return res;
    }
    
    public static String getExecuteTypeOutput(String in){
        String[] keyWords = new String[]{"nullpointerexception","outofboundsexception", "arithmeticexception", "illegalargumentexception", "illegalstateexception", "classnotfoundexception", "nosuchmethodexception"};
        String res = "Erreur d'exécution";
        boolean empty = true;
        if(in.toLowerCase().contains(keyWords[0])){
            res += "\nVérifiez que vous accédez à un élément qui existe !";
            empty = false;
        }
        if(in.toLowerCase().contains(keyWords[1])){
            res += "\nVérifiez vos écritures/lectures tableau, liste!";
            empty = false;
        }
        if(in.toLowerCase().contains(keyWords[2])){
            res += "\nVérifiez vos calculs !";
            empty = false;
        }
        if(in.toLowerCase().contains(keyWords[3])){
            res += "\nVérifiez vos paramètres de méthodes (types, nombre) !";
            empty = false;
        }
        if(in.toLowerCase().contains(keyWords[4])){
            res += "\nVérifiez le retour de vos méthodes !";
            empty = false;
        }
        if(in.toLowerCase().contains(keyWords[5])){
            res += "\nVérifiez vos déclarations de classe !";
            empty = false;
        }
        if(in.toLowerCase().contains(keyWords[6])){
            res += "\nVérifiez que les méthodes appellées existent\n- Attention aux fautes de frappe !";
            empty = false;
        }
        if(empty) res = "";
        return res;
    }
}
