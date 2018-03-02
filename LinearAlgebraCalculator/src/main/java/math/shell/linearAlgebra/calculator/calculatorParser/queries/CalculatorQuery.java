package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.exceptions.IncorrectSyntaxException;
import math.shell.linearAlgebra.calculator.exceptions.NoMatrixInQueueException;
import math.shell.linearAlgebra.calculator.exceptions.NothingInQueueException;

/**
 *	Interface for queries returned by Parser
 *	@author Avraham Katz
 *	@version 1.1
 */

public interface CalculatorQuery
{
    public void runOperation() throws NothingInQueueException, NoMatrixInQueueException, IncorrectSyntaxException;
}
