package math.shell.linearAlgebra.calculator.calculatorParser.queries;

import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.containers.LastMatrixOutput;
import math.shell.linearAlgebra.calculator.containers.LastOutputType;
import math.shell.linearAlgebra.calculator.exceptions.NonSquareMatrixException;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.Print;

/**
 *	Query for user input demanding Minor Operation
 *	@author Avraham Katz
 *	@version 1.1
 */

public class MinorQuery implements CalculatorQuery
{
    private Matrix matrix;
    private int row;
    private int column;


    public MinorQuery(String variable, int row, int column)
    {
        this.matrix = InternalStorage.Singleton.get(variable);
        this.row = row;
        this.column = column;

        if (this.row < 1 || row > this.matrix.getRowLength())
        {
            System.out.println("ERROR: row number for index not in parameters of matrix");
            throw new IllegalArgumentException("ERROR: row number for index not in parameters of matrix");
        }

        if (this.column < 1 || this.column > this.matrix.getColumnHeight())
        {
            System.out.println("ERROR: column number for index not in parameters of matrix");
            throw new IllegalArgumentException("ERROR: column number for index not in parameters of matrix");
        }
    }


    public MinorQuery(int row, int column)
    {
        this.matrix = LastMatrixOutput.Singleton.getLastOutput();
        this.row = row;
        this.column = column;

        if (this.row < 1 || this.row > this.matrix.getRowLength())
        {
            System.out.println("ERROR: row number for index not in parameters of matrix");
            throw new IllegalArgumentException("ERROR: row number for index not in parameters of matrix");
        }

        if (this.column < 1 || this.column > this.matrix.getColumnHeight())
        {
            System.out.println("ERROR: column number for index not in parameters of matrix");
            throw new IllegalArgumentException("ERROR: column number for index not in parameters of matrix");
        }
    }


    /**
     * Runs the operation demanded by the query
     */
    public void runOperation()
    {
        try
        {
            Matrix finalMatrix = this.matrix.minor(this.row - 1, this.column - 1);	//matrices in real world numbered 1 to n, but 2d arrays in java run from 0 to n, so converting for java purposes
            LastMatrixOutput.Singleton.add(finalMatrix);
            LastOutputType.Singleton.setLastOutputMATRIX();
            Print.printMatrix(finalMatrix);
        }

        catch (NonSquareMatrixException nonSquare)
        {
            System.out.printf("<ERROR: trying to use non-square matrix in operation that requires square matrix> %n%n");
        }
    }
}
