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