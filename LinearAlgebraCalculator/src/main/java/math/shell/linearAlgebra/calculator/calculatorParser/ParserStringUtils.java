package math.shell.linearAlgebra.calculator.calculatorParser;

import math.shell.linearAlgebra.calculator.utilities.BigDecimalUtils;
import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.exceptions.IncorrectSyntaxException;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 *	String Utility methods for the Calculator Parser
 *	@author Avraham Katz
 *	@version 1.1
 */


public class ParserStringUtils
{
    /**
     *	Pulls out a substring representing a BigDecimal or BigDecimal divided by a BigDecimal
     *	@param string String pulling BigDecimal from
     *	@param firstSpot First index of substring containing BigDecimal
     *	@return String String containing BigDecimal or BigDecimal divided by a BigDecimal
     */
    private static String stripUncondensedBigDecimalFromString(String string, int firstSpot)
    {
        String bigDecimalString = "";

        int decimalPointCounter = 0;
        int slashCounter = 0;

        for (int i = firstSpot; i < string.length(); i++)
        {
            if ((!Character.isDigit(string.charAt(i)) && string.charAt(i) != '.' && string.charAt(i) != '/' && string.charAt(i) != '-') || (i != firstSpot && string.charAt(i) == '-' && string.charAt(i - 1) != '/'))
            {
                break;
            }

            else
            {
                if ((i == firstSpot || string.charAt(i - 1) == '/') && string.charAt(i) == '-' && (Character.isDigit(string.charAt(i + 1)) || string.charAt(i + 1) == '.'))
                {
                    bigDecimalString = bigDecimalString + '-';
                }

                else if (Character.isDigit(string.charAt(i)))
                {
                    bigDecimalString = bigDecimalString + string.charAt(i);
                }

                else if (string.charAt(i) == '.')
                {
                    decimalPointCounter++;

                    if (decimalPointCounter > 1)
                    {
                        System.out.println("ERROR: not a number... too many decimal points");
                        throw new IllegalArgumentException();
                    }

                    bigDecimalString = bigDecimalString + '.';
                }

                else if (string.charAt(i) == '/')
                {
                    slashCounter++;
                    decimalPointCounter = 0;

                    if (slashCounter > 1)
                    {
                        System.out.println("ERROR: not a number... too many division signs");
                        throw new IllegalArgumentException();
                    }

                    bigDecimalString = bigDecimalString + '/';
                }
            }
        }

        if (bigDecimalString == "")
        {
            System.out.println("no BigDecimal to parse");
            throw new IllegalArgumentException();
        }

        if (bigDecimalString.endsWith("/"))
        {
            System.out.println("string ends with division sign but no divisor");
            throw new IllegalArgumentException();
        }

        return bigDecimalString;
    }


    /**
     *	Pulls out a substring representing a BigDecimal from a string
     *	@param string String pulling BigDecimal from
     *	@param firstSpot First index of substring containing BigDecimal
     *	@return String Substring holding BigDecimal
     */
    private static String stripCondensedBigDecimalFromString(String string, int firstSpot)
    {
        String bigDecimalString = "";

        int decimalPointCounter = 0;

        for (int i = firstSpot; i < string.length(); i++)
        {
            if ((!Character.isDigit(string.charAt(i)) && string.charAt(i) != '.' && string.charAt(i) != '-') || (i != firstSpot && string.charAt(i) == '-'))
            {
                break;
            }

            else
            {
                if (i == firstSpot && string.charAt(i) == '-')
                {
                    bigDecimalString = bigDecimalString + '-';
                }

                else if (Character.isDigit(string.charAt(i)))
                {
                    bigDecimalString = bigDecimalString + string.charAt(i);
                }

                else if (string.charAt(i) == '.')
                {
                    decimalPointCounter++;

                    if (decimalPointCounter > 1)
                    {
                        decimalPointCounter = 0;
                        System.out.println("ERROR: not a number... too many decimal points");
                        throw new IllegalArgumentException("ERROR: not a number... too many decimal points");
                    }

                    bigDecimalString = bigDecimalString + '.';
                }
            }
        }

        return bigDecimalString;
    }


    /**
     *	Finds length of substring representing BigDecimal
     *	@param string String pulling BigDecimal from
     *	@param firstSpot First index of substring containing BigDecimal
     *	@return int Length of substring
     */
    private static int condensedBigDecimalLengthFromString(String string, int firstSpot)
    {
        String bigDecimalString = stripCondensedBigDecimalFromString(string, firstSpot);

        if (bigDecimalString.equals(""))
        {
            return 0;
        }

        return bigDecimalString.length();
    }


    static MathContext mc = new MathContext(10, RoundingMode.HALF_UP);

