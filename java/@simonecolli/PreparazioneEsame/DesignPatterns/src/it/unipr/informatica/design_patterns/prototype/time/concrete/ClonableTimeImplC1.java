package it.unipr.informatica.design_patterns.prototype.time.concrete;

import it.unipr.informatica.design_patterns.prototype.time.abstracts.ClonableTime;

public class ClonableTimeImplC1 extends ClonableTime{

	private int hr_;
	private int min_;
	private int sec_;
	@Override
	public void setTime(int hr, int min, int sec) {
		hr_ = hr;
		min_ = min;
		sec_= sec;
	}

	@Override
	public int getHours() {
		return hr_;
	}

	@Override
	public int getMinutes() {
		return min_;
	}

	@Override
	public int getSeconds() {
		return sec_;
	}

}
