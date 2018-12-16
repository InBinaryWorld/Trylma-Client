package pl.project.trylma.gui.circles;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public class BrownCircle extends Circle {
  Stop[] brownStop = new Stop[] { new Stop(0, Color.WHITE),
                                  new Stop(1, Color.BROWN)};
  LinearGradient lgBrown = new LinearGradient(0, 0, 1,
          0, true, CycleMethod.NO_CYCLE, brownStop);

  public BrownCircle(double xCenter, double yCenter, double radius) {
    super(xCenter, yCenter, radius);
    setFill(lgBrown);
    setStroke(Color.BROWN);
  }

  public BrownCircle(double radius) {
    super(radius);
    setFill(lgBrown);
    setStroke(Color.BROWN);
  }
}
