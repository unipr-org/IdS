package exam_2023_02_07.lab.concrete;

import exam_2023_02_07.lab.abstracts.Message;

public class MessageFactory {
	private volatile static MessageFactory INSTANCE = null;
	private MessageFactory() {
		
	}
	public static MessageFactory getInstance() {
		if(INSTANCE == null)
			synchronized (MessageFactory.class) {
				if(INSTANCE == null)
					INSTANCE = new MessageFactory();
			}
		return INSTANCE;
	}
	public Message makeMessage(int value) {
		return new MessageImpl(value);
	}

}
