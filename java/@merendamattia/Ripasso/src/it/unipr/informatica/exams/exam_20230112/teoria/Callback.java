package it.unipr.informatica.exams.exam_20230112.teoria;

public interface Callback<T> {
	public void onSuccess(T result);
	public default void onFailure(Throwable t) {
		t.printStackTrace();
	}
}
