package math.shell.linearAlgebra.calculator;

/**
 *	A container with one slot that holds the last equation the user inputs
 *	@author Avraham Katz
 *	@version 1.0
 */

public class LastEquationInput
{
    /**
     *	A singleton pattern that acts as the only container of last equation inputted by user
     */
    public static final LastEquationInput Singleton = new LastEquationInput();

    String[] lastOutput = new String[1];

    private LastEquationInput()
    {

    }


    /**
     *	Adds the last user input as a string to the container
     *	@param equation Equation of type String adding to container
     */
    public void add(String equation)
    {
        this.lastOutput[0] = "(" + equation + ")";
    }


    /**
     *	Gets the equation stored in the container
     *	@return String Equation stored in container
     */
    public String getLastOutput()
    {
        return this.lastOutput[0];
    }
}

