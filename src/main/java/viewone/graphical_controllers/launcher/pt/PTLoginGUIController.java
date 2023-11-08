package viewone.graphical_controllers.launcher.pt;

import utils.MainStage;
import utils.SwitchPage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class PTLoginGUIController {
    @FXML
    public void goForward() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"PTHome.fxml","pt",1);
    }

    @FXML
    public void goBack() throws Exception {
        SwitchPage.setStage(MainStage.getStage(),"ChooseActor.fxml","launcher",1);
    }


    public void getInfo(MouseEvent event){
        //TODO Connected with the LogoInfoButton
    }
}
