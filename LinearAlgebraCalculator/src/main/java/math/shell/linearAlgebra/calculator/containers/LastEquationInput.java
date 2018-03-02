package math.shell.linearAlgebra.calculator.containers;

/**
 *	A containers with one slot that holds the last equation the user inputs
 *	@author Avraham Katz
 *	@version 1.1
 */

public class LastEquationInput
{
    /**
     *	A singleton pattern that acts as the only containers of last equation inputted by user
     */
    public static final LastEquationInput Singleton = new LastEquationInput();

    String[] lastOutput = new String[1];

    private LastEquationInput()
    {

    }


    /**
     *	Adds the last user input as a string to the containers
     *	@param equation Equation of type String adding to containers
     */
    public void add(String equation)
    {
        this.lastOutput[0] = "(" + equation + ")";
    }


    /**
     *	Gets the equation stored in the containers
     *	@return String Equation stored in containers
     */
    public String getLastOutput()
    {
        return this.lastOutput[0];
    }
}

