package math.shell.linearAlgebra.calculator.calculatorParser;

import math.shell.linearAlgebra.calculator.exceptions.ErrorMessages;
import math.shell.linearAlgebra.calculator.calculatorParser.queries.*;
import math.shell.linearAlgebra.calculator.containers.LastOutputType;
import math.shell.linearAlgebra.calculator.exceptions.*;

import java.math.BigDecimal;

/**
 *	Parses user input into commands for the calculator to perform
 *	@author Avraham Katz
 *	@version 1.1
 */

public class CalculatorParser
{

    private String input;


    public CalculatorParser(String input)
    {
        this.input = input;
    }


    /**
     *	Determines if the calculator should do the reduced row echelon form operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseRREF() throws NothingInQueueException, NoMatrixInQueueException
    {
        int nextSpot = 5;

        if (this.input.equals("rref()"))
        { 
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();

            ReducedRowEchelonFormQuery returnQuery = new ReducedRowEchelonFormQuery();
            return returnQuery;
        }

        else if (Character.isLetter(this.input.charAt(nextSpot)) && this.input.charAt(nextSpot + 1) == ')')
        {
            ParserStringUtils.lengthCheck(this.input, 7);

            String matrix = ParserStringUtils.matrixVariableFromString(this.input, nextSpot);

            ReducedRowEchelonFormQuery returnQuery = new ReducedRowEchelonFormQuery(matrix);
            return returnQuery;
        }

        else
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }


    /**
     *	Determines if the calculator should do the row echelon form operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseREF() throws NothingInQueueException, NoMatrixInQueueException
    {
        int nextSpot = 4;

        if (this.input.equals("ref()"))
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();

            RowEchelonFormQuery returnQuery = new RowEchelonFormQuery();
            return returnQuery;
        }

        else if (Character.isLetter(this.input.charAt(nextSpot)) && this.input.charAt(nextSpot + 1) == ')')
        {
            ParserStringUtils.lengthCheck(this.input, 6);

            String matrix = ParserStringUtils.matrixVariableFromString(this.input, nextSpot);

            RowEchelonFormQuery returnQuery = new RowEchelonFormQuery(matrix);
            return returnQuery;
        }

        else
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }


    /**
     *	Determines if the calculator should do the inverse operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseINV() throws NothingInQueueException, NoMatrixInQueueException
    {
        int nextSpot = 4;

        if (this.input.equals("inv()"))
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();

            InverseQuery returnQuery = new InverseQuery();
            return returnQuery;
        }

        else if (Character.isLetter(this.input.charAt(nextSpot)) && this.input.charAt(nextSpot + 1) == ')')
        {
            ParserStringUtils.lengthCheck(this.input, 6);

            String matrix = ParserStringUtils.matrixVariableFromString(this.input, nextSpot);

            InverseQuery returnQuery = new InverseQuery(matrix);
            return returnQuery;
        }

        else
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }


    /**
     *	Determines if the calculator should do the matrix multiplication or scalar multiplication operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     */
    private CalculatorQuery parseMultiplicationSymbol() throws NothingInQueueException
    {
        if (this.input.startsWith("*") && Character.isLetter(this.input.charAt(1)))
        {
            LastOutputType.Singleton.nullStateCheck();
            ParserStringUtils.lengthCheck(this.input, 2);

            if (LastOutputType.Singleton.getLastOutputType() == LastOutputType.OutputType.BIGDECIMAL)
            {
                String matrix = ParserStringUtils.matrixVariableFromString(this.input, 1);

                ScalarMultiplicationQuery returnQuery = new ScalarMultiplicationQuery(matrix);
                return returnQuery;
            }

            else if (LastOutputType.Singleton.getLastOutputType() == LastOutputType.OutputType.MATRIX)
            {
                String matrix2 = ParserStringUtils.matrixVariableFromString(this.input, 1);

                MatrixMultiplicationQuery returnQuery = new MatrixMultiplicationQuery(matrix2);
                return returnQuery;
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax); //for compilation purposes, can never reach here, but had to either throw exception, or return a null CalculatorQuery
            }
        }


