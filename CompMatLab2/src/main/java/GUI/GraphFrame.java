package GUI;

import DefaultData.DefaultEquations;
import DefaultData.DefaultSystemsOfEquations;
import GUI.Interfaces.Frame;
import SolutionsData.EquationData;
import SolutionsData.SystemData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class GraphFrame implements Frame {

    private static final String HEADER_TEXT = "График функции";

    private ChartFrame frame;

    private JFreeChart chart;

    private EquationData equationData;

    private SystemData systemData;

    public GraphFrame(EquationData data) {
        this.equationData = data;
    }

    public GraphFrame(SystemData data) {
        this.systemData = data;
    }

    @Override
    public void Frame() {

        createFilling();

        frame.setIconImage(icon.getImage());
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void createFilling() {
        if (systemData == null) {
            createGraphOfFunction();
        } else {
            createGraphOfSystem();
        }
    }

    private ChartFrame createGraphOfFunction() {

        XYSeries series = new XYSeries(equationData.getEquationView());

        for (double x = equationData.getLeftBorder(); x <= equationData.getRightBorder(); x += 0.1) {
            series.add(x, DefaultEquations.solutionOfEquation(x, this.equationData));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series);

        chart = ChartFactory.createXYLineChart(
                HEADER_TEXT,
                "x",
                "y",
                dataset
        );

        frame = new ChartFrame(TITLE, chart);

        return frame;
    }

    private ChartFrame createGraphOfSystem() {

        XYSeries firstSeries = new XYSeries(systemData.getSystemView()[0]);
        XYSeries secondSeries = new XYSeries(systemData.getSystemView()[1]);

        for (double x = systemData.getLeftBorder(); x <= systemData.getRightBorder(); x += 0.1) {
            firstSeries.add(x, DefaultSystemsOfEquations.solutionOfFirstEquationSystem(x, this.systemData));
            secondSeries.add(x, DefaultSystemsOfEquations.solutionOfSecondEquationSystem(x, this.systemData));
        }

        if (Objects.equals(systemData.getSystemName(), "SECOND_SYSTEM") || Objects.equals(systemData.getSystemName(), "FIRST_SYSTEM")) {
            for (double x = systemData.getLeftBorder(); x <= systemData.getRightBorder(); x += 0.1) {
                firstSeries.add(x, -DefaultSystemsOfEquations.solutionOfFirstEquationSystem(x, this.systemData));
            }
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(firstSeries);
        dataset.addSeries(secondSeries);

        chart = ChartFactory.createXYLineChart(
                HEADER_TEXT,
                "x",
                "y",
                dataset
        );

        frame = new ChartFrame(TITLE, chart);

        return frame;
    }

    @Override
    public JPanel createHeader(String headerText) {
        JPanel headerPanel = new JPanel();

        JLabel header =  new JLabel(headerText);

        header.setFont(HEADER_FONT);
        header.setAlignmentX(Component.CENTER_ALIGNMENT);

        headerPanel.add(header);
        return headerPanel;
    }
}
