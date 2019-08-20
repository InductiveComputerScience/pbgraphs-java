package DirectedGraphs.SpanningTree;

import static java.lang.Math.*;

import static arrays.arrays.arrays.*;

import static math.math.math.*;

import references.references.*;
import static references.references.references.*;

import PriorityQueue.PriorityQueueBTNumbersCs.*;
import static PriorityQueue.PriorityQueueBTNumbersCs.PriorityQueueBTNumbersCs.*;

import PriorityQueue.PriorityQueueBTNumKeyValueCs.*;
import static PriorityQueue.PriorityQueueBTNumKeyValueCs.PriorityQueueBTNumKeyValueCs.*;

import static lists.LinkedListNumbersComputations.LinkedListNumbersComputations.*;

import static lists.DynamicArrayNumbersComputations.DynamicArrayNumbersComputations.*;

import static lists.NumberList.NumberList.*;

import static lists.StringList.StringList.*;

import static lists.BooleanList.BooleanList.*;

import lists.LinkedListNumbers.*;

import lists.DynamicArrayNumbers.*;

import static lists.CharacterList.CharacterList.*;

import Trees.Trees.*;
import static Trees.Trees.Trees.*;


import static DirectedGraphs.Searches.Searches.*;

import DirectedGraphs.ShortestPaths.*;
import static DirectedGraphs.ShortestPaths.ShortestPaths.*;

import DirectedGraphs.DirectedGraphs.*;
import static DirectedGraphs.DirectedGraphs.DirectedGraphs.*;

import static DirectedGraphs.Trees.Trees.*;

public class SpanningTree{
	public static boolean PrimsAlgorithmNoQueue(DirectedGraph g, Forest forest){
		boolean valid, found, minimumSet;
		boolean [] inMST;
		double i, j, root, minimum, minimumTarget, minimumSource, nodesCompleted;
		Node node;
		Edge edge;
		LinkedListNumbers [] linkedListTrees;
		LinkedListNumbers roots;

		valid = DirectedGraphIsValid(g) && IsUndirected(g);

		if(valid){
			inMST = CreateBooleanArray(g.nodes.length, false);
			nodesCompleted = 0d;
			linkedListTrees = CreateLinkedListNumbersArray(g.nodes.length);
			roots = CreateLinkedListNumbers();

			for(; nodesCompleted < g.nodes.length; ){

				/* Find a node not in an MST*/
				found = false;
				root = 0d;
				for(i = 0d; i < g.nodes.length && !found; i = i + 1d){
					if(!inMST[(int)(i)]){
						root = i;
						found = true;
					}
				}

				LinkedListAddNumber(roots, root);

				inMST[(int)(root)] = true;
				nodesCompleted = nodesCompleted + 1d;

				found = true;
				for(; found; ){
					/* Find minimum edge going out from existing tree.*/
					minimum = 0d;
					minimumSet = false;
					minimumTarget = 0d;
					minimumSource = 0d;
					for(i = 0d; i < g.nodes.length; i = i + 1d){
						if(inMST[(int)(i)]){
							node = g.nodes[(int)(i)];
							for(j = 0d; j < node.edge.length; j = j + 1d){
								edge = node.edge[(int)(j)];
								if(!inMST[(int)(edge.nodeNr)]){
									if(!minimumSet){
										minimum = edge.weight;
										minimumTarget = edge.nodeNr;
										minimumSource = i;
										minimumSet = true;
									}else if(edge.weight < minimum){
										minimum = edge.weight;
										minimumTarget = edge.nodeNr;
										minimumSource = i;
									}
								}
							}
						}
					}

					/* Add edge to tree.*/
					if(minimumSet){
						LinkedListAddNumber(linkedListTrees[(int)(minimumSource)], minimumTarget);
						inMST[(int)(minimumTarget)] = true;
						nodesCompleted = nodesCompleted + 1d;
						found = true;
					}else{
						found = false;
					}
				}
			}

			ConvertLinkedListTreesToForest(forest, roots, linkedListTrees);

			/* Free memory.*/
			delete(inMST);
			FreeLinkedListNumbersArray(linkedListTrees);
		}

		return valid;
	}

