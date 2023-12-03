public class ReadThread extends Thread {
	SubjectInterface subject;

	public ReadThread(SubjectInterface subject) {
		this.subject = subject;
	}

	public void run() {
		for (int i = 0; i < subject.getGrades().length; i++) {
			int grade = subject.getGrade(i);
			System.out.println(String.format("Read: %d from position %d", grade, i));
		}
	}
}
