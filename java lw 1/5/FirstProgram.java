import FirstPackage.*;

class FirstClass {
	public static void main(String[] s) {
		SecondClass o = new SecondClass();
		for (int i = 1; i < 8; i++) {
			for (int j = 1; j < 8; j++) {
				o.setI(i);
				o.setJ(j);
				System.out.print(o.mul());
				System.out.print(" ");
			}
			System.out.println();
		}
	}
}