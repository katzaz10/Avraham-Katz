package math.shell.linearAlgebra.calculator.utilities;

import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.calculatorParser.ParserStringUtils;
import math.shell.linearAlgebra.calculator.containers.LastEquationInput;
import math.shell.linearAlgebra.calculator.containers.LastOutputType;
import math.shell.linearAlgebra.calculator.exceptions.IncorrectSyntaxException;

import java.math.BigDecimal;

/**
 *	Methods that involve taking information out of strings or editing strings
 *	@author Avraham Katz
 *	@version 1.1
 */

public class StringUtils
{


    /**
     *	Takes a String and continues to remove the last char in the string if it is a 0, or a decimal point ('.'). If last char is a decimal point, will stop trimming after removing decimal point.
     *  @param string String editting
     *  @return String String editting without trailing zeros or decimal if decimal attached to trailing zeros
     */
    public static String removeTrailingZerosAndDecimal(String string)
    {
        String trimString = string;

        while (true)
        {
            if (!trimString.endsWith("0"))
            {
                break;
            }

            else if (trimString.endsWith("0"))
            {
                trimString = trimString.substring(0, trimString.length() - 1);

                if (trimString.endsWith("."))
                {
                    trimString = trimString.substring(0, trimString.length() - 1);

                    return trimString;
                }
            }
        }

        return trimString;
    }


    /**
     *	Takes a string and returns the number of rows in a matrix, end of row identified by symbol '|'
     *	@param string String holding matrix
     *	@return int Number of rows
     */
    private static int columnHeight(String string)
    {
        int columns = 0;

        for (int i = 0; i < string.length(); i++)
        {
            if (string.charAt(i) == '|')
            {
                columns++;
            }
        }

        return columns;
    }


    /**
     *	Takes a string and returns the number of values in a row of a matrix, end of value identified by symbol ','
     *	@param string String holding matrix
     *	@param firstValueSpot Index of first char of row
     *	@return int Numver values in row
     */
    private static int numberValuesInRow(String string, int firstValueSpot)
    {
        int spots = 0;

        for (int i = firstValueSpot; i < string.length(); i++)
        {
            if (string.charAt(i) == ',')
            {
                spots++;
            }

            else if (string.charAt(i) == '|')
            {
                break;
            }
        }

        return spots + 1;
    }


    /**
     *	Takes a string, and returns a substring that contains the next row in a BigDecimal[][]
     *	@param string String containing matrix
     *	@param firstSpot First index of row in string
     *	@return String Substring containing row
     */
    private static String rowAsString(String string, int firstSpot)
    {
        String row = "";

        for (int i = firstSpot; i < string.length(); i++)
        {
            if (string.charAt(i) == '|')
            {
                row = row + string.charAt(i);
                break;
            }

            else
            {
                row = row + string.charAt(i);
            }
        }

        return row;
    }


    /**
     *	Removes all blank spaces in a string
     *	@param string String editing
     *	@return String String after removing all blank spaces
     */
    public static String removeBlankSpace(String string)
    {
        String trimmedString = string.trim();
        String noBlank = trimmedString.replace(" ", "");

        return noBlank;
    }


    /**
     *	Edits String containing calculator equation, converting to lower case and removing all blank spaces
     *	@param string String editing
     *	@return String String after removing blank spaces and converting to lower case
     */
    public static String editEquation(String string)
    {
        String lowerCaseInput = string.toLowerCase();
        String trimmedInput = removeBlankSpace(lowerCaseInput);

        return trimmedInput;
    }


