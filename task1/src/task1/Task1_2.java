package task1;

import java.util.Random;

public class Task1_2
{
    private static void mix(String[] a)
    {
        Random rnd = new Random();
        for (int i = 1; i < a.length; i++)
        {
            int j = rnd.nextInt(i);
            String temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
    }

    public static void main(String[] args)
    {
        Task1_1.print(args);
        mix(args);
        Task1_1.print(args);
    }
}