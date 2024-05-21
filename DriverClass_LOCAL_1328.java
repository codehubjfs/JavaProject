package com.streams;

public class DriverClass{

	public static void main(String[] args) {
		System.out.println("Hi Ram Kumar ");

		System.out.println("Sample java file");
		for(int i=0;i<5;i++){
			System.out.println("Welcome....!");
		}
		
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
<<<<<<< HEAD
		System.out.println("MADDY | PIRA | JANA" );
                System.out.println("Once U Start!,There's no going Back!");
=======
		System.out.println("Correction Done");
>>>>>>> 2143c5a229a3b0aa1c22697fe3a5c94b511e1ad9

		System.out.print("vicky the mass");
	}
}
