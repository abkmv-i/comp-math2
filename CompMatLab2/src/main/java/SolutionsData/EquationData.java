package SolutionsData;

import java.util.Map;

public class EquationData {
    private String equationName;

    private String equationView;

    private double leftBorder;

    private double rightBorder;

    private double accuracy;

    private Map<String, Double> system;

    public EquationData() {}

    public double getLeftBorder() {
        return leftBorder;
    }

    public void setLeftBorder(double leftBorder) {
        this.leftBorder = leftBorder;
    }

    public double getRightBorder() {
        return rightBorder;
    }

    public void setRightBorder(double rightBorder) {
        this.rightBorder = rightBorder;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public Map<String, Double> getSystem() {
        return system;
    }

    public void setSystem(Map<String, Double> system) {
        this.system = system;
    }

    public String getEquationName() {
        return equationName;
    }

    public void setEquationName(String equationName) {
        this.equationName = equationName;
    }

    public String getEquationView() {
        return equationView;
    }

    public void setEquationView(String equationView) {
        this.equationView = equationView;
    }
}
