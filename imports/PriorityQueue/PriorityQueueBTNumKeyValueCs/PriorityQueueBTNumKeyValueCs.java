package PriorityQueue.PriorityQueueBTNumKeyValueCs;

import static java.lang.Math.*;

import static lists.LinkedListNumbersComputations.LinkedListNumbersComputations.*;

import static lists.DynamicArrayNumbersComputations.DynamicArrayNumbersComputations.*;

import static lists.NumberList.NumberList.*;

import static lists.StringList.StringList.*;

import static lists.BooleanList.BooleanList.*;

import lists.LinkedListNumbers.*;

import lists.DynamicArrayNumbers.*;

import static lists.CharacterList.CharacterList.*;

import references.references.*;
import static references.references.references.*;

import static arrays.arrays.arrays.*;


import PriorityQueue.PriorityQueueBTNumbersCs.*;
import static PriorityQueue.PriorityQueueBTNumbersCs.PriorityQueueBTNumbersCs.*;

public class PriorityQueueBTNumKeyValueCs{
	public static PriorityQueueBTNumKeyValue CreatePriorityQueueBTNumKeyValue(){
		PriorityQueueBTNumKeyValue q;

		q = new PriorityQueueBTNumKeyValue();
		q.heapKey = CreateDynamicArrayNumbers();
		q.heapValue = CreateDynamicArrayNumbers();

		return q;
	}

	public static void FreePriorityQueueBTNumKeyValue(PriorityQueueBTNumKeyValue q){
		FreeDynamicArrayNumbers(q.heapKey);
		FreeDynamicArrayNumbers(q.heapValue);
		delete(q);
	}

	public static boolean PeekPriorityQueueBTNumKeyValue(PriorityQueueBTNumKeyValue q, NumberReference keyReference, NumberReference valueReference){
		boolean found;

		if(!IsEmptyPriorityQueueBTNumKeyValue(q)){
			keyReference.numberValue = DynamicArrayNumbersIndex(q.heapKey, 0d);
			valueReference.numberValue = DynamicArrayNumbersIndex(q.heapValue, 0d);
			found = true;
		}else{
			found = false;
		}

		return found;
	}

	public static void InsertIntoPriorityQueueBTNumKeyValue(PriorityQueueBTNumKeyValue q, double key, double value){
		DynamicArrayAddNumber(q.heapKey, key);
		DynamicArrayAddNumber(q.heapValue, value);

		if(SizeOfPriorityQueueBTNumKeyValue(q) >= 2d){
			SiftUpPriorityQueueBTNumKeyValue(q, q.heapKey.length - 1d);
		}
	}

	public static boolean PopPriorityQueueBTNumKeyValue(PriorityQueueBTNumKeyValue q, NumberReference keyReference, NumberReference valueReference){
		boolean found;

		found = PeekPriorityQueueBTNumKeyValue(q, keyReference, valueReference);

		if(found){
			DeleteTopPriorityQueueBTNumKeyValue(q);
		}

		return found;
	}

	public static boolean DeleteTopPriorityQueueBTNumKeyValue(PriorityQueueBTNumKeyValue q){
		boolean found;
		double last;

		found = IsEmptyPriorityQueueBTNumKeyValue(q);

		if(!IsEmptyPriorityQueueBTNumKeyValue(q)){
			last = q.heapKey.length - 1d;
			SwapElementsOfArray(q.heapKey.array, 0d, last);
			SwapElementsOfArray(q.heapValue.array, 0d, last);

			DynamicArrayRemoveNumber(q.heapKey, last);
			DynamicArrayRemoveNumber(q.heapValue, last);

			SiftDownPriorityQueueBTNumKeyValue(q, 0d);
		}

		return found;
	}

	public static PriorityQueueBTNumKeyValue ArrayToPriorityQueueBTNumKeyValue(double [] keys, double [] values){
		PriorityQueueBTNumKeyValue q;
		double i;

		q = CreatePriorityQueueBTNumKeyValue();

		for(i = 0d; i < keys.length; i = i + 1d){
			InsertIntoPriorityQueueBTNumKeyValue(q, keys[(int)(i)], values[(int)(i)]);
		}

		return q;
	}

	public static double SizeOfPriorityQueueBTNumKeyValue(PriorityQueueBTNumKeyValue q){
		return q.heapKey.length;
	}

	public static boolean IsEmptyPriorityQueueBTNumKeyValue(PriorityQueueBTNumKeyValue q){
		return q.heapKey.length == 0d;
	}

	public static void SiftUpPriorityQueueBTNumKeyValue(PriorityQueueBTNumKeyValue q, double index){
		double parent;
		double iKey, pKey;
		boolean done;

		done = false;
		for(; !done && index != 0d; ){
			parent = floor((index - 1d)/2d);

			iKey = DynamicArrayNumbersIndex(q.heapKey, index);
			pKey = DynamicArrayNumbersIndex(q.heapKey, parent);

			if(iKey > pKey){
				SwapElementsOfArray(q.heapKey.array, index, parent);
				SwapElementsOfArray(q.heapValue.array, index, parent);
			}else{
				done = true;
			}

			index = parent;
		}
	}

	public static void SiftDownPriorityQueueBTNumKeyValue(PriorityQueueBTNumKeyValue q, double index){
		double parent, c1, c2;
		double c1Key, c2Key, pKey, size;
		boolean done;

		size = SizeOfPriorityQueueBTNumKeyValue(q);

		c1Key = 0d;
		c2Key = 0d;
		done = false;
		for(; !done; ){
			parent = index;
			c1 = 2d*parent + 1d;
			c2 = 2d*parent + 2d;

			pKey = DynamicArrayNumbersIndex(q.heapKey, parent);
			if(c1 < size){
				c1Key = DynamicArrayNumbersIndex(q.heapKey, c1);
			}
			if(c2 < size){
				c2Key = DynamicArrayNumbersIndex(q.heapKey, c2);
			}

			if(c1Key > pKey && c1 < size || c2Key > pKey && c2 < size){
				if(c1Key >= c2Key && c1 < size){
					SwapElementsOfArray(q.heapKey.array, c1, parent);
					SwapElementsOfArray(q.heapValue.array, c1, parent);
					index = c1;
				}else if(c1Key <= c2Key && c2 < size){
					SwapElementsOfArray(q.heapKey.array, c2, parent);
					SwapElementsOfArray(q.heapValue.array, c2, parent);
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
