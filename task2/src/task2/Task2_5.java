package task2;

import java.util.*;

public class Task2_5
{
    static private boolean checkChar(String str, char symbol, int i)
    {
        if (i + 1 >= str.length()) return false;
        return  (str.charAt(i + 1) == symbol);
    }
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        String randomChar = "`1234567890-=qwertyuiop[]\\asdfghjkl;'zxcvbnm,./!@#$%^&*()_+QWERTYUIOP{}|ASDFGHJKL:ZXCVBNM<>?йцукенгшщзхъфывапролджэячсмитьбюё";
        while (in.hasNext())
        {
            List<String> list = new ArrayList<String>();
            String str = in.nextLine();
            for (int i = 1; i < str.length(); i+=3)
            {
                char symbol;
                do
                {
                    Random rnd = new Random();
                    symbol = randomChar.charAt(rnd.nextInt(randomChar.length()));
                } while (symbol == str.charAt(i - 1) || symbol == str.charAt(i) || checkChar(str, symbol, i));
                String element = "";
                element += str.charAt(i -1);
                element+= symbol;
                if (i+1 < str.length())element+=str.charAt(i+1);
                list.add(element);
            }
            if (str.length() % 3 == 1)list.add(Character.toString(str.charAt(str.length()-1)));
            Collections.sort(list);
            for (String s : list)
            {
                System.out.print(s + " ");
            }
            System.out.println();
        }
        in.close();
    }
}
