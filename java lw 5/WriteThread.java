import java.util.Random;

public class WriteThread extends Thread {
	int[] marksForRandom = { 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5 };
	SubjectInterface subject;

	public WriteThread(SubjectInterface subject) {
		this.subject = subject;
	}

	public void run() {
		Random random = new Random();
		for (int i = 0; i < subject.getGrades().length; i++) {
			int newGrade = marksForRandom[random.nextInt(marksForRandom.length)];
			subject.setGrade(i, newGrade);
			System.out.println(String.format("Write: %d to position %d", newGrade, i));
		}
	}
}
