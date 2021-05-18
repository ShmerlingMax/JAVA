package com.example.service3.service;

import com.example.service3.entity.Crane;
import com.example.service3.entity.Ship;
import com.example.service3.entity.Statistics;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.*;

public class Service3
{
    final static int weightLoadingCapacity = 1000;
    final static int containerLoadingCapacity = 125;
    final static int unloadingTime = 60;
    private static Statistics statistics = new Statistics();
    public static int calculatePenalty(Vector<Ship> ships)
    {
        int penalty = 0;
        for (Ship ship : ships)
        {
            penalty += (ship.waitingTime_ + ship.durationOfUnloading_ - ship.plannedStayingPeriod_) / 60;
        }
        return penalty * 100;
    }

    private static int simulation (Vector<Ship> ships, int type, int loadingCapacity, int unloadingTime) throws InterruptedException
    {
        int minPenalty = calculatePenalty(ships);
        int minIndex = 1;
        int maxCrane = minPenalty / 30000;
        Vector<Ship> newCargo = new Vector<>();
        for (Ship ship : ships)
        {
            newCargo.add(new Ship(ship));
        }
        for (int i = 2; i <= maxCrane; i++)
        {
            Vector<Thread> threads = new Vector<>();
            Object object = new Object();
            ArrayDeque<Ship> deque = new ArrayDeque<>(newCargo);
            Vector<Crane> cranes = new Vector<>();
            for (int j = 0; j < i; j++)
            {
                cranes.add(new Crane(type, loadingCapacity, unloadingTime));
                threads.add(new Thread(new Unloading(deque, cranes, object, deque.getLast().calculateArrivalTime(), j)));
            }
            for (Thread thread : threads)
            {
                thread.start();
            }
            for (Thread thread : threads)
            {
                thread.join();
            }
            int currentPenalty = calculatePenalty(newCargo);
            if (minPenalty > currentPenalty + 30000 * (i - 1))
            {
                ships.clear();
                for (Ship ship : newCargo)
                {
                    ships.add(new Ship(ship));
                }
                minPenalty = currentPenalty + 30000 * (i -1);
                minIndex = i;
            }
        }
        return minIndex;
    }
    private static class Unloading implements Runnable
    {
        public Unloading(ArrayDeque<Ship> ships, Vector<Crane> cranes,  Object object,
                         int endTime, int index)
        {
            ships_ = ships;
            cranes_ = cranes;
            object_ = object;
            endTime_ = endTime;
            index_ = index;
        }
        @Override
        public void run()
        {
            Crane crane = cranes_.get(index_);
            while (crane.now_ < endTime_)
            {
                synchronized (crane)
                {
                    Ship ship = new Ship();
                    if (ships_.isEmpty())
                    {
                        break;
                    }
                    try
                    {
                        if (ships_.getFirst().calculateArrivalTime() >= crane.now_)
                        {
                            synchronized (object_)
                            {
                                ship = ships_.pop();
                            }
                            Crane optionalCrane = null;
                            for (Crane i : cranes_)
                            {
                                if (i.now_ <= crane.now_)
                                {
                                    optionalCrane = i;
                                    break;
                                }
                            }
                            if (optionalCrane != null && optionalCrane != crane)
                            {
                                synchronized (optionalCrane)
                                {
                                    crane.loadCapacity_ *= 2;
                                    crane.unload(ship);
                                    crane.loadCapacity_ /= 2;
                                    optionalCrane.now_ = crane.now_;
                                }
                            }
                            else
                            {
                                crane.unload(ship);
                            }
                        } else
                        {
                            crane.now_++;
                        }
                    } catch (NoSuchElementException ignored)
                    {}
                }
            }
        }
        private ArrayDeque<Ship> ships_;
        private Vector<Crane> cranes_;
        final private Object object_;
        private int endTime_;
        private int index_;
    }

    public static void getStatistics()
            throws IOException, InterruptedException
    {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8082/service2/document";
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        String json = responseEntity.getBody();
        ObjectMapper mapper = new ObjectMapper();
        Ship[] ships = null;
        try
        {
            ships = mapper.readValue(json, Ship[].class);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        for (Ship ship : ships)
        {
            ship.delay();
        }
        Arrays.sort(ships, new Ship.SortShip());
        Vector<Ship> bulkCargo = new Vector<>();
        Vector<Ship> liquidCargo = new Vector<>();
        Vector<Ship> containerCargo = new Vector<>();
        for(Ship s : ships)
        {
            if (s.typeOfCargo_ == 0)
            {
                bulkCargo.add(s);
            }
            if (s.typeOfCargo_ == 1)
            {
                liquidCargo.add(s);
            }
            if (s.typeOfCargo_ == 2)
            {
                containerCargo.add(s);
            }
        }
        Thread t1 = new Thread(()->
        {
            Crane crane = new Crane(0, weightLoadingCapacity, unloadingTime);
            for(Ship ship : bulkCargo)
            {
                crane.unload(ship);
            }
        });
        Thread t2 = new Thread(()->
        {
            Crane crane = new Crane(1, weightLoadingCapacity, unloadingTime);
            for(Ship ship : liquidCargo)
            {
                crane.unload(ship);
            }
        });
        Thread t3 = new Thread(()->
        {
            Crane crane = new Crane(2, containerLoadingCapacity, unloadingTime);
            for(Ship ship : containerCargo)
            {
                crane.unload(ship);
            }
        });
        t1.start();
        t2.start();
        t3.start();
        t1.join();
        t2.join();
        t2.join();
        statistics.calculate(bulkCargo, liquidCargo, containerCargo);
        statistics.count1_ = 1;
        statistics.count2_ = 1;
        statistics.count3_ = 1;
        statistics.print();
        statistics.count1_ = simulation(bulkCargo, 0, weightLoadingCapacity, unloadingTime);
        statistics.count2_ = simulation(liquidCargo, 1, weightLoadingCapacity, unloadingTime);
        statistics.count3_ = simulation(containerCargo, 2, containerLoadingCapacity, unloadingTime);
        statistics.calculate(bulkCargo, liquidCargo, containerCargo);
        statistics.print();

        url = "http://localhost:8082/service2/save";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try
        {
            json = objectWriter.writeValueAsString(statistics);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
        }
        HttpEntity<String> request = new HttpEntity<>(json, headers);
        restTemplate.postForEntity(url, request, String.class);
    }
}
