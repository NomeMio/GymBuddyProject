package viewone.graphical_controllers.gym;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import utils.MainStage;
import utils.SwitchPage;

import java.net.URL;
import java.util.ResourceBundle;

public class GymPTRegistrationGUI implements Initializable {

    @FXML
    RadioButton Male;
    @FXML RadioButton Female;

    @FXML public void home() throws Exception {
        String path = "/viewone/gym/GymHome.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    @FXML public void info() throws Exception {
        //TODO per il pulsante info
    }

    @FXML public void goBack() throws Exception {
        String path = "/viewone/gym/GymPTView.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    @FXML public void submit() throws Exception {
        String path = "/viewone/gym/GymPTView.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    } //TODO questo diventa il popup da sitemare e cliccabile solo se ogni campo è stato colmato, sennò stampa "ci sono campi incompleti".


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ToggleGroup group1 = new ToggleGroup();
        Male.setToggleGroup(group1);
        Female.setToggleGroup(group1);
        Male.fire();
    }
}