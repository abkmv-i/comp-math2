package DefaultData;

import SolutionsData.SystemData;

import java.util.HashMap;
import java.util.Map;

public class DefaultSystemsOfEquations {

    private static final String FIRST_SYSTEM = "" +
            "\\begin{cases}" +
            "0,1x_1^2 + x_1 + 0,2x_2^2 - 0,3 = 0" +
            "\\\\" +
            "0,2x_1^2 + x_2 + 0,1x_1x_2 - 0,7 = 0" +
            "\\\\" +
            "\\end{cases}";

    private static final String SECOND_SYSTEM = "" +
            "\\begin{cases}" +
            "x_1^2 + x_2^2 - 4 = 0" +
            "\\\\" +
            "-3x_1^2 + x_2 = 0" +
            "\\\\" +
            "\\end{cases}";

    private static final Map[] firstSystemMapOfOdds = new HashMap[] {new HashMap<String, Double>() {{
        put("x_1^2", 0.1);
        put("x_1", 1.0);
        put("x_2^2", 0.2);
        put("-", -0.3);
    }}, new HashMap<String, Double>() {{
        put("x_1^2", 0.2);
        put("x_2", 1.0);
        put("x_1x_2", 0.1);
        put("-", -0.7);
    }}};

    private static final Map[] secondSystemMapOfOdds = new HashMap[] {new HashMap<String, Double>() {{
        put("x_1^2", 1.0);
        put("x_2^2", 1.0);
        put("-", -4.0);
    }}, new HashMap<String, Double>() {{
        put("x_1^2", -3.0);
        put("x_2", 1.0);
    }}};

    private static final HashMap<Integer, Map[]> map = new HashMap<>() {{
       put(0, firstSystemMapOfOdds);
       put(1, secondSystemMapOfOdds);
    }};

    public static class FirstSystemSolutions {

        public static String firstEquationView = "0,1x_1^2 + x_1 + 0,2x_2^2 - 0,3 = 0";

        public static double firstEquationXDerivative(double value) {
            return (value + 5)/(5);
        }

        public static double firstEquationYDerivative(double value) {
            return (2*value)/(5);
        }

        public static double firstEquationSolution(double value) {
            return (-0.5*Math.pow(value, 2) - 5.0*value + 1.5);
        }

        public static String secondEquationView = "0,2x_1^2 + x_2 + 0,1x_1x_2 - 0,7 = 0";

        public static double secondEquationXDerivative(double valueX, double valueY) {
            return (2*valueX)/(5) + 1 + (valueY)/(10);
        }

        public static double secondEquationYDerivative(double value) {
            return (value)/(10);
        }

        public static double secondEquationSolution(double value) {
            return ((-2.0*Math.pow(value, 2)+7.0)/(10.0+value));
        }

    }

    public static class SecondSystemSolutions {

        public static String firstEquationView = "x_1^2 + x_2^2 - 4 = 0";

        public static double firstEquationXDerivative(double value) {
            return (2*value);
        }

        public static double firstEquationYDerivative(double value) {
            return (2*value);
        }

        public static double firstEquationSolution(double value) {
            return (Math.pow(4.0 - Math.pow(value, 2), 0.5));
        }

        public static String secondEquationView = "-3x_1^2 + x_2 = 0";

        public static double secondEquationXDerivative(double value) {
            return -(6*value);
        }

        public static double secondEquationYDerivative(double value) {
            return (1);
        }

        public static double secondEquationSolution(double value) {
            return (Math.pow(3*Math.pow(value, 2), 0.5));
        }

    }

    public static double solutionOfFirstEquationSystem(double root, SystemData data) {
        return switch (data.getSystemName()) {
            case "FIRST_SYSTEM" -> DefaultSystemsOfEquations.FirstSystemSolutions.firstEquationSolution(root);
            case "SECOND_SYSTEM" -> DefaultSystemsOfEquations.SecondSystemSolutions.firstEquationSolution(root);
            default -> root;
        };
    }

    public static double solutionOfSecondEquationSystem(double root, SystemData data) {
        return switch (data.getSystemName()) {
            case "FIRST_SYSTEM" -> DefaultSystemsOfEquations.FirstSystemSolutions.secondEquationSolution(root);
            case "SECOND_SYSTEM" -> DefaultSystemsOfEquations.SecondSystemSolutions.secondEquationSolution(root);
            default -> root;
        };
    }



    public static String getFirstSystem() {return FIRST_SYSTEM;}

    public static String getSecondSystem() {
        return SECOND_SYSTEM;
    }

    public static Map[] getFirstSystemMapOfOdds() {
        return firstSystemMapOfOdds;
    }

    public static Map[] getSecondSystemMapOfOdds() {
        return secondSystemMapOfOdds;
    }

    public static HashMap<Integer, Map[]> getMap() {
        return map;
    }
}
