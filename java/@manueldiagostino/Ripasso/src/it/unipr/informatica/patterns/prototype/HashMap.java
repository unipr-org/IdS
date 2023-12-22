package it.unipr.informatica.patterns.prototype;

/**
 * @author Di Agostino Manuel
 * https://github.com/manueldiagostino
 */
public abstract class HashMap implements Cloneable {
    @Override
    public Object clone() throws CloneNotSupportedException{
        return super.clone();
    }
 
    public abstract void addItem(Object key, Object value);
 
    public abstract int getSize();
}
