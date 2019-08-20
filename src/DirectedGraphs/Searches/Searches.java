package DirectedGraphs.Searches;

import DirectedGraphs.DirectedGraphs.DirectedGraph;
import DirectedGraphs.DirectedGraphs.Edge;
import DirectedGraphs.DirectedGraphs.Node;
import lists.DynamicArrayNumbers.DynamicArrayNumbers;
import lists.LinkedListNumbers.LinkedListNumbers;
import references.references.NumberArrayReference;

import static arrays.arrays.arrays.CreateBooleanArray;
import static lists.DynamicArrayNumbersComputations.DynamicArrayNumbersComputations.*;
import static lists.LinkedListNumbersComputations.LinkedListNumbersComputations.*;

public class Searches{
	public static void DepthFirstSearch(DirectedGraph g, double start, NumberArrayReference list){
		boolean [] visited;
		LinkedListNumbers ll;

		visited = CreateBooleanArray(g.nodes.length, false);
		ll = CreateLinkedListNumbers();

		DepthFirstSearchRecursive(g, g.nodes[(int)(start)], start, visited, ll);

		list.numberArray = LinkedListNumbersToArray(ll);
		FreeLinkedListNumbers(ll);
	}

	public static void DepthFirstSearchRecursive(DirectedGraph g, Node node, double nodeNr, boolean [] visited, LinkedListNumbers list){
		double i;
		Edge e;

		visited[(int)(nodeNr)] = true;

		LinkedListAddNumber(list, nodeNr);

		for(i = 0d; i < node.edge.length; i = i + 1d){
			e = node.edge[(int)(i)];
			if(!visited[(int)(e.nodeNr)]){
				DepthFirstSearchRecursive(g, g.nodes[(int)(e.nodeNr)], e.nodeNr, visited, list);
			}
		}
	}

	public static void BreadthFirstSearch(DirectedGraph g, double start, NumberArrayReference list){
		boolean [] visited;
		double i, front, v, length;
		Edge e;
		Node n;
		DynamicArrayNumbers da;

		da = CreateDynamicArrayNumbers();
		visited = CreateBooleanArray(g.nodes.length, false);
		length = 0d;
		front = 0d;

		visited[(int)(start)] = true;

		DynamicArrayAddNumber(da, start);
		length = length + 1d;

		for(; front != length; ){
			v = DynamicArrayNumbersIndex(da, front);
			front = front + 1d;

			n = g.nodes[(int)(v)];

			for(i = 0d; i < n.edge.length; i = i + 1d){
				e = n.edge[(int)(i)];
				if(!visited[(int)(e.nodeNr)]){
					visited[(int)(e.nodeNr)] = true;

					DynamicArrayAddNumber(da, e.nodeNr);
					length = length + 1d;
				}
			}
		}

		list.numberArray = DynamicArrayNumbersToArray(da);
		FreeDynamicArrayNumbers(da);
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
