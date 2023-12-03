import java.util.Arrays;

public class UnmodifiableSubject implements SubjectInterface {
	private final SubjectInterface subject;

	public UnmodifiableSubject(SubjectInterface subject) {
		this.subject = subject;
	}

	@Override
	public int getGrade(int index) {
		return subject.getGrade(index);
	}

	@Override
	public int[] getGrades() {
		return subject.getGrades().clone(); // Возвращаем копию массива, чтобы избежать изменений внутреннего массива
	}

	@Override
	public void setGrade(int index, int value) {
		throw new UnsupportedOperationException("Object is unmodifiable");
	}

	@Override
	public void setGrades(int[] grades) throws GradesIsNotValidException {
		throw new UnsupportedOperationException("Object is unmodifiable");
	}

	@Override
	public String getName() {
		return subject.getName();
	}

	@Override
	public void setName(String name) {
		throw new UnsupportedOperationException("Object is unmodifiable");
	}

	@Override
	public int getHoursCount() {
		return subject.getHoursCount();
	}

	@Override
	public void setHoursCount(int hoursCount) {
		throw new UnsupportedOperationException("Object is unmodifiable");
	}

	@Override
	public double getAvgGrade() {
		return subject.getAvgGrade();
	}

	@Override
	public int getResultedGrade() {
		return subject.getResultedGrade();
	}

	@Override
	public java.util.Iterator<Integer> iterator() {
		return Arrays.stream(subject.getGrades()).iterator();
	}

	@Override
	public void output(java.io.OutputStream out) throws java.io.IOException {
		subject.output(out);
	}

	@Override
	public void write(java.io.Writer out) throws java.io.IOException {
		subject.write(out);
	}
}
