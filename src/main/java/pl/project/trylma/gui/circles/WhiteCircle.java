package pl.project.trylma.gui.circles;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public class WhiteCircle extends Circle {
  Stop[] whiteStop= new Stop[] { new Stop(0, Color.LIGHTGREY),
                                 new Stop(1, Color.WHITE)};
  LinearGradient lgWhite = new LinearGradient(0, 0, 1,
          0, true, CycleMethod.NO_CYCLE, whiteStop);

  public WhiteCircle(double xCenter, double yCenter, double radius) {
    super(xCenter, yCenter, radius);
    setFill(lgWhite);
    setStroke(Color.WHITE);
  }
}
