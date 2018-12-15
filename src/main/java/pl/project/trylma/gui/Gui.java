package pl.project.trylma.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.project.trylma.client.CommandHandler;
import pl.project.trylma.models.Coord;
import pl.project.trylma.models.Movement;
import pl.project.trylma.models.Owner;


public class Gui extends Stage {
  private Scene scene;
  private BorderPane pane;
  private Label label;
  private int[][] arr;
  private Circle[][] circles;
  private CommandHandler commandHandler;

  private Coord oldCoord;
  private Coord newCoord;


  public Gui() {
    pane = new BorderPane();
    scene = new Scene(pane, 550, 600);
    label = new Label("Welcome!");
    this.sizeToScene();
    setTitle("Trylma");
    setLayout();
    pane.setStyle("-fx-base: rgba(60, 60, 60, 255)");
  }

  public void setFields(int[][] arr) {
    this.arr = arr;
  }

  public void setCommandHandler(CommandHandler commandHandler) {
    this.commandHandler = commandHandler;
  }

  private void setLayout() {
    setScene(scene);
    label.setFont(new Font("Courier New", 16));
    HBox hBox = new HBox();
    Button exitButton = new Button(" Skip ");
    exitButton.setOnAction(e -> commandHandler.sendMove(null));
    VBox leftVbox = new VBox(this.label);
    leftVbox.setAlignment(Pos.CENTER);
    leftVbox.setMinWidth(pane.getWidth()/2);
    VBox rightVbox = new VBox(exitButton);
    rightVbox.setMinWidth(pane.getWidth()/2);
    rightVbox.setAlignment(Pos.CENTER);
    hBox.setPadding(new Insets(10));
    hBox.getChildren().addAll(leftVbox, rightVbox);
    pane.setTop(hBox);
  }

  public void setLabel(String label) {
    this.label.setText(label);
  }

  private void setBoard() {
    Pane pane = new Pane();
    pane.setStyle("-fx-background-color: lightgray");
    int ArrayXSize = arr[0].length;
    int ArrayYSize = arr.length;
    final int horizontalShift = 20;
    final int verticalShift = 15;
    circles = new Circle[ArrayYSize][ArrayXSize];

    double WidthUnit = this.pane.getWidth() / ArrayXSize;
    double HeightUnit = (this.pane.getHeight()-50)/ ArrayYSize;
    double x = WidthUnit / 2;
    double y = HeightUnit / 2;
    Circle circle;

    Stop[] redStop = new Stop[] { new Stop(0, Color.WHITE), new Stop(1, Color.TOMATO)};
    LinearGradient lgRed = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, redStop);

    Stop[] blueStop= new Stop[] { new Stop(0, Color.WHITE), new Stop(1, Color.STEELBLUE)};
    LinearGradient lgBlue = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, blueStop);

    Stop[] greenStop= new Stop[] { new Stop(0, Color.WHITE), new Stop(1, Color.GREEN)};
    LinearGradient lgGreen = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, greenStop);

    Stop[] violetStop= new Stop[] { new Stop(0, Color.WHITE), new Stop(1, Color.PALEVIOLETRED)};
    LinearGradient lgViolet = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, violetStop);

    Stop[] orangeStop= new Stop[] { new Stop(0, Color.WHITE), new Stop(1, Color.BROWN)};
    LinearGradient lgOrange = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, orangeStop);

    Stop[] yellowStop= new Stop[] { new Stop(0, Color.WHITE), new Stop(1, Color.DARKGOLDENROD)};
    LinearGradient lgYellow = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, yellowStop);

    Stop[] whiteStop= new Stop[] { new Stop(0, Color.LIGHTGREY), new Stop(1, Color.WHITE)};
    LinearGradient lgWhite = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, whiteStop);

    for (int i = 0; i < ArrayYSize; i++) {
      for (int j = 0; j < ArrayXSize; j++) {
        switch (arr[i][j]) {
          case 1:
            circle = new Circle(x+horizontalShift, y+verticalShift, 15);
            circle.setFill(lgRed);
            circle.setStroke(Color.TOMATO);
            circles[i][j] = circle;
            pane.getChildren().add(circle);
            break;
          case 2:
            circle = new Circle(x+horizontalShift, y+verticalShift, 15);
            circle.setFill(lgBlue);
            circle.setStroke(Color.STEELBLUE);
            circles[i][j] = circle;
            pane.getChildren().add(circle);
            break;
          case 3:
            circle = new Circle(x+horizontalShift, y+verticalShift, 15);
            circle.setFill(lgGreen);
            circle.setStroke(Color.GREEN);
            circles[i][j] = circle;
            pane.getChildren().add(circle);
            break;
          case 4:
            circle = new Circle(x+horizontalShift, y+verticalShift, 15);
            circle.setFill(lgViolet);
            circle.setStroke(Color.PALEVIOLETRED);
            circles[i][j] = circle;
            pane.getChildren().add(circle);
            break;
          case 5:
            circle = new Circle(x+horizontalShift, y+verticalShift, 15);
            circle.setFill(lgOrange);
            circle.setStroke(Color.BROWN);
            circles[i][j] = circle;
            pane.getChildren().add(circle);
            break;
          case 6:
            circle = new Circle(x+horizontalShift, y+verticalShift, 15);
            circle.setFill(lgYellow);
            circle.setStroke(Color.DARKGOLDENROD);
            circles[i][j] = circle;
            pane.getChildren().add(circle);
            break;
          case 7:
            circle = new Circle(x+horizontalShift, y+verticalShift, 15);
            circles[i][j] = circle;
            circle.setStroke(Color.WHITE);
            circle.setFill(lgWhite);
            pane.getChildren().add(circle);
        }
        x += WidthUnit;
      }
      x = WidthUnit / 2;
      y += HeightUnit;
    }
    Platform.runLater(()->this.pane.setCenter(pane));
  }

  public void makeMove(Movement movement) {
    int oldX, oldY, newX, newY;
    oldX = movement.getFrom().getX();
    oldY = movement.getFrom().getY();
    newX = movement.getTo().getX();
    newY = movement.getTo().getY();

    arr[oldY][oldX] = Owner.NONE.getValue();
    arr[newY][newX] = movement.getFrom().getOwner().getValue();

    refresh();
  }

  public void refresh() {
    setBoard();
  }

  public void activate(Owner owner) {
    int ArrayXSize = arr[0].length;
    int ArrayYSize = arr.length;
    for (int i = 0; i < ArrayYSize; i++) {
      for (int j = 0; j < ArrayXSize; j++) {
        if (arr[i][j] == owner.getValue()) {
          int finalJ = j;
          int finalI = i;
          circles[i][j].setOnMousePressed(e -> {
            oldCoord = new Coord(finalJ, finalI);
            newCoord = null;
          });
        } else if (arr[i][j] == Owner.NONE.getValue()) {
          int finalJ = j;
          int finalI = i;
          circles[i][j].setOnMousePressed(e -> {
            newCoord = new Coord(finalJ, finalI);
            if (oldCoord != null) {
              try {
                commandHandler.sendMove(new Movement(oldCoord, newCoord, owner));
                oldCoord=null;
              } catch (Exception f) {
                f.printStackTrace();
              }
            }
          });
        }
      }
    }
  }
}
