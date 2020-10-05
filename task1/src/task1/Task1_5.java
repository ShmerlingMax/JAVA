package task1;

public class Task1_5
{
    public static void main(String[] args)
    {
        System.out.println(args[0] + ' ' + args[1]);
        int rows = Integer.parseInt(args[0]);
        int columns = Integer.parseInt(args[1]);
        int[][] matrix = new int[rows][columns];
        int tmp = 2;
        int min = Integer.MAX_VALUE;
        int row = 0;
        for (int i = 0; i < rows; i++)
        {
            int maxElement = Integer.MIN_VALUE;
            for (int j = 0; j < columns; j++)
            {
                matrix[i][j] = Integer.parseInt(args[tmp]);
                tmp++;
                if (matrix[i][j] > maxElement)
                {
                    maxElement = matrix[i][j];
                }
            }
            if (maxElement < min)
            {
                min = maxElement;
                row = i;
            }
        }
        Task1_4.print(matrix, rows, columns);
        int[][] answer = new int[rows - 1][columns];
        for (int i = 0; i < rows - 1; i++)
        {
            for (int j = 0; j < columns; j++)
            {
                if (i >= row)
                {
                    answer[i][j] = matrix[i + 1][j];
                } else
                {
                    answer[i][j] = matrix[i][j];
                }
            }
        }
        Task1_4.print(answer, rows - 1, columns);
    }
}
