package it.unipr.informatica.design_patterns.behavioral.iterator.complete_binary_tree_to_end;


public class CompleteBinaryTreeImp<T> implements CompleteBinaryTree<T>{
	
	private CompleteBinaryTreeNode<T> root_;
	private BFSIterator<T> iter_;
	
	
	public CompleteBinaryTreeImp(CompleteBinaryTreeNode<T> root) {
		this.root_ = root;
		this.iter_ = new BFSIteratorImpl();
	}
	
	@Override
	public CompleteBinaryTreeNode<T> getRoot() {
		return root_;
	}

	@Override
	public void setRoot(CompleteBinaryTreeNode<T> node) {
		root_ = node;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CompleteBinaryTreeNode<T> next() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CompleteBinaryTreeNode<T> previous() {
		// TODO Auto-generated method stub
		return null;
	}
	

	@Override
	public CompleteBinaryTreeNode<T> begin() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return false;
	}

	private class BFSIteratorImpl implements BFSIterator<T> {
		
		private CompleteBinaryTreeNode<T> begin_;
		private CompleteBinaryTreeNode<T> current_;
		
		private BFSIteratorImpl() {}
		
		@Override
		public boolean hasNext() {
			return false;
			
		}
		
		@Override
		public CompleteBinaryTreeNode<T> next() {
			// TODO Auto-generated method stub
			return null;
		}
		
		@Override
		public CompleteBinaryTreeNode<T> previous() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public CompleteBinaryTreeNode<T> begin() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public boolean hasPrevious() {
			// TODO Auto-generated method stub
			return false;
		}
		
	}

}
