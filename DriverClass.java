package com.streams;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DriverClass{

    public static void main(String[] args) {
        
        // Stream Iterate and filter method
        System.out.print("First 5 multiples of 5: ");
        Stream.iterate(1, next -> next + 1)
                .filter(num -> num % 5 == 0)
                .limit(5)
                .forEach(value -> System.out.print(value + "\t"));
        
        // Usage of map and collect method
        List<Integer> listStream = Stream.iterate(1, next -> next + 1)
                .filter(num -> num % 5 == 0)
                .limit(5)
                .map(value -> value * 2)
                .collect(Collectors.toList());
        System.out.println("\nDoubled multiples of 5: " + listStream);
        
        // With Strings - usage of filter method
        Collection<String> collection = Arrays.asList("Model", "Race", "Race", "Reflection", "Model", "Spain", "Football");
        List<String> streamOfCollection = collection.stream()
                .filter(s -> s.startsWith("R"))
                .collect(Collectors.toList());
        System.out.println("Strings starting with 'R': " + streamOfCollection);
        
        // Usage of sorted method
        List<String> streamStringSorted = collection.stream().sorted().collect(Collectors.toList());
        System.out.println("Sorted collection: " + streamStringSorted);
        
        // Usage of reduce method
        Integer[] numbers = new Integer[] {4, 2, 6, 1, 7, 3, 3, 9};
        int oddSum = Arrays.stream(numbers)
                .filter(x -> x % 2 != 0)
                .distinct()
                .map(x -> x * 2)
                .reduce(0, Integer::sum);
        System.out.println("Sum of doubled distinct odd numbers: " + oddSum);
        
        // Usage of set
        Set<String> streamStringSet = collection.stream().collect(Collectors.toSet());
        System.out.println("Collection as a set: " + streamStringSet);
	System.out.println("Correction collected");
    }
}
