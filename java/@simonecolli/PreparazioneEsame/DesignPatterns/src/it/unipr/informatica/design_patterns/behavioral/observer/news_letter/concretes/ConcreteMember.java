package it.unipr.informatica.design_patterns.behavioral.observer.news_letter.concretes;

import it.unipr.informatica.design_patterns.behavioral.observer.news_letter.abstracts.Member;
import it.unipr.informatica.design_patterns.behavioral.observer.news_letter.abstracts.NewsLetter;

public class ConcreteMember implements Member{
	
	private NewsLetter newsLetter_;	
	private String name_;
	
	public ConcreteMember(String name) {
		this.name_ = name;
	}

	@Override
	public void registerToNewsLetter(NewsLetter newsLetter) {
		newsLetter_ = newsLetter;
		newsLetter_.subscribe(this);
	}
	
	@Override
	public void update() {
		System.out.println("[" + name_ + "] You received a new:");
		System.out.println(newsLetter_.getLastNew().getTitle());
	}
	
	

}
