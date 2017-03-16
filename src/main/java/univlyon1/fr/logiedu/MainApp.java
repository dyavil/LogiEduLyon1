package univlyon1.fr.logiedu;

import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import univlyon1.fr.logiedu.Model.LoadData;
import univlyon1.fr.logiedu.Model.User;
import univlyon1.fr.logiedu.View.MainView;
import univlyon1.fr.logiedu.View.UserPane;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        /*Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");
        
        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();*/
        
        stage.setTitle("Logi Edu");
        MainView v = new MainView(600, 600);
        LoadData load = new LoadData();
        ArrayList<User> l = load.loadUserList();
        System.out.println(l.size());
        for(User us : l){
            UserPane usp = new UserPane(l.get(0).getUserName());
            v.addUserPane(usp);
        }
        
        v.displayUsersPane();
        stage.setScene(v.getScene());
        stage.show();
    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
