class FirstClass {
	public static void main(String[] s) {
		// task 2
		// System.out.println("Hello world!!!");

		// task 3
		// for (int i = 0; i < s.length; i++)
		// System.out.println(s[i]);

		// task 4
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

class SecondClass {
	private int i, j;

	public SecondClass(int i, int j) {
		this.i = i;
		this.j = j;
	}

	public SecondClass() {
		i = j = 0;
	}

	public void setI(int newVal) {
		i = newVal;
	}

	public void setJ(int newVal) {
		j = newVal;
	}

	public int getI() {
		return i;
	}

	public int getJ() {
		return j;
	}

	public int mul() {
		return i * j;
	}
}