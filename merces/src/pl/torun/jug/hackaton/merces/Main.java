package pl.torun.jug.hackaton.merces;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pl.torun.jug.hackaton.merces.controller.EventController;
import pl.torun.jug.hackaton.merces.model.Event;
import pl.torun.jug.hackaton.merces.model.User;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    EventController eventController = new EventController();
    String eventName;
    String eventId;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Toruń JUG - Nagrody");
        Group root = new Group();
        Scene scene = new Scene(root, 500, 500, Color.WHITE);

        ObservableList<Event> eventList = getEventList();

        final GridPane gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(10);
        gridpane.setVgap(10);

        gridpane.add(eventListPane(eventList, gridpane), 0, 0);

        Label listViewLabel = new Label(" ");
        GridPane.setHalignment(listViewLabel, HPos.CENTER);
        gridpane.add(listViewLabel, 0, 1);

        final ObservableList<String> observableList = FXCollections.observableArrayList();
        final ListView<String> listView = new ListView<String>(observableList);
        listView.setPrefWidth(450);
        listView.setPrefHeight(350);
        listView.setDisable(true);
        gridpane.add(listView, 0, 2);

        Button userListBtn = new Button("Lista uczestników");
        Button awardListBtn = new Button("Lista nagród");
        Button addAwardBtn = new Button("Dodaj nagrodę");
        userListBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                clearList(listView, observableList);
                System.out.println(eventId);
                List<User> eventUsers = eventController.getEventUsers(eventId);
                List<String> eventUserList = new ArrayList<String>();
                for (User  user: eventUsers) {
                    eventUserList.add(user.getName());
                }
                observableList.addAll(eventUserList);
            }
        });
        awardListBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clearList(listView, observableList);
                observableList.addAll("AWARD1", "AWARD2", "AWARD3");
            }
        });
        addAwardBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                GridPane gridPane = new GridPane();
                Label name = new Label("Nazwa");
                gridPane.add(name, 0, 1);

                TextField nameTextField = new TextField();
                gridPane.add(nameTextField, 1, 1);

                Label amount = new Label("Ilość");
                gridPane.add(amount, 0, 2);

                TextField amountTextField = new TextField();
                gridPane.add(amountTextField, 1, 2);

                Button button = new Button();
                button.setText("Dodaj");

                button.setOnAction(new EventHandler<ActionEvent>(){

                    @Override
                    public void handle(ActionEvent actionEvent) {
                        System.out.println("Klikniecie");
                    }
                });

                gridPane.add(button, 0, 3);
                gridpane.add(gridPane, 0, 2);
            }
        });

        HBox hbox = new HBox(5);
        hbox.getChildren().addAll(userListBtn,awardListBtn,addAwardBtn);
        hbox.setDisable(true);

        gridpane.add(hbox, 0, 3);

        root.getChildren().add(gridpane);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private ObservableList<Event> getEventList() {
        List<Event> allEvents = eventController.getAllEvents();
        ObservableList<Event> eventObservableList = FXCollections.observableArrayList(allEvents);
        return eventObservableList;
    }

    private void clearList(ListView<String> listView, ObservableList<String> observableList) {
        ObservableList<String> list = listView.getItems();
//        System.out.println(list);
        observableList.removeAll(list);
    }

    /**
     *
     * @param eventList
     * @param gridpane
     * @return
     */
    private FlowPane eventListPane(ObservableList<Event> eventList, final GridPane gridpane) {
        final FlowPane choicePane = new FlowPane();
        choicePane.setHgap(100);
        final ComboBox<Event> meetupEventList = new ComboBox(FXCollections.observableArrayList(eventList));

        Label eventLabel = new Label("Wydarzenia");
        choicePane.getChildren().add(eventLabel);
        choicePane.getChildren().add(meetupEventList);

        meetupEventList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Event>() {
            @Override
            public void changed(ObservableValue<? extends Event> observableValue, Event event, Event event2) {
                eventName = event2.getName();
                eventId = event2.getId();
//                System.out.println(eventId);
//                System.out.println(observableValue.getValue());

                Node node = gridpane.getChildren().get(1);
                Label label = (Label) node;
                label.setText(eventName);

                Node node2 = gridpane.getChildren().get(2);
                node2.setDisable(false);
                Node node3 = gridpane.getChildren().get(3);
                node3.setDisable(false);
            }
        });

        return choicePane;
    }
}
