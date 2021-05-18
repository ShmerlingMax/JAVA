package service2;

import com.fasterxml.jackson.databind.ObjectMapper;
import service1.Service1;
import service1.Ship;
import service3.Service3;
import service3.Statistics;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

public class Service2
{
    public static void main(String[] args)
    {
        final int days = 30, minutes = 1440, maximumNumberOfShips =  101, maximumLoadWeight = 5000,
                maximumNumberOfContainers = 625, weightLoadingCapacity = 1000, containerLoadingCapacity = 125, unloadingTime = 60;
        Vector<Ship> ships = Service1.generateSchedule(days, minutes, maximumNumberOfShips, maximumLoadWeight, maximumNumberOfContainers,
                weightLoadingCapacity, containerLoadingCapacity, unloadingTime);
        System.out.println("Сколько записей вы хотите добавить в расписание?");
        int count;
        Scanner in = new Scanner(System.in);
        while (true)
        {
            String line = in.nextLine();
            try
            {
                count = Integer.parseInt(line);
                if (count < 0)
                {
                    System.out.println("Введите целое неотрицательное число!");
                }
                else
                {
                    break;
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Введите целое неотрицательное число!");
            }
        }
        if (count != 0)
        {
            System.out.println("Введите записи в форме: название дд:чч:мм тип количество!");
        }
        while (true)
        {
            if(count == 0)
            {
                break;
            }
            String line = in.nextLine();
            String[] record = line.split(" ");
            if (record.length != 4)
            {
                System.out.println("Введенная вами запись некорректна!");
                continue;
            }
            try
            {
                String name = record[0];
                int type = Integer.parseInt(record[2]);
                int quantity = Integer.parseInt(record[3]);
                if (type < 0 || type > 2 || quantity < 0)
                {
                    System.out.println("Введенная вами запись некорректна!");
                    continue;
                }
                String[] time = record[1].split(":");
                if (time.length != 3)
                {
                    System.out.println("Введенная вами запись некорректна!");
                    continue;
                }
                int day = Integer.parseInt(time[0]);
                int hours = Integer.parseInt(time[1]);
                int minutes1 = Integer.parseInt(time[2]);
                if (day < 1 || day > 30 || hours < 0 || hours > 23 || minutes1 < 0 || minutes1 > 59)
                {
                    System.out.println("Введенная вами запись некорректна!");
                    continue;
                }
                if (type != 2)
                {
                    ships.add(new Ship(name, day, hours, minutes1, type, quantity, weightLoadingCapacity, unloadingTime));
                }
                else
                {
                    ships.add(new Ship(name, day, hours, minutes1, type, quantity, containerLoadingCapacity, unloadingTime));
                }
                count--;
                if (count == 0)
                {
                    break;
                }
            }
            catch (NumberFormatException e)
            {
                System.out.println("Введенная вами запись некорректна!");
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            mapper.writeValue(new File("ships"), ships);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Statistics statistics = new Statistics();
        try
        {
            statistics = Service3.getStatistics(containerLoadingCapacity, weightLoadingCapacity, unloadingTime);
            mapper.writeValue(new File("statistics"), statistics);

        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
