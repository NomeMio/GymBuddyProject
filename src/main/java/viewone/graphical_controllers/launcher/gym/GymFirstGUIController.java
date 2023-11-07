package viewone.graphical_controllers.launcher.gym;

import utils.MainStage;
import utils.SwitchPage;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import viewone.GymBuddy;

public class GymFirstGUIController {
    //TODO sistema sti cazzo di pulsanti avanti indietro che non apparono
    @FXML
    protected void login(ActionEvent event) throws Exception {
        String path = "/viewone/launcher/GymLogin.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);

    }

    @FXML protected void registration(ActionEvent event) throws Exception {
       String path = "/viewone/launcher/GymRegistration.fxml";
       SwitchPage.setStage(MainStage.getStage(),path);

    }

    @FXML public void getInfo(MouseEvent event) throws Exception {
        //TODO organizza il bottone info
    }

    @FXML public void goBack(MouseEvent event) throws Exception {
        String path = "/viewone/launcher/ChooseActor.fxml";
        SwitchPage.setStage(MainStage.getStage(),path);

    }

}