	public static boolean PrimsAlgorithm(DirectedGraph g, Forest forest){
		boolean valid, found, minimumSet, empty;
		boolean [] inMST, minimumEdgeSet;
		double i, root, minimumTarget, minimumSource, nodesCompleted;
		double [] minimumEdges, minimumSources;
		PriorityQueueBTNumKeyValue q;
		NumberReference targetReference, weightReference;
		LinkedListNumbers [] linkedListTrees;
		LinkedListNumbers roots;

		valid = DirectedGraphIsValid(g) && IsUndirected(g);

		if(valid){
			q = CreatePriorityQueueBTNumKeyValue();
			targetReference = new NumberReference();
			weightReference = new NumberReference();
			inMST = CreateBooleanArray(g.nodes.length, false);
			minimumEdgeSet = CreateBooleanArray(g.nodes.length, false);
			minimumEdges = CreateNumberArray(g.nodes.length, 0d);
			minimumSources = CreateNumberArray(g.nodes.length, 0d);
			linkedListTrees = CreateLinkedListNumbersArray(g.nodes.length);
			roots = CreateLinkedListNumbers();
			nodesCompleted = 0d;

			for(; nodesCompleted < g.nodes.length; ){
				/* Find a node not in an MST*/
				found = false;
				root = 0d;
				for(i = 0d; i < g.nodes.length && !found; i = i + 1d){
					if(!inMST[(int)(i)]){
						root = i;
						found = true;
					}
				}

				/* Record tree root.*/
				LinkedListAddNumber(roots, root);
				inMST[(int)(root)] = true;
				nodesCompleted = nodesCompleted + 1d;

				/* Add all outgoing edges to priority queue*/
				AddOutgoingEdgesToPriorityQueue(g.nodes[(int)(root)], minimumEdgeSet, root, minimumEdges, minimumSources, q);

				/* Expand tree one vertex at a time.*/
				found = true;
				for(; found; ){
					/* Find minimum edge going out from existing tree using queue.*/
					minimumSet = false;
					empty = false;
					for(; !minimumSet && !empty; ){
						empty = !PopPriorityQueueBTNumKeyValue(q, weightReference, targetReference);
						if(!empty && !inMST[(int)(targetReference.numberValue)]){
							minimumSet = true;
						}
					}

					if(minimumSet){
						/* Add edge to tree.*/
						minimumTarget = targetReference.numberValue;
						minimumSource = minimumSources[(int)(minimumTarget)];

						LinkedListAddNumber(linkedListTrees[(int)(minimumSource)], minimumTarget);
						inMST[(int)(minimumTarget)] = true;
						nodesCompleted = nodesCompleted + 1d;
						found = true;

						/* Add all outgoing edges to priority queue.*/
						AddOutgoingEdgesToPriorityQueue(g.nodes[(int)(minimumTarget)], minimumEdgeSet, minimumTarget, minimumEdges, minimumSources, q);
					}else{
						found = false;
					}
				}
			}

			ConvertLinkedListTreesToForest(forest, roots, linkedListTrees);

			/* Free memory.*/
			FreePriorityQueueBTNumKeyValue(q);
			delete(targetReference);
			delete(weightReference);
			delete(inMST);
			delete(minimumEdgeSet);
			FreeLinkedListNumbersArray(linkedListTrees);
			delete(minimumEdges);
			delete(minimumSources);
		}

		return valid;
	}

	public static void AddOutgoingEdgesToPriorityQueue(Node node, boolean [] minimumEdgeSet, double source, double [] minimumEdges, double [] minimumSources, PriorityQueueBTNumKeyValue q){
		double i, target;
		Edge edge;

		for(i = 0d; i < node.edge.length; i = i + 1d){
			edge = node.edge[(int)(i)];
			target = edge.nodeNr;
			InsertIntoPriorityQueueBTNumKeyValue(q, 1d/edge.weight, target);
			if(!minimumEdgeSet[(int)(target)]){
				minimumEdges[(int)(target)] = edge.weight;
				minimumSources[(int)(target)] = source;
				minimumEdgeSet[(int)(target)] = true;
			}else if(minimumEdges[(int)(target)] > edge.weight){
				minimumEdges[(int)(target)] = edge.weight;
				minimumSources[(int)(target)] = source;
			}
		}
	}

