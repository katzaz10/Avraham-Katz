package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.containers.LastEquationInput;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.containers.LastOutputType;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Recasting a variable Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class RecastLastVariableQuery implements CalculatorQuery
{
    private String originalVariable;
    private String newVariable;


    public RecastLastVariableQuery(String originalVariable, String newVariable) 
    {
        this.originalVariable = originalVariable;
        this.newVariable = newVariable;
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        InternalStorage.Singleton.add(this.newVariable, InternalStorage.Singleton.get(this.originalVariable));
        LastEquationInput.Singleton.add(this.newVariable);
        LastMatrixOutput.Singleton.add(InternalStorage.Singleton.get(this.originalVariable));
        LastOutputType.Singleton.setLastOutputMATRIX();

        System.out.println();
        System.out.println("Matrix '" + this.newVariable +"':");
        Print.printMatrix(InternalStorage.Singleton.get(this.newVariable));
    }
}
