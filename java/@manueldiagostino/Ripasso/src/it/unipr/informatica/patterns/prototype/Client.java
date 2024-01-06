package it.unipr.informatica.patterns.prototype;

import java.util.LinkedHashMap;

import javax.swing.plaf.synth.SynthOptionPaneUI;

/**
 * @author Di Agostino Manuel https://github.com/manueldiagostino
 */
public class Client {
	public static void main(String[] args) throws CloneNotSupportedException {
		MyLinkedHashMap map = new MyLinkedHashMap();
		map.addItem("key1", new MutableObject(0));

		System.out.println("Prototype");
		System.out.println("ClassName: " + map.getClass().getCanonicalName());
		System.out.println("ClassHashCode:" + map.hashCode());

		MyLinkedHashMap mapCloned = (MyLinkedHashMap) map.clone();
		System.out.println("Clone:");
		System.out.println("ClassName: " + mapCloned.getClass().getCanonicalName());
		System.out.println("ClassHashCode:" + mapCloned.hashCode());

		System.out.println("Prototype Hashtable size: " + map.getSize());
		System.out.println("Cloned Hashtable size: " + mapCloned.getSize());

		System.out.println("Adding new key");
		map.addItem("key2", new MutableObject(0));

		System.out.println("Prototype Hashtable size: " + map.getSize());
		System.out.println("Cloned Hashtable size: " + mapCloned.getSize());

		LinkedHashMap<Object, Object> internalMap = map.getMap();
		LinkedHashMap<Object, Object> internalMapCloned = mapCloned.getMap();

		MutableObject m = (MutableObject) internalMap.get("key1");
		MutableObject mCloned = (MutableObject) internalMapCloned.get("key1");
		System.out.println("\ncontenuto di value del prototype:" + m.data);
		System.out.println("contenuto di value del cloned:" + mCloned.data);

		System.out.println("\nmodifico value nel prototype");
		m.data = 7;

		System.out.println("\ncontenuto di value del prototype:" + m.data);
		System.out.println("contenuto di value del cloned:" + mCloned.data);
	}
} // ! Client
