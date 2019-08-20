package PriorityQueue.PriorityQueueBTNumbersCs;

import references.references.NumberReference;

import static arrays.arrays.arrays.SwapElementsOfArray;
import static java.lang.Math.floor;
import static lists.DynamicArrayNumbersComputations.DynamicArrayNumbersComputations.*;

public class PriorityQueueBTNumbersCs{
	public static PriorityQueueBTNumbers CreatePriorityQueueBTNumbers(){
		PriorityQueueBTNumbers q;

		q = new PriorityQueueBTNumbers();
		q.heap = CreateDynamicArrayNumbers();

		return q;
	}

	public static void FreePriorityQueueBTNumbers(PriorityQueueBTNumbers q){
		FreeDynamicArrayNumbers(q.heap);
		delete(q);
	}

	public static boolean PeekPriorityQueueBTNumbers(PriorityQueueBTNumbers q, NumberReference keyReference){
		boolean found;

		if(!IsEmptyPriorityQueueBTNumbers(q)){
			keyReference.numberValue = DynamicArrayNumbersIndex(q.heap, 0d);
			found = true;
		}else{
			found = false;
		}

		return found;
	}

	public static void InsertIntoPriorityQueueBTNumbers(PriorityQueueBTNumbers q, double key){
		DynamicArrayAddNumber(q.heap, key);

		if(SizeOfPriorityQueueBTNumbers(q) >= 2d){
			SiftUpPriorityQueueBTNumbers(q, q.heap.length - 1d);
		}
	}

	public static boolean PopPriorityQueueBTNumbers(PriorityQueueBTNumbers q, NumberReference keyReference){
		boolean found;

		found = PeekPriorityQueueBTNumbers(q, keyReference);

		if(found){
			DeleteTopPriorityQueueBTNumbers(q);
		}

		return found;
	}

	public static boolean DeleteTopPriorityQueueBTNumbers(PriorityQueueBTNumbers q){
		boolean found;
		double last;

		found = IsEmptyPriorityQueueBTNumbers(q);

		if(!IsEmptyPriorityQueueBTNumbers(q)){
			last = q.heap.length - 1d;
			SwapElementsOfArray(q.heap.array, 0d, last);

			DynamicArrayRemoveNumber(q.heap, last);

			SiftDownPriorityQueueBTNumbers(q, 0d);
		}

		return found;
	}

	public static PriorityQueueBTNumbers ArrayToPriorityQueueBTNumbers(double [] keys){
		PriorityQueueBTNumbers q;
		double i;

		q = CreatePriorityQueueBTNumbers();

		for(i = 0d; i < keys.length; i = i + 1d){
			InsertIntoPriorityQueueBTNumbers(q, keys[(int)(i)]);
		}

		return q;
	}

	public static double SizeOfPriorityQueueBTNumbers(PriorityQueueBTNumbers q){
		return q.heap.length;
	}

	public static boolean IsEmptyPriorityQueueBTNumbers(PriorityQueueBTNumbers q){
		return q.heap.length == 0d;
	}

	public static void SiftUpPriorityQueueBTNumbers(PriorityQueueBTNumbers q, double index){
		double parent;
		double iKey, pKey;
		boolean done;

		done = false;
		for(; !done && index != 0d; ){
			parent = floor((index - 1d)/2d);

			iKey = DynamicArrayNumbersIndex(q.heap, index);
			pKey = DynamicArrayNumbersIndex(q.heap, parent);

			if(iKey > pKey){
				SwapElementsOfArray(q.heap.array, index, parent);
			}else{
				done = true;
			}

			index = parent;
		}
	}

	public static void SiftDownPriorityQueueBTNumbers(PriorityQueueBTNumbers q, double index){
		double parent, c1, c2;
		double c1Key, c2Key, pKey, size;
		boolean done;

		size = SizeOfPriorityQueueBTNumbers(q);

		done = false;
		for(; !done; ){
			parent = index;
			c1 = 2d*parent + 1d;
			c2 = 2d*parent + 2d;

			pKey = DynamicArrayNumbersIndex(q.heap, parent);
			c1Key = DynamicArrayNumbersIndex(q.heap, c1);
			c2Key = DynamicArrayNumbersIndex(q.heap, c2);

			if(c1Key > pKey && c1 < size || c2Key > pKey && c2 < size){
				if(c1Key >= c2Key && c1 < size){
					SwapElementsOfArray(q.heap.array, c1, parent);
					index = c1;
				}else if(c1Key <= c2Key && c2 < size){
					SwapElementsOfArray(q.heap.array, c2, parent);
					index = c2;
				}else{
					done = true;
				}
			}else{
				done = true;
			}
		}
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
