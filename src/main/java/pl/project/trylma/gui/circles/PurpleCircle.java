package pl.project.trylma.gui.circles;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public class PurpleCircle extends Circle {
  Stop[] violetStop= new Stop[] { new Stop(0, Color.WHITE),
                                  new Stop(1, Color.PALEVIOLETRED)};
  LinearGradient lgViolet = new LinearGradient(0, 0, 1,
          0, true, CycleMethod.NO_CYCLE, violetStop);

  public PurpleCircle(double xCenter, double yCenter, double radius) {
    super(xCenter, yCenter, radius);
    setFill(lgViolet);
    setStroke(Color.PALEVIOLETRED);
  }

  public PurpleCircle(double radius) {
    super(radius);
    setFill(lgViolet);
    setStroke(Color.PALEVIOLETRED);
  }
}
