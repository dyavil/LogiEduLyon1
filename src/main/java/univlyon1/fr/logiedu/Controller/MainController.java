/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.Controller;

import java.util.Optional;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import univlyon1.fr.logiedu.Model.App;
import univlyon1.fr.logiedu.Model.Course;
import univlyon1.fr.logiedu.Model.Exercice;
import univlyon1.fr.logiedu.Model.Theme;
import univlyon1.fr.logiedu.Model.User;
import univlyon1.fr.logiedu.View.CourseListItem;
import univlyon1.fr.logiedu.View.CourseView;
import univlyon1.fr.logiedu.View.ExerciceGridView;
import univlyon1.fr.logiedu.View.ExerciceListItem;
import univlyon1.fr.logiedu.View.ExerciceView;
import univlyon1.fr.logiedu.View.MainView;
import univlyon1.fr.logiedu.View.UserPane;

/**
 *
 * @author dyavil
 */
public class MainController {
    private MainView view;
    private App model;
    private Stage stage;
    
    public MainController(final MainView v, final App m, final Stage s){
        this.model = m;
        this.view = v;
        this.stage = s;
        for(User us : this.model.getUsers()){
            this.view.addUserPane(new UserPane(us.getUserName(), us.getId()));
        }
        this.view.displayUsersPane();
        this.stage.setScene(this.view.getScene());
        
        this.refreshUsersPane();
        
        this.view.getHead().getAddUser().setOnAction((ActionEvent e) -> {
                addUserAction();
            });
        this.logoutHandler();
        
    }
    
