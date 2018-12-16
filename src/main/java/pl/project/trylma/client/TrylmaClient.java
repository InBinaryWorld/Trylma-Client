package pl.project.trylma.client;

import javafx.application.Platform;
import javafx.scene.control.ChoiceDialog;
import pl.project.trylma.gui.Gui;
import pl.project.trylma.models.Movement;
import pl.project.trylma.models.Owner;
import pl.project.trylma.models.PlayerOptions;
import pl.project.trylma.models.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrylmaClient {
  private static CommandHandler commandHandler;
  private static Owner id;
  private Gui gui;
  private static Movement movement;
  private static boolean myTurn;

  TrylmaClient(){
    commandHandler = new CommandHandler(this);
    gui = new Gui();

    gui.setCommandHandler(this.commandHandler);
    commandHandler.start();
  }

  public static CommandHandler getCommandHandler() {
    return commandHandler;
  }

  public static boolean isMyTurn() {
    return myTurn;
  }

  public static void setMyTurn(boolean myTurn) {
    TrylmaClient.myTurn = myTurn;
  }

  public void setMovement(Movement movement) {
    this.movement = movement;
  }

  public Gui getGui() {
    return gui;
  }

  void setId(Owner id) {
    this.id=id;
    gui.setFooter(id);
  }

  public static Owner getId() {
    return id;
  }

  private int numOfReal() {
    int real = 0;
    List<Integer> choices = new ArrayList<Integer>();
    choices.add(1);
    choices.add(2);
    choices.add(3);
    choices.add(4);
    choices.add(6);
    ChoiceDialog<Integer> dialog = new ChoiceDialog<Integer>(1, choices);
    dialog.setTitle("Trylma");
    dialog.setHeaderText("Number of players");
    dialog.setContentText("Choose number of real players:");
    Optional<Integer> result = dialog.showAndWait();

    if (result.isPresent()) {
      real = result.get();
    }
    return real;
  }

  private int numOfBot(int real) {
    int bot = 0;
    List<Integer> choices = new ArrayList<Integer>();
    switch (real) {
      case 1 : choices.add(1);
               choices.add(2);
               choices.add(3);
               choices.add(5);
               break;
      case 2 : choices.add(0);
               choices.add(1);
               choices.add(2);
               choices.add(4);
               break;
      case 3 : choices.add(0);
               choices.add(1);
               choices.add(3);
               break;
      case 4:  choices.add(0);
               choices.add(2);
               break;
      case 6 : return 0;
    }
    ChoiceDialog<Integer> dialog = new ChoiceDialog<Integer>(choices.get(0), choices);
    dialog.setTitle("Trylma");
    dialog.setHeaderText("Number of bots");
    dialog.setContentText("Choose number of bot players:");
    Optional<Integer> result = dialog.showAndWait();
    if (result.isPresent()){
      bot = result.get();
    }
    return bot;
  }

  void setServerOptions() {
    try {
      int real = numOfReal();
      int bot = numOfBot(real);
      Platform.runLater(() -> commandHandler.sendPlayersOptions(new PlayerOptions(bot, real)));
    } catch (IndexOutOfBoundsException e) {
      System.exit(0);
    }
  }

  void setMessage(String message) {
    gui.setLabel(message);
  }

  void myTurn() {
    setMyTurn(true);
    gui.activate(id);
  }

  public void start(int [][] map) {
    gui.setFields(map);
    gui.refresh();
  }

  public void doMove(Movement move) {
    gui.makeMove(move);
  }

  void endGame(Result result) {
   Platform.runLater(() -> {
     setMessage(result.name());
     commandHandler.disconnect();
   });
    //metoda Informuje o końcu gry z danym rezultatem.
  }

}
