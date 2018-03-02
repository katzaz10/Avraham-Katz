package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.exceptions.NonSquareMatrixException;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Matrix raised to exponent Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class MatrixToExponentQuery implements CalculatorQuery
{
    private Matrix matrix;
    private Integer power;


    public MatrixToExponentQuery(String variable, Integer power)
    {
        this.matrix = InternalStorage.Singleton.get(variable);
        this.power = power;
    }


    public MatrixToExponentQuery(Integer power)
    {
        this.matrix = LastMatrixOutput.Singleton.getLastOutput();
        this.power = power;
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        try
        {
            Matrix finalMatrix = this.matrix.toPower(this.power);
            LastMatrixOutput.Singleton.add(finalMatrix);
            //currentType = CalculatorParser.OutputType.MATRIX;
            Print.printMatrix(finalMatrix);
        }

        catch (NonSquareMatrixException nonSquare)
        {
            System.out.printf("<ERROR: trying to use non-square matrix in operation that requires square matrix> %n%n");
        }

    }
}
