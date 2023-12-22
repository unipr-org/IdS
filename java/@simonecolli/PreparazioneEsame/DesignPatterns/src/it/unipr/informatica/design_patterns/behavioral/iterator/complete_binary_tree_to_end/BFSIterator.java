package it.unipr.informatica.design_patterns.behavioral.iterator.complete_binary_tree_to_end;

public interface BFSIterator<T>{
	public CompleteBinaryTreeNode<T> begin();
	public boolean hasNext();
	public boolean hasPrevious();
	public CompleteBinaryTreeNode<T> next();
	public CompleteBinaryTreeNode<T> previous();
}
