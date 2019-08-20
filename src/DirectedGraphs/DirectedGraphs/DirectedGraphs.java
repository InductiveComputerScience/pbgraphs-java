package DirectedGraphs.DirectedGraphs;

import lists.LinkedListNumbers.LinkedListNumbers;
import references.references.NumberArrayReference;
import references.references.NumberReference;

import static DirectedGraphs.Searches.Searches.DepthFirstSearch;
import static arrays.arrays.arrays.*;
import static lists.LinkedListNumbersComputations.LinkedListNumbersComputations.*;
import static math.math.math.IsInteger;
import static references.references.references.CreateNumberReference;

public class DirectedGraphs{
	public static Edge CreateEdge(double nodeNr, double weight){
		Edge e;

		e = new Edge();

		e.nodeNr = nodeNr;
		e.weight = weight;

		return e;
	}

	public static boolean DirectedGraphIsValid(DirectedGraph g){
		boolean valid;
		double i, j;
		Node node;
		Edge edge;

		valid = true;

		for(i = 0d; i < g.nodes.length; i = i + 1d){
			node = g.nodes[(int)(i)];
			for(j = 0d; j < node.edge.length; j = j + 1d){
				edge = node.edge[(int)(j)];
				if(IsInteger(edge.nodeNr)){
					if(edge.nodeNr >= 0d && edge.nodeNr < g.nodes.length){
					}else{
						valid = false;
					}
				}else{
					valid = false;
				}
			}
		}

		return valid;
	}

	public static boolean DirectedGraphContainsCycleDFS(DirectedGraph g){
		double [] incoming;
		double i, zeroIncomming;
		boolean cycle;

		cycle = false;
		incoming = DirectedGraphCountIncomingEdges(g);

		zeroIncomming = 0d;
		for(i = 0d; i < g.nodes.length && !cycle; i = i + 1d){
			if(incoming[(int)(i)] == 0d){
				zeroIncomming = zeroIncomming + 1d;

				cycle = DirectedGraphContainsCycleFromNodeDFS(g, i);
			}
		}

		delete(incoming);

		if(g.nodes.length > 0d && zeroIncomming == 0d){
			cycle = true;
		}

		return cycle;
	}

	public static double [] DirectedGraphCountIncomingEdges(DirectedGraph g){
		double [] incoming;
		double i, j;
		Node node;
		Edge e;

		incoming = CreateNumberArray(g.nodes.length, 0d);

		for(i = 0d; i < g.nodes.length; i = i + 1d){
			node = g.nodes[(int)(i)];
			for(j = 0d; j < node.edge.length; j = j + 1d){
				e = node.edge[(int)(j)];
				incoming[(int)(e.nodeNr)] = incoming[(int)(e.nodeNr)] + 1d;
			}
		}

		return incoming;
	}

	public static boolean DirectedGraphContainsCycleFromNodeDFS(DirectedGraph g, double nodeNr){
		boolean [] visited;
		boolean cycle;

		visited = CreateBooleanArray(g.nodes.length, false);

		cycle = DirectedGraphContainsCycleFromNodeDFSRecursive(g, nodeNr, visited);

		delete(visited);

		return cycle;
	}

	public static boolean DirectedGraphContainsCycleFromNodeDFSRecursive(DirectedGraph g, double nodeNr, boolean [] visited){
		double i;
		Edge e;
		boolean cycle;
		Node node;

		cycle = false;
		node = g.nodes[(int)(nodeNr)];

		for(i = 0d; i < node.edge.length && !cycle; i = i + 1d){
			e = node.edge[(int)(i)];
			if(visited[(int)(e.nodeNr)]){
				cycle = true;
			}else{
				visited[(int)(e.nodeNr)] = true;
				cycle = DirectedGraphContainsCycleFromNodeDFSRecursive(g, e.nodeNr, visited);
				visited[(int)(e.nodeNr)] = false;
			}
		}

		return cycle;
	}

	public static double DirectedGraphCountCyclesDFS(DirectedGraph g){
		double [] incoming;
		double i, zeroIncoming, cycleCount;

		cycleCount = 0d;
		incoming = DirectedGraphCountIncomingEdges(g);

		zeroIncoming = 0d;
		for(i = 0d; i < g.nodes.length; i = i + 1d){
			if(incoming[(int)(i)] == 0d){
				zeroIncoming = zeroIncoming + 1d;

				cycleCount = cycleCount + DirectedGraphCountCyclesFromNodeDFS(g, i);
			}
		}

		if(g.nodes.length > 0d && zeroIncoming == 0d){
			cycleCount = cycleCount + DirectedGraphCountCyclesFromNodeDFS(g, 0d);
		}

		delete(incoming);

		return cycleCount;
	}

