package it.unipr.informatica.design_patterns.behavioral.command.tv;

public class TV {
	
	private boolean isOn_;
	private int volume_;
	private int channel_;
	
	public static final int MIN_VOLUME = 0;
	public static final int MAX_VOLUME = 100;
	public static final int MIN_CHANNEL= 1;
	public static final int MAX_CHANNEL = 1000;
	
	public TV() {
		this.isOn_ = false;
		this.volume_ = MIN_VOLUME;
		this.channel_ = 1;
	}
	
	public void power() {
		if(isOn_) {
			isOn_ = false;
			System.out.println("Tv is off");
		} else {
			isOn_ = true;
			System.out.println("Tv is on");
		}
	}
	public void volumeUp() {
		if(!isOn_)
			return;
		if(volume_ < MAX_VOLUME)
			++volume_;
		System.out.println("Current volume: " + volume_);
	}

	public void volumeDown() {
		if(!isOn_)
			return;
		
		if(volume_ > MIN_VOLUME)
			--volume_;
		System.out.println("Current volume: " + volume_);
	}
	
	public void setChannen(int channel) {
		if(!isOn_)
			return;
		if(channel >= MIN_CHANNEL && channel < MAX_CHANNEL )
			this.channel_ = channel;
		
		System.out.println("Current channel: " + channel_);

	}
}
