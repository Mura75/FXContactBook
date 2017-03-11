import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.util.Collections;
import java.util.List;


/**
 * Created by Murager on 3/1/17.
 */
public class ContactBook extends Application
        implements CreateContactDialog.OnContactCreate,
        UpdateContactDialog.OnContactUpdated, ChangeListener<String> {

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


    ObservableList<Contact> contactList = FXCollections.observableArrayList(
            new Contact("Bob"), new Contact("Alex"), new Contact("Mike")
    );



    ObservableList<String> filterList = FXCollections.observableArrayList();

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


        TextField searchFiled = new TextField();
        searchFiled.setPromptText("Search");

        searchFiled.textProperty().addListener(this);

        listView = new ListView(contactList);

        listView.setCellFactory(new Callback<ListView<Contact>, ListCell<Contact>>() {

            @Override
            public ListCell<Contact> call(ListView<Contact> param) {

                ListCell<Contact> cell = new ListCell<Contact>(){
                    @Override
                    protected void updateItem(Contact item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item != null) {
                            setText(item.getId() + " ------ " + item.getName());
                        }
                    }
                };



                return cell;
            }
        });

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
            int updatePos = listView.getSelectionModel().getSelectedIndex();

            if (updatePos >= 0) {
                String name = stringList.get(updatePos);
                UpdateContactDialog.display(ContactBook.this, updatePos, name);
            }
            else {
                NotificationMessage.display("You should select name to update!");
            }

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
            filterList.clear();
            String searchedString = searchFiled.getText();
            if (searchedString != null || searchedString.length() > 0) {
                for (String s : stringList) {
                    if (s.toLowerCase().contains(searchedString.toLowerCase())) {
                        filterList.add(s);
                        System.out.println("Finded words:  " + s);
                    }
                }
                listView.setItems(filterList);
            }
        });

        buttonSortContactBy = new Button();
        buttonSortContactBy.setText("Sortiroviat po");
        buttonSortContactBy.setOnAction(e -> {
            Collections.sort(stringList);
        });

        VBox leftMenu = new VBox(8);
        leftMenu.getChildren().addAll(buttonCreate, buttonUpdate, buttonDelete,
                buttonFindContact, buttonSortContactBy);

        VBox rightMenu = new VBox(4);
        rightMenu.getChildren().addAll(searchFiled, listView);


        HBox holder = new HBox(16);
        holder.getChildren().addAll(leftMenu, rightMenu);


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
    public void contactCreated(Contact data) {
//        stringList.add(data);
//        filterList = stringList;
        contactList.add(data);
    }

    @Override
    public void updateContact(int position, String data) {
        stringList.set(position, data);
        filterList = stringList;
    }

    ObservableList<String> tempList = stringList;

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue == null || newValue.length() < 1) {
            filterList.clear();
            listView.setItems(stringList);
        }
        else {
            filterList.clear();
            for (String s : stringList) {
                if (s.toLowerCase().contains(newValue.toLowerCase())) {
                    filterList.add(s);
                    System.out.println("Finded words:  " + s);
                }
            }
            listView.setItems(filterList);
        }
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
