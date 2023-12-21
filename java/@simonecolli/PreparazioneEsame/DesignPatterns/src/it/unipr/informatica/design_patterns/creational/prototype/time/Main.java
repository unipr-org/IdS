package it.unipr.informatica.design_patterns.creational.prototype.time;

import java.time.LocalTime;

import it.unipr.informatica.design_patterns.creational.prototype.time.abstracts.ClonableTime;
import it.unipr.informatica.design_patterns.creational.prototype.time.concrete.ClonableTimeImplC1;
import it.unipr.informatica.design_patterns.creational.prototype.time.concrete.ClonableTimeImplC2;
import it.unipr.informatica.design_patterns.creational.prototype.time.concrete.SchedulerClient;

public class Main {
	public static void main(String[] args) throws CloneNotSupportedException {
		ClonableTime ct1 = new ClonableTimeImplC1();
		
		LocalTime localTime = LocalTime.now();
		
		ct1.setTime(localTime.getHour(), localTime.getMinute(), localTime.getSecond());
		
		System.out.println("start: " + ct1.getHours() + ":" + ct1.getMinutes() + ":" + ct1.getSeconds());
		
		ClonableTime end = SchedulerClient.calculateEnd(ct1, 6);		
		System.out.println("end: " + end.getHours() + ":" + end.getMinutes() + ":" + end.getSeconds());
		
		
		ClonableTime ct2 = new ClonableTimeImplC2();
		
		localTime = LocalTime.now();
		
		ct2.setTime(localTime.getHour(), localTime.getMinute(), localTime.getSecond());
		
		System.out.println("start: " + ct2.getHours() + ":" + ct2.getMinutes() + ":" + ct2.getSeconds());
		
		ClonableTime end2 = SchedulerClient.calculateEnd(ct2, 10);		
		System.out.println("end: " + end2.getHours() + ":" + end2.getMinutes() + ":" + end2.getSeconds());
	
	}
}
