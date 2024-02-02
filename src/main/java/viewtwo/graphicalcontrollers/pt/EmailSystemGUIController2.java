package viewtwo.graphicalcontrollers.pt;

import beans.RequestBean;
import controllers.SatisfyWorkoutRequestsController;
import engineering.LoggedUserSingleton;
import exceptions.EmailFormException;
import exceptions.NoLoggedUserException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import viewtwo.engegnering.MainMenuSingleton;

import java.io.IOException;
import java.net.URISyntaxException;

public class EmailSystemGUIController2 {

    @FXML private TextField objectTextField;
    @FXML private TextField contentTextField;

    RequestBean selectedRequest;

    @FXML
    public void sendEmail() throws IOException {
        SatisfyWorkoutRequestsController controller;
        try{
            controller = new SatisfyWorkoutRequestsController();
        } catch (NoLoggedUserException e){

                e.callMe(1);
                return;

        }
        try {
            controller.sendClarificationEmail(
                    LoggedUserSingleton.getSingleton().getMyBean(),
                    selectedRequest.getAthleteBean(),
                    objectTextField.getText(),
                    contentTextField.getText()
            );
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        } catch (EmailFormException e) {
            e.callMe(2);
            return;
        }
        MainMenuSingleton.getMainMenu().setActivity("ptHome.fxml", "pt");
    }

    public void setValue(RequestBean selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

}
