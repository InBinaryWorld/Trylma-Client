package pl.project.trylma.gui.circles;

        import javafx.scene.paint.Color;
        import javafx.scene.paint.CycleMethod;
        import javafx.scene.paint.LinearGradient;
        import javafx.scene.paint.Stop;
        import javafx.scene.shape.Circle;

public class BlueCircle extends Circle {
  Stop[] blueStop= new Stop[] { new Stop(0, Color.WHITE),
          new Stop(1, Color.STEELBLUE)};
  LinearGradient lgBlue = new LinearGradient(0, 0, 1,
          0, true, CycleMethod.NO_CYCLE, blueStop);

  public BlueCircle(double xCenter, double yCenter, double radius) {
    super(xCenter, yCenter, radius);
    setFill(lgBlue);
    setStroke(Color.STEELBLUE);
  }

  public BlueCircle(double radius) {
    super(radius);
    setFill(lgBlue);
    setStroke(Color.STEELBLUE);
  }
}
