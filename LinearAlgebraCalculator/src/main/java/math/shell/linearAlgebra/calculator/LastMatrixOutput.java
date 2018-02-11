package math.shell.linearAlgebra.calculator;

/**
 *	A container with one slot that holds the last output of type MATRIX
 *	@author Avraham Katz
 *	@version 1.0
 */

public class LastMatrixOutput
{
    /**
     *	A singleton pattern that acts as the only container of last output of type MATRIX
     */
    public static final LastMatrixOutput Singleton = new LastMatrixOutput();

    Matrix[] lastOutput = new Matrix[1];

    private LastMatrixOutput()
    {

    }


    /**
     *	Adds the Matrix to the container
     *	@param	matrix Matrix adding to container
     */
    public void add(Matrix matrix)
    {
        this.lastOutput[0] = matrix;
    }


    /**
     *	Gets the Matrix stored in the container
     *	@return Matrix The Matrix that was stored
     */
    public Matrix getLastOutput()
    {
        return this.lastOutput[0];
    }
}

