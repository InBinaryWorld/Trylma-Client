package pl.project.trylma.gui.circles;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public class RedCircle extends Circle {
  private Stop[] redStop = new Stop[] { new Stop(0, Color.WHITE),
                                        new Stop(1, Color.TOMATO)};
  private LinearGradient lgRed = new LinearGradient(0, 0, 1,
          0, true, CycleMethod.NO_CYCLE, redStop);

  public RedCircle(double xCenter, double yCenter, double radius) {
    super(xCenter, yCenter, radius);
    setFill(lgRed);
    setStroke(Color.TOMATO);
  }

  public RedCircle(double radius) {
    super(radius);
    setFill(lgRed);
    setStroke(Color.TOMATO);
  }
}
