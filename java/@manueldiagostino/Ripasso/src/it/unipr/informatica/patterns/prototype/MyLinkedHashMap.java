package it.unipr.informatica.patterns.prototype;

import java.util.LinkedHashMap;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public class MyLinkedHashMap extends HashMap {
	private LinkedHashMap<Object, Object> hashMap = new LinkedHashMap();
    
	@Override
	public Object clone() throws CloneNotSupportedException{
	    MyLinkedHashMap myLinkedHashMap = (MyLinkedHashMap) super.clone();
	    myLinkedHashMap.hashMap = new LinkedHashMap<Object, Object>();
	    
//	    for (Object obj : hashMap.keySet()) {
//	    	myLinkedHashMap.hashMap.put(obj.clone(), hashMap.get(obj).clone());
//		}
	    
	    return myLinkedHashMap;
	}
    
    @Override
    public void addItem(Object key, Object value) {
    	hashMap.put(key, value);
    }

    @Override
    public int getSize() {
        return hashMap.size();
    }
    
    public LinkedHashMap<Object, Object> getMap() {
    	return this.hashMap;
    }
}
