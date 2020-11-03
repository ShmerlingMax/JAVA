package task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

//В файле text.txt идут логины и пароли - по паре на каждую строку. Место топа могут поделить несколько паролей, если их популярность одинакова.
public class Task2_2
{
    public static void main (String[] args) throws FileNotFoundException
    {
        File file = new File("text.txt");
        Scanner in = new Scanner(new FileReader(file));
        String data;
        String[] array;
        HashMap<String, Integer> passwords = new HashMap<>();
        while (in.hasNext())
        {
            data = in.nextLine();
            array = data.split(" ", 2);
            if (passwords.containsKey(array[1]))
            {
                passwords.merge(array[1], 1, Integer::sum);
            }
            else
            {
                passwords.put(array[1], 1);
            }
        }
        StringBuilder top1 = new StringBuilder();
        StringBuilder top2 = new StringBuilder();
        StringBuilder top3 = new StringBuilder();
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        for (Map.Entry<String, Integer> entry : passwords.entrySet())
        {
            int value = entry.getValue();
            if (value < count3) continue;
            if (value == count3)
            {
                top3.append(entry.getKey()).append(" ");
                continue;
            }
            if (value == count2)
            {
                top2.append(entry.getKey()).append(" ");
                continue;
            }
            if (value == count1)
            {
                top1.append(entry.getKey()).append(" ");
                continue;
            }
            if (value > count1)
            {
                top3 = top2;
                count3 = count2;
                top2 = top1;
                count2 = count1;
                top1 = new StringBuilder(entry.getKey());
                top1.append(" ");
                count1 = value;
                continue;
            }
            if (value > count2)
            {
                top3 = top2;
                count3 = count2;
                top2 = new StringBuilder(entry.getKey());
                top2.append(" ");
                count2 = value;
                continue;
            }
            if (value > count3)
            {
                top3 = new StringBuilder(entry.getKey());
                top3.append(" ");
                count3 = value;
            }
        }
        System.out.println("Top number 1: " + top1);
        System.out.println("Top number 2: " + top2);
        System.out.println("Top number 3: " + top3);
        in.close();
    }
}
