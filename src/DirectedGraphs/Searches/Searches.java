package DirectedGraphs.Searches;

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


import static DirectedGraphs.SpanningTree.SpanningTree.*;

import DirectedGraphs.ShortestPaths.*;
import static DirectedGraphs.ShortestPaths.ShortestPaths.*;

import DirectedGraphs.DirectedGraphs.*;
import static DirectedGraphs.DirectedGraphs.DirectedGraphs.*;

import static DirectedGraphs.Trees.Trees.*;

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
