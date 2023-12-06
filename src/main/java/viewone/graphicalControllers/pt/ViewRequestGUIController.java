package viewone.graphicalControllers.pt;

import beans.ExerciseBean;
import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.ExerciseInventory;
import engineering.manageListView.ManageRequestList;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.text.Text;
import model.Exercise;
import model.ExerciseStatus;
import model.Gym;
import model.Trainer;
import model.record.Credentials;
import model.record.PersonalInfo;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.time.LocalDate;
import java.util.*;

public class ViewRequestGUIController implements Initializable {

    @FXML
    private ListView<RequestBean> requestList;

    @FXML
    private Text textUsersRequest;

    @FXML
    private Text usernameRequestText;

    private RequestBean selectedRequest;
    private Trainer trainer; //poi gestisci con loggedUser (vedi todo)

    private final SatisfyWorkoutRequestsController satisfyWorkoutRequestsController;

    public ViewRequestGUIController() {
        Gym palestra1 = new Gym("palestra1", new Credentials("alecortix@gmail.com",
                "forzanapule1926"),
                "BBBBBBBBBBBBBBBBBBBBBB", "roma", "Piazza dei Consoli, 11") ;
        this.trainer = new Trainer("AleCortix",
                new PersonalInfo("Alessandro", "Cortese", LocalDate.now(), "CRTLSN99T24H501R", 'm'),
                new Credentials("alecortix@gmail.com", "forzanapule1926"), palestra1);
        satisfyWorkoutRequestsController = new SatisfyWorkoutRequestsController();
    }

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }
    public void logout() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"Login.fxml","launcher",1);
    }
    @FXML
    public void askClarification() throws Exception {
        //TODO sistema la nuova grafica SwitchPage.setStage(MainStage.getStage(),"SatisfyWorkoutRoutineRequest.fxml","pt",1);
    }

    @FXML
    public void wantToCreateNewRoutine() throws Exception {
        if (!requestList.getSelectionModel().getSelectedIndices().isEmpty()) {
            createNewRoutine();
        }
    }







    //TODO ATTENZIONE DEVI SISTEMARE STA COSA QUA PRIMA DI TUTTO



    public void createNewRoutine() throws Exception {
        SatisfyWorkoutRoutineRequestGUIController controller = (SatisfyWorkoutRoutineRequestGUIController) SwitchPage.setStage(MainStage.getStage(), "SatisfyWorkoutRoutineRequest.fxml", "pt", 1);
        Objects.requireNonNull(controller).setValue(selectedRequest, satisfyWorkoutRequestsController);
    }

    @FXML
    public void rejectRequest() {
        //TODO gestisci la cancellazione di una richiesta
        satisfyWorkoutRequestsController.rejectRequest(selectedRequest);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        /* try {*/
        ManageRequestList.setRequestList(requestList, satisfyWorkoutRequestsController);
        requestList.getSelectionModel().selectedItemProperty().
                addListener(new ChangeListener<>() {
                    @Override public void changed(ObservableValue<? extends RequestBean> observableValue, RequestBean oldItem, RequestBean newItem) {
                        if(newItem != null){
                            textUsersRequest.setText(newItem.getInfo());
                            selectedRequest = newItem;
                            usernameRequestText.setText(selectedRequest.getAthleteBean().getUsername() + " Request");
                            System.out.println("Username:" + selectedRequest.getAthleteBean().getUsername() +
                                    "data" + selectedRequest.getRequestDate() + "trainer" + selectedRequest.getTrainerFc());
                        }
                    }
                });

/*        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }
}