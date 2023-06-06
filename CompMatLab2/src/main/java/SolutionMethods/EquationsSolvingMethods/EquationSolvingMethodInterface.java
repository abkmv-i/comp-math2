package SolutionMethods.EquationsSolvingMethods;

import SolutionsData.SystemData;

public interface EquationSolvingMethodInterface {

    double accuracy = 0;

    String[] iterationsCycle();

    boolean checkEndCriterion();

}
