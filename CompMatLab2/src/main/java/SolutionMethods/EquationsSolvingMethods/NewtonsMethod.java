package SolutionMethods.EquationsSolvingMethods;

import DefaultData.DefaultEquations;
import SolutionsData.EquationData;

public class NewtonsMethod implements EquationSolvingMethodInterface {

    private EquationData data;

    public static final String METHOD_NAME = "Метод Ньютона";

    private double approximateRootValue;

    private final String[] outputData = new String[3];

    public NewtonsMethod(EquationData data) {
        this.data = data;
    }

    private double leftBorder;
    private double rightBorder;

    @Override
    public String[] iterationsCycle() {
        if (!checkSufficientCondition()) {
            return null;
        }
        int iterationNumber = 0;
        leftBorder = data.getLeftBorder();
        rightBorder = data.getRightBorder();
        double root = choiceInitialGuess();
        while (!checkEndCriterion() && iterationNumber <= 999) {
            approximateRootValue = root - ((DefaultEquations.solutionOfEquation(root, this.data))/
                    (DefaultEquations.solutionOfDerivativeEquation(root, this.data)));
            root = approximateRootValue;
            iterationNumber++;
        }
        outputData[0] = String.valueOf(iterationNumber);
        outputData[1] = String.valueOf(approximateRootValue);
        outputData[2] = String.valueOf(DefaultEquations.solutionOfEquation(approximateRootValue, data));
        return outputData;
    }

    private double choiceInitialGuess() {
        if (DefaultEquations.solutionOfEquation(leftBorder, this.data) *
                DefaultEquations.solutionOfSecondDerivativeEquation(leftBorder, this.data) > 0) {
            return leftBorder;
        } else {
            return rightBorder;
        }
    }

    private boolean checkSufficientCondition() {
        if (DefaultEquations.solutionOfEquation(data.getLeftBorder(), this.data)*DefaultEquations.solutionOfEquation(data.getRightBorder(), this.data) >= 0 ||
                DefaultEquations.solutionOfDerivativeEquation(1, this.data) == 0) {
            return false;
        }
        double step = (data.getRightBorder() - data.getLeftBorder()) / 49.0;
        for (int i = 1; i < 50; i++) {
            double currentValue = DefaultEquations.solutionOfDerivativeEquation(1.0 + i * step, this.data);
            double pastValue = DefaultEquations.solutionOfDerivativeEquation(1.0 + (i-1) * step, this.data);
            if (currentValue*pastValue < 0) {
                return false;
            }
        }
        for (int i = 1; i < 50; i++) {
            double currentValue = DefaultEquations.solutionOfSecondDerivativeEquation(1.0 + i * step, this.data);
            double pastValue = DefaultEquations.solutionOfSecondDerivativeEquation(1.0 + (i-1) * step, this.data);
            if (currentValue*pastValue < 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean checkEndCriterion() {
        return Math.abs(DefaultEquations.solutionOfEquation(approximateRootValue, this.data)) <= data.getAccuracy();
    }
}
