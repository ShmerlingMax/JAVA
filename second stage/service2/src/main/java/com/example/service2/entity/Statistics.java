package com.example.service2.entity;

import java.util.Vector;

public class Statistics
{

    private static int calculatePenalty(Vector<Ship> ships)
    {
        int penalty = 0;
        for (Ship ship : ships)
        {
            penalty += (ship.waitingTime_ + ship.durationOfUnloading_ - ship.plannedStayingPeriod_) / 60;
        }
        return penalty * 100;
    }
    private double calculateAverageQueueLength(Vector<Ship> ships)
    {
        int n = 0, sum = 0;
        boolean flag = true;
        for (Ship ship : ships)
        {
            if (ship.waitingTime_ != 0)
            {
                sum++;
                if (flag)
                {
                    n++;
                    flag = false;
                }
            }
            else
            {
                flag = true;
                n++;
            }
        }
        return (double)sum / n;
    }

    private double calculateAverageWaitingTime(Vector<Ship> ships)
    {
        int sum = 0;
        for (Ship ship : ships)
        {
            sum += ship.waitingTime_;
        }
        return (double)sum / ships.size();
    }

    private int calculateMaximumUnloadingDelay(Vector<Ship> ships)
    {
        int max = 0;
        for (Ship ship : ships)
        {
            if (ship.durationOfUnloading_ - ship.plannedStayingPeriod_ > max)
            {
                max = ship.durationOfUnloading_ - ship.plannedStayingPeriod_;
            }
        }
        return max;
    }

    private double calculateAverageUnloadingDelay(Vector<Ship> ships)
    {
        int sum = 0;
        for (Ship ship : ships)
        {
            sum += ship.durationOfUnloading_ - ship.plannedStayingPeriod_;
        }
        return sum / (double)ships.size();
    }
    public void calculate(Vector<Ship> bulkCargo, Vector<Ship> liquidCargo, Vector<Ship> containerCargo)
    {
        bulkCargo_ = new Vector<>();
        liquidCargo_= new Vector<>();
        containerCargo_= new Vector<>();
        for (Ship ship : bulkCargo)
        {
            bulkCargo_.add(new Ship(ship));
        }
        for (Ship ship : liquidCargo)
        {
            liquidCargo_.add(new Ship(ship));
        }
        for (Ship ship : containerCargo)
        {
            containerCargo_.add(new Ship(ship));
        }
        size1_ = bulkCargo_.size();
        size2_ = liquidCargo_.size();
        size3_ = containerCargo_.size();
        averageQueueLength1_ = calculateAverageQueueLength(bulkCargo_);
        averageQueueLength2_ = calculateAverageQueueLength(liquidCargo_);
        averageQueueLength3_ = calculateAverageQueueLength(containerCargo_);
        averageWaitingTime1_ = calculateAverageWaitingTime(bulkCargo_);
        averageWaitingTime2_ = calculateAverageWaitingTime(liquidCargo_);
        averageWaitingTime3_ = calculateAverageWaitingTime(containerCargo_);
        maximumUnloadingDelay1_ = calculateMaximumUnloadingDelay(bulkCargo_);
        maximumUnloadingDelay2_ = calculateMaximumUnloadingDelay(liquidCargo_);
        maximumUnloadingDelay3_ = calculateMaximumUnloadingDelay(containerCargo_);
        averageUnloadingDelay1_ = calculateAverageUnloadingDelay(bulkCargo_);
        averageUnloadingDelay2_ = calculateAverageUnloadingDelay(liquidCargo_);
        averageUnloadingDelay3_ = calculateAverageUnloadingDelay(containerCargo_);
        penalty1_ = calculatePenalty(bulkCargo_);
        penalty2_ = calculatePenalty(liquidCargo_);
        penalty3_ = calculatePenalty(containerCargo_);
    }

