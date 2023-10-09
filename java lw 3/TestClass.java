import java.util.Arrays;

/**
 * TestClass
 */
public class TestClass implements SubjectInterface {
	int[] grades;
	String name;
	int hoursCount;

	public TestClass() {
	}

	public TestClass(int[] grades, String name, int hoursCount) {
		this.grades = grades;
		this.name = name;
		this.hoursCount = hoursCount;
	}

	public double getAvgGrade() {
		double accum = 0;
		for (int grade : grades) {
			accum += grade;
		}
		if (grades.length == 0) {
			throw new ArithmeticException("Division by zero");
		}
		return accum / grades.length;
	}

	public int getResultedGrade() {
		return getAvgGrade() >= 3 ? 5 : 2;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(grades);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + hoursCount;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TestClass other = (TestClass) obj;
		if (!Arrays.equals(grades, other.grades))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (hoursCount != other.hoursCount)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Зачёт [оценки=" + Arrays.toString(grades) + ", название=\"" + name + "\", количество часов="
				+ hoursCount
				+ "]";
	}

	// #region getters and setters
	public int[] getGrades() {
		return grades;
	}

	public void setGrades(int[] grades) throws GradesIsNotValidException {
		if (!GradesIsNotValidException.validateGradesArray(grades)) {
			throw new GradesIsNotValidException("Grades is not valid");
		}
		this.grades = grades;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getHoursCount() {
		return hoursCount;
	}

	public void setHoursCount(int hoursCount) {
		this.hoursCount = hoursCount;
	}
	// #endregion
}