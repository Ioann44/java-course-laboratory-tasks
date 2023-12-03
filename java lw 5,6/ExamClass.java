import java.io.IOException;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.Arrays;
import java.util.Iterator;

/**
 * ExamClass
 */
public class ExamClass implements SubjectInterface, Serializable {
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

	@Override
	public Iterator<Integer> iterator() {
		return new GradesIterator(grades);
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
		return String.format("Экзамен [оценки=%s, название=\"%s\", количество часов=%d]",
				Arrays.toString(grades), name, hoursCount);
	}

	// #region getters and setters
	public int getGrade(int index) {
		return grades[index];
	}

	public int[] getGrades() {
		return grades;
	}

	public void setGrade(int index, int value) {
		this.grades[index] = value;
	}

	public void setGrades(int[] grades) {
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

	// #region write byte/symbol
	public void output(OutputStream out) throws IOException {
		out.write('E');
		out.write(grades.length);
		for (int grade : grades) {
			out.write(grade);
		}
		out.write(name.length());
		out.write(name.getBytes());
		out.write(hoursCount);
	}

	public void write(Writer out) throws IOException {
		char delimiter = ';';
		out.write("E" + delimiter);
		out.write(Integer.toString(grades.length) + delimiter);
		for (int grade : grades) {
			out.write(Integer.toString(grade) + delimiter);
		}
		out.write(name + delimiter);
		out.write(Integer.toString(hoursCount) + '\n');
	}
	// #endregion
}