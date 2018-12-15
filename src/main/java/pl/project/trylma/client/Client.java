package pl.project.trylma.client;

import javafx.application.Application;
import javafx.stage.Stage;

public class Client extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  public void start(Stage primaryStage) throws Exception {
     TrylmaClient trylmaClient = new TrylmaClient();
      primaryStage = trylmaClient.getGui();
      primaryStage.setOnCloseRequest(e -> TrylmaClient.getCommandHandler().disconnect());
      primaryStage.setResizable(false);
      primaryStage.show();
  }
}