    private void addUserAction(){
        TextInputDialog dialog = new TextInputDialog("pseudo");
                dialog.setTitle("Pseudo : ");
                dialog.setHeaderText("");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    User us = this.model.addUser(result.get());
                    System.out.println("Pseudo: " + result.get());
                    this.view.addUserPane(new UserPane(us.getUserName(), us.getId()));
                    this.view.displayUsersPane();
                    this.refreshUsersPane();
                }
    }
    
    
    private void refreshUsersPane(){
        this.view.getUsersPane().stream().forEach((usp) -> {
            usp.getLogButton().setOnAction((ActionEvent e) -> {
                this.model.setLoggedUser(usp.getID());
                this.loadHomePane();
            });
        });
        this.view.getAddButton().setOnAction((ActionEvent e) -> {
            addUserAction();
        });
    }
    
    private void loadHomePane(){
        this.view.displayHomePane(this.model.getLoggedUser().getUserName());
        logoutHandler();
        this.view.getHomePane().getCoursesButton().setOnAction((ActionEvent e) -> {
                this.loadCourseList();  
            });
        this.view.getHomePane().getExercicesButton().setOnAction((ActionEvent e) -> {
                this.loadExerciceList();
            });
    }
    
    private void colorList(){
        this.view.getCoursePane().getCoursesList().setCellFactory(tv -> {
        TreeCell<HBox> cell = new TreeCell<HBox>() {
            @Override
            public void updateItem(HBox item, boolean empty) {
                super.updateItem(item, empty);
                setDisclosureNode(null);

                if (empty) {
                    setGraphic(null);
                } else {
                    CourseListItem it = (CourseListItem) item;
                    //setText(""); // appropriate text for item
                    Label lab = it.getLab();
                    lab.getStyleClass().add(it.getCss());
                    setGraphic(lab);
                }
            }
        };
                
            return cell ;
        });
    }
    
    private void exerciceColorList(){
        this.view.getExercicePane().getExercicesList().setCellFactory(tv -> {
        TreeCell<HBox> cell = new TreeCell<HBox>() {
            @Override
            public void updateItem(HBox item, boolean empty) {
                super.updateItem(item, empty);
                setDisclosureNode(null);

                if (empty) {
                    setGraphic(null);
                } else {
                    ExerciceListItem it = (ExerciceListItem) item;
                    //setText(""); // appropriate text for item
                    Label lab = it.getLab();
                    lab.getStyleClass().add(it.getCss());
                    setGraphic(lab);
                }
            }
        };
                
            return cell ;
        });
    }

    private void loadCourseList() {
        this.view.getCoursePane().getCoursesList().getRoot().getChildren().clear();
        for(Theme th : this.model.getThemes()){
            CourseListItem tbox = new CourseListItem(th.getName(), "no-change", true, th.getId());
            
            TreeItem<HBox> themBranch = new TreeItem<>(tbox);
            themBranch.getChildren().clear();
            for(Course co : th.getCourseList()){
                CourseListItem box = new CourseListItem(co.getTitle(), co.getCourseProgress().getCorrespondingCSS().get(co.getCourseProgress().getState()), true, th.getId(), co.getId());
                TreeItem<HBox> item = new TreeItem<>(box);
                themBranch.getChildren().add(item);
            }
            this.view.getCoursePane().getCoursesList().getRoot().getChildren().add(themBranch);
        }
        
        this.view.getCoursePane().getCoursesList().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.colorList();
        this.setSelectCourseEventHandler();
        this.view.getCoursePane().getHomeButton().setOnAction((ActionEvent e) -> {
                this.loadHomePane();
            });
        this.view.getCoursePane().getCoursesList().getRoot().setExpanded(true);
        this.view.displayCoursesPane();
    }
    
    private void logoutHandler(){
        this.view.getHead().getLogoutLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    view.displayUsersPane();
                    model.setLoggedUser(null);
            }

        });
    }
    
    private void loadExerciceList() {
        this.view.getExercicePane().getExercicesList().getRoot().getChildren().clear();
        for(Theme th : this.model.getThemes()){
            ExerciceListItem tbox = new ExerciceListItem(th.getName(), "no-change");
            
            TreeItem<HBox> themBranch = new TreeItem<>(tbox);
            themBranch.getChildren().clear();
            for(Course co : th.getCourseList()){
                ExerciceListItem box = new ExerciceListItem(co.getTitle(), co.getCourseProgress().getCorrespondingCSS().get(co.getCourseProgress().getState()), true, th.getId(), co.getId());
                TreeItem<HBox> coItem = new TreeItem<>(box);
                themBranch.getChildren().add(coItem);
                for(Exercice exo : co.getExercices()){
                    ExerciceListItem exBox = new ExerciceListItem(exo.getName(), "no-change", true, th.getId(), co.getId(), exo.getId());
                    TreeItem<HBox> exItem = new TreeItem<>(exBox);
                    coItem.getChildren().add(exItem);
                }
                
            }
            themBranch.setExpanded(true);
            this.view.getExercicePane().getExercicesList().getRoot().getChildren().add(themBranch);
        }
        
        this.view.getExercicePane().getExercicesList().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.exerciceColorList();
        this.setSelectExerciceEventHandler();
        this.view.getExercicePane().getHomeButton().setOnAction((ActionEvent e) -> {
                this.loadHomePane();
            });
        this.view.getExercicePane().getExercicesList().getRoot().setExpanded(true);
        this.view.displayExercicesPane();
    }


    private void setSelectCourseEventHandler() {
        view.getCoursePane().hidegoButton();
        this.view.getCoursePane().getCoursesList().getSelectionModel().getSelectedItem();
        this.view.getCoursePane().getCoursesList().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                if(view.getCoursePane().getCoursesList().getSelectionModel().getSelectedItem().getValue() instanceof CourseListItem){
                    CourseListItem it = (CourseListItem) view.getCoursePane().getCoursesList().getSelectionModel().getSelectedItem().getValue();
                    if(it.getIsCourse()){
                        
                        view.getCoursePane().showgoButton();
                        view.getCoursePane().getGoButton().setOnAction((ActionEvent e) -> {
                            System.out.println("selec th " + it.getThemeId() + ", course : "+ it.getCourseId());
                            Course currCo = model.getThemes().get(it.getThemeId()).getCourseList().get(it.getCourseId());
                            setCourseViewEvents(currCo, 0);
                            
                        });
                        if(event.getButton().equals(MouseButton.PRIMARY)){
                            if(event.getClickCount() == 2) {
                                System.out.println("selec th " + it.getThemeId() + ", course : "+ it.getCourseId());
                                Course currCo = model.getThemes().get(it.getThemeId()).getCourseList().get(it.getCourseId());
                                setCourseViewEvents(currCo, 0);
                            }
                            
                        }
                    
                    }
                    else if(it.getIsTheme()){
                        if(event.getButton().equals(MouseButton.PRIMARY)){
                            if(!view.getCoursePane().getCoursesList().getSelectionModel().getSelectedItem().isExpanded()) view.getCoursePane().getCoursesList().getSelectionModel().getSelectedItem().setExpanded(true);
                            else view.getCoursePane().getCoursesList().getSelectionModel().getSelectedItem().setExpanded(false);
                            if(event.getClickCount() == 2) {
                                Course currCo = model.getThemes().get(it.getThemeId()).getCourseList().get(0);
                                setCourseViewEvents(currCo, 0);
                            }
                            
                        }
                    }
                    else view.getCoursePane().hidegoButton();
                }
            }

        });
    }

    private void setSelectExerciceEventHandler() {
        //view.getCoursePane().hidegoButton();
        this.view.getExercicePane().getExercicesList().getSelectionModel().getSelectedItem();
        this.view.getExercicePane().getExercicesList().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                if(view.getExercicePane().getExercicesList().getSelectionModel().getSelectedItem().getValue() instanceof ExerciceListItem){
                    ExerciceListItem it = (ExerciceListItem) view.getExercicePane().getExercicesList().getSelectionModel().getSelectedItem().getValue();
                    if(it.getIsExercice()){
                        
                        //view.getCoursePane().showgoButton();
                        /*view.getExercicePane().getGoButton().setOnAction((ActionEvent e) -> {
                            System.out.println("selec th " + it.getThemeId() + ", course : "+ it.getCourseId());
                            Course currCo = model.getThemes().get(it.getThemeId()).getCourseList().get(it.getCourseId());
                            setCourseViewEvents(currCo, 0);
                            
                        });*/
                        if(event.getButton().equals(MouseButton.PRIMARY)){
                            if(event.getClickCount() == 2) {
                                System.out.println("selec th " + it.getThemeId() + ", course : "+ it.getCourseId());
                                Course currCo = model.getThemes().get(it.getThemeId()).getCourseList().get(it.getCourseId());
                                setExerciceViewEvents(currCo.getExercices().get(it.getExerciceId()));
                            }
                            
                        }
                    
                    }
                    else if(it.getIsCourse()){
                        if(event.getButton().equals(MouseButton.PRIMARY)){
                            if(event.getClickCount() == 1) {
                                if(!view.getExercicePane().getExercicesList().getSelectionModel().getSelectedItem().isExpanded())
                                view.getExercicePane().getExercicesList().getSelectionModel().getSelectedItem().setExpanded(true);
                                else view.getExercicePane().getExercicesList().getSelectionModel().getSelectedItem().setExpanded(false);
                            }
                            if(event.getClickCount() == 2) {
                                System.out.println("selec th " + it.getThemeId() + ", course : "+ it.getCourseId());
                                Course currCo = model.getThemes().get(it.getThemeId()).getCourseList().get(it.getCourseId());
                                setExerciceGridViewEvents(currCo);
                            }
                            
                        }
                    }
                   // else view.getCoursePane().hidegoButton();
                }
            }

        });
    }
    
    private void setExerciceGridViewEvents(Course co) {
        ExerciceGridView egv = new ExerciceGridView(view.getTWidth());
        for(Exercice ex : co.getExercices()) {
            ExerciceGridView.ExerciceGridSample exGrid = egv.addExerciceElem(ex.getName(), ex.getContent());
            exGrid.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getClickCount() == 2) setExerciceViewEvents(ex);
            }
            });
        }
        egv.displayExerciceGrid(view.getTWidth());
        view.displayExerciceGridView(egv);
        egv.getHomeButton().setOnAction((ActionEvent e1) -> {
            loadHomePane();
        });
        egv.getBackButton().setOnAction((ActionEvent e2) -> {
            loadExerciceList();
        });
        egv.getCourseButton().setOnAction((ActionEvent e3) -> {
            setCourseViewEvents(co, 0);
        });
    }
    
    private void setExerciceViewEvents(Exercice ex) {
        ExerciceView exv = new ExerciceView(ex.getCorrespondingCourse().getTitle(), ex.getName(), ex.getContent(), (int) view.getTWidth());
        view.displayExerciceView(exv);
        ex.CompileCode();
        ex.ExecuteCode();
        if(ex.getId() < ex.getCorrespondingCourse().getExercices().size()-1) {
            exv.showNextButton();
            exv.getNextExercice().setOnAction((ActionEvent e) -> {
                Exercice nextEx = ex.getCorrespondingCourse().getExercices().get(ex.getId()+1);
                setExerciceViewEvents(nextEx);
            });
        }
        else exv.hideNextButton();
        if((ex.getId() < ex.getCorrespondingCourse().getExercices().size()) && (ex.getId() > 0)) {
            exv.showPrevButton();
            exv.getPrevExercice().setOnAction((ActionEvent e) -> {
                Exercice prevEx = ex.getCorrespondingCourse().getExercices().get(ex.getId()-1);
                setExerciceViewEvents(prevEx);
            });
        }
        else exv.hidePrevButton();
        
        exv.getHomeButton().setOnAction((ActionEvent e1) -> {
            loadHomePane();
        });
        exv.getBackButton().setOnAction((ActionEvent e2) -> {
            loadExerciceList();
        });
        exv.getCourseButton().setOnAction((ActionEvent e3) -> {
            setCourseViewEvents(ex.getCorrespondingCourse(), 0);
        });
    }
    
    private void setCourseViewEvents(Course co, int sl){
        
        CourseView cv;
        if(sl == 0)model.loadCourseContent(co);
        if(!"".equals(co.getSlides().get(sl).getImagePath())) {
            cv = new CourseView(co.getReferingTheme().getName(), co.getTitle(), applyStyle(co.getSlides().get(sl).getContent()), (int) view.getTWidth(), co.getSlides().get(sl).getImagePath());
        }
        else cv = new CourseView(co.getReferingTheme().getName(), co.getTitle(), applyStyle(co.getSlides().get(sl).getContent()), (int) view.getTWidth());
            if(co.getCourseProgress().getState() < co.getCourseProgress().getCorrespondingCSS().size()-2){
                co.getCourseProgress().nextStep();
                model.updateCourse(co);
            }
            
            view.displayCourseView(cv);
            if(co.getId() < co.getReferingTheme().getCourseList().size()-1) {
                cv.switchToNextCours();
                cv.showNextButton();
                cv.getNextCourse().setOnAction((ActionEvent e) -> {
                    Course nextCo = co.getReferingTheme().getCourseList().get(co.getId()+1);
                    setCourseViewEvents(nextCo, 0);
                });
            }
            else {
                if(co.getReferingTheme().getId() < this.model.getThemes().size()-1){
                    cv.switchToNextTheme();
                    cv.showNextButton();
                    cv.getNextCourse().setOnAction((ActionEvent e) -> {
                        Course nextCo = this.model.getThemes().get(co.getReferingTheme().getId()+1).getCourseList().get(0);
                        setCourseViewEvents(nextCo, 0);
                });
                }else cv.hideNextButton();
            }
            if((co.getId() < co.getReferingTheme().getCourseList().size()) && (co.getId() > 0)) {
                cv.showPrevButton();
                cv.getPrevCourse().setOnAction((ActionEvent e) -> {
                    Course prevCo = co.getReferingTheme().getCourseList().get(co.getId()-1);
                    setCourseViewEvents(prevCo, 0);
                });
            }
            else {
                if(co.getReferingTheme().getId() < this.model.getThemes().size() && co.getReferingTheme().getId() > 0){
                    cv.switchToPrevTheme();
                    cv.showPrevButton();
                    cv.getPrevCourse().setOnAction((ActionEvent e) -> {
                        Course PrevCo = this.model.getThemes().get(co.getReferingTheme().getId()-1).getCourseList().get(this.model.getThemes().get(co.getReferingTheme().getId()-1).getCourseList().size()-1);
                        setCourseViewEvents(PrevCo, 0);
                });
                }else cv.hidePrevButton();
            }
            
            if(sl < co.getSlides().size()-1) {
                cv.showNextSlideButton();
                cv.getNextSlide().setOnAction((ActionEvent e) -> {
                    setCourseViewEvents(co, sl+1);
                });
            }
            else cv.hideNextSlideButton();
            if((sl <  co.getSlides().size()) && (sl > 0)) {
                cv.showPrevSlideButton();
                cv.getPrevSlide().setOnAction((ActionEvent e) -> {
                    setCourseViewEvents(co, sl-1);
                });
            }
            else cv.hidePrevSlideButton();
            
            cv.getHomeButton().setOnAction((ActionEvent e1) -> {
                loadHomePane();
            });
            cv.getBackToList().setOnAction((ActionEvent e2) -> {
                loadCourseList();
            });
            cv.getExercices().setOnAction((ActionEvent e3) -> {
                setExerciceGridViewEvents(co);
            });
   }
    
    
    private TextFlow applyStyle(String in){
        TextFlow res = new TextFlow();
        String[] spl = in.split("£");
        for(String one : spl){
            String[] temp = one.split("§");
            if(temp.length == 2){
                Text t1 = new Text(temp[1]);
                t1.getStyleClass().add("course-content");
                if(temp[0].contains("color-")){
                    t1.setStyle("-fx-fill:"+temp[0].split("color-")[1]+";");
                }
                else t1.getStyleClass().add(temp[0]);
                res.getChildren().add(t1);
            }
            else{
                Text t1 = new Text(temp[0]);
                t1.getStyleClass().add("course-content");
                res.getChildren().add(t1);
            }
        }
        return res;
    }
    
    
}
