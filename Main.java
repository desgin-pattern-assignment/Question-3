package question3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Employee CEO = createEmployees();
    
    public static void main(String... args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLGraphicsPanel.fxml"));
        Scene scene = new Scene(root);
        stage.setTitle("Question 3");
        stage.setScene(scene);
        stage.show();
    }

    public static Employee createEmployees() {
        // root
        Employee ceo = new Employee("Mr. Bigshot", "CEO", 50000);

        // level 1
        Employee marketingVP = new Employee("Mrs. Brainstorm", "VP of Marketing", 30000);
        Employee productionVP = new Employee("Mrs. Maker", "VP of Production", 30000);
        ceo.add(marketingVP);
        ceo.add(productionVP);

        // level 2
        Employee salesManager = new Employee("Ms. Cheque", "Sales Manager", 20000);
        Employee marketingManager = new Employee("Mr. Promo", "Marketing Manager", 20000);
        marketingVP.add(salesManager);
        marketingVP.add(marketingManager);

        Employee productionManager = new Employee("Mr. Machine", "Production Manager", 20000);
        Employee shippingManager = new Employee("Mr. Shipment", "Shipment Manager", 20000);
        productionVP.add(productionManager);
        productionVP.add(shippingManager);

        // level 3
        Employee salesClerk1 = new Employee("Mr. Money", "Sales Clerk", 10000);
        Employee salesClerk2 = new Employee("Mrs. Cash", "Sales Clerk", 10000);
        salesManager.add(salesClerk1);
        salesManager.add(salesClerk2);

        Employee marketingSecretary = new Employee("Mr. Helper", "Marketing Secretary", 10000);
        marketingManager.add(marketingSecretary);

        Employee manufacturer1 = new Employee("Ms. Input", "Manufacture Worker", 10000);
        Employee manufacturer2 = new Employee("Mr. Output", "Manufacture Worker", 10000);
        Employee manufacturer3 = new Employee("Mr. Assembly", "Manufacture Worker", 10000);
        productionManager.add(manufacturer1);
        productionManager.add(manufacturer2);
        productionManager.add(manufacturer3);

        Employee shippingClerk1 = new Employee("Mrs. Tag-It", "Shipping Clerk", 10000);
        Employee shippingClerk2 = new Employee("Mr. Bag-It", "Shipping Clerk", 10000);
        shippingManager.add(shippingClerk1);
        shippingManager.add(shippingClerk2);

        return ceo;
    }
}
