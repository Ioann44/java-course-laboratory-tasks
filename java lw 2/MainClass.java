public class MainClass {
	public static void main(String[] args) {
		VectorClass vector = new VectorClass(10);
		vector.fillRandom();
		System.out.println("Исходный вектор");
		vector.print();
		System.out.println("После сортировки");
		vector.sort();
		vector.print();

		VectorClass vector2 = new VectorClass(10);
		vector2.fillRandom();
		System.out.println("Второй вектор");
		vector2.print();

		VectorClass vector3 = VectorClass.sumVectors(vector, vector2);
		System.out.println("Сложенные первый и второй");
		vector3.print();

		System.out.println("Евклидова норма первого");
		System.out.println(vector.euclideanNorm());

		System.out.println("Скалярное произведение первого и второго");
		System.out.println(VectorClass.scalarMul(vector, vector2));
	}
}
