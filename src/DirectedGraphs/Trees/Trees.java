package DirectedGraphs.Trees;

import Trees.Trees.Forest;
import Trees.Trees.Tree;
import lists.LinkedListNumbers.LinkedListNodeNumbers;
import lists.LinkedListNumbers.LinkedListNumbers;

import static lists.LinkedListNumbersComputations.LinkedListNumbersComputations.LinkedListNumbersLength;

public class Trees{
	public static void ConvertPreviousListToForest(Forest forest, double [] prev){
		double length, i, j, root;
		boolean found;

		length = 0d;
		for(i = 0d; i < prev.length; i = i + 1d){
			if(prev[(int)(i)] == i){
				length = length + 1d;
			}
		}

		forest.trees = new Tree [(int)(length)];

		j = 0d;
		for(i = 0d; i < length; i = i + 1d){
			/* Find next root.*/
			root = 0d;
			found = false;
			for(; j < prev.length && !found; j = j + 1d){
				if(prev[(int)(j)] == j){
					root = j;
					found = true;
				}
			}

			/* Create tree from root.*/
			forest.trees[(int)(i)] = ConvertPreviousListToTree(root, prev);
		}
	}

	public static Tree ConvertPreviousListToTree(double root, double [] prev){
		Tree tree;
		double branches, i, branch;

		tree = new Tree();
		tree.label = root;

		/* Count branches.*/
		branches = 0d;
		for(i = 0d; i < prev.length; i = i + 1d){
			if(prev[(int)(i)] == root && root != i){
				branches = branches + 1d;
			}
		}

		/* Add branches.*/
		tree.branches = new Tree [(int)(branches)];
		branch = 0d;
		for(i = 0d; i < prev.length; i = i + 1d){
			if(prev[(int)(i)] == root && root != i){
				tree.branches[(int)(branch)] = ConvertPreviousListToTree(i, prev);
				branch = branch + 1d;
			}
		}

		return tree;
	}

	public static void ConvertLinkedListTreesToForest(Forest forest, LinkedListNumbers roots, LinkedListNumbers [] trees){
		LinkedListNodeNumbers node;
		double length, current;

		node = roots.first;
		length = LinkedListNumbersLength(roots);
		forest.trees = new Tree [(int)(length)];

		for(current = 0d; !node.end; current = current + 1d){
			forest.trees[(int)(current)] = ConvertLinkedListTreeToTree(node.value, trees);
			node = node.next;
		}
	}

	public static Tree ConvertLinkedListTreeToTree(double root, LinkedListNumbers [] trees){
		Tree tree;
		LinkedListNumbers rootList;
		LinkedListNodeNumbers node;
		double current, length;

		rootList = trees[(int)(root)];

		tree = new Tree();
		tree.label = root;
		length = LinkedListNumbersLength(rootList);
		tree.branches = new Tree [(int)(length)];

		node = rootList.first;

		for(current = 0d; !node.end; current = current + 1d){
			tree.branches[(int)(current)] = ConvertLinkedListTreeToTree(node.value, trees);
			node = node.next;
		}

		return tree;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
