package viewone.graphicalControllers.pt;

import beans.ExerciseBean;
import beans.ExerciseForWorkoutRoutineBean;
import beans.RequestBean;
import beans.SearchBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.manageListView.listCells.ExerciseListCellFactoryForStatus;
import engineering.manageListView.ManageExerciseList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import utils.MainStage;
import utils.SwitchPage;
import viewone.ExerciseStatusButtonController;

import java.net.URL;
import java.util.*;

public class SetExerciseStatusGUIController implements Initializable{

    private Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap;
    private RequestBean requestBean;
    private SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;
    @FXML private ListView<ExerciseBean> exerciseDBList;
    @FXML private Button suspendStatusButton;
    @FXML private Button activeStatusButton;
    @FXML private TextField searchExerciseText;
    @FXML private Button setStatusButton;
    private final ExerciseStatusButtonController exerciseStatusButtonController = new ExerciseStatusButtonController();;


    @FXML
    public void logout() throws Exception{
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }
    @FXML
    public void turnBackToRoutine() throws Exception{
        SatisfyWorkoutRoutineRequestGUIController controller = (SatisfyWorkoutRoutineRequestGUIController) SwitchPage.setStage(MainStage.getStage(),"SatisfyWorkoutRoutineRequest.fxml","pt",1);
        Objects.requireNonNull(controller).setBackValue(requestBean, satisfyWorkoutRequestsController, dayExercisesMap);
    }
    @FXML
    public void searchExercise(){
        //TODO controlla se funziona
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.searchExercise(new SearchBean(searchExerciseText.getText()));
        System.out.println("Exercise Bean List Size: " + exerciseBeanList.size());
        ManageExerciseList.updateList(exerciseDBList, exerciseBeanList);
    }

    public ListView<ExerciseBean> getExerciseDBList() {
        return exerciseDBList;
    }

    public void setVisibleButtons(Boolean bool) {
        activeStatusButton.setVisible(bool);
        suspendStatusButton.setVisible(bool);
        setStatusButton.setVisible(bool);
    }

    public void setValue(RequestBean requestBean, SatisfyWorkoutRequestsController satisfyWorkoutRequestsController, Map<String, List<ExerciseForWorkoutRoutineBean>> dayExercisesMap) {
        this.dayExercisesMap=dayExercisesMap;
        this.requestBean= requestBean;
        this.satisfyWorkoutRequestsController=satisfyWorkoutRequestsController;
        ManageExerciseList.setListenerDBSet(exerciseDBList, satisfyWorkoutRequestsController, this);
        List<ExerciseBean> exerciseBeanList = satisfyWorkoutRequestsController.getGymExerciseBean();
        ManageExerciseList.updateList(exerciseDBList, exerciseBeanList);
    }

    @FXML
    public void changeStatus(ActionEvent event) {
        exerciseStatusButtonController.statusButtonAction(event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        exerciseDBList.setCellFactory(new ExerciseListCellFactoryForStatus());
        setVisibleButtons(false);
    }

    public void setFireBotton(int i) {
        if(i==1){
            activeStatusButton.fire();
        } else {
            suspendStatusButton.fire();
        }
    }

    @FXML public void setStatus() {
        ExerciseBean selectedExercise = exerciseDBList.getSelectionModel().getSelectedItem();
        String selectedStatus;
        if(activeStatusButton.isPressed()){
            selectedStatus = "active";
        } else {
            selectedStatus = "suspended";
        }
        satisfyWorkoutRequestsController.setStatus(selectedExercise, selectedStatus);
    }
}
