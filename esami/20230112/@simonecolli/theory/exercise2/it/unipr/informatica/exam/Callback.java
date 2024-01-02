package exam_2023_01_12.theory.exercise2.it.unipr.informatica.exam;

public interface Callback<T> {
	public void onSuccess(T val);
	public default void onFailure(Throwable t) {
		throw new RuntimeException(t.getMessage());
	}
}
