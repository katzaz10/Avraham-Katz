package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.containers.LastEquationInput;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Storing the previous output Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class StorePreviousOutputQuery implements CalculatorQuery
{
    private String variable;


    public StorePreviousOutputQuery(String variable)
    {
        this.variable = variable;
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        InternalStorage.Singleton.add(this.variable, LastMatrixOutput.Singleton.getLastOutput());
        LastEquationInput.Singleton.add(this.variable);

        System.out.println();
        System.out.println("Matrix '" + this.variable +"':");
        Print.printMatrix(LastMatrixOutput.Singleton.getLastOutput());
    }
}
