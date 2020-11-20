package task5;

import java.util.Collections;
import java.util.Scanner;
import java.util.Stack;

public class task5_2
{
    public static void main (String[] args)
    {
        Stack<Integer> stack = new Stack<>();
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt())
        {
            int number = in.nextInt();
            while (number != 0)
            {
                stack.push(number % 10);
                number /= 10;
            }
            Collections.reverse(stack);
            while (!stack.empty())
            {
                System.out.print(stack.pop());
            }
            System.out.println();
        }
    }
}
