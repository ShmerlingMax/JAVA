package com.example.service3.entity;

import java.util.Random;

public class Crane
{
    public Crane(int type, int loadCapacity, int time)
    {
        type_ = type;
        loadCapacity_ = loadCapacity;
        time_ = time;
    }

    public Crane()
    {}

    public void unload(Ship ship)
    {
        ship.waitingTime_ = 0;
        int t = ship.calculateArrivalTime();
        if (t < now_)
        {
            ship.waitingTime_ = now_ - t;
        }
        else
        {
            now_ = t;
        }
        ship.unloadingStartTime_ = now_;
        Random rnd = new Random();
        now_ += rnd.nextInt(1440) + ((int)Math.ceil(ship.cargoQuantity_ / (double) loadCapacity_ * time_));
        ship.durationOfUnloading_ = now_ - ship.unloadingStartTime_;
    }
    public int type_;
    public int loadCapacity_;
    public int time_;
    public int now_ = 0;
}
