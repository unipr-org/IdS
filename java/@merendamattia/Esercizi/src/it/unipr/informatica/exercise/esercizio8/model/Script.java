package it.unipr.informatica.exercise.esercizio8.model;

import java.util.Iterator;

public interface Script extends GlobalExpr {
	public Iterator<GlobalExpr> iterator();
}
