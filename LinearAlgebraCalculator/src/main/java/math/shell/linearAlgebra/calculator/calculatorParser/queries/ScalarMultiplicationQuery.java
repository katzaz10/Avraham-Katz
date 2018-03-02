package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.containers.LastBigDecimalOutput;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.containers.LastOutputType;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;
import java.math.BigDecimal;

/**
 *	Query for user input demanding Scalar Multiplication Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class ScalarMultiplicationQuery implements CalculatorQuery
{
    private Matrix matrix;
    private BigDecimal scalar;


    public ScalarMultiplicationQuery(String variable, BigDecimal scalar)
    {
        this.matrix = InternalStorage.Singleton.get(variable);
        this.scalar = scalar;
    }


    public ScalarMultiplicationQuery(String variable)
    {
        this.matrix = InternalStorage.Singleton.get(variable);
        this.scalar = LastBigDecimalOutput.Singleton.getLastOutput();
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        Matrix finalMatrix = this.matrix.scalarMultiply(this.scalar);
        LastMatrixOutput.Singleton.add(finalMatrix);
        LastOutputType.Singleton.setLastOutputMATRIX();
        Print.printMatrix(finalMatrix);
    }

}
