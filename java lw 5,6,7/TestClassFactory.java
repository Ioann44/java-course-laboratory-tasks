import java.util.Random;

public class TestClassFactory implements SubjectFactory {
	Random random = new Random();
	int[] marksForRandom = { 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5 };

	@Override
	public TestClass createInstance() {
		int hoursCount = random.nextInt(16) + 15;
		int[] grades = new int[(int) (hoursCount * (0.3 + random.nextDouble() * 0.7))];
		TestClass test = new TestClass(grades, "TestName", hoursCount);
		for (int j = 0; j < grades.length; j++) {
			grades[j] = marksForRandom[random.nextInt(marksForRandom.length)];
		}
		return test;
	}
}