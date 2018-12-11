package pl.project.trylma.models;

public class PlayerOptions {
  private int bot;
  private int real;

  public int getReal() {
    return real;
  }

  public void setReal(int real) {
    this.real = real;
  }

  public int getBot() {
    return bot;
  }

  public void setBot(int bot) {
    this.bot = bot;
  }

  public int getNumOfPlayers(){
    return bot+real;
  }

  public PlayerOptions(int bot, int real) {
    this.bot = bot;
    this.real = real;
  }
}