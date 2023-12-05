import java.util.Iterator;

public class GradesIterator implements Iterator<Integer> {
	private int[] grades;
	private int position;

	public GradesIterator(int[] grades) {
		this.grades = grades;
		this.position = 0;
	}

	@Override
	public boolean hasNext() {
		return position < grades.length;
	}

	@Override
	public Integer next() {
		if (!hasNext()) {
			throw new IndexOutOfBoundsException("No more elements in the array");
		}
		return grades[position++];
	}
}
