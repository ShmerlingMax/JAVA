package task5;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class task5_1 {
    public static List<String> rightShift(List<String> list, int shift)
    {
        List<String> tmp = new ArrayList<>(List.copyOf(list));
        for (int i = shift; i < list.size(); i++)
        {
            tmp.set(i, list.get(i - shift));
        }
        int j = 0;
        for (int i = list.size() - shift; i < list.size(); i++)
        {
            tmp.set(j,list.get(i));
            j++;
        }
        return tmp;
    }
    public static List<String> leftShift(List<String> list, int shift)
    {
        List<String> tmp = new ArrayList<>(List.copyOf(list));
        for (int i = list.size() - 1; i  + shift != -1; i--)
        {
            tmp.set(i + shift, list.get(i));
        }
        int j = 0;
        for (int i = list.size() + shift; i < list.size(); i++)
        {
            tmp.set(i, list.get(j));
            j++;
        }
        return tmp;
    }
    public static void main(String[] args)
    {
        List<String> list = new ArrayList<>();
        System.out.print("Введите количество элементов в списке: ");
        int N;
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt())
        {
            N = in.nextInt();
            if (N < 1)
            {
                System.out.println("Количество элементов должно быть задано натуральным числом!");
                return;
            }
        }
        else
        {
            System.out.println("Количество элементов должно быть задано натуральным числом!");
            return;
        }
        System.out.println("Введите элементы(каждый на своей строке): ");
        in.nextLine();
        while (list.size() != N)
        {
            list.add(in.nextLine());
        }
        System.out.print("Введите сдвиг: ");
        if (in.hasNextInt())
        {
            int shift = in.nextInt();
            shift = shift % N;
            if (shift != 0)
            {
                list = (shift > 0) ? rightShift(list, shift) : leftShift(list, shift);
            }
        }
        else
        {
            System.out.println("Сдвиг должен быть задан целым числом!");
            return;
        }

        for (String str : list)
        {
            System.out.print(str + ' ');
        }
    }
}
