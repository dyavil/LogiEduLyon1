/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
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
import javafx.stage.Stage;
import univlyon1.fr.logiedu.Model.App;
import univlyon1.fr.logiedu.Model.Course;
import univlyon1.fr.logiedu.Model.Theme;
import univlyon1.fr.logiedu.Model.User;
import univlyon1.fr.logiedu.View.CourseListItem;
import univlyon1.fr.logiedu.View.CourseView;
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
            });
        this.logoutHandler();
        
    }
    
    
    private void refreshUsersPane(){
        this.view.getUsersPane().stream().forEach((usp) -> {
            usp.getLogButton().setOnAction((ActionEvent e) -> {
                this.model.setLoggedUser(usp.getID());
                this.loadHomePane();
            });
        });
    }
    
    private void loadHomePane(){
        this.view.displayHomePane(this.model.getLoggedUser().getUserName());
        logoutHandler();
        this.view.getHomePane().getCoursesButton().setOnAction((ActionEvent e) -> {
                this.loadCourseList();
                
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
                    //setText("colorList"); // appropriate text for item
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
            CourseListItem tbox = new CourseListItem(th.getName(), "no-change");
            
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
                            setCourseViewEvents(currCo);
                            
                        });
                        if(event.getButton().equals(MouseButton.PRIMARY)){
                            if(event.getClickCount() == 2) {
                                System.out.println("selec th " + it.getThemeId() + ", course : "+ it.getCourseId());
                                Course currCo = model.getThemes().get(it.getThemeId()).getCourseList().get(it.getCourseId());
                                setCourseViewEvents(currCo);
                            }
                            
                        }
                    
                    }
                    else view.getCoursePane().hidegoButton();
                }
            }

        });
    }
    
    
    private void setCourseViewEvents(Course co){
        CourseView cv = new CourseView(co.getTitle(), model.getCourseContent(co), (int) view.getWidth());
            if(co.getCourseProgress().getState() < co.getCourseProgress().getCorrespondingCSS().size()-2){
                co.getCourseProgress().nextStep();
                model.updateCourse(co);
            }
            
            view.displayCourseView(cv);
            if(co.getId() < co.getReferingTheme().getCourseList().size()-1) {
                cv.showNextButton();
                cv.getNextCourse().setOnAction((ActionEvent e) -> {
                    Course nextCo = co.getReferingTheme().getCourseList().get(co.getId()+1);
                    setCourseViewEvents(nextCo);
                });
            }
            else cv.hideNextButton();
            if((co.getId() < co.getReferingTheme().getCourseList().size()) && (co.getId() > 0)) {
                System.out.println(co.getId());
                cv.showPrevButton();
                cv.getPrevCourse().setOnAction((ActionEvent e) -> {
                    Course prevCo = co.getReferingTheme().getCourseList().get(co.getId()-1);
                    setCourseViewEvents(prevCo);
                });
            }
            else cv.hidePrevButton();
            cv.getHomeButton().setOnAction((ActionEvent e1) -> {
                loadHomePane();
            });
            cv.getBackToList().setOnAction((ActionEvent e2) -> {
                loadCourseList();
            });
   }
    
    
}
