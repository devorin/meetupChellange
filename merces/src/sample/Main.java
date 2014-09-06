package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("Toruń JUG - Nagrody");

        BorderPane componentLayout = new BorderPane();
        componentLayout.setPadding(new Insets(20));

        String[] eventList = {"Event1", "Event2"};
        Map<String, FlowPane> sMap = new HashMap<String, FlowPane>();
        for ( String eventName : eventList) {
            final FlowPane pane = new FlowPane();
            pane.setHgap(100);
            Label label = new Label(eventName);
            pane.getChildren().add(label);
            sMap.put(eventName, pane);
        }
        final FlowPane choicePane = eventListPane(componentLayout, eventList, sMap);
        componentLayout.setTop(choicePane);


//        //The button uses an inner class to handle the button click event
//        Button vegFruitBut = new Button("Fruit or Veg");
//        vegFruitBut.setOnAction(new EventHandler() {
//
//            @Override
//            public void handle(Event event) {
//                //switch the visibility for each FlowPane
//                choicePane.setVisible(true);
//                listPane.setVisible(true);
////                choicePane.setVisible(!choicePane.isVisible());
////                listPane.setVisible(!listPane.isVisible());
//            }
//
//        });
//
//        componentLayout.setBottom(vegFruitBut);

        Scene appScene = new Scene(componentLayout,500,500);
        primaryStage.setScene(appScene);
        primaryStage.show();
    }

    private FlowPane eventListPane(final BorderPane componentLayout, String[] eventList, final Map<String, FlowPane> sMap) {
        final FlowPane choicePane = new FlowPane();
        choicePane.setHgap(100);
        Label eventLabel = new Label("Wydarzenia");
        final ChoiceBox meetupEventList = new ChoiceBox(FXCollections.observableArrayList(eventList));
//        meetupEventList.getSelectionModel().selectLast();

        choicePane.getChildren().add(eventLabel);
        choicePane.getChildren().add(meetupEventList);

        meetupEventList.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println(meetupEventList.getItems().get((Integer) number2));
                componentLayout.setCenter(sMap.get(meetupEventList.getItems().get((Integer) number2)));
            }
        });

        return choicePane;
    }
}
