package com.example.service2.controller;

import com.example.service2.service.Service2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/service2")
public class ServiceController_2
{
    final static String NOT_FOUND = "The schedule file with this name was not found!";
    @GetMapping("/document")
    public static String getDocument()
    {
        return Service2.generateScheduleDocument();
    }

    @GetMapping(value = "/schedule")
    public static ResponseEntity<String> getScheduleRequestParam(@RequestParam("name") String name)
    {
        try
        {
            return new ResponseEntity<>(Service2.getSchedule(name), HttpStatus.OK);
        }
        catch (IOException e)
        {
            return new ResponseEntity<>(NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/schedule/{name}")
    public static ResponseEntity<String> getSchedulePathVariable(@PathVariable("name") String name)
    {
        try
        {
            return new ResponseEntity<>(Service2.getSchedule(name), HttpStatus.OK);
        }
        catch (IOException e)
        {
            return new ResponseEntity<>(NOT_FOUND, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/save")
    public static void saveStatistics(@RequestBody String statistics)
    {
        Service2.saveStatistics(statistics);
    }
}
