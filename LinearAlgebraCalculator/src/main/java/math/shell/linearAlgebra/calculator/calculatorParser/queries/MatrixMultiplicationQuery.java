package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.containers.LastOutputType;
import math.shell.linearAlgebra.calculator.exceptions.UndefinedSolutionException;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Matrix Multiplication Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class MatrixMultiplicationQuery implements CalculatorQuery
{
    private Matrix matrix1;
    private Matrix matrix2;


    public MatrixMultiplicationQuery(String variable1, String variable2)
    {
        this.matrix1 = InternalStorage.Singleton.get(variable1);
        this.matrix2 = InternalStorage.Singleton.get(variable2);
    }


    public MatrixMultiplicationQuery(String variable2)
    {
        this.matrix1 = LastMatrixOutput.Singleton.getLastOutput();
        this.matrix2 = InternalStorage.Singleton.get(variable2);
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        Matrix finalMatrix = null;
        try
        {
            finalMatrix = this.matrix1.multiply(this.matrix2);
            LastMatrixOutput.Singleton.add(finalMatrix);
            LastOutputType.Singleton.setLastOutputMATRIX();
            Print.printMatrix(finalMatrix);
        }

        catch (UndefinedSolutionException rowNotColumn)
        {
            System.out.printf("<ERROR: Row length of first matrix must be same size as column height of second matrix to perform matrix multiplication... %d != %d> %n%n", this.matrix1.getRowLength(), matrix2.getColumnHeight());
        }
    }
}
