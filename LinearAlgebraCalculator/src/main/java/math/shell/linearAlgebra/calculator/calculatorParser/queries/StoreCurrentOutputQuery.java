package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.calculatorParser.CalculatorParser;
import math.shell.linearAlgebra.calculator.containers.LastEquationInput;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.exceptions.IncorrectSyntaxException;
import math.shell.linearAlgebra.calculator.exceptions.NoMatrixInQueueException;
import math.shell.linearAlgebra.calculator.exceptions.NothingInQueueException;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Storing the current output Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class StoreCurrentOutputQuery implements CalculatorQuery
{
    private String variable;
    private String operation;


    public StoreCurrentOutputQuery(String variable, String operation)
    {
        this.variable = variable;
        this.operation = operation;
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation() throws NothingInQueueException, NoMatrixInQueueException, IncorrectSyntaxException
    {
        CalculatorParser cqp = new CalculatorParser(this.operation);
        cqp.parse().runOperation();
        InternalStorage.Singleton.add(this.variable, LastMatrixOutput.Singleton.getLastOutput());
        LastEquationInput.Singleton.add(this.variable);

        System.out.println();
        System.out.println("Matrix '" + this.variable +"':");
        Print.printMatrix(LastMatrixOutput.Singleton.getLastOutput());
    }
}
