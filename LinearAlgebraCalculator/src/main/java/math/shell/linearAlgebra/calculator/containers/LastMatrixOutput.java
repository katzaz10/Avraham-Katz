package math.shell.linearAlgebra.calculator.containers;

import math.shell.linearAlgebra.calculator.Matrix;

/**
 *	A containers with one slot that holds the last output of type MATRIX
 *	@author Avraham Katz
 *	@version 1.1
 */

public class LastMatrixOutput
{
    /**
     *	A singleton pattern that acts as the only containers of last output of type MATRIX
     */
    public static final LastMatrixOutput Singleton = new LastMatrixOutput();

    Matrix[] lastOutput = new Matrix[1];

    private LastMatrixOutput()
    {

    }


    /**
     *	Adds the Matrix to the containers
     *	@param	matrix Matrix adding to containers
     */
    public void add(Matrix matrix)
    {
        this.lastOutput[0] = matrix;
    }


    /**
     *	Gets the Matrix stored in the containers
     *	@return Matrix The Matrix that was stored
     */
    public Matrix getLastOutput()
    {
        return this.lastOutput[0];
    }
}

