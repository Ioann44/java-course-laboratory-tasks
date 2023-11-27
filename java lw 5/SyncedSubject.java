import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

public class SyncedSubject implements SubjectInterface {
	private final Object lock = new Object();
	private SubjectInterface subject;

	public SyncedSubject(SubjectInterface subject) {
		this.subject = subject;
	}

	// get / set
	// mark
	@Override
	public synchronized int getGrade(int index) {
		synchronized (lock) {
			return subject.getGrade(index);
		}
	}

	// mark
	@Override
	public synchronized int[] getGrades() {
		synchronized (lock) {
			return subject.getGrades();
		}
	}

	// mark
	@Override
	public synchronized void setGrade(int index, int value) {
		synchronized (lock) {
			subject.setGrade(index, value);
		}
	}

	// mark
	@Override
	public synchronized void setGrades(int[] salaries) throws GradesIsNotValidException {
		synchronized (lock) {
			subject.setGrades(salaries);
		}
	}

	// mark
	@Override
	public synchronized String getName() {
		synchronized (lock) {
			return subject.getName();
		}
	}

	// mark
	@Override
	public synchronized void setName(String name) {
		synchronized (lock) {
			subject.setName(name);
		}
	}

	// mark
	@Override
	public synchronized int getHoursCount() {
		synchronized (lock) {
			return subject.getHoursCount();
		}
	}

	// mark
	@Override
	public synchronized void setHoursCount(int maxStaffCount) {
		synchronized (lock) {
			subject.setHoursCount(maxStaffCount);

		}
	}

	// business logic
	// mark
	@Override
	public synchronized double getAvgGrade() {
		synchronized (lock) {
			return subject.getAvgGrade();
		}
	}

	// mark
	@Override
	public synchronized int getResultedGrade() {
		synchronized (lock) {
			return subject.getResultedGrade();
		}
	}

	// write byte/symbol
	// mark
	@Override
	public synchronized void output(OutputStream out) throws IOException {
		synchronized (lock) {
			subject.output(out);

		}
	}

	// mark
	@Override
	public synchronized void write(Writer out) throws IOException {
		synchronized (lock) {
			subject.write(out);
		}
	}

	@Override
	public String toString() {
		return subject.toString();
	}
}