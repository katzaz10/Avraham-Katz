package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.containers.LastOutputType;
import math.shell.linearAlgebra.calculator.exceptions.UndefinedSolutionException;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Matrix Addition Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class MatrixAdditionQuery implements CalculatorQuery
{
    private Matrix matrix1;
    private Matrix matrix2;


    public MatrixAdditionQuery(String variable1, String variable2)
    {
        this.matrix1 = InternalStorage.Singleton.get(variable1);
        this.matrix2 = InternalStorage.Singleton.get(variable2);
    }


    public MatrixAdditionQuery(String variable2)
    {
        this.matrix1 = LastMatrixOutput.Singleton.getLastOutput();
        this.matrix2 = InternalStorage.Singleton.get(variable2);
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        try
        {
            Matrix finalMatrix = this.matrix1.add(this.matrix2);
            LastMatrixOutput.Singleton.add(finalMatrix);
            LastOutputType.Singleton.setLastOutputMATRIX();
            Print.printMatrix(finalMatrix);
        }

        catch (UndefinedSolutionException notSameSize)
        {
            System.out.printf("<ERROR: cannot perform matrix addition on matrices of different sizes... %s != %s> %n%n", this.matrix1.getSize(), this.matrix2.getSize());
        }
    }
}
