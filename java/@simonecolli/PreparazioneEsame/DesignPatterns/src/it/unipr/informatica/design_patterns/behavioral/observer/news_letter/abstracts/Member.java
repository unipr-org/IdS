package it.unipr.informatica.design_patterns.behavioral.observer.news_letter.abstracts;

public interface Member extends ObserverMember {
	public void registerToNewsLetter(NewsLetter newsLetter);
}
