package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.containers.LastEquationInput;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Print Matrix Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class PrintMatrixQuery implements CalculatorQuery
{
    private Matrix matrix;
    private String matrixName;


    public PrintMatrixQuery(String variable)
    {
        this.matrix = InternalStorage.Singleton.get(variable);
        this.matrixName = variable;
    }


    public PrintMatrixQuery()
    {
        this.matrix = LastMatrixOutput.Singleton.getLastOutput();
        this.matrixName = LastEquationInput.Singleton.getLastOutput();
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        System.out.println();
        System.out.println("Matrix '" + this.matrixName + "':");
        Print.printMatrix(matrix);
    }
}
