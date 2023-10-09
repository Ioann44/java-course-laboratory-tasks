public class VectorClass {
	private double[] vector;
	private int size;

	public VectorClass(int size) {
		this.size = size;
		vector = new double[size];
	}

	public void fillRandom() {
		for (int i = 0; i < size; i++) {
			vector[i] = (double) RandomClass.getRandom(1000) / 10;
		}
	}

	public void print() {
		for (double vi : vector) {
			System.out.print(vi);
			System.out.print(' ');
		}
		System.out.println();
	}

	public double getAt(int i) {
		return vector[i];
	}

	public void setAt(int i, double value) {
		vector[i] = value;
	}

	public int length() {
		return vector.length;
		// return size;
	}

	public double min() {
		if (this.size == 0) {
			throw new Error("Can't get minimal element of empty vector");
		}
		double curMin = Double.MAX_VALUE;
		for (double element : vector) {
			if (element < curMin) {
				curMin = element;
			}
		}
		return curMin;
	}

	public double max() {
		if (this.size == 0) {
			throw new Error("Can't get maximal element of empty vector");
		}
		double curMax = -Double.MAX_VALUE;
		for (double element : vector) {
			if (element > curMax) {
				curMax = element;
			}
		}
		return curMax;
	}

	private void sort(int left, int right) {
		if (left + 1 >= right) {
			return;
		}
		int mid = left + RandomClass.getRandom(right - left);
		double midElement = vector[mid];
		int begin = left, end = right - 1;
		double holder;
		while (begin < end) {
			while (vector[begin] < midElement) {
				begin++;
			}
			while (vector[end] > midElement) {
				end--;
			}
			if (begin < end) {
				holder = vector[begin];
				vector[begin] = vector[end];
				vector[end] = holder;
				begin++;
				end--;
			}
		}
		sort(left, begin);
		sort(begin, right);
	}

	public void sort() {
		sort(0, size);
	}

	public double euclideanNorm() {
		double sumOfSquares = 0;
		for (double element : vector) {
			sumOfSquares += element * element;
		}
		return Math.sqrt(sumOfSquares);
	}

	public void mulByNum(double num) {
		for (int i = 0; i < size; i++) {
			vector[i] *= num;
		}
	}

	static VectorClass returnEachElementOperationOf2Vectors(VectorClass v1, VectorClass v2,
			DoubleElementwiseInterface func) {
		if (v1.length() != v2.length()) {
			throw new Error("Vectors must have equal length");
		}
		VectorClass result = new VectorClass(v1.length());
		for (int i = 0; i < result.length(); i++) {
			result.setAt(i, func.sam(v1.getAt(i), v2.getAt(i)));
		}
		return result;
	}

	public static VectorClass sumVectors(VectorClass v1, VectorClass v2) {
		return returnEachElementOperationOf2Vectors(v1, v2, (double a, double b) -> {
			return a + b;
		});
	}

	public static double scalarMul(VectorClass v1, VectorClass v2) {
		VectorClass vecOfSums = returnEachElementOperationOf2Vectors(v1, v2, (double a, double b) -> {
			return a * b;
		});
		double sum = 0;
		for (int i = 0; i < vecOfSums.size; i++) {
			sum += vecOfSums.getAt(i);
		}
		return sum;
	}
}

interface DoubleElementwiseInterface {
	double sam(double a, double b); // single abstract method
}

// We can't use existing classes...
class RandomClass {
	static int value = 1;
	static final int mul = 1_499_999_949;
	static final int addition = 999_999_883;
	static final int divisor = 1_999_999_967;

	public static int getRandom(int rightBound) {
		value = (int) (((long) value * mul + addition) % divisor);
		return value / (Integer.MAX_VALUE / rightBound);
	}

	public static int getRandom() {
		return getRandom(Integer.MAX_VALUE);
	}
}