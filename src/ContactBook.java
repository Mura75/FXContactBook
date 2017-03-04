import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;



/**
 * Created by Murager on 3/1/17.
 */
public class ContactBook extends Application implements CreateContactDialog.OnContactCreate {

    //Okno
    Stage window;


    Scene sceneMain;

    //Maket na new budut raspolozhini graficheskie elementi elementi
    StackPane layout;

    //Knopka
    Button buttonHello, buttonGoodbye;

    Button buttonCreate, buttonUpdate, buttonDelete,
            buttonFindContact, buttonSortContactBy;

    //Spisok
    ListView listView;

    ObservableList<String> stringList = FXCollections.observableArrayList(
            "qwerty", "qwerty1", "qwerty2", "qwerty3"
    );

    Label label;

    //button -> layout -> sceneMain -> window

    @Override
    public void start(Stage primaryStage) throws Exception {
        window = primaryStage;
        window.setTitle("Contact book");

        layout = new StackPane();

        label = new Label();
        label.setText("Text");
        label.setTranslateX(-200);
        label.setTranslateY(-100);

        buttonHello = new Button("Hello");
        buttonHello.setOnAction(e -> label.setText("hello"));

        buttonGoodbye = new Button("Goodbye");
        //buttonGoodbye.setTranslateY(50);
        buttonGoodbye.setOnAction(e -> label.setText("Goodbye"));


        listView = new ListView(stringList);
        //listView.setItems(stringList);


        buttonCreate = new Button();
        buttonCreate.setText("Sozdiat");
        buttonCreate.setPrefSize(90, 30);
        buttonCreate.setOnAction(e -> {
            //stringList.add("Hello!");

            CreateContactDialog.display(this);

        });


        buttonUpdate = new Button();
        buttonUpdate.setText("Obnovit");
        buttonUpdate.setOnAction(e -> {

        });

        buttonDelete = new Button();
        buttonDelete.setText("Udalit");
        buttonDelete.setOnAction(e -> {
            int itemPosition = listView.
                    getSelectionModel()
                    .getSelectedIndex();

            if (itemPosition >= 0) {
                stringList.remove(itemPosition);
            }
            else {
                NotificationMessage.display("You should select name");
            }

        });

        buttonFindContact = new Button();
        buttonFindContact.setText("Naiti");
        buttonFindContact.setOnAction(e-> {

        });

        buttonSortContactBy = new Button();
        buttonSortContactBy.setText("Sortiroviat po");
        buttonSortContactBy.setOnAction(e -> {

        });

        VBox leftMenu = new VBox(8);
        leftMenu.getChildren().addAll(buttonCreate, buttonUpdate, buttonDelete,
                buttonFindContact, buttonSortContactBy);


        HBox holder = new HBox(16);
        holder.getChildren().addAll(leftMenu, listView);


        //listView.setItems(stringList);


        layout.getChildren().addAll(holder);


        sceneMain = new Scene(layout, 500, 300);



        window.setScene(sceneMain);
        window.show();

    }



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void contactCreated(String data) {
        stringList.add(data);
    }


    public static class NotificationMessage {

        public static void display(String message) {

            Stage window = new Stage();

            Label label = new Label();
            label.setText(message);

            StackPane layout = new StackPane();
            layout.getChildren().addAll(label);

            Scene scene = new Scene(layout, 150, 75);

            window.setScene(scene);
            window.show();
        }
    }
}
