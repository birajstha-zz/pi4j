package com.toggle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

@RestController
public class LEDController {

	private static GpioPinDigitalOutput pin;
	
	@RequestMapping("/hello")
	public String hello() {
		return "Hi";
	}
	
	@RequestMapping("/light")
	public String light() {
		if (pin == null) {
			GpioController gpio = GpioFactory.getInstance();
			pin =gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01,"MyLED", PinState.LOW);
		}
		
		pin.toggle();
		
		System.out.println("OK");
		return "OK";
	}
	
}
