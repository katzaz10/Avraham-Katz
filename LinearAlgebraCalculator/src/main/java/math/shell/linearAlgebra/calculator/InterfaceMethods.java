package math.shell.linearAlgebra.calculator;

import java.util.Scanner;
import java.math.BigDecimal;

/**
 *	Methods that are strictly for the calculator interface
 *	@author Avraham Katz
 *	@version 1.0
 */

public class InterfaceMethods
{


    public static String bump = "   ";

    /**
     *	Outprints an intro statement to the calculator
     */
    public static void introStatement()
    {
        System.out.printf("%n%n******************************************************************************************************************************** %n");
        System.out.println("                                  -------------------------");
        System.out.println("                      		      LINEAR ALGEBRA CALCULATOR");
        System.out.println("                                  -------------------------");
        System.out.println();
        System.out.println("	      <Variables can only be written as single letters: 'a','b',...,'z'>");
        System.out.println("         <If input uppercase variable, will be converted to lowercase: 'A' --> 'a'>");
        System.out.println("   <To view different operation options and formats at any time, type 'frmt' and press enter>");
        System.out.println("                 <To exit program at any time, type 'exit' and press enter>");
        System.out.println();
    }


    /**
     *	Outprints a menu of different operation formats one can view
     */
    private static void operationFormatMenu()
    {
        System.out.printf("%n******************************************************************************************************************************** %n");
        System.out.println(bump + "enter number of operation to see format of operation, else press 0 to return to calculator...");
        System.out.println(bump +"<M = matrix variable, N = integer, R = rational (can be written as R/R) #, i = row of type N, j = column of type N, O = matrix operation>");
        System.out.println();
        System.out.println(bump + "1.  Matrix addition");
        System.out.println(bump + "2.  Matrix subtraction");
        System.out.println(bump + "3.  Matrix multiplication");
        System.out.println(bump + "4.  Matrix to power n");
        System.out.println(bump + "5.  Scalar multiplication");
        System.out.println(bump + "6.  Row echelon form");
        System.out.println(bump + "7.  Reduced row echelon form");
        System.out.println(bump + "8.  Inverse matrix");
        System.out.println(bump + "9.  Transpose matrix");
        System.out.println(bump + "10. Minor of spot i,j");
        System.out.println(bump + "11. Cofactor matrix");
        System.out.println(bump + "12. Adjoint matrix");
        System.out.println(bump + "13. Trace");
        System.out.println(bump + "14. Determinant");
        System.out.println(bump + "15. Cofactor of spot i,j");
        System.out.println(bump + "16. Create identity matrix of size n");
        System.out.println(bump + "17. View storage");
        System.out.println(bump + "18. Store new matrix in variable");
        System.out.println(bump + "19. Exit program");
        System.out.printf("******************************************************************************************************************************** %n%n");
    }


