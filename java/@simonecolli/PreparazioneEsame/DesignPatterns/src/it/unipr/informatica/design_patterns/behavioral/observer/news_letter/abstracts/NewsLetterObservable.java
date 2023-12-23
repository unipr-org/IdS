package it.unipr.informatica.design_patterns.behavioral.observer.news_letter.abstracts;

public interface NewsLetterObservable {
	public boolean subscribe(ObserverMember m);
	public boolean unsubscribe(ObserverMember m);
	public void sendNotification();
}