        else
        {
            if (this.input.charAt(0) == '(' && (Character.isDigit(this.input.charAt(1)) || this.input.charAt(1) == '.' || this.input.charAt(1) == '-'))
            {
                BigDecimal scalar = ParserStringUtils.bigDecimalFromString(this.input, 1);

                int bigDecimalLength = ParserStringUtils.bigDecimalLengthFromString(this.input, 1);

                if (this.input.charAt(bigDecimalLength + 1) == ')' && this.input.charAt(bigDecimalLength + 2) == '*' && Character.isLetter(this.input.charAt(bigDecimalLength + 3)))
                {
                    ParserStringUtils.lengthCheck(this.input, bigDecimalLength + 4);

                    String matrix = ParserStringUtils.matrixVariableFromString(this.input, bigDecimalLength + 3);

                    ScalarMultiplicationQuery returnQuery = new ScalarMultiplicationQuery(matrix, scalar);
                    return returnQuery;
                }

                else
                {
                    throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                }
            }

            else if (Character.isLetter(this.input.charAt(0)) && this.input.charAt(1) == '*' && Character.isLetter(this.input.charAt(2)))
            {
                ParserStringUtils.lengthCheck(this.input, 3);

                String matrix1 = ParserStringUtils.matrixVariableFromString(this.input, 0);
                String matrix2 = ParserStringUtils.matrixVariableFromString(this.input, 2);

                MatrixMultiplicationQuery returnQuery = new MatrixMultiplicationQuery(matrix1, matrix2);
                return returnQuery;
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }


    /**
     *	Determines if the calculator should do the matrix addition operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseAdditionSymbol() throws NothingInQueueException, NoMatrixInQueueException
    {
        if (this.input.startsWith("+") && Character.isLetter(this.input.charAt(1)) && this.input.length() == 2)
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();

            String matrix2 = ParserStringUtils.matrixVariableFromString(this.input, 1);

            MatrixAdditionQuery returnQuery = new MatrixAdditionQuery(matrix2);
            return returnQuery;

        }


        else if (Character.isLetter(this.input.charAt(0)) && this.input.charAt(1) == '+' && Character.isLetter(this.input.charAt(2)))
        {
            ParserStringUtils.lengthCheck(this.input, 3);

            String matrix1 = ParserStringUtils.matrixVariableFromString(this.input, 0);
            String matrix2 = ParserStringUtils.matrixVariableFromString(this.input, 2);

            MatrixAdditionQuery returnQuery = new MatrixAdditionQuery(matrix1, matrix2);
            return returnQuery;
        }

        else
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }


    /**
     *	Determines if the calculator should do the matrix subtraction operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseSubtractionSymbol() throws NothingInQueueException, NoMatrixInQueueException
    {
        if (this.input.startsWith("-") && Character.isLetter(this.input.charAt(1)) && this.input.length() == 2)
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();

            String matrix2 = ParserStringUtils.matrixVariableFromString(this.input, 1);

           MatrixSubstractionQuery returnQuery = new MatrixSubstractionQuery(matrix2);
           return returnQuery;
        }


        else if (Character.isLetter(this.input.charAt(0)) && this.input.charAt(1) == '-' && Character.isLetter(this.input.charAt(2)))
        {
            ParserStringUtils.lengthCheck(this.input, 3);

            String matrix1 = ParserStringUtils.matrixVariableFromString(this.input, 0);
            String matrix2 = ParserStringUtils.matrixVariableFromString(this.input, 2);

            MatrixSubstractionQuery returnQuery = new MatrixSubstractionQuery(matrix1, matrix2);
            return returnQuery;
        }

        else
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }


    /**
     *	Determines if the calculator should do the transpose or matrix to power n operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseCarrotSymbol() throws NothingInQueueException, NoMatrixInQueueException
    {
        if (this.input.startsWith("^") && (this.input.charAt(1) == 't' || this.input.charAt(1) == '('))
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();

            if (this.input.charAt(1) == 't')
            {
                ParserStringUtils.lengthCheck(this.input, 2);

                TransposeQuery returnQuery = new TransposeQuery();
                return returnQuery;
            }

            else if (this.input.charAt(1) == '(' && (Character.isDigit(this.input.charAt(2)) || this.input.charAt(2) == '-'))
            {
                Integer power = ParserStringUtils.intFromString(this.input, 2);

                int powerLength = power.toString().length();

                ParserStringUtils.lengthCheck(this.input, powerLength + 3);

                MatrixToExponentQuery returnQuery = new MatrixToExponentQuery(power);
                return returnQuery;
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }

        else if (Character.isLetter(this.input.charAt(0)) && this.input.charAt(1) == '^' && (this.input.charAt(2) == 't' || this.input.charAt(2) == '('))
        {
            if (this.input.charAt(2) == 't')
            {
                ParserStringUtils.lengthCheck(this.input, 3);

                String matrix = ParserStringUtils.matrixVariableFromString(this.input, 0);

                TransposeQuery returnQuery = new TransposeQuery(matrix);
                return returnQuery;
            }

            else if (this.input.charAt(2) == '(' && (Character.isDigit(this.input.charAt(3)) || this.input.charAt(3) == '-'))
            {
                Integer power = ParserStringUtils.intFromString(this.input, 3);

                int powerLength = power.toString().length();

                ParserStringUtils.lengthCheck(this.input, powerLength + 4);

                String matrix = ParserStringUtils.matrixVariableFromString(this.input, 0);

                MatrixToExponentQuery returnQuery = new MatrixToExponentQuery(matrix, power);
                return returnQuery;
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


    /**
     *	Determines if the calculator should do the minor operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseMNR() throws NothingInQueueException, NoMatrixInQueueException
    {
        int nextSpot = 4;

        if (Character.isDigit(this.input.charAt(nextSpot)))
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();

            Integer row = ParserStringUtils.intFromString(this.input, nextSpot);

            int firstIntLength = row.toString().length();

            nextSpot = nextSpot + firstIntLength;

            if (this.input.charAt(nextSpot) == ',' && Character.isDigit(this.input.charAt(nextSpot + 1)))
            {
                Integer column = ParserStringUtils.intFromString(this.input, nextSpot + 1);

                int secondIntLength = column.toString().length();

                nextSpot = nextSpot + 1 + secondIntLength;

                if (this.input.charAt(nextSpot) != ')')
                {
                    throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                }

                ParserStringUtils.lengthCheck(this.input, 6 + firstIntLength + secondIntLength);

                MinorQuery returnQuery = new MinorQuery(row, column);
                return returnQuery;
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }

        else if (Character.isLetter(this.input.charAt(nextSpot)) && this.input.charAt(nextSpot + 1) == ',')
        {
           String matrix = ParserStringUtils.matrixVariableFromString(this.input, nextSpot);

            nextSpot = nextSpot + 2;

            if (Character.isDigit(this.input.charAt(nextSpot)))
            {
                Integer row = ParserStringUtils.intFromString(this.input, nextSpot);

                int firstIntLength = row.toString().length();

                nextSpot = nextSpot + firstIntLength;

                if (this.input.charAt(nextSpot) == ',' && Character.isDigit(this.input.charAt(nextSpot + 1)))
                {
                    Integer column = ParserStringUtils.intFromString(this.input, nextSpot + 1);

                    int secondIntLength = column.toString().length();

                    nextSpot = nextSpot + 1 + secondIntLength;

                    if (this.input.charAt(nextSpot) != ')')
                    {
                        throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                    }

                    ParserStringUtils.lengthCheck(this.input, 8 + firstIntLength + secondIntLength);

                    MinorQuery returnQuery = new MinorQuery(matrix, row, column);
                    return returnQuery;
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


    /**
     *	Determines if the calculator should do the cofactor matrix operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseCOFM() throws NothingInQueueException, NoMatrixInQueueException
    {
        int nextSpot = 5;

        if (this.input.equals("cofm()"))
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();

            CofactorMatrixQuery returnQuery = new CofactorMatrixQuery();
            return returnQuery;
        }

        else if (Character.isLetter(this.input.charAt(nextSpot)) && this.input.charAt(nextSpot + 1) == ')')
        {
            ParserStringUtils.lengthCheck(this.input, 7);

            String matrix = ParserStringUtils.matrixVariableFromString(this.input, nextSpot);

            CofactorMatrixQuery returnQuery = new CofactorMatrixQuery(matrix);
            return returnQuery;
        }

        else
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }


    /**
     *	Determines if the calculator should do the adjoint matrix operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseADJM() throws NothingInQueueException, NoMatrixInQueueException
    {
        int nextSpot = 5;

        if (this.input.equals("adjm()"))
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();


            AdjointMatrixQuery returnQuery = new AdjointMatrixQuery();
            return returnQuery;
        }

        else if (Character.isLetter(this.input.charAt(nextSpot)) && this.input.charAt(nextSpot + 1) == ')')
        {
            ParserStringUtils.lengthCheck(this.input, 7);

            String matrix = ParserStringUtils.matrixVariableFromString(this.input, nextSpot);

            AdjointMatrixQuery returnQuery = new AdjointMatrixQuery(matrix);
            return returnQuery;
        }

        else
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }


    /**
     *	Determines if the calculator should do the trace operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseTR() throws NothingInQueueException, NoMatrixInQueueException
    {
        int nextSpot = 3;

        if (this.input.equals("tr()"))
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();

            TraceQuery returnQuery = new TraceQuery();
            return returnQuery;
        }

        else if (Character.isLetter(this.input.charAt(nextSpot)) && this.input.charAt(nextSpot + 1) == ')')
        {
            ParserStringUtils.lengthCheck(this.input, 5);

            String matrix = ParserStringUtils.matrixVariableFromString(this.input, nextSpot);

            TraceQuery returnQuery = new TraceQuery(matrix);
            return returnQuery;
        }

        else
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }


    /**
     *	Determines if the calculator should do the determinant operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseDET() throws NothingInQueueException, NoMatrixInQueueException
    {
        int nextSpot = 4;

        if (this.input.equals("det()"))
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();

            DeterminantQuery returnQuery = new DeterminantQuery();
            return returnQuery;
        }

        else if (Character.isLetter(this.input.charAt(nextSpot)) && this.input.charAt(nextSpot + 1) == ')')
        {
            ParserStringUtils.lengthCheck(this.input, 6);

            String matrix = ParserStringUtils.matrixVariableFromString(this.input, nextSpot);

            DeterminantQuery returnQuery = new DeterminantQuery(matrix);
            return returnQuery;
        }

        else
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }


    /**
     *	Determines if the calculator should do the cofactor operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseCOF() throws NothingInQueueException, NoMatrixInQueueException
    {
        int nextSpot = 4;

        if (Character.isDigit(this.input.charAt(nextSpot)))
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();

            Integer row = ParserStringUtils.intFromString(this.input, nextSpot);

            int firstIntLength = row.toString().length();

            nextSpot = nextSpot + firstIntLength;

            if (this.input.charAt(nextSpot) == ',' && Character.isDigit(this.input.charAt(nextSpot + 1)))
            {
                Integer column = ParserStringUtils.intFromString(this.input, nextSpot + 1);

                int secondIntLength = column.toString().length();

                nextSpot = nextSpot + 1 + secondIntLength;

                if (this.input.charAt(nextSpot) != ')')
                {
                    throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                }

                ParserStringUtils.lengthCheck(this.input, 6 + firstIntLength + secondIntLength);

               CofactorQuery returnQuery = new CofactorQuery(row, column);
               return returnQuery;
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }

        else if (Character.isLetter(this.input.charAt(nextSpot)) && this.input.charAt(nextSpot + 1) == ',')
        {
            String matrix = ParserStringUtils.matrixVariableFromString(this.input, nextSpot);

            nextSpot = nextSpot + 2;

            if (Character.isDigit(this.input.charAt(nextSpot)))
            {
                Integer row = ParserStringUtils.intFromString(this.input, nextSpot);

                int firstIntLength = row.toString().length();

                nextSpot = nextSpot + firstIntLength;

                if (this.input.charAt(nextSpot) == ',' && Character.isDigit(this.input.charAt(nextSpot + 1)))
                {
                    Integer column = ParserStringUtils.intFromString(this.input, nextSpot + 1);

                    int secondIntLength = column.toString().length();

                    nextSpot = nextSpot + 1 + secondIntLength;

                    if (this.input.charAt(nextSpot) != ')')
                    {
                        throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
                    }

                    ParserStringUtils.lengthCheck(this.input, 8 + firstIntLength + secondIntLength);

                    CofactorQuery returnQuery = new CofactorQuery(matrix, row, column);
                    return returnQuery;
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


    /**
     *	Determines if the calculator should do the identity matrix operation
     *  @return CalculatorQuery Query of operation user looking to perform
     */
    private CalculatorQuery parseIDM()
    {
        int nextSpot = 4;

        if (Character.isDigit(this.input.charAt(nextSpot)))
        {
            Integer matrixSize = ParserStringUtils.intFromString(this.input, nextSpot);
            int intSize = matrixSize.toString().length();

            nextSpot = nextSpot + intSize;

            if (this.input.charAt(nextSpot) == ')')
            {
                ParserStringUtils.lengthCheck(this.input, 5 + intSize);

                IdentityMatrixQuery returnQuery = new IdentityMatrixQuery(matrixSize);
                return returnQuery;
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


    /**
     *	Determines if the calculator should do the storages operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseSTRG() throws NothingInQueueException, NoMatrixInQueueException
    {
        if (this.input.equals("strg")) //outputs all matrices
        {
            PrintStorageQuery returnQuery = new PrintStorageQuery();
            return returnQuery;
        }

        else if (this.input.equals("strg()")) //prints the last matrix outputted, if last operation had this matrix
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();

            PrintMatrixQuery returnQuery = new PrintMatrixQuery();
            return returnQuery;
        }

        else if (this.input.charAt(4) == '(' && Character.isLetter(this.input.charAt(5)) && this.input.charAt(6) == ')') //outputs a specific matrix
        {
            ParserStringUtils.lengthCheck(this.input, 7);

            String matrix = ParserStringUtils.matrixVariableFromString(this.input, 5);

            PrintMatrixQuery returnQuery = new PrintMatrixQuery(matrix);
            return returnQuery;
        }

        else
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }


    /**
     *	Determines if the calculator should do the variable designation operation
     *  @return CalculatorQuery Query of operation user looking to perform
     *	@throws NothingInQueueException Thrown if calculator at start state and attempting to use last output
     *	@throws NoMatrixInQueueException Thrown if last output was not of type MATRIX and attempting to use last output
     */
    private CalculatorQuery parseEqualSign() throws NothingInQueueException, NoMatrixInQueueException
    {
        if (this.input.startsWith("=") && Character.isLetter(this.input.charAt(1)))		//=M
        {
            LastOutputType.Singleton.nullStateCheck();
            LastOutputType.Singleton.matrixTypeCheck();
            ParserStringUtils.lengthCheck(this.input, 2);

            String matrixName = "" + this.input.charAt(1);

            StorePreviousOutputQuery returnQuery = new StorePreviousOutputQuery(matrixName);
            return returnQuery;
        }

        else if (Character.isLetter(this.input.charAt(0)) && this.input.charAt(1) == '=' && Character.isLetter(this.input.charAt(2)) && this.input.length() == 3)     //M=M
        {
            String newMatrix = "" + this.input.charAt(0);
            String originalMatrix = "" + this.input.charAt(2);

            RecastLastVariableQuery returnQuery = new RecastLastVariableQuery(originalMatrix, newMatrix);
            return returnQuery;
        }

        else if (Character.isLetter(this.input.charAt(0)) && this.input.charAt(1) == '='&& this.input.charAt(2) == '[' && this.input.endsWith("]")) //M=[R,R,R,R/R,R,R,R]
        {
            String matrixName = "" + this.input.charAt(0);

            String matrixString = this.input.substring(2);

            StoreNewMatrixQuery returnQuery = new StoreNewMatrixQuery(matrixString, matrixName);
            return returnQuery;
        }

        else if (Character.isLetter(this.input.charAt(0)) && this.input.charAt(1) == '=' && !Character.isDigit(this.input.charAt(2))) ///M=O
        {
            String matrixName = "" + this.input.charAt(0);
            String operation = this.input.substring(2);

            StoreCurrentOutputQuery returnQuery = new StoreCurrentOutputQuery(matrixName, operation);
            return returnQuery;
        }

        else
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }


    /**
     *	Determines if the calculator should do the exit operation, and in the process writes all Matrices stored in calculator storages to external file "calculator_storage.txt" for subsequent runnings of program
     *  @return CalculatorQuery Query of operation user looking to perform
     */
    public CalculatorQuery parseEXIT()
    {
        ExitQuery returnQuery = new ExitQuery();
        return returnQuery;
    }


    /**
     *	Determines if the calculator should do the format menu operation
     *  @return CalculatorQuery Query of operation user looking to perform
     */
    private CalculatorQuery parseFRMT()
    {
        FormatMenuQuery returnQuery = new FormatMenuQuery();
        return returnQuery;
    }


    /**
     *	Parses the user input to determine the proper operation to perform
     *  @return CalculatorQuery Query of operation user looking to perform
     */
    public CalculatorQuery parse() throws NothingInQueueException, NoMatrixInQueueException, IncorrectSyntaxException
    {
        CalculatorQuery returnQuery = null;

        if (this.input.contains("="))
        {
            returnQuery = parseEqualSign();
            return returnQuery;
        }

        else if (this.input.contains("^"))
        {
            returnQuery = parseCarrotSymbol();
            return returnQuery;
        }

        else if (this.input.contains("*"))
        {
            returnQuery = parseMultiplicationSymbol();
            return returnQuery;
        }

        else if (this.input.startsWith("rref("))
        {
            returnQuery = parseRREF();
            return returnQuery;
        }

        else if (this.input.startsWith("ref("))
        {
            returnQuery = parseREF();
            return returnQuery;
        }

        else if (this.input.startsWith("inv("))
        {
            returnQuery = parseINV();
            return returnQuery;
        }

        else if (this.input.startsWith("mnr("))
        {
            returnQuery = parseMNR();
            return returnQuery;
        }

        else if (this.input.startsWith("cofm("))
        {
            returnQuery = parseCOFM();
            return returnQuery;
        }

        else if (this.input.startsWith("adjm("))
        {
            returnQuery = parseADJM();
            return returnQuery;
        }

        else if (this.input.startsWith("tr("))
        {
            returnQuery = parseTR();
            return returnQuery;
        }

        else if (this.input.startsWith("det("))
        {
            returnQuery = parseDET();
            return returnQuery;
        }

        else if (this.input.startsWith("cof("))
        {
            returnQuery = parseCOF();
            return returnQuery;
        }

        else if (this.input.contains("+"))
        {
            returnQuery = parseAdditionSymbol();
            return returnQuery;
        }

        else if (this.input.contains("-"))
        {
            returnQuery = parseSubtractionSymbol();
            return returnQuery;
        }

        else if (this.input.startsWith("idm("))
        {
            returnQuery = parseIDM();
            return returnQuery;
        }

        else if (this.input.startsWith("strg"))
        {
            returnQuery = parseSTRG();
            return returnQuery;
        }

        else if (this.input.equals("exit"))
        {
            returnQuery = parseEXIT();
            return returnQuery;
        }

        else if (this.input.equals("frmt"))
        {
            returnQuery = parseFRMT();
            return returnQuery;
        }

        else
        {
            throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
        }
    }
}