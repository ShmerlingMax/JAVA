package com.example.service1.service;

import com.example.service1.entity.Ship;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.ArrayList;
import java.util.Random;

public class Service1
{
    final static int days = 30, minutes = 1440, maximumNumberOfShips = 101, maximumLoadWeight = 5000,
            maximumNumberOfContainers = 625, weightLoadingCapacity = 1000, containerLoadingCapacity = 125, unloadingTime = 60;
    public static String generateSchedule()
    {
        Random rnd = new Random();
        ArrayList<Ship> ships = new ArrayList<>();
        System.out.printf("%65s%n", "Сгенерированное расписание");
        System.out.println("Название     Запланированое время прибытия   Тип груза   Вес/Количество    Планируемый срок стоянки");
        for (int i = 0; i < rnd.nextInt(maximumNumberOfShips) + 300; i++)
        {
            ships.add(new Ship(days, minutes, maximumLoadWeight, maximumNumberOfContainers, weightLoadingCapacity,
                    containerLoadingCapacity, unloadingTime, i));
            ships.get(i).print();
        }
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = null;
        try
        {
            json = objectWriter.writeValueAsString(ships);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        return json;
    }
}
