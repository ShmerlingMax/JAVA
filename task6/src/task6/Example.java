package task6;

public class Example
{
    @IntegerParameter(value = 2)
    private static void method1()
    {
        System.out.println("method1");
    }
    private static void method2()
    {
        System.out.println("method2");
    }
    @IntegerParameter(value = 4)
    private static void method3()
    {
        System.out.println("method3");
    }
    private static void method4()
    {
        System.out.println("method4");
    }
}
