package SolutionMethods.EquationsSolvingMethods;
import DefaultData.DefaultEquations;
import SolutionsData.EquationData;

public class BisectionMethod implements EquationSolvingMethodInterface {
    private EquationData data;

    public static final String METHOD_NAME = "Метод половинного деления";

    private double approximateRootValue;

    private String[] outputData = new String[3];

    public BisectionMethod(EquationData data) {
        this.data = data;
    }

    private double leftBorder;
    private double rightBorder;

    @Override
    public String[] iterationsCycle() {
        // Проверяем достаточное условие для применения метода половинного деления
        if (!checkSufficientCondition()) {
            return null;
        }

        int iterationNumber = 0;
        leftBorder = data.getLeftBorder();
        rightBorder = data.getRightBorder();

        // Цикл итераций
        while (!checkEndCriterion() && iterationNumber <= 999) {
            // Вычисляем приближенное значение корня по методу половинного деления
            approximateRootValue = (leftBorder + rightBorder) / 2.0;

            // Выбираем новый интервал
            choiceNewInterval();

            iterationNumber++;
        }

        // Записываем данные в массив для вывода
        outputData[0] = String.valueOf(iterationNumber);
        outputData[1] = String.valueOf(approximateRootValue);
        outputData[2] = String.valueOf(DefaultEquations.solutionOfEquation(approximateRootValue, data));
        return outputData;
    }

    // Выбор нового интервала
    private void choiceNewInterval() {
        double functionValue = DefaultEquations.solutionOfEquation(approximateRootValue, this.data);

        if (functionValue * DefaultEquations.solutionOfEquation(leftBorder, this.data) < 0) {
            rightBorder = approximateRootValue;
        } else {
            leftBorder = approximateRootValue;
        }
    }

    // Проверка достаточного условия для применения метода половинного деления
    private boolean checkSufficientCondition() {
        double leftBorder = DefaultEquations.solutionOfEquation(data.getLeftBorder(), this.data);
        double rightBorder = DefaultEquations.solutionOfEquation(data.getRightBorder(), this.data);

        if (leftBorder * rightBorder > 0) {
            return false;
        }

        return true;
    }

    @Override
    public boolean checkEndCriterion() {
        return Math.abs(DefaultEquations.solutionOfEquation(approximateRootValue, this.data)) <= data.getAccuracy();
    }
}
