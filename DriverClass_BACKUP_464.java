package com.streams;

<<<<<<< HEAD
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//Ram Kumar 
//Ram Kumar
//Gopal
//Gopal
//Samrutha
//Samrutha
=======
>>>>>>> 52a1fa800b244b453d3535b38e8fc51615526bc7
public class DriverClass{

	public static void main(String[] args) {


		System.out.println("Sample java file");

		
<<<<<<< HEAD
		System.out.println('JFS')
		
=======
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
		int odd = Arrays.stream(numbers).filter(x->x%2!=0).distinct().map(x->x*2).reduce(0,(ans,i)-> ans+i);//Changed by me :)
		System.out.println(odd);
		//int sum = Arrays.stream(numbers).a
		//usage of set
		Set<String> streamStringSet = collection.stream().collect(Collectors.toSet());
		System.out.println(streamStringSet);
>>>>>>> 52a1fa800b244b453d3535b38e8fc51615526bc7
	}
}
