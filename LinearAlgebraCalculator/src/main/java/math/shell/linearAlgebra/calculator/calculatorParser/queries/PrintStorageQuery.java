package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Print entire storage Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class PrintStorageQuery implements CalculatorQuery
{


    public PrintStorageQuery()
    {

    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        if (InternalStorage.Singleton.isEmpty())
        {
            System.out.printf("%nNo matrices in storages... %n%n");
        }

        else
        {
            System.out.printf("%nMatrices in storages...");
            System.out.printf("%n********************************************************************************************************************************%n");
            Print.printAllInternalStorage();
            System.out.printf("********************************************************************************************************************************%n%n");
        }
    }
}
