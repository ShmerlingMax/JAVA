package service1;

import java.util.Random;
import java.util.Vector;

public class Service1
{
    public static Vector<Ship> generateSchedule(int days, int minutes, int maximumNumberOfShips, int maximumLoadWeight,
                                        int maximumNumberOfContainers, int weightLoadingCapacity, int containerLoadingCapacity, int unloadingTime)
    {
        Random rnd = new Random();
        Vector<Ship> ships = new Vector<>();
        System.out.printf("%65s%n","Сгенерированное расписание");
        System.out.println("Название     Запланированое время прибытия   Тип груза   Вес/Количество    Планируемый срок стоянки");
        for (int i = 0; i < rnd.nextInt(maximumNumberOfShips) + 300; i++)
        {
            ships.add(new Ship(days, minutes, maximumLoadWeight, maximumNumberOfContainers, weightLoadingCapacity,
                    containerLoadingCapacity, unloadingTime, i));
            ships.get(i).print();
        }
        return ships;
    }
}
