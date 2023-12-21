package it.unipr.informatica.design_patterns.structural.proxy.file;

// la distinzione principale tra proxy e decorator sta nel fatto che
// l'obbiettivo è quello di ridurre l'utilizzo delle risorse utilizzate
public class ProxyFileHandler extends FileHandler{
	
	private RealFileHandler realFileHandler_;
	private int lineNum_;
	private String textLine_;
	
	public ProxyFileHandler(String fileName) {
		super(fileName);
		System.out.println("creating a peoxy handler without file content");
		this.realFileHandler_ = null;
		this.lineNum_ = -1;
		this.textLine_ = "";
	}

	@Override
	public String getContent() {
		if(realFileHandler_ == null)
			realFileHandler_ = new RealFileHandler(super.fileName_);
		return realFileHandler_.getContent();
	}

	@Override
	public String getLine(int requestedLine) {
		if(lineNum_ == requestedLine) {
			System.out.println("accessing from proxy cache");
			return textLine_;
		}
		if(realFileHandler_ == null)
			realFileHandler_ = new RealFileHandler(super.fileName_);
		
//		crezione cache del proxy
		textLine_ = realFileHandler_.getLine(requestedLine);
		lineNum_ = requestedLine;
		
		return textLine_;
			
	}

}
