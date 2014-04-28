import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {
	public static void main(String[] args) {
		List<Student> students = new ArrayList<Student>(Student.MAX);
		for (int i = 0; i < Student.MAX; i++) {
			students.add(new Student(i + 1));
		}

		int[] input = handleInput();
		Processer processer = new Processer(input[0], input[1], input[2]);
		processer.process(students);

		for (Student student : students) {
			student.speak();
		}
	}

	private static int[] handleInput() {
		System.out.println("Enter three num (e.g. \"1,2,3\") : ");
		String input;
		Scanner scanIn = new Scanner(System.in);
		input = scanIn.nextLine();
		scanIn.close();
		String[] strNums = input.split(",");
		return new int[] { getInt(strNums[0]), getInt(strNums[1]),
				getInt(strNums[2]) };
	}

	private static int getInt(String str) {
		return Integer.parseInt(str);
	}
}

class Processer {
	private int first;
	private int second;
	private int third;

	private static final int DECIMAL_BASE = 10;

	public Processer(int first, int second, int third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}

	public void process(List<Student> students) {
		processFirst(students);
		processSecond(students);
		processThird(students);
	}

	private void processFirst(List<Student> students) {
		for (int i = 0; i < DECIMAL_BASE; i++) {
			students.get(first + i * DECIMAL_BASE - 1).setSpecial(true);
			students.get(first * DECIMAL_BASE + i - 1).setSpecial(true);
		}

		if (first == 1) {
			students.get(Student.MAX - 1).setSpecial(true);
		}

		int maxMulti = Student.MAX / first;
		for (int i = 1; i <= maxMulti; i++) {
			students.get(i * first - 1).setFizz(true);
		}
	}

	private void processSecond(List<Student> students) {
		int maxMulti = Student.MAX / second;
		for (int i = 1; i <= maxMulti; i++) {
			students.get(i * second - 1).setBuzz(true);
		}
	}

	private void processThird(List<Student> students) {
		int maxMulti = Student.MAX / third;
		for (int i = 1; i <= maxMulti; i++) {
			students.get(i * third - 1).setWhizz(true);
		}
	}
}

class Student {

	private int idx;

	private boolean fizz;
	private boolean buzz;
	private boolean whizz;
	private boolean special;

	private static final String FIZZ = "Fizz";
	private static final String BUZZ = "Buzz";
	private static final String WHIZZ = "Whizz";

	public static final int MAX = 100;

	public Student(int idx) {
		this.idx = idx;
	}

	public void setFizz(boolean fizz) {
		this.fizz = fizz;
	}

	public void setBuzz(boolean buzz) {
		this.buzz = buzz;
	}

	public void setWhizz(boolean whizz) {
		this.whizz = whizz;
	}

	public void setSpecial(boolean special) {
		this.special = special;
	}

	public void speak() {
		if (special) {
			println(FIZZ);
		} else {
			StringBuilder stringBuilder = new StringBuilder();
			if (fizz) {
				stringBuilder.append(FIZZ);
			}
			if (buzz) {
				stringBuilder.append(BUZZ);
			}
			if (whizz) {
				stringBuilder.append(WHIZZ);
			}
			if (stringBuilder.length() > 0) {
				println(stringBuilder.toString());
			} else {
				println(idx);
			}
		}
	}

	private static void println(Object str) {
		System.out.println(str);
	}
}