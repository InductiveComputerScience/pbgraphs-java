package lists.StringList;

import static java.lang.Math.*;

import references.references.*;
import static references.references.references.*;

import static arrays.arrays.arrays.*;


import static lists.LinkedListNumbersComputations.LinkedListNumbersComputations.*;

import static lists.DynamicArrayNumbersComputations.DynamicArrayNumbersComputations.*;

import static lists.NumberList.NumberList.*;

import static lists.BooleanList.BooleanList.*;

import lists.LinkedListNumbers.*;

import lists.DynamicArrayNumbers.*;

import static lists.CharacterList.CharacterList.*;

public class StringList{
	public static StringReference [] AddString(StringReference [] list, StringReference a){
		StringReference [] newlist;
		double i;

		newlist = new StringReference [(int)(list.length + 1d)];

		for(i = 0d; i < list.length; i = i + 1d){
			newlist[(int)(i)] = list[(int)(i)];
		}
		newlist[(int)(list.length)] = a;
		
		delete(list);
		
		return newlist;
	}

	public static void AddStringRef(StringArrayReference list, StringReference i){
		list.stringArray = AddString(list.stringArray, i);
	}

	public static StringReference [] RemoveString(StringReference [] list, double n){
		StringReference [] newlist;
		double i;

		newlist = new StringReference [(int)(list.length - 1d)];

		if(n >= 0d && n < list.length){
			for(i = 0d; i < list.length; i = i + 1d){
				if(i < n){
					newlist[(int)(i)] = list[(int)(i)];
				}
				if(i > n){
					newlist[(int)(i - 1d)] = list[(int)(i)];
				}
			}

			delete(list);
		}else{
			delete(newlist);
		}
		
		return newlist;
	}

	public static StringReference GetStringRef(StringArrayReference list, double i){
		return list.stringArray[(int)(i)];
	}

	public static void RemoveStringRef(StringArrayReference list, double i){
		list.stringArray = RemoveString(list.stringArray, i);
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
