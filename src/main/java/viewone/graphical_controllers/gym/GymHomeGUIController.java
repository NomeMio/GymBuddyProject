package viewone.graphical_controllers.gym;

import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;

public class GymHomeGUIController {

    @FXML
    public void manageAthletes() throws Exception {
        String path = "/viewone/gym/GymUserView.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }

    @FXML
    public void managePT() throws Exception {
        String path = "/viewone/gym/GymPTView.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
    @FXML
    public void manageGymEquipment() throws Exception {
        String path = "/viewone/gym/GymEquipmentView.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
    @FXML
    public void sendCommunication() throws Exception {
        String path = "/viewone/gym/SendGymCommunication.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }
    @FXML
    public void logout() throws Exception {
        String path = "/viewone/launcher/GymFirst.fxml";
        SwitchPage.setStage(MainStage.stage,path);
    }


}
