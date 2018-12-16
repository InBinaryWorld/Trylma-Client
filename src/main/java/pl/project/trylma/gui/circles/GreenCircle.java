package pl.project.trylma.gui.circles;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public class GreenCircle extends Circle {
  Stop[] greenStop= new Stop[] { new Stop(0, Color.WHITE),
                                 new Stop(1, Color.GREEN)};
  LinearGradient lgGreen = new LinearGradient(0, 0, 1,
          0, true, CycleMethod.NO_CYCLE, greenStop);

  public GreenCircle(double xCenter, double yCenter, double radius) {
    super(xCenter, yCenter, radius);
    setFill(lgGreen);
    setStroke(Color.GREEN);
  }

  public GreenCircle(double radius) {
    super(radius);
    setFill(lgGreen);
    setStroke(Color.GREEN);
  }
}