	public static double DirectedGraphCountCyclesFromNodeDFS(DirectedGraph g, double nodeNr){
		double [] color;
		double cycleCount;

		color = CreateNumberArray(g.nodes.length, 0d);

		cycleCount = DirectedGraphCountCyclesFromNodeDFSRecursive(g, nodeNr, color);

		delete(color);

		return cycleCount;
	}

	public static double DirectedGraphCountCyclesFromNodeDFSRecursive(DirectedGraph g, double nodeNr, double [] color){
		double i, cycleCount;
		Edge e;
		Node node;

		cycleCount = 0d;
		node = g.nodes[(int)(nodeNr)];

		color[(int)(nodeNr)] = 1d;

		for(i = 0d; i < node.edge.length; i = i + 1d){
			e = node.edge[(int)(i)];

			if(color[(int)(e.nodeNr)] != 2d){
				if(color[(int)(e.nodeNr)] == 1d){
					cycleCount = cycleCount + 1d;
				}else{
					cycleCount = cycleCount + DirectedGraphCountCyclesFromNodeDFSRecursive(g, e.nodeNr, color);
				}
			}
		}

		color[(int)(nodeNr)] = 2d;

		return cycleCount;
	}

	public static Cycle [] DirectedGraphGetCyclesDFS(DirectedGraph g){
		double cycleCount;
		Cycle [] cycles;
		double [] incoming;
		double i, zeroIncoming;
		NumberReference cycleNumber;

		cycleNumber = CreateNumberReference(0d);
		cycleCount = DirectedGraphCountCyclesDFS(g);

		cycles = new Cycle [(int)(cycleCount)];

		incoming = DirectedGraphCountIncomingEdges(g);

		zeroIncoming = 0d;
		for(i = 0d; i < g.nodes.length; i = i + 1d){
			if(incoming[(int)(i)] == 0d){
				zeroIncoming = zeroIncoming + 1d;

				DirectedGraphGetCyclesFromNodeDFS(g, i, cycles, cycleNumber);
			}
		}

		if(g.nodes.length > 0d && zeroIncoming == 0d){
			DirectedGraphGetCyclesFromNodeDFS(g, 0d, cycles, cycleNumber);
		}

		delete(incoming);

		return cycles;
	}

	public static void DirectedGraphGetCyclesFromNodeDFS(DirectedGraph g, double nodeNr, Cycle [] cycles, NumberReference cycleNumber){
		double [] color, cycleMark;
		double [] previous;
		double previousLength;

		color = CreateNumberArray(g.nodes.length, 0d);
		cycleMark = CreateNumberArray(g.nodes.length, 0d);
		previous = CreateNumberArray(g.nodes.length, 0d);
		previousLength = 0d;

		DirectedGraphGetCyclesFromNodeDFSRecursive(g, nodeNr, cycleNumber, color, cycles, previous, previousLength);

		delete(color);
		delete(cycleMark);
	}

	public static void DirectedGraphGetCyclesFromNodeDFSRecursive(DirectedGraph g, double nodeNr, NumberReference cycleNumber, double [] color, Cycle [] cycles, double [] previous, double previousLength){
		double i, j, current, cycleLength, next;
		Edge e;
		Node node;
		boolean done;

		node = g.nodes[(int)(nodeNr)];

		color[(int)(nodeNr)] = 1d;

		previous[(int)(previousLength)] = nodeNr;

		for(i = 0d; i < node.edge.length; i = i + 1d){
			e = node.edge[(int)(i)];
			if(color[(int)(e.nodeNr)] != 2d){
				if(color[(int)(e.nodeNr)] == 1d){
					/* Get cycle length*/
					cycleLength = 0d;
					done = false;
					current = previousLength;
					for(; !done; ){
						cycleLength = cycleLength + 1d;
						if(previous[(int)(current)] == e.nodeNr){
							done = true;
						}
						current = current - 1d;
					}

					/* Get cycle in order*/
					cycles[(int)(cycleNumber.numberValue)] = new Cycle();
					cycles[(int)(cycleNumber.numberValue)].nodeNrs = new double [(int)(cycleLength)];
					for(j = 0d; j < cycleLength; j = j + 1d){
						next = previousLength - cycleLength + 1d + j;
						cycles[(int)(cycleNumber.numberValue)].nodeNrs[(int)(j)] = previous[(int)(next)];
					}

					cycleNumber.numberValue = cycleNumber.numberValue + 1d;
				}else{
					DirectedGraphGetCyclesFromNodeDFSRecursive(g, e.nodeNr, cycleNumber, color, cycles, previous, previousLength + 1d);
				}
			}
		}

		color[(int)(nodeNr)] = 2d;
	}

