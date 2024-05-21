package Patterns;
import java.util.*;
public class Star1 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in=new Scanner(System.in);
		int n=in.nextInt();
		for(int i=1;i<=n;i++) {
			for(int j=1;j<=n-i;j++) {
			System.out.print(" ");
			}
			for(int k=1;k<=i*2-1;k++) {
				System.out.print("#");
			}
			System.out.println();
		}
	}
}
