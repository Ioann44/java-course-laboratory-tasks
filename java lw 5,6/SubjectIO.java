import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SubjectIO {
	private static SubjectFactory factory = new ExamClassFactory();

	public static void setSubjectInterfaceFactory(SubjectFactory newFactory) {
		factory = newFactory;
	}

	public static SubjectInterface createInstance() {
		return factory.createInstance();
	}

	public static SubjectInterface unmodifiableSubject(SubjectInterface object) {
		return new UnmodifiableSubject(object);
	}

	public static SyncedSubject getSyncedSubject(SubjectInterface subject) {
		return new SyncedSubject(subject);
	}

	// запись в байтовый поток
	public static void output(SubjectInterface o, OutputStream out) throws IOException {
		o.output(out);
	}

	// чтениe из байтового потока
	public static SubjectInterface input(InputStream dis) throws IOException {
		char type = (char) dis.read();
		int gradesLen = dis.read();
		int grades[] = new int[gradesLen];
		for (int i = 0; i < gradesLen; i++) {
			grades[i] = dis.read();
		}
		int nameLen = dis.read();
		byte nameBytes[] = new byte[nameLen];
		for (int i = 0; i < nameBytes.length; i++) {
			nameBytes[i] = (byte) dis.read();
		}
		String name = new String(nameBytes);
		int hoursCount = dis.read();

		SubjectInterface test = createInstance();
		test.setGrades(grades);
		test.setName(name);
		test.setHoursCount(hoursCount);
		return test;
	}

	// запись в символьный поток
	public static void write(SubjectInterface o, Writer out) throws IOException {
		o.write(out);
	}

	// чтениe из символьного потока
	public static SubjectInterface read(Reader in) throws IOException {
		String input = "";
		char inputCh;
		while (true) {
			inputCh = (char) in.read();
			if (inputCh == '\n') {
				break;
			}
			input += inputCh;
		}

		String strArr[] = input.split(";");

		int i = 0;
		char type = strArr[0].charAt(i++);
		int gradesLen = Integer.parseInt(strArr[i++]);
		int grades[] = new int[gradesLen];
		for (int j = 0; j < gradesLen; j++, i++) {
			grades[j] = Integer.parseInt(strArr[i]);
		}
		String name = strArr[i++];
		int hoursCount = Integer.parseInt(strArr[i++]);

		if (type == 'T') {
			return new TestClass(grades, name, hoursCount);
		} else {
			return new ExamClass(grades, name, hoursCount);
		}
	}

	// вывод сериализованных объектов
	public static void serialize(SubjectInterface o, OutputStream out) throws IOException {
		ObjectOutputStream outObj = new ObjectOutputStream(out);
		outObj.writeObject(o);
	}

	// ввод десериализованного объекта
	public static SubjectInterface deserialize(InputStream in) throws IOException, ClassNotFoundException {
		ObjectInputStream inObj = new ObjectInputStream(in);
		SubjectInterface deserializedExam = (SubjectInterface) inObj.readObject();
		return deserializedExam;
	}

	// вывод с объектов
	public static void writeFormat(SubjectInterface o, Writer out) throws IOException {
		out.write(o.toString());
		out.write('\n');
	}

	// ввод объекта (параметром метода является объект типа Scanner)
	public static SubjectInterface readFormat(Scanner in) {
		Pattern pattern = Pattern
				.compile("(Зачёт|Экзамен) \\[оценки=\\[(.*?)\\], название=\\\"(.*?)\\\", количество часов=(\\d+)\\]");
		Matcher matcher = pattern.matcher(in.nextLine());

		if (matcher.matches()) {
			String type = matcher.group(1);
			String gradesString = matcher.group(2);
			String[] gradesArray = gradesString.split(", ");
			int[] grades = Arrays.stream(gradesArray).mapToInt(Integer::parseInt).toArray();
			String name = matcher.group(3);
			int hoursCount = Integer.parseInt(matcher.group(4));

			if ("Зачёт".equals(type)) {
				return new TestClass(grades, name, hoursCount);
			} else if ("Экзамен".equals(type)) {
				return new ExamClass(grades, name, hoursCount);
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
