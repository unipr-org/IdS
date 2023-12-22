package it.unipr.informatica.design_patterns.behavioral.iterator.complete_binary_tree_to_end;

public class CompleteBinaryTreeNodeImpl<T> implements CompleteBinaryTreeNode<T>{
	
	private T value_;
	private CompleteBinaryTreeNode<T> left_;
	private CompleteBinaryTreeNode<T> right_;
	
	public CompleteBinaryTreeNodeImpl(T value) {
		this.value_ = value;
		this.left_ = null;
		this.right_ = null;
	}
	
	@Override
	public T getValue() {
		return this.value_;
	}

	@Override
	public void setValue(T val) {
		this.value_ = val;
	}

	@Override
	public void addLeft(CompleteBinaryTreeNode<T> node) {
		if (this.left_ != null)
			throw new IllegalMonitorStateException("left_ != null");
		
		this.left_ = node;
	}

	@Override
	public void addRight(CompleteBinaryTreeNode<T> node) {
		if (this.right_ != null)
			throw new IllegalMonitorStateException("right_ != null");
		
		this.right_ = node;
	}
	
}
