package it.unipr.informatica.design_patterns.structural.composite.component;

// client
public class Main {
	
	public static void main(String[] args) {
		Component root = new CompoundPart("Computer");
		Component io = new CompoundPart("IO");
		Component coreSystem = new CompoundPart("coreSystem");
		
		try {
			io.add(new SinglePart("Monitor"));
			io.add(new SinglePart("Mouse"));
			io.add(new SinglePart("keybord"));
			coreSystem.add(new SinglePart("cpu"));
			coreSystem.add(new SinglePart("ram"));
			coreSystem.add(new SinglePart("ssd"));
			
			root.add(io);
			root.add(coreSystem);
		} catch (SinglePartException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(root.toString());
	}
}
