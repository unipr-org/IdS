package it.unipr.informatica.design_patterns.structural.proxy.file;

import java.io.FileInputStream;

public class RealFileHandler extends FileHandler {

	private byte[] content_;
	
	public RealFileHandler(String fileName) {
		super(fileName);
		System.out.println("creating a real handler with file content");
		FileInputStream file;
		try {
			file = new FileInputStream(fileName);
			int numBytes = file.available();
			content_ = new byte[numBytes];
			file.read();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public String getContent() {
		return new String(content_);
	}
	
//	https://stackoverflow.com/questions/33208518/difference-between-0x0a-and-0x0d
	@Override
	public String getLine(int requestedLine) {
		System.out.println("accessing from real handler");
		int numBytes = content_.length;
		int currentLine = 0;
		int startingPos = -1;
		int lineLength = 0;
		
		for(int i=0;i<numBytes; ++i) {
			if(
					(currentLine == requestedLine) &&
					(content_[i] != 0x0A )
			){
				if(startingPos == -1)
					startingPos = i;
				++lineLength;
			}
			
			if(content_[i] == 0x0D)
				currentLine++;
		}
		String lineText = "";
		if(startingPos != -1)
			lineText = new String( content_, startingPos, lineLength-1 );
		return "\"" + lineText + "\"";
	}

}
