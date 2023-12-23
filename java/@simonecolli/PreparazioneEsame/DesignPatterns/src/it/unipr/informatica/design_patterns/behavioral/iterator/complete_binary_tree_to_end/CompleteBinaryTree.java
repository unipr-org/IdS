package it.unipr.informatica.design_patterns.behavioral.iterator.complete_binary_tree_to_end;

public interface CompleteBinaryTree<T> extends BFSIterator<T> {
	public CompleteBinaryTreeNode<T> getRoot();
	public void setRoot(CompleteBinaryTreeNode<T> node);
}
