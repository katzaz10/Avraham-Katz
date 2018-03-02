package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.containers.LastOutputType;
import math.shell.linearAlgebra.calculator.exceptions.NonSquareMatrixException;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Cofactor Matrix Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class CofactorMatrixQuery implements CalculatorQuery
{
    private Matrix matrix;


    public CofactorMatrixQuery(String variable)
    {
        this.matrix = InternalStorage.Singleton.get(variable);
    }


    public CofactorMatrixQuery()
    {
        this.matrix = LastMatrixOutput.Singleton.getLastOutput();
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
       try
       {
           Matrix finalMatrix = this.matrix.cofactorMatrix();
           LastMatrixOutput.Singleton.add(finalMatrix);
           LastOutputType.Singleton.setLastOutputMATRIX();
           Print.printMatrix(finalMatrix);
       }

       catch (NonSquareMatrixException nonSquare)
       {
           System.out.printf("<ERROR: trying to use non-square matrix in operation that requires square matrix> %n%n");
       }
    }
}
