package exam_2023_02_07.lab.it.unipr.informatica.exam.abstracts;

public abstract class Message {
	private int content_;
	protected Message(int content) {
		this.content_ = content;
	}
	public int getContent() {
		return this.content_;
	}
}
