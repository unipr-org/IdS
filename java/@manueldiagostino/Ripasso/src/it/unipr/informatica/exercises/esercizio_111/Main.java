package it.unipr.informatica.exercises.esercizio_111;
import java.util.Collection;

import javax.swing.plaf.synth.SynthOptionPaneUI;

import it.unipr.informatica.exercises.esercizio_111.Abstractions.*;
import it.unipr.informatica.exercises.esercizio_111.Implementations.Country;
import it.unipr.informatica.exercises.esercizio_111.Implementations.District;
import it.unipr.informatica.exercises.esercizio_111.Implementations.Leaf;
import it.unipr.informatica.exercises.esercizio_111.Implementations.NodeType;
import it.unipr.informatica.exercises.esercizio_111.Implementations.Region;
import it.unipr.informatica.exercises.esercizio_111.Iterators.Iterator;
import it.unipr.informatica.exercises.esercizio_111.Iterators.NodeBreadthFirstIterator;
import it.unipr.informatica.exercises.esercizio_111.Iterators.NodeDeepFirstIterator;
import it.unipr.informatica.exercises.esercizio_111.Iterators.NodeSpecificTypeIterator;
import it.unipr.informatica.exercises.esercizio_111.Visitors.*;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class Main {
	public void test01() {
Node root = new Country("Country 1");
		
		for (int i=0; i<10; ++i) {
			if (root.getComposite() != null) {
				NodeComposite nodeC = (NodeComposite)(root);
				if (i!=5)
					nodeC.add(new Leaf("Leaf " + i));
				else
					nodeC.add(new District("District " + i));
			}
		}

		System.out.println(root);
		NodeComposite nodeC;
		
		if (root.getComposite() != null)
			nodeC = (NodeComposite)(root);
		else return;
		
		Collection<Node> children = nodeC.getChildren();
		System.out.println("\nRoot's children:");
		for (Node n : children)
			System.out.println(n);
	}
	
	public void testDeepVisit() {
		NodeComposite root = new Country("Italia");
		NodeComposite abruzzo = new Region("Abruzzo");
		NodeComposite calabria = new Region("Calabria");
		NodeComposite emilia = new Region("Emilia Romagna");
		root.add(abruzzo);
		root.add(calabria);
		root.add(emilia);
		
		abruzzo.add(new Leaf("Teramo"));
		abruzzo.add(new Leaf("Chieti"));
		abruzzo.add(new Leaf("Pescara"));
		abruzzo.add(new Leaf("L'Aquila"));
		
		emilia.add(new Leaf("Parma"));
		
		calabria.add(new Leaf("Catanzaro"));
		calabria.add(new Leaf("Catanzaro Lido"));
	
		System.out.println("Albero:");
		Iterator it = new NodeDeepFirstIterator(root);
		while (!it.isDone()) {
			System.out.println(it.next());
		}
		
		calabria.remove("Catanzaro");
		System.out.println("\nAlbero post distruzione di Catanzaro:");
		it = new NodeDeepFirstIterator(root);
		while (!it.isDone()) {
			System.out.println(it.next());
		}
		
		System.out.println("\nAlbero parziale (Abruzzo):");
		it = new NodeDeepFirstIterator(abruzzo);
		while (!it.isDone()) {
			System.out.println(it.next());
		}
		
		System.out.println("\nAlbero con solo una foglia (Bologna):");
		Node bologna = new Leaf("Bologna");
		it = new NodeDeepFirstIterator(bologna);
		while (!it.isDone()) {
			System.out.println(it.next());
		}
		
	}
	
	public void testBreadthVisit() {
		NodeComposite root = new Country("Italia");
		NodeComposite abruzzo = new Region("Abruzzo");
		NodeComposite calabria = new Region("Calabria");
		NodeComposite emilia = new Region("Emilia Romagna");
		root.add(abruzzo);
		root.add(calabria);
		root.add(emilia);
		
		abruzzo.add(new Leaf("Teramo"));
		abruzzo.add(new Leaf("Chieti"));
		abruzzo.add(new Leaf("Pescara"));
		abruzzo.add(new Leaf("L'Aquila"));
		
		emilia.add(new Leaf("Parma"));
		
		calabria.add(new Leaf("Catanzaro"));
		calabria.add(new Leaf("Catanzaro Lido"));
	
		System.out.println("Albero:");
		Iterator it = new NodeBreadthFirstIterator(root);
		while (!it.isDone()) {
			System.out.println(it.next());
		}
		
		calabria.remove("Catanzaro");
		System.out.println("\nAlbero post distruzione di Catanzaro:");
		it = new NodeBreadthFirstIterator(root);
		while (!it.isDone()) {
			System.out.println(it.next());
		}
		
		System.out.println("\nAlbero parziale (Abruzzo):");
		it = new NodeBreadthFirstIterator(abruzzo);
		while (!it.isDone()) {
			System.out.println(it.next());
		}
		
		System.out.println("\nAlbero con solo una foglia (Bologna):");
		Node bologna = new Leaf("Bologna");
		it = new NodeBreadthFirstIterator(bologna);
		while (!it.isDone()) {
			System.out.println(it.next());
		}	
	}
	
	public void testSpecificNodeType() {
		NodeComposite root = new Country("Italia");
		NodeComposite abruzzo = new Region("Abruzzo");
		NodeComposite calabria = new Region("Calabria");
		NodeComposite emilia = new Region("Emilia Romagna");
		root.add(abruzzo);
		root.add(calabria);
		root.add(emilia);
		
		abruzzo.add(new Leaf("Teramo"));
		abruzzo.add(new Leaf("Chieti"));
		abruzzo.add(new Leaf("Pescara"));
		abruzzo.add(new Leaf("L'Aquila"));
		
		emilia.add(new Leaf("Parma"));
		
		calabria.add(new Leaf("Catanzaro"));
		calabria.add(new Leaf("Catanzaro Lido"));
	
		System.out.println("Elementi di tipo LEAF:");
		Iterator it = new NodeSpecificTypeIterator(root, NodeType.LEAF);
		while (!it.isDone()) {
			System.out.println(it.next());
		}
		
		System.out.println("Elementi di tipo REGION:");
		it = new NodeSpecificTypeIterator(root, NodeType.REGION);
		while (!it.isDone()) {
			System.out.println(it.next());
		}
	}
	
	public void testCategoryVisitor() {
		NodeComposite root = new Country("Italia");
		NodeComposite abruzzo = new Region("Abruzzo");
		NodeComposite calabria = new Region("Calabria");
		NodeComposite emilia = new Region("Emilia Romagna");
		root.add(abruzzo);
		root.add(calabria);
		root.add(emilia);
		
		abruzzo.add(new Leaf("Teramo"));
		abruzzo.add(new Leaf("Chieti"));
		abruzzo.add(new Leaf("Pescara"));
		abruzzo.add(new Leaf("L'Aquila"));
		
		emilia.add(new Leaf("Parma"));
		
		calabria.add(new Leaf("Catanzaro"));
		calabria.add(new Leaf("Catanzaro Lido"));
		
		CategoryVisitor categoryVisitor = new CategoryVisitor();
		PrintVisitor printVisitor = new PrintVisitor();
		
		System.out.println("Albero:");
		Iterator it = new NodeBreadthFirstIterator(root);
		
		while (!it.isDone()) {
			Node node = (Node) it.next();
			node.accept(printVisitor);
			node.accept(categoryVisitor);
		}
		
		System.out.println("LEAFS: " + categoryVisitor.leafs);
		System.out.println("DISTRICTS: " + categoryVisitor.districts);
		System.out.println("REGIONS: " + categoryVisitor.regions);
		System.out.println("COUNTRIES: " + categoryVisitor.countries);
	}
	
	public static void main(String[] args) {
		new Main().testCategoryVisitor();
	}
}
