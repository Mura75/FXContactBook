import javafx.scene.CacheHint;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Murager on 3/4/17.
 */
public class CreateContactDialog {


    public static void display(OnContactCreate listener) {

        Stage window = new Stage();
        window.setTitle("Create contact");
        window.setMinHeight(300);
        window.setMinWidth(200);

        VBox vBox = new VBox(16);

        TextField tfName = new TextField();
        TextField tfSurname = new TextField();
        TextField tfPhone = new TextField();
        TextField tfEmail = new TextField();

        tfName.setPromptText("Name");

        Button buttonCreate = new Button();
        buttonCreate.setText("Create");
        buttonCreate.setOnAction(e -> {
            Contact contact = new Contact();
            contact.setName(tfName.getText());

            listener.contactCreated(contact);
            window.close();
        });

        vBox.getChildren().addAll(tfName, tfSurname, tfPhone, tfEmail, buttonCreate);

        Scene scene = new Scene(vBox);
        window.setScene(scene);

        window.show();
    }

    public interface OnContactCreate {

        void contactCreated(Contact data);
    }
}
