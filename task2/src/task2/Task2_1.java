package task2;

import java.util.Scanner;

public class Task2_1
{
    static private String getEndTh(int th, int d1)
    {
        boolean temp1 = ((d1 >= 2) && (d1 <= 4));
        boolean temp2 = ((d1 > 4) || (d1 == 0));
        if (((th > 2) && temp1) || ((th == 2) && (d1 == 1))) return "а";
        if ((th > 2) && temp2) return "ов";
        if ((th == 2) && temp1) return "и";
        else return "";
    }
    static private String getEndDek1(int d1, boolean isMale)
    {
        if ((d1 > 2) || (d1 == 0)) return "";
        if (d1 == 1)
        {
            return isMale? "ин" : "на";
        }
        else
        {
            return isMale? "а" : "е";
        }
    }
    static private String convert(int number)
    {
        String sing = "";
        if (number < 0)
        {
            sing = "минус";
            number *= -1;
        }
        if (number == 0) return "Ноль";
        String[] dek1 = {"", " од", " дв", " три", " четыре", " пять", " шесть", " семь", " восемь", " девять", " десять", " одиннадцать", " двенадцать", " тринадцать", " четырнадцать", " пятнадцать", " шестнадцать", " семнадцать", " восемнадцать", " девятнадцать"};
        String[] dek2 = {"", "", " двадцать", " тридцать", " сорок", " пятьдесят", " шестьдесят", " семьдесят", " восемьдесят", " девяносто" };
        String[] dek3 = {"", " сто", " двести", " триста", " четыреста", " пятьсот", " шестьсот", " семьсот", " восемьсот", " девятьсот"};
        String[] th = {"", "", " тысяч", " миллион", " миллиард", " триллион", " квадрилион", " квинтилион"};
        String str = "";
        for (int i = 1; number > 0; i++)
        {
            int gr = number % 1000;
            number = (number - gr) / 1000;
            if (gr > 0)
            {
                int d3 = (gr - (gr % 100)) / 100;
                int d1= gr % 10;
                int d2 = (gr - d3 * 100 - d1) / 10;
                if (d2 == 1) d1 += 10;
                boolean isMale = (i > 2) || (i == 1);
                str = dek3[d3] + dek2[d2] + dek1[d1] + getEndDek1(d1, isMale) + th[i] + getEndTh(i, d1) + str;
            }
        }
        str = sing + str;
        return str;
    }

    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        if (in.hasNextInt())
        {
            int number = in.nextInt();
            String str = convert(number);
            System.out.println(str);
        }
        else
        {
            System.out.println("It is not a number!");
        }
        in.close();
    }
}
