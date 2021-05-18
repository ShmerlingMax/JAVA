package com.example.service2.controller;

import com.example.service2.service.Service2;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/service2")
public class ServiceController_2
{
    @GetMapping("/document")
    public static String getDocument()
    {
        return Service2.generateScheduleDocument();
    }

    @GetMapping(value = "/schedule")
    public static String getScheduleRequestParam(@RequestParam("name") String name)
    {
        try
        {
            return Service2.getSchedule(name);
        }
        catch (IOException e)
        {
            return "The schedule file with this name was not found!";
        }
    }

    @GetMapping(value = "/schedule/{name}")
    public static String getSchedulePathVariable(@PathVariable("name") String name)
    {
        try
        {
            return Service2.getSchedule(name);
        }
        catch (IOException e)
        {
            return "The schedule file with this name was not found!";
        }
    }

    @PostMapping("/save")
    public static void saveStatistics(@RequestBody String statistics)
    {
        Service2.saveStatistics(statistics);
    }
}
