import java.util.Iterator;

public class Main {
	public static void main(String[] args) throws InterruptedException {
		ExamClass exam = (ExamClass) SubjectIO.createInstance();
		SubjectIO.setSubjectInterfaceFactory(new TestClassFactory());
		TestClass test = (TestClass) SubjectIO.createInstance();
		System.out.println(exam);
		System.out.println(test);

		// While
		Iterator<Integer> iterator = exam.iterator();
		while (iterator.hasNext()) {
			System.out.print(iterator.next());
			System.out.print(" ");
		}
		System.out.println();

		// For each
		for (int grade : exam) {
			System.out.print(grade);
			System.out.print(" ");
		}
		System.out.println();

		// Unmodifiable cover
		SubjectInterface constExam = SubjectIO.unmodifiableSubject(exam);
		System.out.println(constExam.getName());
		try {
			constExam.setName("Mod exam");
		} catch (Exception e) {
			System.out.println(String.format("Catched exception: %s", e));
		}
	}
}
