package com.example.service3.entity;

import java.util.Comparator;
import java.util.Random;

public class Ship
{

    public Ship()
    {}
    public Ship(Ship ship)
    {
        name_ = ship.name_;
        day_ = ship.day_;
        hours_ = ship.hours_;
        minutes_ = ship.minutes_;
        typeOfCargo_ = ship.typeOfCargo_;
        cargoQuantity_ = ship.cargoQuantity_;
        plannedStayingPeriod_ = ship.plannedStayingPeriod_;
        waitingTime_ = ship.waitingTime_;
        unloadingStartTime_ = ship.unloadingStartTime_;
        durationOfUnloading_ = ship.durationOfUnloading_;
    }
    public Ship(int days, int minutes, int maximumLoadWeight, int maximumNumberOfContainers, int weightLoadingCapacity,
                int containerLoadingCapacity, int unloadingTime, int name)
    {
        Random rnd = new Random();
        day_ = rnd.nextInt(days) + 1;
        int minutes1 = rnd.nextInt(minutes);
        hours_ = minutes1 / 60;
        minutes_ = minutes1 % 60;
        name_ = "name" + name;
        typeOfCargo_ = rnd.nextInt(3);
        if (typeOfCargo_ == 0 || typeOfCargo_ == 1)
        {
            cargoQuantity_ = rnd.nextInt(maximumLoadWeight);
            plannedStayingPeriod_ = (int)Math.ceil(cargoQuantity_ / (double)weightLoadingCapacity * unloadingTime);
        }
        else
        {
            cargoQuantity_ = rnd.nextInt(maximumNumberOfContainers);
            plannedStayingPeriod_ = (int)Math.ceil(cargoQuantity_ / (double)containerLoadingCapacity * unloadingTime);
        }
    }

    public Ship(String name, int day, int hours, int minutes, int type, int quantity, int LoadingCapacity, int unloadingTime)
    {
        name_ = name;
        day_ = day;
        hours_ = hours;
        minutes_ = minutes;
        typeOfCargo_ = type;
        cargoQuantity_ = quantity;
        plannedStayingPeriod_ = ((int)Math.ceil(cargoQuantity_ / (double) LoadingCapacity * unloadingTime));
    }

    public void print()
    {
        System.out.printf("%-25s", name_);
        System.out.printf("%-20s", right("0"+day_) + ":" + right("0" + hours_)
                + ":" + right("0" + minutes_));
        if (typeOfCargo_ == 0)
        {
            System.out.printf("%-17s", "сыпучий");
        }
        if (typeOfCargo_ == 1)
        {
            System.out.printf("%-17s", "жидкий");
        }
        if (typeOfCargo_ == 2)
        {
            System.out.printf("%-17s", "контейнера");
        }
        System.out.printf("%-22s", cargoQuantity_);
        System.out.println(right("0"+(plannedStayingPeriod_/60)/24)+":" +right("0"
                +((plannedStayingPeriod_/60)-((plannedStayingPeriod_/60)/24)*24))+":"+right("0"
                +plannedStayingPeriod_%60));
    }
    public void printStatistics()
    {
        System.out.printf("%-25s", name_);
        System.out.printf("%-35s", right("0"+day_) + ":" + right("0" + hours_)
                + ":" + right("0" + minutes_));
        System.out.printf("%-35s", right("0"+(waitingTime_/60)/24)+":" + right("0"
                +((waitingTime_/60)-((waitingTime_/60)/24)*24))+":"+right("0"+waitingTime_%60));
        System.out.printf("%-35s", right("0"+(unloadingStartTime_/60)/24)+":" + right("0"
                +((unloadingStartTime_/60)-((unloadingStartTime_/60)/24)*24))+":"+right("0"
                +unloadingStartTime_%60));
        System.out.println(right("0"+(durationOfUnloading_/60)/24)+":" + right("0"
                +((durationOfUnloading_/60)-((durationOfUnloading_/60)/24)*24))+":"+right("0"
                +durationOfUnloading_%60));
    }

    public void delay()
    {
        Random rnd = new Random();
        int t1 = rnd.nextInt(20161) - 10080;
        int t2 = (day_ - 1) * 24 * 60 + hours_ * 60 + minutes_;
        if (t2 + t1 <= 1440)
        {
            day_ = 1;
            hours_ = 0;
            minutes_ = 0;
        }
        else
        {
            if (t2 + t1 > 44639)
            {
                day_ = 30;
                hours_ = 23;
                minutes_ = 59;
            }
            else
            {
                t2 += t1;
                minutes_ = t2 % 60;
                day_ = (t2 / 60) / 24;
                hours_ = (t2 / 60) - 24 * day_;
            }
        }
    }

    public int calculateArrivalTime()
    {
        return day_ * 24 * 60 + hours_ * 60 + minutes_;
    }
    public String right(String str)
    {
        return str.charAt(str.length() - 2) + String.valueOf(str.charAt(str.length() - 1));
    }
    public int day_;
    public int hours_;
    public int minutes_;
    public String name_;
    public int typeOfCargo_;
    public int cargoQuantity_;
    public int plannedStayingPeriod_;
    public int waitingTime_ = 0;
    public int unloadingStartTime_ = 0;
    public int durationOfUnloading_ = 0;
    public static class SortShip implements Comparator<Ship>
    {
        public int compare(Ship a, Ship b)
        {
            if (a.day_ == b.day_)
            {
                if (a.hours_ == b.hours_)
                {
                    return a.minutes_ - b.minutes_;
                }
                else
                {
                    return a.hours_ - b.hours_;
                }
            }
            else
            {
                return a.day_- b.day_;
            }
        }
    }
}
