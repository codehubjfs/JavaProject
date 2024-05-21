package Patterns;
import java.util.*;
public class Star3 {
	public static void main(String args[]) {
	Scanner in=new Scanner(System.in);
	int n=in.nextInt();
	for(int i=0;i<n;i++) {
		for(int j=0;j<n-i;j++) {
			System.out.print(" ");
		}
		for(int j=0;j<2*i+1;j++) {
			if(j%2==0) {
			System.out.print("#");
			}
			else {
			System.out.print("S");
			}
		}
		System.out.println("");
	}
	}
 }