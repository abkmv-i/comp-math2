package SolutionMethods.SystemSolvingMethods;

import DefaultData.DefaultSystemsOfEquations;
import SolutionMethods.SystemSolvingMethods.SystemSolvingMethodInterface;
import SolutionsData.SystemData;
import SolutionMethods.AbstractClasses.NewtonMethodAbs;

import java.util.Objects;

public class NewtonMethod extends NewtonMethodAbs  implements SystemSolvingMethodInterface {

    private SystemData data;
    private double[] initialGuess = new double[2];
    private final double accuracy = 1e-6;

    private double[] oldValue;
    private double[] newValue;

    private final String[] outputData = new String[3];

    public NewtonMethod() {
    }

    @Override
    public String[] iterationsCycle() {
        if (!checkProcess()) {
            return null;
        }

        int iterationNumber = 0;


        initialGuess[0]=data.getLeftBorder();
        initialGuess[1]=data.getRightBorder();
        oldValue = initialGuess.clone();
        newValue = new double[oldValue.length];

        while (iterationNumber <= 999) {
            double[][] jacobianMatrix = computeJacobianMatrix(oldValue);

            double[] equations = computeEquations(oldValue);

            double[] delta = solveLinearSystem(jacobianMatrix, equations);

            for (int i = 0; i < newValue.length; i++) {
                newValue[i] = oldValue[i] - delta[i];
            }

            if (checkEndCriterion(delta)) {
                break;
            }

            oldValue = newValue.clone();
            iterationNumber++;
        }

        outputData[0] = String.valueOf(iterationNumber);
        outputData[1] = formatArray(newValue);
        outputData[2] = formatArray(computeEquations(newValue));
        return outputData;
    }



    private double[][] computeJacobianMatrix(double[] values) {
        double[][] jacobianMatrix = new double[values.length][values.length];
        if (Objects.equals(data.getSystemName(), "FIRST_SYSTEM")) {
            jacobianMatrix[0][0] = DefaultSystemsOfEquations.FirstSystemSolutions.firstEquationXDerivative(values[0]);
            jacobianMatrix[0][1] = DefaultSystemsOfEquations.FirstSystemSolutions.firstEquationYDerivative(values[1]);
            jacobianMatrix[1][0] = DefaultSystemsOfEquations.FirstSystemSolutions.secondEquationXDerivative(values[0], values[1]);
            jacobianMatrix[1][1] = DefaultSystemsOfEquations.FirstSystemSolutions.secondEquationYDerivative(values[1]);
        } else {
            jacobianMatrix[0][0] = DefaultSystemsOfEquations.SecondSystemSolutions.firstEquationXDerivative(values[0]);
            jacobianMatrix[0][1] = DefaultSystemsOfEquations.SecondSystemSolutions.firstEquationYDerivative(values[1]);
            jacobianMatrix[1][0] = DefaultSystemsOfEquations.SecondSystemSolutions.secondEquationXDerivative(values[0]);
            jacobianMatrix[1][1] = DefaultSystemsOfEquations.SecondSystemSolutions.secondEquationYDerivative(values[1]);
        }
        return jacobianMatrix;
    }

    private double[] computeEquations(double[] values) {
        double[] equations = new double[values.length];
        if (Objects.equals(data.getSystemName(), "FIRST_SYSTEM")) {
            equations[0] = DefaultSystemsOfEquations.FirstSystemSolutions.firstEquationSolution(values[0]);
            equations[1] = DefaultSystemsOfEquations.FirstSystemSolutions.secondEquationSolution(values[1]);
        } else {
            equations[0] = DefaultSystemsOfEquations.SecondSystemSolutions.firstEquationSolution(values[0]);
            equations[1] = DefaultSystemsOfEquations.SecondSystemSolutions.secondEquationSolution(values[1]);
        }
        return equations;
    }

    public double[] solveLinearSystem(double[][] matrix, double[] vector) {
        // Реализация метода реш
        int n = vector.length;
        double[] delta = new double[n];
        double[] b = vector.clone();
        double[][] a = new double[n][n];

        for (int i = 0; i < n; i++) {
            a[i] = matrix[i].clone();
        }

        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                double factor = a[i][k] / a[k][k];
                b[i] -= factor * b[k];
                for (int j = k + 1; j < n; j++) {
                    a[i][j] -= factor * a[k][j];
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < n; j++) {
                sum += a[i][j] * delta[j];
            }
            delta[i] = (b[i] - sum) / a[i][i];
        }

        return delta;
    }

    private boolean checkProcess() {
        if (Objects.equals(data.getSystemName(), "FIRST_SYSTEM")) {
            return Math.max(Math.abs(DefaultSystemsOfEquations.FirstSystemSolutions.firstEquationXDerivative(initialGuess[0]) +
                            DefaultSystemsOfEquations.FirstSystemSolutions.firstEquationYDerivative(initialGuess[1])),
                    Math.abs(DefaultSystemsOfEquations.FirstSystemSolutions.secondEquationXDerivative(initialGuess[0], initialGuess[1]) +
                            DefaultSystemsOfEquations.FirstSystemSolutions.secondEquationYDerivative(initialGuess[1]))) < 1;
        } else {
            return Math.max(Math.abs(DefaultSystemsOfEquations.SecondSystemSolutions.firstEquationXDerivative(initialGuess[0]) +
                            DefaultSystemsOfEquations.SecondSystemSolutions.firstEquationYDerivative(initialGuess[1])),
                    Math.abs(DefaultSystemsOfEquations.SecondSystemSolutions.secondEquationXDerivative(initialGuess[0]) +
                            DefaultSystemsOfEquations.SecondSystemSolutions.secondEquationYDerivative(initialGuess[1]))) < 1;
        }
    }

    private boolean checkEndCriterion(double[] delta) {
        for (double value : delta) {
            if (Math.abs(value) > accuracy) {
                return false;
            }
        }
        return true;
    }

    public void setData(SystemData data) {
        this.data = data;
    }

    public SystemData getData() {
        return data;
    }


    private String formatArray(double[] array) {
        StringBuilder builder = new StringBuilder();
        for (double value : array) {
            builder.append(value).append(" ");
        }
        return builder.toString().trim();
    }
    @Override
    public double solutionOfEquation(double border) {
        return 0;
    }

    @Override
    public boolean checkEndCriterion() {
        return false;
    }
}

