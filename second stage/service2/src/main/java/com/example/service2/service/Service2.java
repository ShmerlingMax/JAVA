package com.example.service2.service;

import com.example.service2.entity.Ship;
import com.example.service2.entity.Statistics;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Service2
{
    final static int weightLoadingCapacity = 1000;
    final static int containerLoadingCapacity = 125;
    final static int unloadingTime = 60;
    public static String generateScheduleDocument ()
    {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8081/service1";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String json = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<Ship> ships = null;
        try
        {
            ships = mapper.readValue(json, new TypeReference<ArrayList<Ship>>(){});
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
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
                } else
                {
                    break;
                }
            } catch (NumberFormatException e)
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
            if (count == 0)
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
                } else
                {
                    ships.add(new Ship(name, day, hours, minutes1, type, quantity, containerLoadingCapacity, unloadingTime));
                }
                count--;
                if (count == 0)
                {
                    break;
                }
            } catch (NumberFormatException e)
            {
                System.out.println("Введенная вами запись некорректна!");
            }
        }
        try
        {
            mapper.writeValue(new File("schedules/ships"), ships);
        } catch (IOException exception)
        {
            exception.printStackTrace();
        }
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
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

    static public String getSchedule(String name) throws IOException
    {
        return new String(Files.readAllBytes(Paths.get("schedules/"+name)));
    }
    static public void saveStatistics(String statistics)
    {
        try(FileWriter writer = new FileWriter("results/statistics", false))
        {
            writer.write(statistics);
            writer.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }

}
