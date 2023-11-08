package viewone.graphical_controllers.athlete;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class AthletesRoutineGUIController implements Initializable {

    @FXML
    private ListView<String> athletesList;

    @FXML
    public void goBack() throws Exception {
        String path = "/viewone/athlete/AthleteHome.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    public void logout() throws Exception {
        String path = "/viewone/launcher/AthleteLogin.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);
    }
    @FXML
    public void askForNewRoutine() {
        //TODO gestisci la rischiesta di una nuova scheda
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}