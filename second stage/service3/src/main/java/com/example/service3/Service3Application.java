package com.example.service3;

import com.example.service3.service.Service3;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class Service3Application {

	public static void main(String[] args)
	{
		SpringApplication.run(Service3Application.class, args);
		try
		{
			Service3.getStatistics();
		}
		catch (IOException | InterruptedException e)
		{
			e.printStackTrace();
		}

	}

}
