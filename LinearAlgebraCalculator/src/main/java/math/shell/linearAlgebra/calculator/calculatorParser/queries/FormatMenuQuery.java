package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.userInterface.InterfaceMethods;

/**
 *	Query for user input demanding Format Menu Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class FormatMenuQuery implements CalculatorQuery
{


    public FormatMenuQuery()
    {

    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        InterfaceMethods.findFormat();
    }
}
