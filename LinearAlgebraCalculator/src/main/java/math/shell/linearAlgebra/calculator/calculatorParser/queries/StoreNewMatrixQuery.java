package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.containers.LastEquationInput;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.containers.LastOutputType;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Store new Matrix Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class StoreNewMatrixQuery implements CalculatorQuery
{
    private String matrixString;
    private String variable;


    public StoreNewMatrixQuery(String matrixString, String variable)
    {
        this.matrixString = matrixString;
        this.variable = variable;
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        Matrix newMatrix = new Matrix(matrixString);

        InternalStorage.Singleton.add(this.variable, newMatrix);
        LastEquationInput.Singleton.add(this.variable);
        LastMatrixOutput.Singleton.add(newMatrix);
        LastOutputType.Singleton.setLastOutputMATRIX();

        System.out.println();
        System.out.println("Matrix '" + this.variable +"':");
        Print.printMatrix(this.variable);
    }



}
