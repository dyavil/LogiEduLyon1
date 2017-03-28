/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import univlyon1.fr.logiedu.Controller.MainController;
import univlyon1.fr.logiedu.Model.App;
import univlyon1.fr.logiedu.View.MainView;


public class MainApp extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        stage.setTitle("Logi Edu");
        MainView view = new MainView(1200, 700);
        App model = new App();
        MainController controller = new MainController(view, model, stage);
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
