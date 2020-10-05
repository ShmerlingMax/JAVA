package task1;

public class Task1_4
{
    static void print(int[][] matrix, int rows, int columns)
    {
        for (int i = 0; i < rows; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                System.out.print(matrix[i][j]);
                System.out.print(' ');
            }
            System.out.println();
        }
        System.out.println();
    }
    public static void main(String[] args)
    {
        Task1_1.print(args);
        int rows = Integer.parseInt(args[0]);
        int columns = Integer.parseInt(args[1]);
        int[][] matrix = new int[rows][columns];
        for (int i = 0; i < rows; i++)
        {
            int counter = i + 1;
            while (counter != 0)
            {
                for (int j = 0; j < columns; j++)
                {
                    matrix[i][j]++;
                    counter--;
                    if (counter == 0)
                    {
                        break;
                    }
                }
            }
        }
        print(matrix,rows,columns);
    }
}
