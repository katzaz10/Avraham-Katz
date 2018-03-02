package math.shell.linearAlgebra.calculator;

import math.shell.linearAlgebra.calculator.utilities.StringUtils;
import math.shell.linearAlgebra.calculator.calculatorParser.CalculatorParser;
import math.shell.linearAlgebra.calculator.exceptions.IncorrectSyntaxException;
import math.shell.linearAlgebra.calculator.exceptions.NoMatrixInQueueException;
import math.shell.linearAlgebra.calculator.exceptions.NothingInQueueException;
import math.shell.linearAlgebra.calculator.storages.ExternalStorage;
import math.shell.linearAlgebra.calculator.userInterface.InterfaceMethods;
import math.shell.linearAlgebra.calculator.userInterface.Print;

import java.util.Scanner;

/**
 *	A linear algebra calculator that does singular matrix operations that can be chained in subsequent inputs
 *	@author Avraham Katz
 *	@version 1.1
 */

public class Driver
{

    /**
     * Takes the user input, finds the correct operation to run and runs it
     * @param equation Input parsing and running
     */
    private static void parseAndRunOperation(String equation)
    {
        try
        {
            CalculatorParser cpq = new CalculatorParser(equation);
            cpq.parse().runOperation();
        }


        catch (NothingInQueueException nullState)
        {
            System.out.println("<ERROR: trying to use last output for current operation, but no matrices or doubles in queue (either no operations performed yet, no operations with equation performed yet, or previous operation illegal/bad operation)");
            System.out.println();
        }

        catch (NoMatrixInQueueException nonMatrix)
        {
            System.out.printf("<ERROR: trying to use last output for current operation, but operation requires matrix and last output not a matrix> %n%n");
        }


        catch (IncorrectSyntaxException badSyntax)
        {
            System.out.printf("<ERROR: input '%s' has incorrect syntax> %n%n", equation);
        }


        catch (IllegalArgumentException all)
        {
            //catch and leave empty to make sure calculator does not exit, but prints the println in the method that throws the IllegalArgumentException, and continues running
        }


        catch (RuntimeException all)
        {
            System.out.printf("<ERROR: cannot complete operation...try new operation> %n%n");
        }

    }



    private static void run()
    {
        InterfaceMethods.introStatement();

        ExternalStorage.readExternalStorage();

        Scanner sc = new Scanner(System.in);

        while (true)
        {
            System.out.print(">");

            String input = sc.nextLine();
            Print.printEquation(input);

            String equation = StringUtils.editEquation(input);

            parseAndRunOperation(equation);
        }
    }


    /**
     * 	Access point to calculator
     */
    public static void calculator()
    {
        run();
    }



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void main(String[] args)
    {
        calculator();
    }
}

