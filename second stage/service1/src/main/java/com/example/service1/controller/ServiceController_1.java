package com.example.service1.controller;

import com.example.service1.service.Service1;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service1")
public class ServiceController_1
{
    @GetMapping
    public static String getSchedule()
    {
        return Service1.generateSchedule();
    }
}
