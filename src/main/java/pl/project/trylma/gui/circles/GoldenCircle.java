package pl.project.trylma.gui.circles;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Circle;

public class GoldenCircle extends Circle {
  Stop[] goldenStop = new Stop[] { new Stop(0, Color.WHITE),
                                  new Stop(1, Color.DARKGOLDENROD)};
  LinearGradient lgGold = new LinearGradient(0, 0, 1,
          0, true, CycleMethod.NO_CYCLE, goldenStop);

  public GoldenCircle(double xCenter, double yCenter, double radius) {
    super(xCenter, yCenter, radius);
    setFill(lgGold);
    setStroke(Color.DARKGOLDENROD);
  }

  public GoldenCircle(double radius) {
    super(radius);
    setFill(lgGold);
    setStroke(Color.DARKGOLDENROD);
  }
}
