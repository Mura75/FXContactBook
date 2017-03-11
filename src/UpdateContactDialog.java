import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Created by Murager on 3/11/17.
 */
public class UpdateContactDialog {


    public static void display(OnContactUpdated listener,
                               int position, String name) {

        Stage window = new Stage();
        window.setTitle("Update contact");
        window.setMinHeight(300);
        window.setMinWidth(200);

        VBox verticalBox = new VBox(16);

        TextField tfName = new TextField();
        TextField tfSurname = new TextField();
        TextField tfPhone = new TextField();
        TextField tfEmail = new TextField();

        tfName.setPromptText("Name");
        tfSurname.setPromptText("Surname");
        tfPhone.setPromptText("Phone");
        tfEmail.setPromptText("Email");

        tfName.setText(name);

        Button buttonUpdate = new Button();
        buttonUpdate.setText("Update");
        buttonUpdate.setOnAction(e -> {
            listener.updateContact(position, tfName.getText());
            window.close();
        });

        verticalBox.getChildren()
                .addAll(tfName, tfSurname,
                        tfPhone, tfEmail,
                        buttonUpdate);

        Scene scene = new Scene(verticalBox);
        window.setScene(scene);
        window.show();
    }

    public interface OnContactUpdated {

        void updateContact(int position, String data);
    }
}
