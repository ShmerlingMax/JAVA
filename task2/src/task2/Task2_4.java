package task2;

import java.util.Scanner;

public class Task2_4
{
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        while (in.hasNext())
        {
            String str = in.nextLine();
            str = str.trim();
            str = str.replaceAll("\\s+", " ");
            System.out.println(str);
        }
        in.close();
    }
}
