public class ReadRun implements Runnable {
	private SyncRun synchronizer;
	private SubjectInterface subject;

	public ReadRun(SyncRun synchronizer, SubjectInterface subject) {
		this.synchronizer = synchronizer;
		this.subject = subject;
	}

	@Override
	public void run() {
		for (int i = 0; i < subject.getGrades().length; i++) {
			try {
				synchronizer.waitForRead();
				System.out.println(String.format("Read: %d from position %d", subject.getGrade(i), i));
				synchronizer.switchTurn();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
