package task5;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class task5_3
{

    public static <K,V> Map<V,K> reverse(Map<K,V> map)
    {
        Map<V,K> rev = new LinkedHashMap<V, K>();
        for(Map.Entry<K,V> entry : map.entrySet())
            rev.put(entry.getValue(), entry.getKey());
        return rev;
    }
    public static void main(String[] args)
    {
        Map<String, String> map  = new LinkedHashMap<>();
        Scanner in = new Scanner(System.in);
        while (in.hasNext())
        {
            String[] tmp;
            tmp = in.nextLine().split(" ",2);
            map.put(tmp[0], tmp[1]);
        }
        map = reverse(map);
        for (Map.Entry<String, String> entry : map.entrySet())
        {
            System.out.println(entry.getKey() + ' ' + entry.getValue());
        }
    }
}
