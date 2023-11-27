import java.util.Random;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		Random random = new Random();

		int hoursCount = random.nextInt(16) + 15;
		int[] grades = new int[(int) (hoursCount * (0.3 + random.nextDouble() * 0.7))];
		ExamClass exam = new ExamClass(grades, "ExamName", hoursCount);

		System.out.println(exam);

		/* Threads */
		System.out.println("\n\n=== Threads ===");
		WriteThread writeThread = new WriteThread(exam);
		ReadThread readThread = new ReadThread(exam);
		// writeThread.setPriority(1);
		// readThread.setPriority(10);
		writeThread.start();
		readThread.start();

		writeThread.join();
		readThread.join();
		System.out.println(exam);

		/* Runnable */
		System.out.println("\n\n=== Runnable ===");
		for (int i = 0; i < exam.getGrades().length; i++) {
			exam.setGrade(i, 0);
		}

		SyncRun synchronizer = new SyncRun();
		Runnable writeTask = new WriteRun(synchronizer, exam);
		Runnable readTask = new ReadRun(synchronizer, exam);
		Thread writeThreadRun = new Thread(writeTask);
		Thread readThreadRun = new Thread(readTask);

		writeThreadRun.start();
		readThreadRun.start();

		writeThreadRun.join();
		readThreadRun.join();
		System.out.println(exam);

		/* Synced version */
		System.out.println("\n\n=== Threads ===");
		for (int i = 0; i < exam.getGrades().length; i++) {
			exam.setGrade(i, 0);
		}
		SyncedSubject syncedExam = SubjectIO.getSyncedSubject(exam);
		writeThread = new WriteThread(syncedExam);
		readThread = new ReadThread(syncedExam);
		writeThread.setPriority(1);
		readThread.setPriority(10);
		writeThread.start();
		readThread.start();
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}

		System.out.println(syncedExam);
	}
}
