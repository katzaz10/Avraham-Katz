package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.storages.ExternalStorage;

/**
 *	Query for user input demanding Exit Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class ExitQuery implements CalculatorQuery
{


    public ExitQuery()
    {

    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        ExternalStorage.writeExternalStorage();
        System.out.printf("%nExiting calculator... %n%n");
        System.exit(0);
    }
}
