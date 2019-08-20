package DirectedGraphs.ShortestPaths;

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

import static DirectedGraphs.SpanningTree.SpanningTree.*;

import DirectedGraphs.DirectedGraphs.*;
import static DirectedGraphs.DirectedGraphs.DirectedGraphs.*;

import static DirectedGraphs.Trees.Trees.*;

public class ShortestPaths{
	public static void DijkstrasAlgorithm(DirectedGraph g, double src, NumberArrayReference dist, BooleanArrayReference distSet, NumberArrayReference prev){
		boolean [] nodeDone;
		double i, j, v, nodes, u, edges, alt;
		Edge edge;

		nodes = g.nodes.length;

		distSet.booleanArray = CreateBooleanArray(nodes, false);
		nodeDone = CreateBooleanArray(nodes, false);
		dist.numberArray = CreateNumberArray(nodes, 0d);
		prev.numberArray = CreateNumberArray(nodes, 0d);

		dist.numberArray[(int)(src)] = 0d;
		distSet.booleanArray[(int)(src)] = true;

		for(i = 0d; i < nodes; i = i + 1d){
			/* Get node with lowest distance*/
			u = ListFindLowestSetAndIncluded(dist.numberArray, distSet.booleanArray, nodeDone);

			/* Mark node as done*/
			nodeDone[(int)(u)] = true;

			edges = GetEdgesForNodeFromDirectedGraph(g, u);
			for(j = 0d; j < edges; j = j + 1d){
				edge = GetEdgeFromDirectedGraph(g, u, j);

				if(!nodeDone[(int)(edge.nodeNr)]){
					v = edge.nodeNr;
					alt = dist.numberArray[(int)(u)] + edge.weight;
					if(!distSet.booleanArray[(int)(v)]){
						dist.numberArray[(int)(v)] = alt;
						distSet.booleanArray[(int)(v)] = true;
						prev.numberArray[(int)(v)] = u;
					}else if(alt < dist.numberArray[(int)(v)]){
						dist.numberArray[(int)(v)] = alt;
						prev.numberArray[(int)(v)] = u;
					}
				}
			}
		}

		delete(distSet);
		delete(nodeDone);
	}

	public static double ListFindLowestSetAndIncluded(double [] list, boolean [] set, boolean [] exclude){
		double i, nodes, lowest, u;
		boolean lowestSet;

		nodes = list.length;
		lowest = 0d;
		u = 0d;

		lowestSet = false;
		for(i = 0d; i < nodes; i = i + 1d){
			if(!exclude[(int)(i)] && set[(int)(i)]){
				if(!lowestSet){
					lowest = list[(int)(i)];
					u = i;
					lowestSet = true;
				}else if(list[(int)(i)] < lowest){
					lowest = list[(int)(i)];
					u = i;
				}
			}
		}

		return u;
	}

	public static boolean DijkstrasAlgorithmDestinationOnly(DirectedGraph g, double src, double dest, NumberArrayReference path, NumberReference distance){
		NumberArrayReference distances, previous;
		BooleanArrayReference distanceSet;
		boolean found;

		distances = new NumberArrayReference();
		previous = new NumberArrayReference();
		distanceSet = new BooleanArrayReference();

		DijkstrasAlgorithm(g, src, distances, distanceSet, previous);

		found = distanceSet.booleanArray[(int)(dest)];

		if(found){
			distance.numberValue = distances.numberArray[(int)(dest)];

			ExtractForwardPathFromReverseList(src, dest, previous, path);
		}

		delete(distances);
		delete(previous);
		delete(distanceSet);

		return found;
	}

	public static void ExtractForwardPathFromReverseList(double src, double dest, NumberArrayReference previous, NumberArrayReference path){
		double next, length, i;

		next = dest;
		for(length = 1d; next != src; length = length + 1d){
			next = previous.numberArray[(int)(next)];
		}

		path.numberArray = CreateNumberArray(length, 0d);

		next = dest;
		for(i = 0d; i < length; i = i + 1d){
			path.numberArray[(int)(length - i - 1d)] = next;
			next = previous.numberArray[(int)(next)];
		}
	}

