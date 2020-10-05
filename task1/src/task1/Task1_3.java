package task1;

public class Task1_3
{
    public static void main(String[] args)
    {
        Task1_1.print(args);
        int counter = 0;
        for (int i = 0; i < args.length; i++)
        {
            int tmp = 0;
            for (int j = 0; j < args.length; j++)
            {
                if (args[i].equals(args[j]))
                {
                    tmp++;
                }
            }
            if (tmp == 1)
            {
                counter++;
            }
        }
        System.out.print(counter);
    }
}
