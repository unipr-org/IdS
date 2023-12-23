package it.unipr.informatica.design_patterns.behavioral.iterator.complete_binary_tree_to_end;

public interface CompleteBinaryTreeNode<T> {
	public T getValue();
	public void setValue(T val);
	public void addLeft(CompleteBinaryTreeNode<T> node);
	public void addRight(CompleteBinaryTreeNode<T> node);
}
