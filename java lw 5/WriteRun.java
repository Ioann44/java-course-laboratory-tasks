import java.util.Random;

public class WriteRun implements Runnable {
	int[] marksForRandom = { 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5 };
	private SyncRun synchronizer;
	private SubjectInterface subject;

	public WriteRun(SyncRun synchronizer, SubjectInterface subject) {
		this.synchronizer = synchronizer;
		this.subject = subject;
	}

	@Override
	public void run() {
		Random random = new Random();
		for (int i = 0; i < subject.getGrades().length; i++) {
			try {
				int newGrade = marksForRandom[random.nextInt(marksForRandom.length)];
				synchronizer.waitForWrite();
				subject.setGrade(i, newGrade);
				System.out.println(String.format("Write: %d to position %d", newGrade, i));
				synchronizer.switchTurn();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
