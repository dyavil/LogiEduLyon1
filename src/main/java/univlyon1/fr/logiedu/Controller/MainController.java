/* 
 * Quintard LivaÃ¯
 * Project for Logiciel Educatif
 * UniversitÃ© lyon 1
 */
package univlyon1.fr.logiedu.Controller;

import java.util.Collections;
import java.util.Optional;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
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
import univlyon1.fr.logiedu.View.ExerciceWithSourcesView;
import univlyon1.fr.logiedu.View.MainView;
import univlyon1.fr.logiedu.View.UserPane;
import univlyon1.fr.logiedu.View.infoPane.infoPane;

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
        Collections.sort(this.model.getUsers());
        for(int i = 0; i < 4; i++){
            if(i < this.model.getUsers().size()){
                User us = this.model.getUsers().get(i);
                this.view.addUserPane(new UserPane(us.getUserName(), us.getId()));
            }
        }
        this.view.displayUsersPane(checkMore());
        this.stage.setScene(this.view.getScene());
        
        this.refreshUsersPane();
        
        this.view.getHead().getAddUser().setOnAction((ActionEvent e) -> {
                addUserAction();
            });
        this.logoutHandler();
        
    }
    
    /**
     * Method called to check weither it is needed to add a dropdown
     * button or not, depending on the number of user
     * @return 
     */
    private Boolean checkMore(){
        if(this.model.getUsers().size()>4){
            this.view.getOtherUsersList().getItems().clear();
            this.view.getOtherUsersList().getItems().add("autre...");
            this.view.getOtherUsersList().getSelectionModel().selectFirst();
            for (int i = 4; i < this.model.getUsers().size(); i++) {
                this.view.getOtherUsersList().getItems().add(this.model.getUsers().get(i));
            }
            this.view.getOtherUsersList().getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
                @Override
                public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                    if(((Integer) number2) < 0) System.out.println("deal");
                    else if(view.getOtherUsersList().getItems().get((Integer) number2).equals("autre...")){
                        System.out.println("no select");
                    }
                    else {
                        User usp = (User)view.getOtherUsersList().getItems().get((Integer) number2);
                        view.getOtherUsersListLog().setOnAction((ActionEvent e) -> {
                        model.setLoggedUser(usp.getId());
                        loadHomePane();
                    });
                    }

                }
              });
            return true;
        }else return false;
    }
    
    /**
     * Method used to add a user into the model then
     * update the starting pane accordingly
     */
    private void addUserAction(){
        TextInputDialog dialog = new TextInputDialog("pseudo");
                dialog.setTitle("Pseudo : ");
                dialog.setHeaderText("");
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    this.model.addUser(result.get());
                    updateUserPane();
                    Boolean more =checkMore();
                    this.view.displayUsersPane(more);
                    this.refreshUsersPane();
                }
    }
    
    /**
     * Based on the model, update the userpanes 
     * of the view
     */
    private void updateUserPane(){
        Collections.sort(this.model.getUsers());
        this.view.getUsersPane().clear();
        for(int i = 0; i < 4; i++){
            if(i < this.model.getUsers().size()){
                User us = this.model.getUsers().get(i);
                this.view.addUserPane(new UserPane(us.getUserName(), us.getId()));
            }
        }
    }
    
    /**
     * Set actions on start pane
     */
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
    
    /**
     * Called when logged in, display the home
     * view
     */
    private void loadHomePane(){
        this.view.displayHomePane(this.model.getLoggedUser().getUserName());
        this.loadCourseList();  
        //this.loadExerciceList();
        logoutHandler();
        this.view.getHomePane().getCoursesButton().setOnAction((ActionEvent e) -> {
                this.loadCourseList();  
            });
        this.view.getHomePane().getExercicesButton().setOnAction((ActionEvent e) -> {
                this.loadExerciceList();
            });
    }
    
    /**
     * Method used to handle the course tree appearance 
     * depending on the node type
     */
    private void colorList(){
        this.view.getHomeView().getCoursesList().setCellFactory(tv -> {
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
                    /*if(!it.getIsTheme() && !it.getIsCourse()){
                        Image image = new Image(getClass().getResourceAsStream("/images/square.png"));
                        lab.setGraphic(new ImageView(image));
                    }*/
                    if(!it.getIsTheme() && it.getIsCourse()){
                        Image image = new Image(getClass().getResourceAsStream("/images/puce.png"));
                        lab.setGraphic(new ImageView(image));
                    }
                    if(it.getIsTheme() && !it.getIsCourse()){
                        Image image;
                        if(it.getIsExpanded()){
                            image = new Image(getClass().getResourceAsStream("/images/arrowd.png"));
                        }else{
                            image = new Image(getClass().getResourceAsStream("/images/arrow.png"));
                        }
                        lab.setGraphic(new ImageView(image));
                    }
                    setGraphic(lab);
                }
            }
        };
                
            return cell ;
        });
    }
    
    /**
     * Method used to handle the exercise tree appearance 
     * depending on the node type
     */
    private void exerciceColorList(){
        this.view.getHomeView().getExercicesList().setCellFactory(tv -> {
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
                    /*if(!it.getIsExercice() && !it.getIsCourse()){
                        Image image = new Image(getClass().getResourceAsStream("/images/arrowd.png"));
                        lab.setGraphic(new ImageView(image));
                    }*/
                    if(!it.getIsExercice() && it.getIsCourse()){
                        Image image;
                        if(it.getIsExpanded()){
                            image = new Image(getClass().getResourceAsStream("/images/arrowd.png"));
                        }
                        else{
                            image = new Image(getClass().getResourceAsStream("/images/arrow.png"));
                        }
                        lab.setGraphic(new ImageView(image));
                    }
                    if(it.getIsExercice() && !it.getIsCourse()){
                        Image image = new Image(getClass().getResourceAsStream("/images/puce.png"));
                        lab.setGraphic(new ImageView(image));
                    }
                    setGraphic(lab);
                }
            }
        };
                
            return cell ;
        });
    }

    private void loadCourseList() {
        this.view.getHomeView().getCoursesList().getRoot().getChildren().clear();
        for(Theme th : this.model.getThemes()){
            CourseListItem tbox = new CourseListItem(th.getName(), "no-change", true, th.getId());
            
            TreeItem<HBox> themBranch = new TreeItem<>(tbox);
            themBranch.getChildren().clear();
            for(Course co : th.getCourseList()){
                CourseListItem box = new CourseListItem(co.getTitle(), co.getCourseProgress().getCorrespondingCSS().get(co.getCourseProgress().getState()), true, th.getId(), co.getId());
                TreeItem<HBox> item = new TreeItem<>(box);
                themBranch.getChildren().add(item);
            }
            this.view.getHomeView().getCoursesList().getRoot().getChildren().add(themBranch);
        }
        
        this.view.getHomeView().getCoursesList().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.colorList();
        this.setSelectCourseEventHandler();
        /*this.view.getCoursePane().getHomeButton().setOnAction((ActionEvent e) -> {
                this.loadHomePane();
            });*/
        this.view.getHomeView().getCoursesList().getRoot().setExpanded(true);
        //this.view.displayCoursesPane();
    }
    
    private void logoutHandler(){
        this.view.getHead().getLogoutLabel().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                    view.displayUsersPane(checkMore());
                    model.setLoggedUser(null);
            }

        });
    }
    
    private void loadExerciceList() {
        this.view.getHomeView().getExercicesList().getRoot().getChildren().clear();
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
            this.view.getHomeView().getExercicesList().getRoot().getChildren().add(themBranch);
        }
        
        this.view.getHomeView().getExercicesList().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.exerciceColorList();
        this.setSelectExerciceEventHandler();
        /*this.view.getExercicePane().getHomeButton().setOnAction((ActionEvent e) -> {
                this.loadHomePane();
            });*/
        this.view.getHomeView().getExercicesList().getRoot().setExpanded(true);
        //this.view.displayExercicesPane();
    }
    
    private void loadShortExerciceList(Course co) {
        this.view.getHomeView().getExercicesList().getRoot().getChildren().clear();
        ExerciceListItem tbox = new ExerciceListItem(co.getTitle(), "no-change");

        TreeItem<HBox> themBranch = new TreeItem<>(tbox);
        themBranch.getChildren().clear();
        
        int i = 0;
        for(Exercice exo : co.getExercices()){
            ExerciceListItem exBox = new ExerciceListItem(exo.getName(), exo.getProgress().getCorrespondingCSS().get(exo.getProgress().getState()), true, co.getReferingTheme().getId(), co.getId(), exo.getId());
            TreeItem<HBox> exItem = new TreeItem<>(exBox);
            themBranch.getChildren().add(exItem);
            i++;
        }
        System.out.println(i);
        themBranch.setExpanded(true);
        this.view.getHomeView().getExercicesList().getRoot().getChildren().add(themBranch);
        this.view.getHomeView().getExercicesList().getSelectionModel().select(themBranch);

        this.view.getHomeView().getExercicesList().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        this.exerciceColorList();
        this.setSelectExerciceEventHandler();
        /*this.view.getExercicePane().getHomeButton().setOnAction((ActionEvent e) -> {
                this.loadHomePane();
            });*/
        this.view.getHomeView().getExercicesList().getRoot().setExpanded(true);
        //this.view.displayExercicesPane();
    }


    private void setSelectCourseEventHandler() {
        view.getCoursePane().hidegoButton();
        this.view.getHomeView().getCoursesList().getSelectionModel().getSelectedItem();
        this.view.getHomeView().getCoursesList().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                if(view.getHomeView().getCoursesList().getSelectionModel().getSelectedItem().getValue() instanceof CourseListItem){
                    CourseListItem it = (CourseListItem) view.getHomeView().getCoursesList().getSelectionModel().getSelectedItem().getValue();
                    if(it.getIsCourse()){
                        
                        view.getCoursePane().showgoButton();
                        view.getCoursePane().getGoButton().setOnAction((ActionEvent e) -> {
                            System.out.println("selec th " + it.getThemeId() + ", course : "+ it.getCourseId());
                            Course currCo = model.getThemes().get(it.getThemeId()).getCourseList().get(it.getCourseId());
                            setCourseViewEvents(currCo, 0);
                            
                        });
                        if(event.getButton().equals(MouseButton.PRIMARY)){
                            if(event.getClickCount() == 1){
                                System.out.println("show exercises");
                                Course currCo = model.getThemes().get(it.getThemeId()).getCourseList().get(it.getCourseId());
                                loadShortExerciceList(currCo);
                            }
                            if(event.getClickCount() == 2) {
                                System.out.println("selec th " + it.getThemeId() + ", course : "+ it.getCourseId());
                                Course currCo = model.getThemes().get(it.getThemeId()).getCourseList().get(it.getCourseId());
                                setCourseViewEvents(currCo, 0);
                            }
                            
                        }
                    
                    }
                    else if(it.getIsTheme()){
                        if(event.getButton().equals(MouseButton.PRIMARY)){
                            if(!view.getHomeView().getCoursesList().getSelectionModel().getSelectedItem().isExpanded()) {
                                view.getHomeView().getCoursesList().getSelectionModel().getSelectedItem().setExpanded(true);
                                it.setIsExpanded(true);
                            }
                            else {
                                view.getHomeView().getCoursesList().getSelectionModel().getSelectedItem().setExpanded(false);
                                it.setIsExpanded(false);
                            }
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
        this.view.getHomeView().getExercicesList().getSelectionModel().getSelectedItem();
        this.view.getHomeView().getExercicesList().setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                
                if(view.getHomeView().getExercicesList().getSelectionModel().getSelectedItem().getValue() instanceof ExerciceListItem){
                    ExerciceListItem it = (ExerciceListItem) view.getHomeView().getExercicesList().getSelectionModel().getSelectedItem().getValue();
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
                                if(!view.getHomeView().getExercicesList().getSelectionModel().getSelectedItem().isExpanded()){
                                    view.getHomeView().getExercicesList().getSelectionModel().getSelectedItem().setExpanded(true);
                                    ExerciceListItem itm = (ExerciceListItem)view.getHomeView().getExercicesList().getSelectionModel().getSelectedItem().getValue();
                                    itm.setIsExpanded(true);
                            }
                                else {
                                    view.getHomeView().getExercicesList().getSelectionModel().getSelectedItem().setExpanded(false);
                                    ExerciceListItem itm = (ExerciceListItem)view.getHomeView().getExercicesList().getSelectionModel().getSelectedItem().getValue();
                                    itm.setIsExpanded(false);
                                }
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
        /*egv.getBackButton().setOnAction((ActionEvent e2) -> {
            loadExerciceList();
        });*/
        egv.getCourseButton().setOnAction((ActionEvent e3) -> {
            setCourseViewEvents(co, 0);
        });
    }
    
    private void setExerciceViewEvents(Exercice ex) {
        ExerciceView exv;
        if(!ex.getGotSources()) exv = new ExerciceView(ex.getCorrespondingCourse().getTitle(), ex.getName(), ex.getContent(), (int) view.getTWidth());
        else exv = new ExerciceWithSourcesView(ex.getCorrespondingCourse().getTitle(), ex.getName(), ex.getContent(), (int) view.getTWidth(), ex.getSourceContent(model.getLoggedUser()));
        view.displayExerciceView(exv);
        
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
        /*exv.getBackButton().setOnAction((ActionEvent e2) -> {
            loadExerciceList();
        });*/
        exv.getCourseButton().setOnAction((ActionEvent e3) -> {
            setCourseViewEvents(ex.getCorrespondingCourse(), 0);
        });
        
        if(ex.getGotSources()){
            if(!ex.getProgress().isFinal()){
                ex.getProgress().nextStep();
                model.updateExercice(ex.getCorrespondingCourse(), ex);
            } 
            ((ExerciceWithSourcesView)exv).getValidateButton().setOnAction((ActionEvent e4) -> {
                boolean validEx = ex.compareResult(ex.gotStdExecutionRes(model.getLoggedUser()));
                System.out.println("res :    " + ex.getExpectedOutput());
                if (validEx) {
                    ex.getProgress().nextStep();
                    ((ExerciceWithSourcesView)exv).getCompilePane().setContent("Résultat OK !", "ok");
                    model.updateExercice(ex.getCorrespondingCourse(), ex);
                    boolean allEnd = true;
                    for(Exercice exol : ex.getCorrespondingCourse().getExercices()){
                        if (!exol.getProgress().isFinal()) allEnd = false;
                    }
                    if (allEnd){
                        //System.out.println("ennnnnnd : "+ allEnd);
                        ex.getCorrespondingCourse().getCourseProgress().nextStep();
                        model.updateCourse(ex.getCorrespondingCourse());
                    }
                }
                else ((ExerciceWithSourcesView)exv).getCompilePane().setContent("Résultat erroné !", "warning");
            });
            ((ExerciceWithSourcesView)exv).getRunButton().setOnAction((ActionEvent e4) -> {
            ex.writeToFile(((ExerciceWithSourcesView)exv).getEditor().getCodeAndSnapshot(), model.getLoggedUser());
            if(ex.CompileCode(model.getLoggedUser())) {
                ex.ExecuteCode(model.getLoggedUser());
                String tmp = ex.gotStdExecutionRes(model.getLoggedUser());
                //((ExerciceWithSourcesView)exv).getStdOutput().setText(tmp);
                tmp = tmp.replaceAll("\n", "</br>");
                ((ExerciceWithSourcesView)exv).setOutput("<div style='color:white; font-size:13px; font-family: \"Helvetica\";'><i style='font-size:14px;'>Sortie standard :</i></br><div style='padding-left:5px;'>"+tmp+"</div></div>");
                
                ((ExerciceWithSourcesView)exv).getCompilePane().setContent("Compilation OK !", "ok");
                String tmp2 = ex.gotErrExecutionRes(0, model.getLoggedUser());
                //((ExerciceWithSourcesView)exv).getErrOutput().setText(tmp2);
                tmp2 = tmp2.replaceAll("\n", "</br>");
                System.out.println("lololololol"+tmp);
                if(!tmp2.equals("no error")) ((ExerciceWithSourcesView)exv).addOutput("</br><div style='color:red; font-size:13px; font-family: \"Helvetica\";'><i style='font-size:14px;'>Sortie erreur :</i></br><div style='padding-left:5px;'>"+tmp2+"</div></div>");
                if(!ex.getExecuteLog().equals("")) ((ExerciceWithSourcesView)exv).getCompilePane().setContent(ex.getExecuteLog(), "warning");
                
            }else{
                String tmp = ex.gotErrExecutionRes(1, model.getLoggedUser());
                //((ExerciceWithSourcesView)exv).getErrOutput().setText(tmp);
                System.out.println("lololololol"+tmp);
                tmp = tmp.replaceAll("\n", "</br>");
                ((ExerciceWithSourcesView)exv).setOutput("<div style='color:red; font-size:13px; font-family: \"Helvetica\";'><i style='font-size:14px;'>Sortie erreur :</i></br><div style='padding-left:5px;'>"+tmp+"</div></div>");
                ((ExerciceWithSourcesView)exv).getCompilePane().setContent(ex.getCompileLog(), "warning");
                
            }
            
        });
        }
    }
    
    private void setCourseViewEvents(Course co, int sl){
        
        CourseView cv;
        if(sl == 0)model.loadCourseContent(co);
        if(!"".equals(co.getSlides().get(sl).getImagePath())) {
            cv = new CourseView(co.getReferingTheme().getName(), co.getTitle(), co.getSlides().get(sl).getContent(), (int) view.getTWidth(), co.getSlides().get(sl).getImagePath());
        }
        else cv = new CourseView(co.getReferingTheme().getName(), co.getTitle(), co.getSlides().get(sl).getContent(), (int) view.getTWidth());
            if(co.getCourseProgress().getState() < co.getCourseProgress().getCorrespondingCSS().size()-2){
                co.getCourseProgress().nextStep();
                if (co.getExercices().size() == 0) co.getCourseProgress().nextStep();
                for (Exercice ex : co.getExercices()) {
                    //ex.getProgress().nextStep();
                }
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
            cv.getExercices().setOnAction((ActionEvent e3) -> {
                setExerciceGridViewEvents(co);
            });
   }
    
    
    
}
