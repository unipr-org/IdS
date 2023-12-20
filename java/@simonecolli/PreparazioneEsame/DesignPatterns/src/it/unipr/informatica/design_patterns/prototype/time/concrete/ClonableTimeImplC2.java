package it.unipr.informatica.design_patterns.prototype.time.concrete;

import it.unipr.informatica.design_patterns.prototype.time.abstracts.ClonableTime;

public class ClonableTimeImplC2 extends ClonableTime{

	private int sec_;
	@Override
	public void setTime(int hr, int min, int sec) {
		sec_= sec + hr * 3600 + min * 60;
	}

	@Override
	public int getHours() {
		return sec_/3600;
	}

	@Override
	public int getMinutes() {
		return (sec_ - 3600 * getHours()) /60;
	}

	@Override
	public int getSeconds() {
		return sec_ % 60;
	}

}