    /**
     *	Takes a string and pulls out a substring representing a BigDecimal starting at designated index until hits next non-Digit, non-period, non-slash, or non-negative sign char. Converts the string to an actual BigDecimal.
     *	If the BigDecimal string contains 2 BigDecimal substrings with a '/' between them, all together representing a rational as a fraction, divides the rationals to form one rational value.
     *	Examples:
     *				bigDecimalFromString("1234", 0) returns 1234
     *				bigDecimalFromString("1234", 2) returns 34
     *				bigDecimalFromString("abs1234.98mhm", 3) returns 1234.98
     *				bigDecimalFromString("abs-1234.98mhm", 3) returns -1234.98
     *				bigDecimalFromString("abs-12/2mhm", 3) returns -6.0000000000
     *
     *	@param string String holding BigDecimal
     *	@param firstSpot First index of char at beginning of BigDecimal
     *	@return BigDecimal BigDecimal from string. If string contains BigDecimal/BigDecimal, returns BigDecimal after dividing the 2 values
     */
    public static BigDecimal bigDecimalFromString(String string, int firstSpot)
    {
        String uncondensedString = stripUncondensedBigDecimalFromString(string, firstSpot);

        if (!uncondensedString.contains("/"))	//if the BigDecimal string represenations does not contains a BigDecimal/BigDecimal, just return the uncondensed string
        {
            BigDecimal returnValue = new BigDecimal(uncondensedString);
            return returnValue;
        }

        String numeratorString = stripCondensedBigDecimalFromString(uncondensedString, 0);
        int numeratorLength = condensedBigDecimalLengthFromString(uncondensedString, 0);

        String denominatorString = stripCondensedBigDecimalFromString(uncondensedString, numeratorLength + 1);
        int denominatorLength = condensedBigDecimalLengthFromString(uncondensedString, numeratorLength + 1);

        lengthCheck(uncondensedString, numeratorLength + denominatorLength + 1);

        BigDecimal numerator = new BigDecimal(numeratorString);
        BigDecimal denominator = new BigDecimal(denominatorString);

        try
        {
            BigDecimal returnBigDecimal = BigDecimalUtils.precisionCheck(numerator.divide(denominator));
            return returnBigDecimal;
        }

        catch (ArithmeticException nonTerminating)
        {
            BigDecimal returnBigDecimal = BigDecimalUtils.precisionCheck(numerator.divide(denominator, new MathContext(10, RoundingMode.HALF_UP)));
            return returnBigDecimal;
        }
    }


    /**
     *	Gets the length of a substring representing a BigDecimal in a String
     *	Examples:
     *				bigDecimalLengthFromString("1234", 0) returns 4
     *				bigDecimalLengthFromString("1234", 2) returns 2
     *				bigDecimalLengthFromString("abs1234.98mhm", 3) returns 7
     *				bigDecimalLengthFromString("abs-1234.98mhm", 3) returns 8
     *				bigDecimalLengthFromString("abs-12/2mhm", 3) returns 5
     *
     *	@param string String getting BigDecimal from
     *	@param firstSpot First index of char at beginning of BigDecimal
     *	@return int Length of BigDecimal substring parsed from String
     */
    public static int bigDecimalLengthFromString(String string, int firstSpot)
    {
        String bigDecimalString = stripUncondensedBigDecimalFromString(string, firstSpot);

        if (bigDecimalString.equals(""))
        {
            return 0;
        }

        return bigDecimalString.length();
    }


    /**
     *	Takes a string and pulls out a substring representing an int starting at designated index until hits next non-Digit, or non-negative sign char. Converts the substring to an int
     *	Examples:
     *				intFromString("abs423", 3) = 423;
     *				intFromString("abs423", 4) = 23;
     *
     *	@param string String holding int
     *	@param firstIntSpot First index of char at beginning of int
     *	@return boolean Whether triangular matrix or not
     */
    public static int intFromString(String string, int firstIntSpot)
    {
        String intString = "";

        int negativeSignCounter = 0;

        for (int i = firstIntSpot; i < string.length(); i++)
        {
            if (string.charAt(i) == '.')
            {
                System.out.println("ERROR: not an int");
                throw new IllegalArgumentException();
            }

            if (string.charAt(i) == '-')
            {
                negativeSignCounter++;

                if (negativeSignCounter > 1)
                {
                    System.out.println("cannot have more than one negative sign");
                    throw new IllegalArgumentException();
                }
            }

            if (!Character.isDigit(string.charAt(i)) && string.charAt(i) != '-')
            {
                break;
            }

            else
            {
                char nextValue = string.charAt(i);

                intString = intString + nextValue;
            }
        }

        return Integer.parseInt(intString);
    }


    /**
     *	Takes a string, parses out a variable linked to a Matrix in the calculator storages, and gets Matrix from storages associated with that variable
     *	@param string String holding Matrix variable
     *	@param matrixSpot Index of char where Matrix variable at
     *	@return Matrix Matrix associated with the parsed variable
     */
    public static String matrixVariableFromString(String string, int matrixSpot)
    {
        char charKey = string.charAt(matrixSpot);

        return String.valueOf(charKey);
    }


    /**
     *	Checks to see if a string is the designated length, if it not the right length, throws an IncorrectSyntaxException. Used for determining user input is legitimate
     *	@param string String checking
     *	@param length Length checking
     */
    public static void lengthCheck(String string, int length)
    {
        if (string.length() != length)
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }

}
