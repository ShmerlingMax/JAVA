package task1;

public class Task1_1
{
    static private void shellSort(String[] array)
    {
        for (int step = array.length / 2; step > 0; step /= 2)
        {
            for (int i = step; i < array.length; i++)
            {
                String tmp = array[i];
                int j;
                for (j = i; j >= step; j -= step)
                {
                    if (Integer.parseInt(tmp) < Integer.parseInt(array[j - step]))
                    {
                        array[j] = array[j - step];
                    } else
                    {
                        break;
                    }
                }
                array[j] = tmp;
            }
        }
    }

    static void print(String[] array)
    {
        for (String s : array)
        {
            System.out.print(s + ' ');
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        print(args);
        shellSort(args);
        print(args);
    }
}
