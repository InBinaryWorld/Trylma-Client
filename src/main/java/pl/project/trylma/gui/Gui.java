package pl.project.trylma.gui;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import pl.project.trylma.client.CommandHandler;
import pl.project.trylma.client.TrylmaClient;
import pl.project.trylma.gui.circles.*;
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
    scene = new Scene(pane, 550, 650);
    label = new Label("Welcome!");
    this.sizeToScene();
    setTitle("Trylma");
    setLayout();
    pane.setStyle("-fx-base: rgba(60, 60, 60, 255)");
    getIcons().add(new Image("/upIcon.png"));
  }

  public void setFields(int[][] arr) {
    this.arr = arr;
  }

  public void setCommandHandler(CommandHandler commandHandler) {
    this.commandHandler = commandHandler;
  }

  private Circle assignCircle(Owner owner) {
    switch (owner) {
      case FIRST:
        return new RedCircle(10);
      case SECOND:
        return new BlueCircle(10);
      case THIRD:
        return new GreenCircle(10);
      case FOURTH:
        return new PurpleCircle(10);
      case FIFTH:
        return new BrownCircle(10);
      case SIXTH:
        return new GoldenCircle(10);
    }
    return null;
  }

  public void setFooter(Owner owner) {
    HBox footer = new HBox();
    footer.setPadding(new Insets(10));
    VBox vBoxleft = new VBox();
    Label label = new Label("Your pawns: ");
    label.setFont(new Font("Courier New", 16));
    vBoxleft.getChildren().addAll(label);
    VBox vBoxright = new VBox(assignCircle(owner));
    footer.getChildren().addAll(vBoxleft, vBoxright);
    this.pane.setBottom(footer);
  }

  public void setLayout() {
    setScene(scene);
    label.setFont(new Font("Courier New", 16));
    HBox hBox = new HBox();
    Button skip = new Button(" Skip ");
    skip.setOnAction(e -> {
      if(TrylmaClient.isMyTurn()) {
        commandHandler.sendMove(null);
        TrylmaClient.setMyTurn(false);
      }
    });
    VBox leftVbox = new VBox(this.label);
    leftVbox.setAlignment(Pos.CENTER);
    leftVbox.setMinWidth(pane.getWidth()/2);
    VBox rightVbox = new VBox(skip);
    rightVbox.setMinWidth(pane.getWidth()/2);
    rightVbox.setAlignment(Pos.CENTER);
    hBox.setPadding(new Insets(10));
    hBox.getChildren().addAll(leftVbox, rightVbox);
    pane.setTop(hBox);
  }

  public void setLabel(String label) {
    this.label.setText(label);
  }

  private void updateCircles() {
    final int ArrayXSize = arr[0].length;
    final int ArrayYSize = arr.length;
    circles = new Circle[ArrayYSize][ArrayXSize];
    final int horizontalShift = 20;
    final int verticalShift = 15;
    final double VerticalUnit = this.pane.getWidth() / ArrayXSize;
    final double HorizontalUnit = (this.pane.getHeight()-75)/ ArrayYSize;
    double x = VerticalUnit / 2;
    double y = HorizontalUnit / 2;
    for (int i = 0; i < ArrayYSize; i++) {
      for (int j = 0; j < ArrayXSize; j++) {
        switch(arr[i][j]) {
          case 1 : circles[i][j] = new RedCircle(x+horizontalShift, y+verticalShift, 15);
                   break;
          case 2 : circles[i][j] = new BlueCircle(x+horizontalShift, y+verticalShift, 15);
                   break;
          case 3 : circles[i][j] = new GreenCircle(x+horizontalShift, y+verticalShift, 15);
                   break;
          case 4 : circles[i][j] = new PurpleCircle(x+horizontalShift, y+verticalShift, 15);
                   break;
          case 5 : circles[i][j] = new BrownCircle(x+horizontalShift, y+verticalShift, 15);
                   break;
          case 6 : circles[i][j] = new GoldenCircle(x+horizontalShift, y+verticalShift, 15);
                   break;
          case 7 : circles[i][j] = new WhiteCircle(x+horizontalShift, y+verticalShift, 15);
                   break;
        }
        x += VerticalUnit;
      }
      x = VerticalUnit/2;
      y += HorizontalUnit;
    }
  }

  private void setBoard() {
    updateCircles();
    final int ArrayXSize = arr[0].length;
    final int ArrayYSize = arr.length;
    Pane pane = new Pane();
    pane.setStyle("-fx-background-color: lightgray");
    for (int i = 0; i < ArrayYSize; i++) {
      for (int j = 0; j < ArrayXSize; j++) {
        if (circles[i][j] != null)
          pane.getChildren().add(circles[i][j]);
      }
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
                TrylmaClient.setMyTurn(false);
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