	public static boolean KruskalsAlgorithm(DirectedGraph g, Forest forest){
		boolean valid;
		PriorityQueueBTNumKeyValue q;
		Node node;
		Edge edge;
		double i, j, edgeNr, source, target, replace, replaceWith, candidate, trees, treeNr;
		DynamicArrayNumbers sources, targets;
		DynamicArrayNumbers edges;
		double [] memberOfTree;
		boolean [] roots;
		NumberReference edgeNrReference, weightReference;
		Tree tree;

		valid = DirectedGraphIsValid(g) && IsUndirected(g);

		if(valid){
			sources = CreateDynamicArrayNumbers();
			targets = CreateDynamicArrayNumbers();
			edges = CreateDynamicArrayNumbers();
			edgeNrReference = new NumberReference();
			weightReference = new NumberReference();
			roots = CreateBooleanArray(g.nodes.length, false);
			memberOfTree = new double [(int)(g.nodes.length)];
			for(i = 0d; i < g.nodes.length; i = i + 1d){
				memberOfTree[(int)(i)] = i;
			}

			q = CreatePriorityQueueBTNumKeyValue();

			/* Add all edges to a priority queue.*/
			edgeNr = 0d;
			for(i = 0d; i < g.nodes.length; i = i + 1d){
				node = g.nodes[(int)(i)];
				for(j = 0d; j < node.edge.length; j = j + 1d){
					edge = node.edge[(int)(j)];
					InsertIntoPriorityQueueBTNumKeyValue(q, 1d/edge.weight, edgeNr);
					DynamicArrayAddNumber(sources, i);
					DynamicArrayAddNumber(targets, edge.nodeNr);

					edgeNr = edgeNr + 1d;
				}
			}

			for(; !IsEmptyPriorityQueueBTNumKeyValue(q); ){
				PopPriorityQueueBTNumKeyValue(q, weightReference, edgeNrReference);

				source = DynamicArrayNumbersIndex(sources, edgeNrReference.numberValue);
				target = DynamicArrayNumbersIndex(targets, edgeNrReference.numberValue);

				if(memberOfTree[(int)(source)] != memberOfTree[(int)(target)]){
					replace = memberOfTree[(int)(target)];
					replaceWith = memberOfTree[(int)(source)];

					for(i = 0d; i < g.nodes.length; i = i + 1d){
						if(memberOfTree[(int)(i)] == replace){
							memberOfTree[(int)(i)] = replaceWith;
						}
					}

					DynamicArrayAddNumber(edges, edgeNrReference.numberValue);
				}
			}

			/* Built forest.*/
			trees = 0d;
			for(i = 0d; i < g.nodes.length; i = i + 1d){
				candidate = memberOfTree[(int)(i)];
				if(!roots[(int)(candidate)]){
					trees = trees + 1d;
					roots[(int)(candidate)] = true;
				}
			}
			forest.trees = new Tree [(int)(trees)];
			treeNr = 0d;
			for(i = 0d; i < g.nodes.length; i = i + 1d){
				if(roots[(int)(i)]){
					tree = CreateTreeFromEdgeCollection(i, i, edges, sources, targets);

					forest.trees[(int)(treeNr)] = tree;
					treeNr = treeNr + 1d;
				}
			}

			/* Free memory.*/
			FreePriorityQueueBTNumKeyValue(q);
			FreeDynamicArrayNumbers(sources);
			FreeDynamicArrayNumbers(targets);
			delete(edgeNrReference);
			delete(weightReference);
		}

		return valid;
	}

	public static Tree CreateTreeFromEdgeCollection(double root, double parent, DynamicArrayNumbers edges, DynamicArrayNumbers sources, DynamicArrayNumbers targets){
		Tree tree;
		double i, edgeNr, source, target, size;
		LinkedListNumbers branches;
		LinkedListNodeNumbers node;

		tree = new Tree();
		tree.label = root;
		branches = CreateLinkedListNumbers();

		size = 0d;
		for(i = 0d; i < edges.length; i = i + 1d){
			edgeNr = DynamicArrayNumbersIndex(edges, i);

			source = DynamicArrayNumbersIndex(sources, edgeNr);
			target = DynamicArrayNumbersIndex(targets, edgeNr);

			if(source == root && target != parent){
				LinkedListAddNumber(branches, target);
				size = size + 1d;
			}else if(target == root && source != parent){
				LinkedListAddNumber(branches, source);
				size = size + 1d;
			}
		}

		tree.branches = new Tree [(int)(size)];
		node = branches.first;
		for(i = 0d; i < size; i = i + 1d){
			tree.branches[(int)(i)] = CreateTreeFromEdgeCollection(node.value, root, edges, sources, targets);

			node = node.next;
		}

		return tree;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
