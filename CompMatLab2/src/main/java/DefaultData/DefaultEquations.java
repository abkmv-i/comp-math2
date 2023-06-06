package DefaultData;


import SolutionsData.EquationData;

import java.util.HashMap;
import java.util.Map;

public final class DefaultEquations {
    private static final String FIRST_EQUATION = "x^4 - 0,2x^2 + 0,5x + 1,5 = 0";
    private static final String SECOND_EQUATION = "3x^4 - 8x^3 - 18x^2 + 2 = 0";
    private static final String THIRD_EQUATION = "x^2 - 20\\sin{x} = 0";
    private static final String FOURTH_EQUATION = "\\sqrt{x+1} = \\frac{1}{x}";

    public static final Map<String, Double> firstEquation = new HashMap<>() {{
        put("x^3", 1.0);
        put("x^2", -0.2);
        put("x", 0.5);
        put("-", 1.5);
    }};

    public static final Map<String, Double> secondEquation = new HashMap<>() {{
        put("x^4", 3.0);
        put("x^3", -8.0);
        put("x^2", -18.0);
        put("-", 2.0);
    }};

    public static final Map<String, Double> thirdEquation = new HashMap<>() {{
        put("x^2", 1.0);
        put("\\sin{x}", 20.0);
    }};

    public static final Map<String, Double> fourthEquation = new HashMap<>() {{
        put("\\sqrt{x+1}", 1.0);
        put("\\frac{1}{x}", 1.0);
    }};

    public static double firstEquationSolution(double value) {
        return (Math.pow(value, 3) - 0.2*Math.pow(value, 2) + 0.5*value + 1.5);
    }

    public static double firstEquationDerivativeSolution(double value) {
        return (3*Math.pow(value, 2) - 0.4*value + 0.5);
    }

    public static double firstEquationSecondDerivativeSolution(double value) {
        return (6*value - 0.4);
    }

    public static double secondEquationSolution(double value) {
        return (3.0*Math.pow(value, 4) - 8.0*Math.pow(value, 3) - 18.0*Math.pow(value, 2) + 2.0);
    }

    public static double secondEquationDerivativeSolution(double value) {
        return (12.0*Math.pow(value, 3) - 24.0*Math.pow(value, 2) - 36.0*value);
    }

    public static double secondEquationSecondDerivativeSolution(double value) {
        return (36.0*Math.pow(value, 2) - 48.0*value - 36.0);
    }

    public static double thirdEquationSolution(double value) {
        return (Math.pow(value, 2) - 20.0*Math.sin(value));
    }

    public static double thirdEquationDerivativeSolution(double value) {
        return (2.0*value - 20.0*Math.cos(value));
    }

    public static double thirdEquationSecondDerivativeSolution(double value) {
        return (2.0 + 20.0*Math.sin(value));
    }

    public static double fourthEquationSolution(double value) {
        return (Math.pow(value+1, 0.5) - (1.0/value));
    }

    public static double fourthEquationDerivativeSolution(double value) {
        return (1.0/2*Math.pow(value+1, 0.5) + (1.0/Math.pow(value, 2)));
    }

    public static double fourthEquationSecondDerivativeSolution(double value) {
        return (1.0/4*Math.pow(value+1, 0.5)*(value+1) - (2.0/Math.pow(value, 3)));
    }

    public static double solutionOfEquation(double root, EquationData data) {
        return switch (data.getEquationName()) {
            case "FIRST_EQUATION" -> DefaultEquations.firstEquationSolution(root);
            case "SECOND_EQUATION" -> DefaultEquations.secondEquationSolution(root);
            case "THIRD_EQUATION" -> DefaultEquations.thirdEquationSolution(root);
            case "FOURTH_EQUATION" -> DefaultEquations.fourthEquationSolution(root);
            default -> root;
        };
    }

    public static double solutionOfDerivativeEquation(double root, EquationData data) {
        return switch (data.getEquationName()) {
            case "FIRST_EQUATION" -> DefaultEquations.firstEquationDerivativeSolution(root);
            case "SECOND_EQUATION" -> DefaultEquations.secondEquationDerivativeSolution(root);
            case "THIRD_EQUATION" -> DefaultEquations.thirdEquationDerivativeSolution(root);
            case "FOURTH_EQUATION" -> DefaultEquations.fourthEquationDerivativeSolution(root);
            default -> root;
        };
    }

    public static double solutionOfSecondDerivativeEquation(double root, EquationData data) {
        return switch (data.getEquationName()) {
            case "FIRST_EQUATION" -> DefaultEquations.firstEquationSecondDerivativeSolution(root);
            case "SECOND_EQUATION" -> DefaultEquations.secondEquationSecondDerivativeSolution(root);
            case "THIRD_EQUATION" -> DefaultEquations.thirdEquationSecondDerivativeSolution(root);
            case "FOURTH_EQUATION" -> DefaultEquations.fourthEquationSecondDerivativeSolution(root);
            default -> root;
        };
    }

    public static String getFirstEquation() {
        return FIRST_EQUATION;
    }

    public static String getSecondEquation() {
        return SECOND_EQUATION;
    }

    public static String getThirdEquation() {
        return THIRD_EQUATION;
    }

    public static String getFourthEquation() {
        return FOURTH_EQUATION;
    }
}
