package math.shell.linearAlgebra.calculator.userInterface;

import math.shell.linearAlgebra.calculator.Matrix;
import math.shell.linearAlgebra.calculator.utilities.StringUtils;
import math.shell.linearAlgebra.calculator.containers.LastEquationInput;
import math.shell.linearAlgebra.calculator.storages.InternalStorage;

import java.util.Map;
import java.util.Iterator;
import java.math.BigDecimal;

/**
 *	A series of methods that involve printing
 *	@author Avraham Katz
 *	@version 1.1
 */

public class Print
{
    /**
     *	Prints out the given Matrix
     *	@param matrix Matrix printing out
     */
    public static void printMatrix(Matrix matrix)
    {
        matrix.unNegateAllZeros();

        int longestLength = 0;	//will be used to determine the longest length of any BigDecimal after being converted to string and removing trailing zeros or decimal points

        String[][] editedOutputs = new String[matrix.getColumnHeight()][matrix.getRowLength()];

        for (int i = 0; i < matrix.getColumnHeight(); i++)
        {
            for (int j = 0; j < matrix.getRowLength(); j++)
            {
                BigDecimal nextOutput = matrix.getWrappedMatrix()[i][j];
                String nextOutputAsString = nextOutput.toPlainString();

                if (nextOutputAsString.contains(".") && nextOutputAsString.endsWith("0"))
                {
                    nextOutputAsString = StringUtils.removeTrailingZerosAndDecimal(nextOutputAsString);
                }

                editedOutputs[i][j] = nextOutputAsString;

                if (nextOutputAsString.length() > longestLength)
                {
                    longestLength = nextOutputAsString.length();
                }
            }
        }

        String formatting = ("%" + longestLength + "s ");	//when printing matrix, this will be the string for the printf used to print each individual value, allows for clear formatting that takes into account the longest value printing out

        for (int i = 0; i < matrix.getColumnHeight(); i++)
        {
            System.out.print("    | ");

            for (int j = 0; j < matrix.getRowLength(); j++)
            {
                System.out.printf(formatting, editedOutputs[i][j]);
            }

            System.out.printf("| %n");
        }

        System.out.println();
    }


    /**
     *	Prints a specific Matrix from the storages by variable name
     *	@param name Variable name of Matrix stored in InternalStorage.Singleton
     */
    public static void printMatrix(String name)
    {
        Matrix matrix = InternalStorage.Singleton.get(name);

        printMatrix(matrix);
    }


    /**
     *	Prints all stored matrices, and current Matrix in InternalStorage.Singleton
     */
    public static void printAllInternalStorage()
    {
        Iterator it = InternalStorage.Singleton.iterator();

        while (it.hasNext())
        {
            Map.Entry pair = (Map.Entry)it.next();
            Matrix matrix = (Matrix) pair.getValue();
            System.out.println("Matrix '" + pair.getKey() + "':");
            printMatrix(matrix);
        }
    }


    /**
     *	Prints out an equation after editing the equation by removing blank spaces, converting to lowercase, and adding in last equation if necessary
     *	@param string Equation outprinting
     */
    public static void printEquation(String string)
    {
        String editedString = StringUtils.editEquation(string);
        String finalString = StringUtils.lastOutputInclusion(editedString);

        if (!finalString.contains("strg") && !finalString.contains("=") && !finalString.equals("frmt") && !finalString.equals("exit") && !finalString.equals("clearinternalstorage"))
        {
            LastEquationInput.Singleton.add(finalString);
            System.out.println();
            System.out.println(InterfaceMethods.bump1 + finalString + "=");
        }
    }


}