    /**
     *	If user chooses to perform operation on last calculator output, inserts equation that resulted in last calculator output in current equation user inputs
     *	@param string Equation user inputs
     *	@return String String after inputting last equation
     */
    public static String lastOutputInclusion(String string)
    {
        if (string.equals("ref()") || string.equals("rref()") || string.equals("inv()") || string.equals("tr()") || string.equals("det()") || string.equals("cofm()") || string.equals("adjm()"))
        {
            return string.replace("()", "(" + LastEquationInput.Singleton.getLastOutput() + ")");
        }

        else if (string.startsWith("^("))
        {
            return string.replace("^(", LastEquationInput.Singleton.getLastOutput() + "^(");
        }

        else if (string.startsWith("^t"))
        {
            return string.replace("^t", LastEquationInput.Singleton.getLastOutput() + "^t");
        }

        else if (string.startsWith("+"))
        {
            return string.replace("+", LastEquationInput.Singleton.getLastOutput() + "+");
        }

        else if (string.startsWith("-"))
        {
            return string.replace("-", LastEquationInput.Singleton.getLastOutput() + "-");
        }

        else if (string.startsWith("*") && LastOutputType.Singleton.getLastOutputType() == LastOutputType.OutputType.MATRIX)
        {
            return string.replace("*", LastEquationInput.Singleton.getLastOutput() + "*");
        }

        else if (string.startsWith("*") && LastOutputType.Singleton.getLastOutputType() == LastOutputType.OutputType.BIGDECIMAL)
        {
            return string.replace("*", LastEquationInput.Singleton.getLastOutput() + "*");
        }

        else if (string.startsWith("min(") && Character.isDigit(string.charAt(4)) && LastEquationInput.Singleton.getLastOutput() != null)
        {
            return string.replace("min(", "min(" + LastEquationInput.Singleton.getLastOutput());
        }

        else if (string.startsWith("cof(") && Character.isDigit(string.charAt(4)) && LastEquationInput.Singleton.getLastOutput() != null)
        {
            return string.replace("cof(", "cof(" + LastEquationInput.Singleton.getLastOutput());
        }

        return string;
    }


    /**
     *	Converts string to an array of values for a row in a matrix
     *	@param string String containing row of matrix
     *	@return BigDecimal[] Array of values in a row of a matrix
     */
    private static BigDecimal[] arrayFromString(String string)
    {
        BigDecimal[] array = new BigDecimal[numberValuesInRow(string, 0)];

        int nextValueToStore = 0;

        int nextSpotInRow = 0;

        if (string.charAt(string.length() - 1) != '|')
        {
            throw new IllegalArgumentException("no end marker '|'");
        }

        while (true)
        {
            if (Character.isDigit(string.charAt(nextSpotInRow)) || string.charAt(nextSpotInRow) == '-' || string.charAt(nextSpotInRow) == '.')
            {
                array[nextValueToStore] = ParserStringUtils.bigDecimalFromString(string, nextSpotInRow);
                nextValueToStore++;
                nextSpotInRow = nextSpotInRow + ParserStringUtils.bigDecimalLengthFromString(string, nextSpotInRow);
            }

            else if (string.charAt(nextSpotInRow) == '|')
            {
                break;
            }

            else if (string.charAt(nextSpotInRow) == ',')
            {
                nextSpotInRow++;
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }

        return array;
    }


    /**
     *	Converts string to a 2D array of values for a Matrix
     *	Example:
     *			"[1,2|3,4|]" =  |1 2|
     *							|3 4|
     *
     *			"[1,0,0|0,1,0|]" = |1 0 0|
     *							   |0 1 0|
     *
     *	@param string String containing matrix
     *	@return BigDecimal[][] Matrix from string
     */
    public static BigDecimal[][] multiDimensionalArrayFromString(String string)
    {
        if (!string.startsWith("[") || (!Character.isDigit(string.charAt(1)) && string.charAt(1) != '-' && string.charAt(1) != '.') || !string.endsWith("]") || string.charAt(string.length() - 2) != '|')
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }

        BigDecimal[][] multiDimensional = new BigDecimal[columnHeight(string)][];

        int nextSpot = 1;
        int nextRow = 0;
        int rowLength = 0;

        while (true)
        {
            String row = rowAsString(string, nextSpot);

            BigDecimal[] rowAsArray = arrayFromString(row);

            multiDimensional[nextRow] = rowAsArray;

            if (nextRow == 0)
            {
                rowLength = arrayFromString(row).length;
            }

            else
            {
                if (arrayFromString(row).length != rowLength)
                {
                    throw new IllegalArgumentException("all rows must be of equal size");
                }
            }

            nextRow++;

            nextSpot = nextSpot + row.length();

            if (nextSpot == string.length() - 1)
            {
                break;
            }
        }

        return multiDimensional;
    }


}