package it.unipr.informatica.design_patterns.behavioral.observer.news_letter.concretes;

import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Vector;

import it.unipr.informatica.design_patterns.behavioral.observer.news_letter.abstracts.New;
import it.unipr.informatica.design_patterns.behavioral.observer.news_letter.abstracts.NewsLetter;
import it.unipr.informatica.design_patterns.behavioral.observer.news_letter.abstracts.ObserverMember;

public class ConcreteNewsLetter implements NewsLetter{
	
	private Vector<ObserverMember> members_;
	private ArrayDeque<New> news_;
	
	public ConcreteNewsLetter() {
		news_ = new ArrayDeque<>();
		members_ = new Vector<>();
	}
	
	@Override
	public boolean subscribe(ObserverMember m) {
		return members_.add(m);
	}

	@Override
	public boolean unsubscribe(ObserverMember m) {
		return members_.remove(m);
	}

	@Override
	public void sendNotification() {
		for (ObserverMember member : members_)
			member.update();
	}

	@Override
	public New getLastNew() {
		return news_.getFirst();
	}

	@Override
	public Collection<New> getAllNews() {
		return news_;
	}
	
	public void postNew(New nw) {
		news_.addFirst(nw);
		sendNotification();
	}
	
	public void clearNews() {
		news_.clear();
	}

}
