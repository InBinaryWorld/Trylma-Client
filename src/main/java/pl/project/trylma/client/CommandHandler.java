package pl.project.trylma.client;


import pl.project.trylma.models.Movement;
import pl.project.trylma.models.Owner;
import pl.project.trylma.models.PlayerOptions;
import pl.project.trylma.models.Result;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommandHandler extends Thread {
  private TrylmaClient client;
  private Socket socket;
  private ObjectInputStream in;
  private ObjectOutputStream out;

  CommandHandler(TrylmaClient client) {
    this.client=client;
    try {
      socket = new Socket("localhost", 9001);
      in = new ObjectInputStream(socket.getInputStream());
      out = new ObjectOutputStream(socket.getOutputStream());
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void run() {
    Object object;
    String command;
    try {
      while (true) {
        object = in.readObject();
        if (object instanceof String) {
          command = (String)object;
          if (command.equals("SET_ID")) {
            client.setId((Owner)in.readObject());
          } else if (command.equals("SET_SERVER_OPTIONS")) {
            client.setServerOptions();
          } else if (command.equals("MESSAGE")) {
            client.setMessage((String)in.readObject());
          } else if (command.equals("YOUR_MOVE")) {
            client.myTurn();
          } else if (command.equals("DO_MOVE")) {
            //client.doMove((Movement)in.readObject());
            client.doMove((Movement) in.readObject());
          } else if (command.equals("END_GAME")) {
            client.endGame((Result)in.readObject());
            break;
          }
        } else {
            out.writeObject("ERROR");
        }
      }
    } catch (IOException e1) {
      e1.printStackTrace();
    } catch (ClassNotFoundException e1) {
      e1.printStackTrace();
    }
    try {
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void sendMove(Movement move) throws IOException {
    out.writeObject(move);
  }

  public void sendPlayersOptions(PlayerOptions playerOptions){
    try {
      out.writeObject(playerOptions);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
