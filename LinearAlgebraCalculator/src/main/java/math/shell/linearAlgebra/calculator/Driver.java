package math.shell.linearAlgebra.calculator;

import java.util.Scanner;

/**
 *	A linear algebra calculator that does singular matrix operations that can be chained in subsequent inputs
 *	@author Avraham Katz
 *	@version 1.0
 */

public class Driver
{


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
            CalculatorParser.parseEquation(equation);
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

