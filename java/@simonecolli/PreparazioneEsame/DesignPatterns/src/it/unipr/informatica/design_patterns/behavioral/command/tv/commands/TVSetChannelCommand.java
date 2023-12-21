package it.unipr.informatica.design_patterns.behavioral.command.tv.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import it.unipr.informatica.design_patterns.behavioral.command.tv.TV;

public class TVSetChannelCommand implements Command{
	
	private TV tv_;
	public TVSetChannelCommand(TV tv) {
		this.tv_ = tv;
	}
	@Override
	public void execute() {
		setChannel();
	}
	private void setChannel() {
		BufferedReader bf = new BufferedReader(
				new InputStreamReader(System.in)
				);
		try {
			System.out.println("Insert channel number: ");
			int channel = Integer.parseInt(bf.readLine());
			tv_.setChannen(channel);
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("!! error !!");
		}
	}

}
