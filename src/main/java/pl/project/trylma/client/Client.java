package pl.project.trylma.client;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage primaryStage) throws Exception {
     TrylmaClient trylmaClient = new TrylmaClient();
//    trylmaClient.getGui().refresh();
      primaryStage = trylmaClient.getGui();
      primaryStage.setOnCloseRequest(e -> TrylmaClient.getCommandHandler().disconnect());
      primaryStage.show();
//    Field field = new Field(new Coord(0, 4), Owner.FIRST);
//    Coord coord = new Coord(7, 5);
//    trylmaClient.doMove(new Movement(field, coord, Owner.FIRST));
  }
}
