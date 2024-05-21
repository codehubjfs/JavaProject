package Patterns;

public class Gpattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int no=2;
		int n=0;
		for(int i=1;i<=no;i++) {
			for(int j=1;j<=no*no-n;j++) {
				if(j<3) {
					System.out.print(no+" ");
				}
				if(j>=3) {
					System.out.print(no-1+" ");
				}
			}
			System.out.println("");
			n=n+2;
		}

	}

}
