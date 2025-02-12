package graphics;


import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import saturday.Saturday;
import saturday.collections.TaskList;
import saturday.models.Task;

public class Controller {
    @FXML
    private TextField commandField;
    @FXML
    private VBox taskListContainer;
    @FXML
    private Label responseField;
    @FXML
    private Button exitButton;

    private Saturday saturday;

    @FXML
    public void initialize() {

    }

    public void setSaturday(Saturday saturday) {
        assert saturday != null : "Saturday instance should not be null";
        this.saturday = saturday;
        updateTaskList();
    }

    private void updateTaskList() {
        TaskList taskList = saturday.getTaskList();

        try {
            taskListContainer.getChildren().clear();
            for (Task task : taskList) {
                if (task.isDisplayed()) {
                    TaskView t = TaskView.getTaskView(task);
                    taskListContainer.getChildren().add(t);
                }
            }
        } catch (Exception e) {
            System.out.println("ERROR");
        }
    }

    @FXML
    private void handleCommand() {
        String input = commandField.getText();
        assert input != null : "Input should not be null";
        String response = saturday.getResponse(input);
        updateTaskList();
        responseField.setText(response);
        commandField.clear();
    }

    @FXML
    private void handleExitButtonAction() {
        Stage stage = (Stage) exitButton.getScene().getWindow();
        assert stage != null : "Stage should not be null";
        stage.close();
    }
}
