package math.shell.linearAlgebra.calculator;

import java.math.BigDecimal;

/**
 *	Parses user input into commands for the calculator to perform
 *	@author Avraham Katz
 *	@version 1.0
 */

public class CalculatorParser
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
                        throw new IllegalArgumentException();
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

        CalculatorParser.lengthCheck(uncondensedString, numeratorLength + denominatorLength + 1);

        BigDecimal numerator = new BigDecimal(numeratorString);
        BigDecimal denominator = new BigDecimal(denominatorString);

        BigDecimal returnBigDecimal = numerator.divide(denominator, BigDecimal.ROUND_HALF_EVEN);

        return returnBigDecimal;
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
     *	Takes a string, parses out a variable linked to a Matrix in the calculator storage, and gets Matrix from storage associated with that variable
     *	@param string String holding Matrix variable
     *	@param matrixSpot Index of char where Matrix variable at
     *	@return Matrix Matrix associated with the parsed variable
     */
    public static Matrix matrixFromString(String string, int matrixSpot)
    {
        char charKey = string.charAt(matrixSpot);

        String key = String.valueOf(charKey);

        Matrix matrix = InternalStorage.Singleton.get(key);

        return matrix;
    }


    public static String bump = "   ";


    /**
     *	Last type outputted by the calculator, starts at NULL, and switches off between MATRIX and BIGDECIMAL
     */
    public enum OutputType
    {
        NULL, MATRIX, BIGDECIMAL
    }


    /**
     *	Holds the last type outputted by the calculator. Starts at NULL, and switches off between MATRIX and BIGDECIMAL
     */
    public static OutputType currentType = OutputType.NULL;


    /**
     *	As parser checks which operation to run, if runs an operation, status of parser becomes HIT, not running through any other operation checkers
     */
    public enum HitOrMiss
    {
        HIT, MISS
    }


    /**
     *	If the string input by user makes it through the first layer of an operation checker method, changes status to HitOrMiss.HIT. If it does get through the first layer, cannot go through any other operation checker method first layers
     */
    public static HitOrMiss fallThroughCheck = HitOrMiss.MISS;


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


    /**
     *	Checks to see if any operations that store a matrix or BigDecimal in the queue have been performed yet
     *	@throws NothingInQueueException Thrown if containers are empty and attempting to use last output
     */
    private static void nullStateCheck() throws NothingInQueueException
    {
        if (currentType == OutputType.NULL)
        {
            throw new NothingInQueueException(ErrorMessages.nullState);
        }
    }


    /**
     *	Checks to see if the last output type is of type MATRIX
     *	@throws NoMatrixInQueueException Thrown if matrix was not last output and attempting to use last output
     */
    private static void matrixTypeCheck() throws NoMatrixInQueueException
    {
        if (currentType != OutputType.MATRIX)
        {
            throw new NoMatrixInQueueException(ErrorMessages.nonMatrix);
        }
    }


    /**
     *	Determines if the calculator should do the reduced row echelon form operation
     *	@param string Equation checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private static void operationRREF(String string) throws NothingInQueueException, NoMatrixInQueueException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.startsWith("rref("))
        {
            fallThroughCheck = HitOrMiss.HIT;

            int nextSpot = 5;

            if (string.equals("rref()"))
            {
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix = LastMatrixOutput.Singleton.getLastOutput();
                Matrix finalMatrix = matrix.reducedRowEchelonForm();
                LastMatrixOutput.Singleton.add(finalMatrix);
                currentType = OutputType.MATRIX;
                Print.printMatrix(finalMatrix);
            }

            else if (Character.isLetter(string.charAt(nextSpot)) && string.charAt(nextSpot + 1) == ')')
            {
                lengthCheck(string, 7);

                Matrix matrix = matrixFromString(string, nextSpot);
                Matrix finalMatrix =  matrix.reducedRowEchelonForm();
                LastMatrixOutput.Singleton.add(finalMatrix);
                currentType = OutputType.MATRIX;
                Print.printMatrix(finalMatrix);
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the row echelon form operation
     *	@param string Equation checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private static void operationREF(String string) throws NothingInQueueException, NoMatrixInQueueException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.startsWith("ref("))
        {
            fallThroughCheck = HitOrMiss.HIT;

            int nextSpot = 4;

            if (string.equals("ref()"))
            {
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix = LastMatrixOutput.Singleton.getLastOutput();
                Matrix finalMatrix =  matrix.rowEchelonForm();
                LastMatrixOutput.Singleton.add(finalMatrix);
                currentType = OutputType.MATRIX;
                Print.printMatrix(finalMatrix);
            }

            else if (Character.isLetter(string.charAt(nextSpot)) && string.charAt(nextSpot + 1) == ')')
            {
                lengthCheck(string, 6);

                Matrix matrix = matrixFromString(string, nextSpot);
                Matrix finalMatrix =  matrix.rowEchelonForm();
                LastMatrixOutput.Singleton.add(finalMatrix);
                currentType = OutputType.MATRIX;
                Print.printMatrix(finalMatrix);
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the inverse operation
     *	@param string Equation checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private static void operationINV(String string) throws NothingInQueueException, NoMatrixInQueueException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.startsWith("inv("))
        {
            fallThroughCheck = HitOrMiss.HIT;

            int nextSpot = 4;

            if (string.equals("inv()"))
            {
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix = LastMatrixOutput.Singleton.getLastOutput(); //create InverseQuery
                Matrix finalMatrix = matrix.inverse();
                LastMatrixOutput.Singleton.add(finalMatrix);
                currentType = OutputType.MATRIX;
                Print.printMatrix(finalMatrix);
            }

            else if (Character.isLetter(string.charAt(nextSpot)) && string.charAt(nextSpot + 1) == ')')
            {
                lengthCheck(string, 6);

                Matrix matrix = matrixFromString(string, nextSpot); //create InverseQuery
                Matrix finalMatrix = matrix.inverse();
                LastMatrixOutput.Singleton.add(finalMatrix);
                currentType = OutputType.MATRIX;
                Print.printMatrix(finalMatrix);
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the matrix multiplication or scalar multiplication operation
     *	@param string Equation checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     */
    private static void operationMultiplicationSymbol(String string) throws NothingInQueueException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.contains("*"))
        {
            fallThroughCheck = HitOrMiss.HIT;

            if (string.startsWith("*") && Character.isLetter(string.charAt(1)))
            {
                nullStateCheck();
                lengthCheck(string, 2);

                if (currentType == OutputType.BIGDECIMAL)
                {
                    Matrix matrix = matrixFromString(string, 1);
                    BigDecimal scalar = LastBigDecimalOutput.Singleton.getLastOutput();

                    Matrix finalMatrix = matrix.scalarMultiply(scalar);
                    LastMatrixOutput.Singleton.add(finalMatrix);
                    currentType = OutputType.MATRIX;
                    Print.printMatrix(finalMatrix);
                }

                else if (currentType == OutputType.MATRIX)
                {
                    Matrix matrix1 = LastMatrixOutput.Singleton.getLastOutput();
                    Matrix matrix2 = matrixFromString(string, 1);

                    try
                    {
                        Matrix finalMatrix = matrix1.multiply(matrix2);
                        LastMatrixOutput.Singleton.add(finalMatrix);
                        currentType = OutputType.MATRIX;
                        Print.printMatrix(finalMatrix);
                    }

                    catch (UndefinedSolutionException rowNotColumn)
                    {
                        System.out.printf("<ERROR: Row length of first matrix must be same size as column height of second matrix to perform matrix multiplication... %d != %d> %n%n", matrix1.getRowLength(), matrix2.getColumnHeight());

                    }
                }
            }

            else
            {
                if (string.charAt(0) == '(' && (Character.isDigit(string.charAt(1)) || string.charAt(1) == '.' || string.charAt(1) == '-'))
                {
                    BigDecimal scalar = bigDecimalFromString(string, 1);

                    int bigDecimalLength = bigDecimalLengthFromString(string, 1);

                    if (string.charAt(bigDecimalLength + 1) == ')' && string.charAt(bigDecimalLength + 2) == '*' && Character.isLetter(string.charAt(bigDecimalLength + 3)))
                    {
                        lengthCheck(string, bigDecimalLength + 4);

                        Matrix matrix = matrixFromString(string, bigDecimalLength + 3);
                        Matrix finalMatrix = matrix.scalarMultiply(scalar);
                        LastMatrixOutput.Singleton.add(finalMatrix);
                        currentType = OutputType.MATRIX;
                        Print.printMatrix(finalMatrix);
                    }

                    else
                    {
                        throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                    }
                }

                else if (Character.isLetter(string.charAt(0)) && string.charAt(1) == '*' && Character.isLetter(string.charAt(2)))
                {
                    lengthCheck(string, 3);

                    Matrix matrix1 = matrixFromString(string, 0);
                    Matrix matrix2 = matrixFromString(string, 2);

                    try
                    {
                        Matrix finalMatrix = matrix1.multiply(matrix2);
                        LastMatrixOutput.Singleton.add(finalMatrix);
                        currentType = OutputType.MATRIX;
                        Print.printMatrix(finalMatrix);
                    }

                    catch (UndefinedSolutionException rowNotColumn)
                    {
                        System.out.printf("<ERROR: Row length of first matrix must be same size as column height of second matrix to perform matrix multiplication... %d != %d> %n%n", matrix1.getRowLength(), matrix2.getColumnHeight());
                    }
                }

                else
                {
                    throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                }
            }
        }
    }


    /**
     *	Determines if the calculator should do the matrix addition operation
     *	@param string Equation checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private static void operationAdditionSymbol(String string) throws NothingInQueueException, NoMatrixInQueueException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.contains("+"))
        {
            fallThroughCheck = HitOrMiss.HIT;

            if (string.startsWith("+") && Character.isLetter(string.charAt(1)) && string.length() == 2)
            {
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix1 = LastMatrixOutput.Singleton.getLastOutput();
                Matrix matrix2 = matrixFromString(string, 1);

                try
                {
                    Matrix finalMatrix = matrix1.add(matrix2);
                    LastMatrixOutput.Singleton.add(finalMatrix);
                    currentType = OutputType.MATRIX;
                    Print.printMatrix(finalMatrix);
                }

                catch (UndefinedSolutionException notSameSize)
                {
                    System.out.printf("<ERROR: cannot perform matrix addition on matrices of different sizes... %s != %s> %n%n", matrix1.getSize(), matrix2.getSize());
                }
            }


            else if (Character.isLetter(string.charAt(0)) && string.charAt(1) == '+' && Character.isLetter(string.charAt(2)))
            {
                lengthCheck(string, 3);

                Matrix matrix1 = matrixFromString(string, 0);
                Matrix matrix2 = matrixFromString(string, 2);

                try
                {
                    Matrix finalMatrix = matrix1.add(matrix2);
                    LastMatrixOutput.Singleton.add(finalMatrix);
                    currentType = OutputType.MATRIX;
                    Print.printMatrix(finalMatrix);
                }

                catch (UndefinedSolutionException notSameSize)
                {
                    System.out.printf("<ERROR: cannot perform matrix addition on matrices of different sizes... %s != %s> %n%n", matrix1.getSize(), matrix2.getSize());
                }
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the matrix subtraction operation
     *	@param string User input checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private static void operationSubtractionSymbol(String string) throws NothingInQueueException, NoMatrixInQueueException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.contains("-"))
        {
            fallThroughCheck = HitOrMiss.HIT;

            if (string.startsWith("-") && Character.isLetter(string.charAt(1)) && string.length() == 2)
            {
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix1 = LastMatrixOutput.Singleton.getLastOutput();
                Matrix matrix2 = matrixFromString(string, 1);

                try
                {
                    Matrix finalMatrix = matrix1.subtract(matrix2);
                    LastMatrixOutput.Singleton.add(finalMatrix);
                    currentType = OutputType.MATRIX;
                    Print.printMatrix(finalMatrix);
                }

                catch (UndefinedSolutionException notSameSize)
                {
                    System.out.printf("<ERROR: cannot perform matrix subtraction on matrices of different sizes... %s != %s> %n%n", matrix1.getSize(), matrix2.getSize());
                }
            }


            else if (Character.isLetter(string.charAt(0)) && string.charAt(1) == '-' && Character.isLetter(string.charAt(2)))
            {
                lengthCheck(string, 3);

                Matrix matrix1 = matrixFromString(string, 0);
                Matrix matrix2 = matrixFromString(string, 2);

                try
                {
                    Matrix finalMatrix = matrix1.subtract(matrix2);
                    LastMatrixOutput.Singleton.add(finalMatrix);
                    currentType = OutputType.MATRIX;
                    Print.printMatrix(finalMatrix);
                }

                catch (UndefinedSolutionException notSameSize)
                {
                    System.out.printf("<ERROR: cannot perform matrix subtraction on matrices of different sizes... %s != %s> %n%n", matrix1.getSize(), matrix2.getSize());
                }
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the transpose or matrix to power n operation
     *	@param string User input checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     *	@throws NonSquareMatrixException Thrown if matrix not a square matrix, both operations require a square matrix
     */
    private static void operationCarrotSymbol(String string) throws NothingInQueueException, NoMatrixInQueueException, NonSquareMatrixException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.contains("^"))
        {
            fallThroughCheck = HitOrMiss.HIT;

            if (string.startsWith("^") && (string.charAt(1) == 't' || string.charAt(1) == '('))
            {
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix = LastMatrixOutput.Singleton.getLastOutput();

                if (string.charAt(1) == 't')
                {
                    lengthCheck(string, 2);

                    Matrix finalMatrix = matrix.transpose();
                    LastMatrixOutput.Singleton.add(finalMatrix);
                    currentType = OutputType.MATRIX;
                    Print.printMatrix(finalMatrix);
                }

                else if (string.charAt(1) == '(' && (Character.isDigit(string.charAt(2)) || string.charAt(2) == '-'))
                {
                    Integer power = intFromString(string, 2);

                    int powerLength = power.toString().length();

                    lengthCheck(string, powerLength + 3);

                    Matrix finalMatrix = matrix.toPower(power);
                    LastMatrixOutput.Singleton.add(finalMatrix);
                    currentType = OutputType.MATRIX;
                    Print.printMatrix(finalMatrix);
                }

                else
                {
                    throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                }
            }

            else if (Character.isLetter(string.charAt(0)) && string.charAt(1) == '^' && (string.charAt(2) == 't' || string.charAt(2) == '('))
            {
                if (string.charAt(2) == 't')
                {
                    lengthCheck(string, 3);

                    Matrix matrix = matrixFromString(string, 0);
                    Matrix finalMatrix = matrix.transpose();
                    LastMatrixOutput.Singleton.add(finalMatrix);
                    currentType = OutputType.MATRIX;
                    Print.printMatrix(finalMatrix);
                }

                else if (string.charAt(2) == '(' && (Character.isDigit(string.charAt(3)) || string.charAt(3) == '-'))
                {
                    Integer power = intFromString(string, 3);

                    int powerLength = power.toString().length();

                    lengthCheck(string, powerLength + 4);

                    Matrix matrix = matrixFromString(string, 0);
                    Matrix finalMatrix = matrix.toPower(power);
                    LastMatrixOutput.Singleton.add(finalMatrix);
                    currentType = OutputType.MATRIX;
                    Print.printMatrix(finalMatrix);
                }

                else
                {
                    throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                }
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the minor operation
     *	@param string User input checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     *	@throws NonSquareMatrixException Thrown if matrix not a square matrix
     */
    private static void operationMNR(String string) throws NothingInQueueException, NoMatrixInQueueException, NonSquareMatrixException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.startsWith("mnr("))
        {
            fallThroughCheck = HitOrMiss.HIT;

            int nextSpot = 4;

            if (Character.isDigit(string.charAt(nextSpot)))
            {
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix = LastMatrixOutput.Singleton.getLastOutput();

                Integer row = intFromString(string, nextSpot);

                if (row < 1 || row > matrix.getRowLength())
                {
                    System.out.println("ERROR: row number for index not in parameters of matrix");
                    throw new IllegalArgumentException();
                }

                int firstIntLength = row.toString().length();

                nextSpot = nextSpot + firstIntLength;

                if (string.charAt(nextSpot) == ',' && Character.isDigit(string.charAt(nextSpot + 1)))
                {
                    Integer column = intFromString(string, nextSpot + 1);

                    if (column < 1 || column > matrix.getColumnHeight())
                    {
                        System.out.println("ERROR: column number for index not in parameters of matrix");
                        throw new IllegalArgumentException();
                    }

                    int secondIntLength = column.toString().length();

                    nextSpot = nextSpot + 1 + secondIntLength;

                    if (string.charAt(nextSpot) != ')')
                    {
                        throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                    }

                    lengthCheck(string, 6 + firstIntLength + secondIntLength);

                    Matrix finalMatrix = matrix.minor(row - 1, column - 1);	//matrices in real world numbered 1 to n, but 2d arrays in java run from 0 to n, so converting for java purposes
                    LastMatrixOutput.Singleton.add(finalMatrix);
                    currentType = OutputType.MATRIX;
                    Print.printMatrix(finalMatrix);
                }

                else
                {
                    throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                }
            }

            else if (Character.isLetter(string.charAt(nextSpot)) && string.charAt(nextSpot + 1) == ',')
            {
                Matrix matrix = matrixFromString(string, nextSpot);

                nextSpot = nextSpot + 2;

                if (Character.isDigit(string.charAt(nextSpot)))
                {
                    Integer row = intFromString(string, nextSpot);

                    if (row < 1 || row > matrix.getRowLength())
                    {
                        System.out.println("ERROR: row number for index not in parameters of matrix");
                        throw new IllegalArgumentException();
                    }

                    int firstIntLength = row.toString().length();

                    nextSpot = nextSpot + firstIntLength;

                    if (string.charAt(nextSpot) == ',' && Character.isDigit(string.charAt(nextSpot + 1)))
                    {
                        Integer column = intFromString(string, nextSpot + 1);

                        if (column < 1 || column > matrix.getColumnHeight())
                        {
                            System.out.println("ERROR: column number for index not in parameters of matrix");
                            throw new IllegalArgumentException();
                        }

                        int secondIntLength = column.toString().length();

                        nextSpot = nextSpot + 1 + secondIntLength;

                        if (string.charAt(nextSpot) != ')')
                        {
                            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                        }

                        lengthCheck(string, 8 + firstIntLength + secondIntLength);

                        Matrix finalMatrix = matrix.minor(row - 1, column - 1); //matrices in real world numbered 1 to n, but 2d arrays in java run from 0 to n, so converting for java purposes
                        LastMatrixOutput.Singleton.add(finalMatrix);
                        currentType = OutputType.MATRIX;
                        Print.printMatrix(finalMatrix);
                    }

                    else
                    {
                        throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                    }
                }

                else
                {
                    throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                }
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the cofactor matrix operation
     *	@param string User input checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     *	@throws NonSquareMatrixException Thrown if matrix not a square matrix
     */
    private static void operationCOFM(String string) throws NothingInQueueException, NoMatrixInQueueException, NonSquareMatrixException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.startsWith("cofm("))
        {
            fallThroughCheck = HitOrMiss.HIT;

            int nextSpot = 5;

            if (string.equals("cofm()"))
            {
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix = LastMatrixOutput.Singleton.getLastOutput();
                Matrix finalMatrix = matrix.cofactorMatrix();
                LastMatrixOutput.Singleton.add(finalMatrix);
                currentType = OutputType.MATRIX;
                Print.printMatrix(finalMatrix);
            }

            else if (Character.isLetter(string.charAt(nextSpot)) && string.charAt(nextSpot + 1) == ')')
            {
                lengthCheck(string, 7);

                Matrix matrix = matrixFromString(string, nextSpot);
                Matrix finalMatrix = matrix.cofactorMatrix();
                LastMatrixOutput.Singleton.add(finalMatrix);
                currentType = OutputType.MATRIX;
                Print.printMatrix(finalMatrix);
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the adjoint matrix operation
     *	@param string User input checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     *	@throws NonSquareMatrixException Thrown if matrix not a square matrix
     */
    private static void operationADJM(String string) throws NothingInQueueException, NoMatrixInQueueException, NonSquareMatrixException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.startsWith("adjm("))
        {
            fallThroughCheck = HitOrMiss.HIT;

            int nextSpot = 5;

            if (string.equals("adjm()"))
            {
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix = LastMatrixOutput.Singleton.getLastOutput();
                Matrix finalMatrix = matrix.adjointMatrix();
                LastMatrixOutput.Singleton.add(finalMatrix);
                currentType = OutputType.MATRIX;
                Print.printMatrix(finalMatrix);
            }

            else if (Character.isLetter(string.charAt(nextSpot)) && string.charAt(nextSpot + 1) == ')')
            {
                lengthCheck(string, 7);

                Matrix matrix = matrixFromString(string, nextSpot);
                Matrix finalMatrix = matrix.adjointMatrix();
                LastMatrixOutput.Singleton.add(finalMatrix);
                currentType = OutputType.MATRIX;
                Print.printMatrix(finalMatrix);
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the trace operation
     *	@param string User input checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     *	@throws NonSquareMatrixException Thrown if matrix not a square matrix
     */
    private static void operationTR(String string) throws NothingInQueueException, NoMatrixInQueueException, NonSquareMatrixException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.startsWith("tr("))
        {
            fallThroughCheck = HitOrMiss.HIT;

            int nextSpot = 3;

            if (string.equals("tr()"))
            {
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix = LastMatrixOutput.Singleton.getLastOutput();
                BigDecimal finalBigDecimal = matrix.trace();
                LastBigDecimalOutput.Singleton.add(finalBigDecimal);
                currentType = OutputType.BIGDECIMAL;
                System.out.println(bump + finalBigDecimal);
                System.out.println();
            }

            else if (Character.isLetter(string.charAt(nextSpot)) && string.charAt(nextSpot + 1) == ')')
            {
                lengthCheck(string, 5);

                Matrix matrix = matrixFromString(string,nextSpot);
                BigDecimal finalBigDecimal = matrix.trace();
                LastBigDecimalOutput.Singleton.add(finalBigDecimal);
                currentType = OutputType.BIGDECIMAL;
                System.out.println(bump + finalBigDecimal);
                System.out.println();

            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the determinant operation
     *	@param string User input checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     *	@throws NonSquareMatrixException Thrown if matrix not a square matrix
     */
    private static void operationDET(String string) throws NothingInQueueException, NoMatrixInQueueException, NonSquareMatrixException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.startsWith("det("))
        {
            fallThroughCheck = HitOrMiss.HIT;

            int nextSpot = 4;

            if (string.equals("det()"))			{
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix = LastMatrixOutput.Singleton.getLastOutput();
                BigDecimal finalBigDecimal = matrix.determinant();
                LastBigDecimalOutput.Singleton.add(finalBigDecimal);
                currentType = OutputType.BIGDECIMAL;
                System.out.println("   " + finalBigDecimal);
                System.out.println();
            }

            else if (Character.isLetter(string.charAt(nextSpot)) && string.charAt(nextSpot + 1) == ')')
            {
                lengthCheck(string, 6);

                Matrix matrix = matrixFromString(string, nextSpot);
                BigDecimal finalBigDecimal = matrix.determinant();
                LastBigDecimalOutput.Singleton.add(finalBigDecimal);
                currentType = OutputType.BIGDECIMAL;
                System.out.println("   " + finalBigDecimal);
                System.out.println();
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the cofactor operation
     *	@param string User input checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     *	@throws NonSquareMatrixException Thrown if matrix not a square matrix
     */
    private static void operationCOF(String string) throws NothingInQueueException, NoMatrixInQueueException, NonSquareMatrixException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.startsWith("cof("))
        {
            fallThroughCheck = HitOrMiss.HIT;

            int nextSpot = 4;

            if (Character.isDigit(string.charAt(nextSpot)))
            {
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix = LastMatrixOutput.Singleton.getLastOutput();

                Integer row = intFromString(string, nextSpot);

                if (row < 1 || row > matrix.getRowLength())
                {
                    System.out.println("ERROR: row number for index not in parameters of matrix");
                    throw new IllegalArgumentException();
                }

                int firstIntLength = row.toString().length();

                nextSpot = nextSpot + firstIntLength;

                if (string.charAt(nextSpot) == ',' && Character.isDigit(string.charAt(nextSpot + 1)))
                {
                    Integer column = intFromString(string, nextSpot + 1);

                    if (column < 1 || column > matrix.getColumnHeight())
                    {
                        System.out.println("ERROR: column number for index not in parameters of matrix");
                        throw new IllegalArgumentException();
                    }

                    int secondIntLength = column.toString().length();

                    nextSpot = nextSpot + 1 + secondIntLength;

                    if (string.charAt(nextSpot) != ')')
                    {
                        throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                    }

                    lengthCheck(string, 6 + firstIntLength + secondIntLength);

                    BigDecimal finalBigDecimal = matrix.cofactor(row - 1, column - 1); //matrices in real world numbered 1 to n, but 2d arrays in java run from 0 to n, so converting for java purposes
                    LastBigDecimalOutput.Singleton.add(finalBigDecimal);
                    currentType = OutputType.BIGDECIMAL;
                    System.out.println("   " + finalBigDecimal);
                    System.out.println();
                }

                else
                {
                    throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                }
            }

            else if (Character.isLetter(string.charAt(nextSpot)) && string.charAt(nextSpot + 1) == ',')
            {
                Matrix matrix = matrixFromString(string, nextSpot);

                nextSpot = nextSpot + 2;

                if (Character.isDigit(string.charAt(nextSpot)))
                {
                    Integer row = intFromString(string, nextSpot);

                    if (row < 1 || row > matrix.getRowLength())
                    {
                        System.out.println("ERROR: row number for index not in parameters of matrix");
                        throw new IllegalArgumentException();
                    }

                    int firstIntLength = row.toString().length();

                    nextSpot = nextSpot + firstIntLength;

                    if (string.charAt(nextSpot) == ',' && Character.isDigit(string.charAt(nextSpot + 1)))
                    {
                        Integer column = intFromString(string, nextSpot + 1);

                        if (column < 1 || column > matrix.getColumnHeight())
                        {
                            System.out.println("ERROR: column number for index not in parameters of matrix");
                            throw new IllegalArgumentException();
                        }

                        int secondIntLength = column.toString().length();

                        nextSpot = nextSpot + 1 + secondIntLength;

                        if (string.charAt(nextSpot) != ')')
                        {
                            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                        }

                        lengthCheck(string, 8 + firstIntLength + secondIntLength);

                        BigDecimal finalBigDecimal = matrix.cofactor(row - 1, column - 1); //matrices in real world numbered 1 to n, but 2d arrays in java run from 0 to n, so converting for java purposes
                        LastBigDecimalOutput.Singleton.add(finalBigDecimal);
                        currentType = OutputType.BIGDECIMAL;
                        System.out.println("   " + finalBigDecimal);
                        System.out.println();
                    }

                    else
                    {
                        throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                    }
                }

                else
                {
                    throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                }
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the identity matrix operation
     *	@param string User input checking
     */
    private static void operationIDM(String string)
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.startsWith("idm("))
        {
            fallThroughCheck = HitOrMiss.HIT;

            int nextSpot = 4;

            if (Character.isDigit(string.charAt(nextSpot)))
            {
                Integer matrixSize = intFromString(string, nextSpot);
                int intSize = matrixSize.toString().length();

                nextSpot = nextSpot + intSize;

                if (string.charAt(nextSpot) == ')')
                {
                    lengthCheck(string, 5 + intSize);

                    Matrix finalMatrix = Matrix.identity(matrixSize);
                    LastMatrixOutput.Singleton.add(finalMatrix);
                    currentType = OutputType.MATRIX;
                    Print.printMatrix(finalMatrix);
                }

                else
                {
                    throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                }
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the storage operation
     *	@param string User input checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private static void operationSTRG(String string) throws NothingInQueueException, NoMatrixInQueueException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.startsWith("strg"))
        {
            fallThroughCheck = HitOrMiss.HIT;

            if (string.equals("strg")) //outputs all matrices
            {
                if (InternalStorage.Singleton.isEmpty())
                {
                    System.out.printf("%nNo matrices in storage... %n%n");
                }

                else
                {
                    System.out.printf("%nMatrices in storage...");
                    System.out.printf("%n********************************************************************************************************************************%n");
                    Print.printAllInternalStorage();
                    System.out.printf("********************************************************************************************************************************%n%n");
                }
            }

            else if (string.equals("strg()")) //prints the last matrix outputted, if last operation had this matrix
            {
                nullStateCheck();
                matrixTypeCheck();

                Matrix matrix = LastMatrixOutput.Singleton.getLastOutput();
                System.out.println();
                System.out.println("Matrix '" + LastEquationInput.Singleton.getLastOutput() + "':");
                Print.printMatrix(matrix);
            }

            else if (string.charAt(4) == '(' && Character.isLetter(string.charAt(5)) && string.charAt(6) == ')') //outputs a specific matrix
            {
                lengthCheck(string, 7);

                Matrix matrix = matrixFromString(string, 5);
                System.out.println();
                System.out.println("Matrix '" + string.charAt(5) + "':");
                Print.printMatrix(matrix);
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the variable designation operation
     *	@param string User input checking
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private static void operationEqualSign(String string) throws NothingInQueueException, NoMatrixInQueueException
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.contains("="))
        {
            if (string.startsWith("=") && Character.isLetter(string.charAt(1)))		//=M
            {
                nullStateCheck();
                matrixTypeCheck();
                lengthCheck(string, 2);

                String matrixName = "" + string.charAt(1);

                InternalStorage.Singleton.add(matrixName, LastMatrixOutput.Singleton.getLastOutput());
                LastEquationInput.Singleton.add(matrixName);

                System.out.println();
                System.out.println("Matrix '" + matrixName +"':");
                Print.printMatrix(LastMatrixOutput.Singleton.getLastOutput());
            }

            else if (Character.isLetter(string.charAt(0)) && string.charAt(1) == '=' && Character.isLetter(string.charAt(2)) && string.length() == 3)     //M=M
            {
                String matrixName = "" + string.charAt(0);

                InternalStorage.Singleton.add(matrixName, matrixFromString(string, 2));
                LastEquationInput.Singleton.add(matrixName);
                LastMatrixOutput.Singleton.add(matrixFromString(string, 2));
                currentType = OutputType.MATRIX;

                System.out.println();
                System.out.println("Matrix '" + matrixName +"':");
                Print.printMatrix(InternalStorage.Singleton.get(matrixName));
            }

            else if (Character.isLetter(string.charAt(0)) && string.charAt(1) == '='&& string.charAt(2) == '[' && string.endsWith("]")) //M=[R,R,R,R/R,R,R,R]
            {
                String matrixName = "" + string.charAt(0);

                String matrixString = string.substring(2);
                Matrix newMatrix = new Matrix(matrixString);

                InternalStorage.Singleton.add(matrixName, newMatrix);
                LastEquationInput.Singleton.add(matrixName);
                LastMatrixOutput.Singleton.add(newMatrix);
                currentType = OutputType.MATRIX;

                System.out.println();
                System.out.println("Matrix '" + matrixName +"':");
                Print.printMatrix(matrixName);
            }

            else if (Character.isLetter(string.charAt(0)) && string.charAt(1) == '=' && !Character.isDigit(string.charAt(2))) ///M=O
            {
                String matrixName = "" + string.charAt(0);
                String operation = string.substring(2);

                parseEquation(operation);
                InternalStorage.Singleton.add(matrixName, LastMatrixOutput.Singleton.getLastOutput());
                LastEquationInput.Singleton.add(matrixName);
                fallThroughCheck = HitOrMiss.HIT;

                System.out.println();
                System.out.println("Matrix '" + matrixName +"':");
                Print.printMatrix(LastMatrixOutput.Singleton.getLastOutput());
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }

            fallThroughCheck = HitOrMiss.HIT;
        }
    }


    /**
     *	Determines if the calculator should do the exit operation, and in the process writes all Matrices stored in calculator storage to external file "calculator_storage.txt" for subsequent runnings of program
     *	@param string User input checking
     */
    public static void operationEXIT(String string)
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.equals("exit"))
        {
            ExternalStorage.writeExternalStorage();
            System.out.printf("%nExiting calculator... %n%n");
            System.exit(0);
        }
    }


    /**
     *	Determines if the calculator should do the format menu operation
     *	@param string User input checking
     */
    private static void operationFRMT(String string)
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.equals("frmt"))
        {
            fallThroughCheck = HitOrMiss.HIT;

            InterfaceMethods.findFormat();
        }
    }


    /**
     *  A series of operations not public to the user
     *  @param string User input checking
     **/
    private static void operationHIDDEN(String string)
    {
        if (fallThroughCheck == HitOrMiss.MISS && string.equals("clearinternalstorage"))
        {
            fallThroughCheck = HitOrMiss.HIT;

            InternalStorage.Singleton.clear();
            System.out.printf("%n%ninternal storage cleared%n%n");
        }
    }


    /**
     *	Iterates through all operation checkers, finding the correct operation to perform
     *	@param string User input checking
     */
    public static void parseEquation(String string)
    {
        try
        {
            operationEqualSign(string);
            operationCarrotSymbol(string);
            operationMultiplicationSymbol(string);
            operationRREF(string);
            operationREF(string);
            operationINV(string);
            operationMNR(string);
            operationCOFM(string);
            operationADJM(string);
            operationTR(string);
            operationDET(string);
            operationCOF(string);
            operationAdditionSymbol(string);
            operationSubtractionSymbol(string);
            operationIDM(string);
            operationSTRG(string);
            operationEXIT(string);
            operationFRMT(string);
            operationHIDDEN(string);

            if (fallThroughCheck == HitOrMiss.MISS)
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }

            else
            {
                fallThroughCheck = HitOrMiss.MISS; //recalibrates the fallThroughCheck to the intial MISS state
            }
        }

        catch (NothingInQueueException nullState)
        {
            System.out.println("<ERROR: trying to use last output for current operation, but no matrices or doubles in queue (either no operations performed yet, no operations with equation performed yet, or previous operation illegal/bad operation)");
            System.out.println();
            fallThroughCheck = HitOrMiss.MISS;
        }

        catch (NoMatrixInQueueException nonMatrix)
        {
            System.out.printf("<ERROR: trying to use last output for current operation, but operation requires matrix and last output not a matrix> %n%n");
            fallThroughCheck = HitOrMiss.MISS;
        }

        catch (NonSquareMatrixException nonSquare)
        {
            System.out.printf("<ERROR: trying to use non-square matrix in operation that requires square matrix> %n%n");
            fallThroughCheck = HitOrMiss.MISS;
        }

        catch (IncorrectSyntaxException badSyntax)
        {
            System.out.printf("<ERROR: input '%s' has incorrect syntax> %n%n", string);
            fallThroughCheck = HitOrMiss.MISS;
        }

        catch (NotInvertibleException notInvertible)
        {
            System.out.printf("<ERROR: matrix not invertible> %n%n");
            fallThroughCheck = HitOrMiss.MISS;
        }

        catch (IllegalArgumentException all)
        {
            fallThroughCheck = HitOrMiss.MISS;
        }

        catch (RuntimeException all)
        {
            System.out.printf("<ERROR: cannot complete operation...try new operation> %n%n");
            fallThroughCheck = HitOrMiss.MISS;
        }
    }


}