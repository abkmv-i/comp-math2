package SolutionsData;

import java.util.Map;

public class SystemData {
    private String[] systemView;
    private String systemName;
    private double leftBorder;
    private double rightBorder;
    private double accuracy;

    private Map<String, Double>[] system;

    public SystemData() {}

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

    public Map<String, Double>[] getSystem() {
        return system;
    }

    public void setSystem(Map<String, Double>[] system) {
        this.system = system;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String[] getSystemView() {
        return systemView;
    }

    public void setSystemView(String[] systemView) {
        this.systemView = systemView;
    }

}