    public void print()
    {
        System.out.println("\n\nСтатистика:");
        System.out.println("Число разгруженных судов с сыпучим грузом: " + size1_ + " шт.");
        System.out.println("Число разгруженных судов с жидким грузом: " + size2_+" шт.");
        System.out.println("Число разгруженных судов с контейнерами: " + size3_+" шт.");
        System.out.println();
        System.out.println("Средняя длина очереди на рузгрузку судов с сыпучим грузом: " + String.format("%.1f",averageQueueLength1_)+" шт.");
        System.out.println("Средняя длина очереди на рузгрузку судов с жидким грузом: " + String.format("%.1f",averageQueueLength2_)+" шт.");
        System.out.println("Средняя длина очереди на рузгрузку судов с контейнерами: " + String.format("%.1f",averageQueueLength3_)+" шт.");
        System.out.println();
        System.out.println("Среднее время ожидания в очереди на рузгрузку судов с сыпучим грузом: " + Math.round(averageWaitingTime1_) + " минут");
        System.out.println("Среднее время ожидания в очереди на рузгрузку судов с жидким грузом: " + Math.round(averageWaitingTime2_) + " минут");
        System.out.println("Среднее время ожидания в очереди на рузгрузку судов с контейнерами: " + Math.round(averageWaitingTime3_) + " минут");
        System.out.println();
        System.out.println("Максимальная задержка разгрузки судов с сыпучим грузом: " + maximumUnloadingDelay1_+ " минут");
        System.out.println("Максимальная задержка разгрузки судов с жидким грузом: " + maximumUnloadingDelay2_+ " минут");
        System.out.println("Максимальная задержка разгрузки судов с контейнерами " + maximumUnloadingDelay3_+ " минут");
        System.out.println();
        System.out.println("Средняя задержка разгрузки судов с сыпучим грузом: " + Math.round(averageUnloadingDelay1_) + " минут");
        System.out.println("Средняя задержка разгрузки судов с жидким грузом: " + Math.round(averageUnloadingDelay2_) + " минут");
        System.out.println("Средняя задержка разгрузки судов с контейнерами " + Math.round(averageUnloadingDelay3_) + " минут");
        System.out.println();
        System.out.println("Общая сумма штрафа за суда с сыпучим грузом: " + penalty1_ + " у.е.");
        System.out.println("Общая сумма штрафа за суда с жидким грузом: " + penalty2_+ " у.е.");
        System.out.println("Общая сумма штрафа за суда с контейнерами: " + penalty3_+ " у.е.");
        System.out.println();
        System.out.println("Количество кранов для сыпучего груза: " + count1_+ " шт.");
        System.out.println("Количество кранов для жидкого груза: " + count2_+ " шт.");
        System.out.println("Количество кранов для контейнеров: " + count3_+ " шт.");
        System.out.println();
        System.out.println();
        System.out.printf("%75s%n", "Сыпучие грузы (" + bulkCargo_.size()+"шт.)");
        System.out.println("Название       Фактическое время прибытия          Время ожидания на разгрузку          Время начала разгрузки           Длительность разгрузки");
        for (Ship ship : bulkCargo_)
        {
            ship.printStatistics();
        }
        System.out.println();
        System.out.println();
        System.out.printf("%75s%n", "Жидкие грузы (" + liquidCargo_.size()+"шт.)");
        System.out.println("Название       Фактическое время прибытия          Время ожидания на разгрузку          Время начала разгрузки           Длительность разгрузки");

        for (Ship ship : liquidCargo_)
        {
            ship.printStatistics();
        }
        System.out.println();
        System.out.println();
        System.out.printf("%75s%n","Контейнера (" + containerCargo_.size()+"шт.)");
        System.out.println("Название       Фактическое время прибытия          Время ожидания на разгрузку          Время начала разгрузки           Длительность разгрузки");
        for (Ship ship : containerCargo_)
        {
            ship.printStatistics();
        }
    }
    public Statistics(){};
    public Vector<Ship> bulkCargo_ = new Vector<>();
    public Vector<Ship> liquidCargo_= new Vector<>();
    public Vector<Ship> containerCargo_= new Vector<>();
    public int size1_;
    public double averageQueueLength1_;
    public double averageWaitingTime1_;
    public int maximumUnloadingDelay1_;
    public double averageUnloadingDelay1_;
    public int penalty1_;
    public int size2_;
    public double averageQueueLength2_;
    public double averageWaitingTime2_;
    public int maximumUnloadingDelay2_;
    public double averageUnloadingDelay2_;
    public int penalty2_;
    public int size3_;
    public double averageQueueLength3_;
    public double averageWaitingTime3_;
    public int maximumUnloadingDelay3_;
    public double averageUnloadingDelay3_;
    public int penalty3_;
    public int count1_;
    public int count2_;
    public int count3_;
}
