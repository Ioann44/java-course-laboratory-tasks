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
}
