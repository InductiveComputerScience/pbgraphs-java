package Trees.Trees;

public class Trees{
	public static double TreeHeight(Tree tree){
		double height, i, branchHeight;
		boolean heightSet;

		heightSet = false;
		height = 0d;

		for(i = 0d; i < tree.branches.length; i = i + 1d){
			branchHeight = TreeHeight(tree.branches[(int)(i)]);
			if(!heightSet){
				height = branchHeight;
				heightSet = true;
			}else if(branchHeight > height){
				height = branchHeight;
			}
		}

		if(tree.branches.length == 0d){
			height = 0d;
		}else{
			height = height + 1d;
		}

		return height;
	}

	public static double TreeNumberOfNodes(Tree tree){
		double nodes, i;

		nodes = 0d;

		for(i = 0d; i < tree.branches.length; i = i + 1d){
			nodes = nodes + TreeNumberOfNodes(tree.branches[(int)(i)]);
		}

		return nodes + 1d;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
