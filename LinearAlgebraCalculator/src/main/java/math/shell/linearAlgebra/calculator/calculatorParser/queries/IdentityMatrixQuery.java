package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.containers.LastOutputType;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Identity Matrix Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class IdentityMatrixQuery implements CalculatorQuery
{
    private int matrixSize;


    public IdentityMatrixQuery(int matrixSize)
    {
        this.matrixSize = matrixSize;
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        Matrix finalMatrix = Matrix.identity(matrixSize);
        LastMatrixOutput.Singleton.add(finalMatrix);
        LastOutputType.Singleton.setLastOutputMATRIX();
        Print.printMatrix(finalMatrix);
    }
}
