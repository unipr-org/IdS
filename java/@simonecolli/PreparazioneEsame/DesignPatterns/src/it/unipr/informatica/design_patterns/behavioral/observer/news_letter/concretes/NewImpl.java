package it.unipr.informatica.design_patterns.behavioral.observer.news_letter.concretes;

import it.unipr.informatica.design_patterns.behavioral.observer.news_letter.abstracts.New;

public class NewImpl implements New {
	private String title_;
	private String description_;
	public NewImpl(String title, String description) {
		this.title_ = title;
		this.description_ = description;
	}
	@Override
	public String getTitle() {
		return title_;
	}

	@Override
	public String getDescription() {
		return description_;
	}

}
