package pl.project.trylma.client;

import pl.project.trylma.client.models.Move;
import pl.project.trylma.client.models.Owner;
import pl.project.trylma.client.models.Result;

class TrylmaClient {
  private CommandHandler commandHandler;
  private Owner id;

  TrylmaClient(){




    //Gdzieś na końcu po utworzeniu GUI
    commandHandler = new CommandHandler(this);
    commandHandler.start();
  }

  void setId(Owner id) {
    this.id=id;
  }

  public Owner getId() {
    return id;
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
  }

  void setMessage(String message) {
    // Metoda przesyła wiadomość od serwera
    // powinna np. ustwiać tekst
    // w jakimś TextField lub TextArea w GUI.
    //nic nie zwraca.
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
  }

  void doMove(Move move) {
    //metoda wykonuje ruch na mapie klienta.
    //nic nie zwraca.
  }

  void endGame(Result result) {
    //metoda Informuje o końcu gry z danym rezultatem.
  }
}
