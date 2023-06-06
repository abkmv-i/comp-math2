package SolutionMethods.SystemSolvingMethods;

public interface SystemSolvingMethodInterface {

    double accuracy = 0;

    String[] iterationsCycle();

    double solutionOfEquation(double border);

    boolean checkEndCriterion();

}
