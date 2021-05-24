package com.example.service1.service;

import com.example.service1.entity.Ship;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.util.ArrayList;
import java.util.Random;

public class Service1
{
    final static int DAYS = 30, MINUTES = 1440, MAXIMUM_NUMBER_OF_SHIPS = 101, MAXIMUM_LOAD_WEIGHT = 5000,
            MAXIMUM_NUMBER_OF_CONTAINERS = 625, WEIGHT_LOADING_CAPACITY = 1000, CONTAINER_LOADING_CAPACITY = 125, UNLOADING_TIME = 60;
    public static String generateSchedule()
    {
        Random rnd = new Random();
        ArrayList<Ship> ships = new ArrayList<>();
        System.out.printf("%65s%n", "Сгенерированное расписание");
        System.out.println("Название     Запланированое время прибытия   Тип груза   Вес/Количество    Планируемый срок стоянки");
        for (int i = 0; i < rnd.nextInt(MAXIMUM_NUMBER_OF_SHIPS) + 300; i++)
        {
            ships.add(new Ship(DAYS, MINUTES, MAXIMUM_LOAD_WEIGHT, MAXIMUM_NUMBER_OF_CONTAINERS, WEIGHT_LOADING_CAPACITY,
                    CONTAINER_LOADING_CAPACITY, UNLOADING_TIME, i));
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
