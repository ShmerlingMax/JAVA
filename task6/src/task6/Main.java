package task6;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {
    public static void main(String[] args)
    {
        try
        {
            Class<Example> c = Example.class;
            for (Method m : c.getDeclaredMethods()) {
                if (m.isAnnotationPresent(IntegerParameter.class))
                {
                    m.setAccessible(true);
                    for (int i = 0; i < m.getAnnotation(IntegerParameter.class).value(); i++)
                    {
                        m.invoke(c);
                    }
                }
            }
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
