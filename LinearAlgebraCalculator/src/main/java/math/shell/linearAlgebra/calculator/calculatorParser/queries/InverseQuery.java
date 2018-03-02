package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.containers.LastOutputType;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Inverse Operation
 *	@author Avraham Katz
 *	@version 1.1
 */
public class InverseQuery implements CalculatorQuery
{
    private Matrix matrix;


    public InverseQuery(String variable)
    {
        this.matrix = InternalStorage.Singleton.get(variable);
    }


    public InverseQuery()
    {
        this.matrix = LastMatrixOutput.Singleton.getLastOutput();
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        Matrix finalMatrix = this.matrix.inverse();
        LastMatrixOutput.Singleton.add(finalMatrix);
        LastOutputType.Singleton.setLastOutputMATRIX();
        Print.printMatrix(finalMatrix);
    }
}
