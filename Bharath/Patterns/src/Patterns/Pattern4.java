package Patterns;
import java.util.*;
public class Pattern4 {
	public static void main(String args[]) {
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
//for(int i=0;i<n;i++) {
//	int num=1;
//	for(int j=0;j<=i;j++) {
//		System.out.print(num+" ");
//		num=num*(i-j)/(j+1);
//	}
//	System.out.println();
//}
//}
		for(int i=n;i>=1;i--) 
		{
		for(int j=i;j<n;j++) {
				System.out.print(" ");
			}
		for(int j=1;j<=(2*i-1);j++)
			System.out.print("*");
		System.out.println("");
		}
	}
}