	public static DirectedGraph CreateDirectedGraph(double nodes){
		DirectedGraph directedGraph;
		double i;

		directedGraph = new DirectedGraph();
		directedGraph.nodes = new Node [(int)(nodes)];

		for(i = 0d; i < nodes; i = i + 1d){
			directedGraph.nodes[(int)(i)] = new Node();
		}

		return directedGraph;
	}

	public static DirectedGraph CreateDirectedGraphFromMatrixForm(DirectedGraphMatrix m){
		DirectedGraph g;
		double nodes, i, j, order, edgeValue, edgeNr;

		nodes = GetNodesFromDirectedGraphMatrix(m);

		g = CreateDirectedGraph(nodes);

		for(i = 0d; i < nodes; i = i + 1d){
			order = GetNodeOrderFromMatrixForm(m, i);
			g.nodes[(int)(i)].edge = new Edge [(int)(order)];
			edgeNr = 0d;
			for(j = 0d; j < nodes; j = j + 1d){
				edgeValue = GetDirectedGraphMatrixEdge(m, i, j);
				if(edgeValue != 0d){
					g.nodes[(int)(i)].edge[(int)(edgeNr)] = CreateEdge(j, edgeValue);
					edgeNr = edgeNr + 1d;
				}
			}
		}

		return g;
	}

	public static double GetDirectedGraphMatrixEdge(DirectedGraphMatrix m, double nodeNr, double edgeNr){
		return m.c[(int)(nodeNr)].r[(int)(edgeNr)];
	}

	public static double GetNodeOrderFromMatrixForm(DirectedGraphMatrix m, double nodeNr){
		double nodes, i, order;

		nodes = GetNodesFromDirectedGraphMatrix(m);

		order = 0d;
		for(i = 0d; i < nodes; i = i + 1d){
			order = order + m.c[(int)(nodeNr)].r[(int)(i)];
		}

		return order;
	}

	public static double GetNodesFromDirectedGraphMatrix(DirectedGraphMatrix m){
		return m.c.length;
	}

	public static DirectedGraphMatrix CreateDirectedGraphMatrixFromListForm(DirectedGraph g){
		DirectedGraphMatrix m;
		double i, j, nodes;
		Node node;
		Edge edge;

		nodes = g.nodes.length;
		m = CreateDirectedGraphMatrix(nodes);

		for(i = 0d; i < nodes; i = i + 1d){
			node = g.nodes[(int)(i)];
			for(j = 0d; j < node.edge.length; j = j + 1d){
				edge = node.edge[(int)(j)];
				m.c[(int)(i)].r[(int)(edge.nodeNr)] = edge.weight;
			}
		}

		return m;
	}

	public static DirectedGraphMatrix CreateDirectedGraphMatrix(double nodes){
		DirectedGraphMatrix m;
		double i;
		m = new DirectedGraphMatrix();

		m.c = new DirectedGraphMatrixColumn [(int)(nodes)];
		for(i = 0d; i < nodes; i = i + 1d){
			m.c[(int)(i)] = new DirectedGraphMatrixColumn();
			m.c[(int)(i)].r = CreateNumberArray(nodes, 0d);
		}

		return m;
	}

	public static boolean DirectedGraphsEqual(DirectedGraph a, DirectedGraph b){
		boolean equal, found, done;
		double nodes, foundCount, i, j, k, edges;
		Edge edgeA, edgeB;

		equal = true;

		if(a.nodes.length == b.nodes.length){
			nodes = a.nodes.length;

			done = false;
			for(i = 0d; i < nodes && !done; i = i + 1d){
				if(GetEdgesForNodeFromDirectedGraph(a, i) == GetEdgesForNodeFromDirectedGraph(b, i)){
					edges = GetEdgesForNodeFromDirectedGraph(a, i);
					foundCount = 0d;
					for(j = 0d; j < edges && !done; j = j + 1d){
						found = false;
						for(k = 0d; k < edges && !found; k = k + 1d){
							edgeA = GetEdgeFromDirectedGraph(a, i, j);
							edgeB = GetEdgeFromDirectedGraph(b, i, k);
							if(edgeA.nodeNr == edgeB.nodeNr){
								if(edgeA.weight == edgeB.weight){
									found = true;
								}
							}
						}
						if(found){
							foundCount = foundCount + 1d;
						}else{
							equal = false;
							done = true;
						}
					}

					if(foundCount == edges){
					}else{
						equal = false;
						done = true;
					}
				}else{
					equal = false;
					done = true;
				}
			}
		}else{
			equal = false;
		}

		return equal;
	}

	public static double GetEdgesForNodeFromDirectedGraph(DirectedGraph g, double nodeNr){
		return g.nodes[(int)(nodeNr)].edge.length;
	}

	public static Edge GetEdgeFromDirectedGraph(DirectedGraph g, double nodeNr, double edgeNr){
		return g.nodes[(int)(nodeNr)].edge[(int)(edgeNr)];
	}

