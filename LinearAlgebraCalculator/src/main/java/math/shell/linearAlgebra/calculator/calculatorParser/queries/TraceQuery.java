package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.containers.LastBigDecimalOutput;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.containers.LastOutputType;
import math.shell.linearAlgebra.calculator.exceptions.NonSquareMatrixException;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.InterfaceMethods;
import java.math.BigDecimal;

/**
 *	Query for user input demanding Trace Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class TraceQuery implements CalculatorQuery
{
    private Matrix matrix;


    public TraceQuery(String variable)
    {
        this.matrix = InternalStorage.Singleton.get(variable);
    }


    public TraceQuery()
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
            BigDecimal finalBigDecimal = this.matrix.trace();
            LastBigDecimalOutput.Singleton.add(finalBigDecimal);
            LastOutputType.Singleton.setLastOutputBIGDECIMAL();
            System.out.println(InterfaceMethods.bump1 + finalBigDecimal);
            System.out.println();
        }

        catch (NonSquareMatrixException nonSquare)
        {
            System.out.printf("<ERROR: trying to use non-square matrix in operation that requires square matrix> %n%n");
        }
    }
}
