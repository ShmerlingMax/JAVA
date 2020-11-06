package task4;

import java.io.CharConversionException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.FileSystemException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/*
    Задания приведены в виде TODO
    комментариев к методам.
 */
public class Main {
    public static void main(String[] args) throws URISyntaxException //без этого не скомпилится, так как это исключение
    // является проверяемым. И его надо либо обработать или пробросить выше
    {
        task1();
        task2();
        task3();
        task4();

        try
        {
            task5();
        }
        catch (NullPointerException | FileNotFoundException e)
        {
            System.out.println("task5: " + e.getClass().getSimpleName());
        }

        try
        {
            task6();
        }
        catch (Exception3 e)
        {
            System.out.println("task6.1: " + e.getClass().getSimpleName());
        }
        catch (Exception2 e)
        {
            System.out.println("task6.2: " + e.getClass().getSimpleName());
        }
        catch (Exception1 e)
        {
            System.out.println("task6.3: " + e.getClass().getSimpleName());
        }

        try
        {
            task7();
        }
        catch (FileSystemException e)
        {
            System.out.println("task7: " + e.getClass().getSimpleName());
        }

        task8();
    }

    /*
       TODO:
        task1 - task4
        1.	Нужно перехватить исключение (и вывести его на экран), указав его тип,
        возникающее при выполнении кода;
        2.	Вывести на экран тип перехваченного исключения.
    */
    private static void task1(){
        try
        {
            int a = 42 / 0;
        }
        catch (ArithmeticException e)
        {
            System.out.println("task1: " + e.getClass().getSimpleName());
        }
    }

    private static void task2(){
        try
        {
            String s = null;
            String m = s.toLowerCase();
        }
        catch (NullPointerException e)
        {
            System.out.println("task2: " + e.getClass().getSimpleName());
        }

    }

    private static void task3(){
        try
        {
            int[] m = new int[3];
            m[6] = 5;
        }
        catch (ArrayIndexOutOfBoundsException e)
        {
            System.out.println("task3: " + e.getClass().getSimpleName());
        }
    }

    private static void task4(){
      try
      {
          int num = Integer.parseInt("XYZ");
          System.out.println(num);
      }
      catch (NumberFormatException e)
      {
          System.out.println("task4: " + e.getClass().getSimpleName());
      }

    }

    /*
     TODO:
        В методе main вызовите метод task5 и перехватите только NullPointerException и FileNotFoundException,
        без перехвата ArithmeticException и URISyntaxException
     */
    private static void task5() throws NullPointerException, ArithmeticException, FileNotFoundException, URISyntaxException {
        int i = (int) (Math.random() * 4);
        if (i == 0) {
            throw new NullPointerException();
        } else if (i == 1) {
            throw new ArithmeticException();
        } else if (i == 2) {
            throw new FileNotFoundException();
        } else if (i == 3) {
            throw new URISyntaxException("", "");
        }
    }


    /*
     TODO:
        В методе main вызовите метод task6 и обработайте по отдельности Exception1, Exception2 и Exception3
     */
    private static void task6() throws Exception1 {
        int i = (int) (Math.random() * 3);
        if (i == 0) {
            throw new Exception1();
        } else if (i == 1) {
            throw new Exception2();
        } else if (i == 2) {
            throw new Exception3();
        }

    }

    /*
     TODO:
        1.	Разберитесь, какие исключения бросает метод methodThrowExceptions класса StatelessBean
        2.	Метод task7 должен вызывать метод methodThrowExceptions и обрабатывать исключения:
                1. Если возникло исключение FileSystemException, то логировать его (вызвать метод log)
                 и пробросить дальше.
                2. Если возникло исключение CharConversionException или любое другое IOException,
                то только логировать его (вызвать метод BEAN.log)
        3.	Добавьте в объявление метода task7 класс исключения, которое вы пробрасываете в п.2.1.
        4.	В методе main обработайте оставшиеся исключения.
     */

    private static void task7() throws FileSystemException {
        StatelessBean tmp = new StatelessBean();
        try
        {
            tmp.methodThrowExceptions();
        }
        catch (FileSystemException e)
        {
            tmp.log(e);
            throw e;
        }
        catch (IOException e)
        {
            tmp.log(e);
        }
    }

    /*
     TODO:
        Напишите программу для ввода чисел с клавиатуры.
        - Код по чтению чисел с клавиатуры должен быть в методе task8.
        - Код внутри task8 обернуть в try..catch.
        - Если пользователь ввёл какой-то текст, вместо ввода числа, то метод должен перехватить исключение
            и вывести на экран все ранее введенные числа в качестве результата.
        - Числа выводить с новой строки сохраняя порядок ввода.
     */
    private static void task8(){
        Scanner scan = new Scanner(System.in);
        ArrayList<Integer> arr = new ArrayList<>();
        try
        {
            while (true)
            {
                arr.add(scan.nextInt());
            }
        }
        catch (InputMismatchException e)
        {
            System.out.println();
            for (int i : arr)
            {
                System.out.print(i + " ");
            }
        }
    }

    static class Exception1 extends Exception{

    }

    static class Exception2 extends Exception1{

    }

    static class Exception3 extends Exception2{

    }

    public static class StatelessBean {
        public void log(Exception exception) {
            System.out.println(exception.getMessage() + ", " + exception.getClass().getSimpleName());
        }

        public void methodThrowExceptions() throws IOException { //данный метод бросает исключения унаследованные от IOException,
            // поэтому можем оставить только IOException
            int i = (int) (Math.random() * 3);
            if (i == 0) {
                throw new CharConversionException();
            } else if (i == 1) {
                throw new FileSystemException("");
            } else if (i == 2) {
                throw new IOException();
            }
        }
    }
}