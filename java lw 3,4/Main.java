import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		int[] marksForRandom = { 2, 2, 3, 3, 3, 4, 4, 4, 4, 5, 5 };
		// #region subjectNames
		String[] subjectNames = """
				Environmental Sustainability and Conservation
				Digital Marketing Strategies
				Comparative Literature and Cultural Studies
				Astrophysics and Cosmology
				Entrepreneurship and Innovation
				Global Health and Disease Prevention
				Neuroscience and Cognitive Psychology
				Human Rights and Social Justice
				Data Science and Big Data Analytics
				International Relations and Diplomacy
				Creative Writing and Literary Arts
				Environmental Engineering and Sustainable Design
				Entrepreneurial Finance and Venture Capital
				Modern Robotics and Automation
				Political Economy and Public Policy
				Digital Media Production and Storytelling
				Genomic Medicine and Biotechnology
				Human-Centered Design and User Experience
				Applied Cryptography and Cybersecurity
				Behavioral Economics and Decision Making
				""".split("\n");
		// #endregion
		Random random = new Random();

		Scanner scanner = new Scanner(System.in);
		// System.out.print("Введите общее количество учебных предметов: ");
		// int subjectsCnt = scanner.nextInt();
		int subjectsCnt = 3;
		System.out.println();
		scanner.close();

		SubjectInterface[] subjects = new SubjectInterface[subjectsCnt];
		for (int i = 0; i < subjects.length; i++) {
			// create hours count
			int hoursCount = random.nextInt(16) + 5;
			// create grades list
			int[] grades = new int[(int) (hoursCount * (0.3 + random.nextDouble() * 0.7))];
			for (int j = 0; j < grades.length; j++) {
				grades[j] = marksForRandom[random.nextInt(marksForRandom.length)];
			}
			// get name
			String subjectName = subjectNames[random.nextInt(subjectNames.length)];
			// create subject
			if (random.nextDouble() > 0.2) {
				subjects[i] = new ExamClass(grades, subjectName, hoursCount);
			} else {
				subjects[i] = new TestClass(grades, subjectName, hoursCount);
			}
		}

		// print all subjects
		System.out.println("Исходные предметы");
		for (SubjectInterface subject : subjects) {
			System.out.println(subject);
		}

		/*
		 * try {
		 * subjects[0].setGrades(new int[] {});
		 * System.out.
		 * println("\nExpected \"GradeIsNotValid\" exception on setting wrong grades values:"
		 * );
		 * subjects[0].setGrades(new int[] { 5, 5, 5, 5, 10 });
		 * } catch (GradesIsNotValidException e) {
		 * System.err.println(e);
		 * }
		 * System.out.
		 * println("\nExpected \"Divide by zero\" exception on getting resulted grade:"
		 * );
		 * 
		 * // split to arrays by business function
		 * ArrayList<SubjectInterface>[] subjectsByResultMark = new ArrayList[6];
		 * for (int i = 0; i < subjectsByResultMark.length; i++) {
		 * subjectsByResultMark[i] = new ArrayList<>();
		 * }
		 * for (SubjectInterface subject : subjects) {
		 * int result = 2;
		 * try {
		 * result = subject.getResultedGrade();
		 * } catch (ArithmeticException e) {
		 * System.err.println(e);
		 * }
		 * subjectsByResultMark[result].add(subject);
		 * }
		 * for (int i = 2; i <= 5; i++) {
		 * System.out.println(String.format("\nОценка: %s", i));
		 * for (SubjectInterface subject : subjectsByResultMark[i]) {
		 * System.out.println(subject);
		 * }
		 * }
		 * 
		 * // split by type
		 * ArrayList<ExamClass> exams = new ArrayList<>();
		 * ArrayList<TestClass> tests = new ArrayList<>();
		 * for (SubjectInterface subject : subjects) {
		 * if (subject instanceof ExamClass) {
		 * exams.add((ExamClass) subject);
		 * } else {
		 * tests.add((TestClass) subject);
		 * }
		 * }
		 * System.out.println("\nЭкзамены:");
		 * for (ExamClass exam : exams) {
		 * System.out.println(exam);
		 * }
		 * System.out.println("\nЗачёты:");
		 * for (TestClass test : tests) {
		 * System.out.println(test);
		 * }
		 */

		// byte write
		try (FileOutputStream outputStream = new FileOutputStream("byte.bin")) {
			for (SubjectInterface subject : subjects) {
				SubjectIO.output(subject, outputStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// byte read
		try (FileInputStream inputStream = new FileInputStream("byte.bin")) {
			for (int i = 0; i < subjects.length; i++) {
				subjects[i] = SubjectIO.input(inputStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\nПредметы после байтовых записи/чтения");
		for (SubjectInterface subject : subjects) {
			System.out.println(subject);
		}

		// symbol write
		try (FileWriter outputReader = new FileWriter("symbol.txt")) {
			for (SubjectInterface subject : subjects) {
				SubjectIO.write(subject, outputReader);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// symbol read
		try (FileReader inputReader = new FileReader("symbol.txt")) {
			for (int i = 0; i < subjects.length; i++) {
				subjects[i] = SubjectIO.read(inputReader);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\nПредметы после символьных записи/чтения");
		for (SubjectInterface subject : subjects) {
			System.out.println(subject);
		}

		// serial write
		try (FileOutputStream outputStream = new FileOutputStream("serialize.bin")) {
			for (SubjectInterface subject : subjects) {
				SubjectIO.serialize(subject, outputStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// serial read
		try (FileInputStream inputStream = new FileInputStream("serialize.bin")) {
			for (int i = 0; i < subjects.length; i++) {
				subjects[i] = SubjectIO.deserialize(inputStream);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("\nПредметы после сериализации/десериализации");
		for (SubjectInterface subject : subjects) {
			System.out.println(subject);
		}

		// formatted write
		try (FileWriter outputReader = new FileWriter("format.txt")) {
			for (SubjectInterface subject : subjects) {
				SubjectIO.writeFormat(subject, outputReader);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		// formatted read
		try (FileReader inputReader = new FileReader("format.txt")) {
			Scanner scan = new Scanner(inputReader);
			for (int i = 0; i < subjects.length; i++) {
				subjects[i] = SubjectIO.readFormat(scan);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("\nПредметы после форматных записи/чтения");
		for (SubjectInterface subject : subjects) {
			System.out.println(subject);
		}
	}
}
