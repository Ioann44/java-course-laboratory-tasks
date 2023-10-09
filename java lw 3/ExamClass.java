import java.util.Arrays;

/**
 * ExamClass
 */
public class ExamClass implements SubjectInterface {
	int[] grades;
	String name;
	int hoursCount;

	public ExamClass() {
	}

	public ExamClass(int[] grades, String name, int hoursCount) {
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
		return (int) Math.round(getAvgGrade());
	}

	@Override
	public String toString() {
		return "Экзамен [оценки=" + Arrays.toString(grades) + ", название=\"" + name + "\", количество часов="
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