package it.unipr.informatica.design_patterns.creational.prototype.time.concrete;

import it.unipr.informatica.design_patterns.creational.prototype.time.abstracts.ClonableTime;

public class SchedulerClient {
	public static ClonableTime calculateEnd(ClonableTime time0, int hours) throws CloneNotSupportedException {
		int hr = time0.getHours() + hours;
		if(hr > 24)
			hr = hr - 24;
		
		ClonableTime end = time0.clone();
		end.setTime(hr, time0.getMinutes(), time0.getSeconds());
		return end;
	}
}