	public static boolean DirectedGraphMatricesEqual(DirectedGraphMatrix a, DirectedGraphMatrix b){
		boolean equal;
		double nodes, i, j;

		equal = true;

		if(GetNodesFromDirectedGraphMatrix(a) == GetNodesFromDirectedGraphMatrix(b)){
			nodes = GetNodesFromDirectedGraphMatrix(a);
			for(i = 0d; i < nodes && equal; i = i + 1d){
				for(j = 0d; j < nodes && equal; j = j + 1d){
					if(GetDirectedGraphMatrixEdge(a, i, j) == GetDirectedGraphMatrixEdge(b, i, j)){
					}else{
						equal = false;
					}
				}
			}
		}else{
			equal = false;
		}

		return equal;
	}

	public static boolean IsUndirected(DirectedGraph g){
		boolean undirected, found;
		double u, i, v, j;
		Node uNode, vNode;
		Edge uEdge, vEdge;

		undirected = true;

		for(u = 0d; u < g.nodes.length; u = u + 1d){
			uNode = g.nodes[(int)(u)];
			for(i = 0d; i < uNode.edge.length; i = i + 1d){
				uEdge = uNode.edge[(int)(i)];
				v = uEdge.nodeNr;

				if(u == v){
				}else{
					vNode = g.nodes[(int)(v)];
					found = false;
					for(j = 0d; j < vNode.edge.length && !found; j = j + 1d){
						vEdge = vNode.edge[(int)(j)];

						if(vEdge.nodeNr == u && vEdge.weight == uEdge.weight){
							found = true;
						}
					}

					if(!found){
						undirected = false;
					}
				}
			}
		}

		return undirected;
	}

	public static boolean GetGraphComponents(DirectedGraph g, NumberArrayReference componentMembership){
		boolean valid, found;
		double i, nodeNr, componentNr, done, startNode;
		boolean [] componentMembershipSet;
		NumberArrayReference list;

		valid = DirectedGraphIsValid(g) && IsUndirected(g);

		if(valid){
			componentMembership.numberArray = CreateNumberArray(g.nodes.length, 0d);
			componentMembershipSet = CreateBooleanArray(g.nodes.length, false);
			list = new NumberArrayReference();
			componentNr = 0d;
			done = 0d;
			startNode = 0d;

			for(; done != g.nodes.length; ){
				/* Find a node not currently in a component.*/
				found = false;
				for(i = 0d; i < g.nodes.length && !found; i = i + 1d){
					if(!componentMembershipSet[(int)(i)]){
						startNode = i;
						found = true;
					}
				}

				/* Use DFS to find a component*/
				DepthFirstSearch(g, startNode, list);

				/* Record the component.*/
				for(i = 0d; i < list.numberArray.length; i = i + 1d){
					nodeNr = list.numberArray[(int)(i)];
					if(!componentMembershipSet[(int)(nodeNr)]){
						componentMembership.numberArray[(int)(nodeNr)] = componentNr;
						componentMembershipSet[(int)(nodeNr)] = true;
						done = done + 1d;
					}
				}

				componentNr = componentNr + 1d;
			}

			delete(componentMembershipSet);
		}

		return valid;
	}

	public static boolean TopologicalSort(DirectedGraph g, NumberArrayReference list){
		boolean [] visited;
		LinkedListNumbers ll;
		double i;
		boolean valid;

		valid = !DirectedGraphContainsCycleDFS(g);

		if(valid){

			visited = CreateBooleanArray(g.nodes.length, false);
			ll = CreateLinkedListNumbers();

			for(i = 0d; i < g.nodes.length; i = i + 1d){
				if(!visited[(int)(i)]){
					TopologicalSortRecursive(g, g.nodes[(int)(i)], i, visited, ll);
				}
			}

			list.numberArray = LinkedListNumbersToArray(ll);
			FreeLinkedListNumbers(ll);

			for(i = 0d; i < list.numberArray.length/2d; i = i + 1d){
				SwapElementsOfArray(list.numberArray, i, list.numberArray.length - i - 1d);
			}
		}

		return valid;
	}

	public static void TopologicalSortRecursive(DirectedGraph g, Node node, double nodeNr, boolean [] visited, LinkedListNumbers list){
		double i;
		Edge e;

		visited[(int)(nodeNr)] = true;

		for(i = 0d; i < node.edge.length; i = i + 1d){
			e = node.edge[(int)(i)];
			if(!visited[(int)(e.nodeNr)]){
				TopologicalSortRecursive(g, g.nodes[(int)(e.nodeNr)], e.nodeNr, visited, list);
			}
		}

		LinkedListAddNumber(list, nodeNr);
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
