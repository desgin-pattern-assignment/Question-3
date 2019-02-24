package question3;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public final class GraphicsController implements Initializable {

    private GraphicsTree graphicsTree;

    @FXML
    private BorderPane root_container;
    @FXML
    private TextField input_name;
    @FXML
    private TextField input_job_title;
    @FXML
    private TextField input_boss_id;
    @FXML
    private TextField input_salary;
    @FXML
    private TextField input_id;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        graphicsTree = new GraphicsTree();
        root_container.setCenter(graphicsTree);
        graphicsTree.widthProperty().bind(root_container.widthProperty());
        graphicsTree.heightProperty().bind(root_container.heightProperty().subtract(50));
        showMessage("Each node in the tree has the following format:"
                + "\nRow 1 = ID"
                + "\nRow 2 = Name"
                + "\nRow 3 = Job Title"
                + "\nRow 4 = Salary"
                + "\nRow 5 = Control Span"
                + "\n\nMaximize the window (when the tree is displayed) for better UX", false);
    }

    @FXML
    private void addOnAction(ActionEvent event) {
        String name = input_name.getText();
        if (name.isEmpty()) {
            showMessage("Please specify the name of the employee", true);
            return;
        }
        String jobTitle = input_job_title.getText();
        if (jobTitle.isEmpty()) {
            showMessage("Please specify the job title of the employee", true);
            return;
        }
        String bossIDStr = input_boss_id.getText();
        int bossID;
        if (bossIDStr.isEmpty()) {
            showMessage("Please specify the employee's boss", true);
            return;
        } else {
            try {
                bossID = Integer.parseInt(bossIDStr);
            } catch (Exception e) {
                showMessage("Please specify a correct ID of the employee's boss", true);
                return;
            }
        }
        Employee boss = findEmployee(Main.CEO, bossID);
        if (boss == null) {
            showMessage("Please specify a correct ID of the employee's boss", true);
            return;
        }
        String salaryStr = input_salary.getText();
        int salary;
        if (salaryStr.isEmpty()) {
            showMessage("Please specify the employee's salary", true);
            return;
        } else {
            try {
                salary = Integer.parseInt(salaryStr);
            } catch (Exception e) {
                showMessage("Invalid input for employee's salary", true);
                return;
            }
        }
        Employee newEmp = new Employee(name, jobTitle, salary);
        boss.add(newEmp);
        graphicsTree.createTree();
        input_name.clear();
        input_job_title.clear();
        input_boss_id.clear();
        input_salary.clear();
        showMessage("Employee with ID: " + newEmp.getID() + " added under employee with ID: " + boss.getID(), false);
    }

    @FXML
    private void removeOnAction(ActionEvent event) {
        String idStr = input_id.getText();
        int id;
        if (idStr.isEmpty()) {
            showMessage("Please specify the employee's ID", true);
            return;
        } else {
            try {
                id = Integer.parseInt(idStr);
            } catch (Exception e) {
                showMessage("Please specify a correct ID of the employee", true);
                return;
            }
        }
        Employee employee = findEmployee(Main.CEO, id);
        if (employee == null) {
            showMessage("Please specify a correct ID of the employee", true);
            return;
        }
        if (employee.getID() == Main.CEO.getID()) {
            showMessage("Can not remove the CEO", true);
            return;
        }
        employee.getBoss().remove(employee);
        graphicsTree.createTree();
        input_id.clear();
        showMessage("Employee with ID: " + employee.getID() + " removed", false);
        
    }

    private static Employee findEmployee(Employee root, int id) {
        if (root == null) {
            return root;
        }
        if (root.getID() == id) {
            return root;
        }
        Employee emp = null;
        for (Employee child : root.getSubordinates()) {
            emp = findEmployee(child, id);
            if (emp != null) {
                break;
            }
        }
        return emp;
    }
    
    private static void showMessage(String message, boolean isError) {
        Alert alert = new Alert(isError ? Alert.AlertType.ERROR : Alert.AlertType.INFORMATION,
                message, ButtonType.OK);
        alert.showAndWait().filter(response -> response == ButtonType.OK).ifPresent(response -> alert.close());
    }
}
