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

class TrylmaClient {
  private static CommandHandler commandHandler;
  private Owner id;
  private Gui gui;
  private static Movement movement;

  TrylmaClient(){
    commandHandler = new CommandHandler(this);
    gui = new Gui();
    gui.setCommandHandler(this.commandHandler);
    commandHandler.start();
  }

  public void setMovement(Movement movement) {
    this.movement = movement;
  }

  public Gui getGui() {
    return gui;
  }

  void setId(Owner id) {
    this.id=id;
  }

  public Owner getId() {
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
    ///Metoda wywołana tylko dla pierwszego gracza
    // który podłączy sie do serwera.
    // wywołanie tej metody oznacza ze serwer czeka
    // aż gracz podejmię decyjze o ilości graczy.
    // Po podjęciu decyzji client wysyła obiekt PlayerOptions
    //  wykonując : commandHandler.sendPlayersOptions(.....);
    // wywołanie powyższej funkcji może odbyć sie
    // z dowolnego miejsca w programie.
    int real = numOfReal();
    int bot = numOfBot(real);
    Platform.runLater(() -> commandHandler.sendPlayersOptions(new PlayerOptions(bot, real)));
  }

  void setMessage(String message) {
    gui.setLabel(message);
  }

  void myTurn() {
    // wywołanie tej metody oznacza ze serwer czeka
    // aż gracz podejmię decyjze o ruchu.
    // Po podjęciu decyzji client wysyła obiekt Move
    //  wykonując : commandHandler.sendMove(.....);
    // wywołanie powyższej funkcji może odbyć sie
    // z dowolnego miejsca w programie.
    // np. w Handlerze przycisku "wykonaj ruch" na GUI
    // Uwaga!! W tym momencie nie zatwierdzamy ruchu
    // na planszy klienta. Jeżeli ruch bedzie poprawny
    // to server wywoła metodę doMove(Move move)
    // u każdego gracza w grze.
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
    //metoda Informuje o końcu gry z danym rezultatem.
  }
}
