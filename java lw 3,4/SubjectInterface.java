import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public interface SubjectInterface {

	// get / set
	public int[] getGrades();

	public void setGrades(int[] salaries) throws GradesIsNotValidException;

	public String getName();

	public void setName(String model);

	public int getHoursCount();

	public void setHoursCount(int maxStaffCount);

	// business logic
	public double getAvgGrade();

	public int getResultedGrade();

	// write byte/symbol
	public void output(OutputStream out) throws IOException;

	public void write(Writer out) throws IOException;
}
