package it.unipr.informatica.design_patterns.behavioral.observer.news_letter.abstracts;

import java.util.Collection;

public interface NewsLetter extends NewsLetterObservable {
	public New getLastNew();
	public Collection<New> getAllNews();
}
