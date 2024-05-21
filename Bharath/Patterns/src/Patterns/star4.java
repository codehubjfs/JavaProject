package Patterns;
public class star4 {
	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=5;
		//1.looping 
//		for(int i=1;i<=n;i++) {
//			for(int j=1;j<=n;j++) {
//				System.out.print(j+" ");
//			}
//			System.out.println(" ");
//		}
		for(int i=1;i<=5;i++) {
		n=5;
		while(n>=1) {
			System.out.print(n+" ");
			n--;
		}
		System.out.println();
		}
	}

}*/
public static void main(String args[]) {
	int no=5;
	for(int row=1;row<=5;row++) {
	for(int col=1;col<=5;col++) {
	System.out.print(row*no+" ");
	}
	System.out.println();
	no--;
	}
}
}