    /**
     *	Switch statement that outprints formats of different operations
     *	@param format The number of the operation one wants to look at the format to
     */
    private static void specificFormats(int format)
    {
        System.out.printf("%n******************************************************************************************************************************** %n");
        switch (format) {
            case 1:
                System.out.println(bump + "Matrix addition:");
                System.out.println(bump + bump + "1. 'M + M' 				(if inputting 2 matrice variables)");
                System.out.println(bump + bump + "2. '+ M' 					(if last output is a matrix, and want to use as first M)");
                break;
            case 2:
                System.out.println(bump + "Matrix subtraction:");
                System.out.println(bump + bump + "1. 'M - M' 				(if inputting 2 matrice variables)");
                System.out.println(bump + bump + "2. '- M' 					(if last output is a matrix, and want to use as first M)");
                break;
            case 3:
                System.out.println(bump + "Matrix multiplication:");
                System.out.println(bump + bump + "1. 'M * M' 				(if inputting 2 matrice variables)");
                System.out.println(bump + bump + "2. '* M' 					(if last output is a matrix, and want to use as first M)");
                break;
            case 4:
                System.out.println(bump + "Matrix to power n:");
                System.out.println(bump + bump + "1. 'M^(N)' 				(if inputting matrix variable)");
                System.out.println(bump + bump + "2. '^(N)' 				(if last output is a matrix, and want to use as M for operation)");
                break;
            case 5:
                System.out.println(bump + "Scalar multiplication:");
                System.out.println(bump + bump + "1. '(R) * M' 				(if inputting 2 matrice variables)");
                System.out.println(bump + bump + "2. '* M' 					(if last output = rational, and want to use as R)");
                break;
            case 6:
                System.out.println(bump + "Row echelon form:");
                System.out.println(bump + bump + "1. 'ref(M)' 				(if inputting matrix variable)");
                System.out.println(bump + bump + "2. 'ref()' 				(if last output is a matrix, and want to use as M)");
                break;
            case 7:
                System.out.println(bump + "Reduced row echelon form:");
                System.out.println(bump + bump + "1. 'rref(M)' 				(if inputting matrix variable)");
                System.out.println(bump + bump + "2. 'rref()' 				(if last output is a matrix, and want to use as M)");
                break;
            case 8:
                System.out.println(bump + "Inverse matrix:");
                System.out.println(bump + bump + "1. 'inv(M)' 				(if inputting matrix variable)");
                System.out.println(bump + bump + "2. 'inv()' 				(if last output is a matrix, and want to use as M)");
                break;
            case 9:
                System.out.println(bump + "Transpose matrix:");
                System.out.println(bump + bump + "1. 'M^t' 					(if inputting matrix variable)");
                System.out.println(bump + bump + "2. '^t' 					(if last output is a matrix, and want to use as M)");
                break;
            case 10:
                System.out.println(bump + "Minor of position i,j:");
                System.out.println(bump + bump + "1. 'min(M, i, j)' 			(if inputting matrix variable)");
                System.out.println(bump + bump + "2. 'min(i,j)' 				(if last output is a matrix, and want to use as M)");
                break;
            case 11:
                System.out.println(bump + "Cofactor matrix:");
                System.out.println(bump + bump + "1. 'cofm(M)' 				(if inputting matrix variable)");
                System.out.println(bump + bump + "2. 'cofm()' 				(if last output is a matrix, and want to use as M)");
                break;
            case 12:
                System.out.println(bump + "Adjoint matrix:");
                System.out.println(bump + bump + "1. 'adjm(M)' 				(if inputting matrix variable)");
                System.out.println(bump + bump + "2. 'adjm()' 				(if last output is a matrix, and want to use as M)");
                break;
            case 13:
                System.out.println(bump + "Trace:");
                System.out.println(bump + bump + "1. 'tr(M)' 				(if inputting matrix variable)");
                System.out.println(bump + bump + "2. 'tr()' 				(if last output is a matrix, and want to use as M)");
                break;
            case 14:
                System.out.println(bump + "Determinant:");
                System.out.println(bump + bump + "1. 'det(M)' 				(if inputting matrix variable variable)");
                System.out.println(bump + bump + "2. 'det()' 				(if last output is a matrix, and want to use as M)");
                break;
            case 15:
                System.out.println(bump + "Cofactor of position i,j:");
                System.out.println(bump + bump + "1. 'cof(M, i, j)' 			(if inputting matrix variable variable)");
                System.out.println(bump + bump + "2. 'cof(i,j)' 				(if last output is a matrix, and want to use as M)");
                break;
            case 16:
                System.out.println(bump + "Create identity matrix of size n:");
                System.out.println(bump + bump + "1. 'idm(N)'");
                break;
            case 17:
                System.out.println(bump + "View storage:");
                System.out.println(bump + bump + "1. 'strg(M)' 				(if printing out one specific matrix)");
                System.out.println(bump + bump + "2. 'strg()' 				(if last output is a matrix, and want to use as M)");
                System.out.println(bump + bump + "2. 'strg' 				(if printing all matrices in calculator storage)");
                break;
            case 18:
                System.out.println(bump + "Store new matrix in storage:");
                System.out.println(bump + bump + "1. 'M = M' 				(if copying matrix from second variable and storing in first variable)");
                System.out.println(bump + bump + "2. '= M' 					(if storing last output of calculator)");
                System.out.println(bump + bump + "3. 'M = O' 				(if storing output of operation O)");
                System.out.println(bump + bump + "4. 'M = [R, R, R | R, R, R| R, R,frmt R|]' 		(if creating new matrix with values of type R)");
                break;
            case 19:
                System.out.println(bump + "Exit program:");
                System.out.println(bump + bump + "1. 'exit' 				(type in command 'exit' and enter)");
                break;
        }
        System.out.printf("********************************************************************************************************************************");
    }



    /**
     *  Finds the operation user wants to see format of, outprints format, and continues to loop until user enters '0' or "exit"
     */
    public static void findFormat()
    {
        Scanner sc = new Scanner(System.in);

        operationFormatMenu();

        while (true)
        {
            System.out.print(">");

            if (!sc.hasNextInt())
            {
                String nextMove = StringUtils.editEquation(sc.next());

                if (nextMove.equals("exit"))
                {
                    CalculatorParser.fallThroughCheck = CalculatorParser.HitOrMiss.MISS;
                    CalculatorParser.operationEXIT("exit");
                }

                else
                {
                    System.out.printf("%n****************************************************************************************************************************************************************%n");
                    System.out.printf("<ERROR: cannot perform operation...input '%s' has incorrect syntax>", nextMove);
                    System.out.printf("%n********************************************************************************************************************************%n%n");
                }
            }

            else if (sc.hasNextInt())
            {
                int nextMove = sc.nextInt();

                if (nextMove == 0)
                {
                    System.out.println();
                    System.out.println("returning to calculator...");
                    System.out.println();
                    break;
                }

                else if (nextMove != 0)
                {
                    specificFormats(nextMove);
                    System.out.println();
                    System.out.println(bump + "enter number of operation to see format of operation, else press 0 to return to calculator...");
                    System.out.println();
                }
            }

            else
            {
                throw new IncorrectSyntaxException(ErrorMessages.badSyntax);
            }
        }
    }



}