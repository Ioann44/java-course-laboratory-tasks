public class GradesIsNotValidException extends Exception {
	public GradesIsNotValidException(String message) {
		super(message);
	}

	public static boolean validateGradesArray(int[] grades) {
		for (int grade : grades) {
			if (grade < 2 || grade > 5) {
				return false;
			}
		}
		return true;
	}
}
