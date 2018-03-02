package math.shell.linearAlgebra.calculator.storages;

import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.Matrix;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;


/**
 *	A containers that holds Matrices, mapping variable names to Matrices
 *	@author Avraham Katz
 *	@version 1.1
 */

public class InternalStorage
{
    /**
     *	A singleton pattern that holds all Matrices stored in calculator
     */
    public static final InternalStorage Singleton = new InternalStorage();

    Map<String, Matrix> storedInternalStorage = new TreeMap<String, Matrix>();

    private InternalStorage()
    {

    }


    /**
     *	Adds a Matrix to the containers, mapping it to a specific variable name
     *	@param name Variable name
     *	@param matrix Matrix storing
     */
    public void add(String name, Matrix matrix)
    {
        if (name.equals(""))
        {
            throw new IllegalArgumentException(ErrorMessages.emptyName);
        }

        if (name.length() > 1)
        {
            throw new IllegalArgumentException("variable name cannot be longer than 1");
        }

        if (this.storedInternalStorage.containsKey(name))
        {
            this.storedInternalStorage.replace(name, matrix);
        }

        else if (!storedInternalStorage.containsKey(name))
        {
            this.storedInternalStorage.put(name, matrix);
        }
    }


    /**
     *	Gets the Matrix mapped to the inputted name
     *	@param name Name of variable
     *	@return Matrix Matrix getting from storages
     */
    public Matrix get(String name)
    {
        if (!storedInternalStorage.containsKey(name))
        {
            System.out.println("ERROR: matrix '" + name + "' does not exist");
            throw new IllegalArgumentException();
        }

        else
        {
            return this.storedInternalStorage.get(name);
        }
    }


    /**
     * Determines if the internal storages is empty or not
     * @return boolean Whether empty or not
     */
    public boolean isEmpty()
    {
        if (this.storedInternalStorage.size() == 0)
        {
            return true;
        }

        return false;
    }


    public Iterator iterator()
    {
        Iterator it = this.storedInternalStorage.entrySet().iterator();

        return it;
    }


}














