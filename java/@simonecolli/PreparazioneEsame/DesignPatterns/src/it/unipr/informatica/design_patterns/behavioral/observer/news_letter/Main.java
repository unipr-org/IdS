package it.unipr.informatica.design_patterns.behavioral.observer.news_letter;

import it.unipr.informatica.design_patterns.behavioral.observer.news_letter.abstracts.Member;
import it.unipr.informatica.design_patterns.behavioral.observer.news_letter.concretes.ConcreteMember;
import it.unipr.informatica.design_patterns.behavioral.observer.news_letter.concretes.ConcreteNewsLetter;
import it.unipr.informatica.design_patterns.behavioral.observer.news_letter.concretes.NewImpl;

public class Main {
	public static void main(String[] args) {
		
		ConcreteNewsLetter uniprNews = new ConcreteNewsLetter();
		Member pietro = new ConcreteMember("Pietro");
		Member marco= new ConcreteMember("Marco");
		
		pietro.registerToNewsLetter(uniprNews);
		marco.registerToNewsLetter(uniprNews);
		
		uniprNews.postNew(new NewImpl("Auguri di buon Natale", "Il rettore augura a tutti un sereno Natale"));
		uniprNews.postNew(new NewImpl("Auguri di buon anno nuovo", "Il rettore augura a tutti un buon anno nuovo!!"));
	}
}
