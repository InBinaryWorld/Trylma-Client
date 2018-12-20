package pl.project.trylma.client;


import javafx.application.Platform;
import pl.project.trylma.models.Movement;
import pl.project.trylma.models.Owner;
import pl.project.trylma.models.PlayerOptions;
import pl.project.trylma.models.Result;

import javax.swing.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class CommandHandler extends Thread {
  private TrylmaClient client;
  private Socket socket;
  private ObjectInputStream in;
  private ObjectOutputStream out;

  /**
   * Try to connect with server
   * and set input, output streams.
   */
  CommandHandler(TrylmaClient client) {
    this.client = client;
    try {
      String address;
      address = JOptionPane.showInputDialog("Enter Server IP address:","localhost" );
      socket = new Socket(address, 9001);
      out = new ObjectOutputStream(socket.getOutputStream());
      out.flush();
      in = new ObjectInputStream(socket.getInputStream());
    } catch (IOException e) {
      System.out.println("Cannot connect to server.");
      System.exit(0);
    }
  }

  /**
   * Listens commands from server
   * and make appropriate actions.
   */
  public void run() {
    Object object;
    String command;
    try {
      label:
      while (true) {
        object = in.readObject();
        if (object instanceof String) {
          command = (String) object;
          switch (command) {
            case "SET_ID":
              client.setId((Owner) in.readObject());
              break;
            case "SET_SERVER_OPTIONS":
              Platform.runLater(() -> client.setServerOptions());
              break;
            case "MESSAGE":
              String s = (String) in.readObject();
              Platform.runLater(() -> client.setMessage(s));
              break;
            case "YOUR_MOVE":
              client.myTurn();
              break;
            case "DO_MOVE":
              client.doMove((Movement) in.readObject());
              break;
            case "SET_BASEBOARD":
              client.start((int[][]) in.readObject());
              break;
            case "END_GAME":
              client.endGame((Result) in.readObject());
              break label;
          }
        }
      }
    } catch (IOException | ClassNotFoundException e1) {
      if (!e1.getMessage().equals("Socket closed")) {
        e1.printStackTrace();
      }
    }
    try {
      socket.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Sends move to server. */
  public void sendMove(Movement move) {
    try {
      out.writeObject(move);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Sends game settings to server. */
  void sendPlayersOptions(PlayerOptions playerOptions) {
    try {
      out.writeObject(playerOptions);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /** Closes socket. */
  void disconnect() {
    try {
      socket.close();
    } catch (IOException ignored) {
    }
  }
}