	public static boolean BellmanFordAlgorithm(DirectedGraph g, double src, NumberArrayReference dist, BooleanArrayReference distSet, NumberArrayReference prev){
		boolean [] nodeDone;
		double i, j, v, nodes, u, edges, w;
		Edge edge;
		boolean success;

		nodes = g.nodes.length;

		distSet.booleanArray = CreateBooleanArray(nodes, false);
		nodeDone = CreateBooleanArray(nodes, false);
		dist.numberArray = CreateNumberArray(nodes, 0d);
		prev.numberArray = CreateNumberArray(nodes, 0d);

		dist.numberArray[(int)(src)] = 0d;
		distSet.booleanArray[(int)(src)] = true;

		for(i = 0d; i < nodes - 1d; i = i + 1d){
			for(u = 0d; u < nodes; u = u + 1d){
				edges = GetEdgesForNodeFromDirectedGraph(g, u);
				for(j = 0d; j < edges; j = j + 1d){
					edge = GetEdgeFromDirectedGraph(g, u, j);

					v = edge.nodeNr;
					w = edge.weight;

					if(distSet.booleanArray[(int)(u)]){
						if(!distSet.booleanArray[(int)(v)]){
							dist.numberArray[(int)(v)] = dist.numberArray[(int)(u)] + w;
							distSet.booleanArray[(int)(v)] = true;
							prev.numberArray[(int)(v)] = u;
						}else if(dist.numberArray[(int)(u)] + w < dist.numberArray[(int)(v)]){
							dist.numberArray[(int)(v)] = dist.numberArray[(int)(u)] + w;
							prev.numberArray[(int)(v)] = u;
						}
					}
				}
			}
		}

		success = true;
		for(u = 0d; u < nodes; u = u + 1d){
			edges = GetEdgesForNodeFromDirectedGraph(g, u);
			for(j = 0d; j < edges; j = j + 1d){
				edge = GetEdgeFromDirectedGraph(g, u, j);

				v = edge.nodeNr;
				w = edge.weight;

				if(dist.numberArray[(int)(u)] + w < dist.numberArray[(int)(v)]){
					success = false;
				}
			}
		}

		delete(distSet);
		delete(nodeDone);

		return success;
	}

	public static boolean BellmanFordAlgorithmDestinationOnly(DirectedGraph g, double src, double dest, NumberArrayReference path, NumberReference distance){
		NumberArrayReference distances, previous;
		BooleanArrayReference distanceSet;
		boolean found;

		distances = new NumberArrayReference();
		previous = new NumberArrayReference();
		distanceSet = new BooleanArrayReference();

		found = BellmanFordAlgorithm(g, src, distances, distanceSet, previous);

		if(found){
			found = distanceSet.booleanArray[(int)(dest)];

			if(found){
				distance.numberValue = distances.numberArray[(int)(dest)];

				ExtractForwardPathFromReverseList(src, dest, previous, path);
			}
		}

		delete(distances);
		delete(previous);
		delete(distanceSet);

		return found;
	}

	public static boolean FloydWarshallAlgorithm(DirectedGraph g, Distances distances){
		double u, v, k, i, j;
		Node n;
		Edge e;
		Target t, ij, ik, kj;
		boolean success;

		success = true;

		for(u = 0d; u < g.nodes.length; u = u + 1d){
			n = g.nodes[(int)(u)];

			for(j = 0d; j < n.edge.length; j = j + 1d){
				e = n.edge[(int)(j)];
				v = e.nodeNr;

				t = distances.from[(int)(u)].to[(int)(v)];
				t.length = e.weight;
				t.lengthSet = true;
				t.next = v;
			}
		}

		for(v = 0d; v < g.nodes.length; v = v + 1d){
			t = distances.from[(int)(v)].to[(int)(v)];
			t.length = 0d;
			t.lengthSet = true;
			t.next = v;
		}

		for(k = 0d; k < g.nodes.length && success; k = k + 1d){
			for(i = 0d; i < g.nodes.length && success; i = i + 1d){
				for(j = 0d; j < g.nodes.length && success; j = j + 1d){
					ij = distances.from[(int)(i)].to[(int)(j)];
					ik = distances.from[(int)(i)].to[(int)(k)];
					kj = distances.from[(int)(k)].to[(int)(j)];

					if(!ij.lengthSet && ik.lengthSet && kj.lengthSet){
						ij.length = ik.length + kj.length;
						ij.lengthSet = true;
						ij.next = ik.next;
					}else if(ij.lengthSet && ik.lengthSet && kj.lengthSet){
						if(ij.length > ik.length + kj.length){
							ij.length = ik.length + kj.length;
							ij.next = ik.next;
						}
					}

					if(i == j){
						if(ij.length < 0d){
							success = false;
						}
					}
				}
			}
		}

		return success;
	}

	public static Distances CreateDistancesFloydWarshallAlgorithm(double nodes){
		Distances distances;
		double i, j;

		distances = new Distances();
		distances.from = new Distance [(int)(nodes)];
		for(i = 0d; i < distances.from.length; i = i + 1d){
			distances.from[(int)(i)] = new Distance();
			distances.from[(int)(i)].to = new Target [(int)(distances.from.length)];
			for(j = 0d; j < distances.from.length; j = j + 1d){
				distances.from[(int)(i)].to[(int)(j)] = new Target();
				distances.from[(int)(i)].to[(int)(j)].length = 0d;
				distances.from[(int)(i)].to[(int)(j)].lengthSet = false;
				distances.from[(int)(i)].to[(int)(j)].next = 0d;
			}
		}

		return distances;
	}

	public static double [] GetPathFromDistances(Distances distances, double u, double v){
		double [] path;
		double length, next, i;
		Target t;

		t = distances.from[(int)(u)].to[(int)(v)];
		if(t.lengthSet){
			/* count*/
			length = 1d;
			next = u;
			for(; next != v; ){
				next = distances.from[(int)(next)].to[(int)(v)].next;
				length = length + 1d;
			}

			path = new double [(int)(length)];

			/* set*/
			next = u;
			for(i = 0d; i < length; i = i + 1d){
				path[(int)(i)] = next;
				next = distances.from[(int)(next)].to[(int)(v)].next;
			}
		}else{
			path = new double [0];
		}

		return path;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
