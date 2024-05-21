package com.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DriverClass{

	public static void main(String[] args) {
		
		//Stream Iterate returns void
		Stream.iterate(1,next->next+1).filter(list->list%5==0).limit(5)
											.forEach(value -> System.out.print(value+"\t"));
		//usage of map and collect method
		@SuppressWarnings("unused")
		List<Integer> listStream=Stream.iterate(1,next->next+1).filter(list->list%5==0).limit(5).map(value->value*2)
				.collect(Collectors.toList());
		System.out.println("\n"+listStream);
		
		//with Strings - usage of filter method
		Collection<String> collection = Arrays.asList("Model", "Race", "Race","Reflection","Model","Spain","Football");
		List<String> streamOfCollection = collection.stream()
											.filter(s->s.startsWith("R"))
											.collect(Collectors.toList());
		System.out.println(streamOfCollection);
		
		//usage of sorted method
		List<String> streamStringSorted = collection.stream().sorted().collect(Collectors.toList());
		System.out.println(streamStringSorted);
		
		//usage of reduce method
		Integer [] numbers = new Integer[] {4,2,6,1,7,3,3,9};
		int even = Arrays.stream(numbers).filter(x->x%2==0).distinct().map(x->x*2).re//reduce(0,(ans,i)-> ans+i);
		System.out.println(even);
		//int sum = Arrays.stream(numbers).a
		//usage of set
		List<String> streamStringSet = collection.stream().collect(Collectors.toList());
		System.out.println(streamStringSet);

		int[]arr=new arr[]{1,2,3,4,5};
		for(int i:arr){
			System.out.println(i);
		}	
		
	}
}
