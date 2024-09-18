
public class TestCases {

	public static void main(String[] args) {
		testStringInt();
		testIntInt();
		testStringString();
	}

	public static void testStringInt() {
		MyHashMap<String, Integer> m = new MyHashMap<String, Integer>();
		m.put("Pagani", 1);
		m.put("Ducati", 34);
		m.put("Maserati", 67);
		m.put("Ferrari", 47);
		m.put("Lancia",3);
		m.put("Alpha Romeo", 777);
		System.out.println(m.keySet());
		m.printTable();
		System.out.println("Size: " + m.size());
		System.out.println("Contains Key: " + m.containsKey("Maserati"));
		System.out.println("Contains Key: " + m.containsKey("Lancia"));
		m.remove("Pagani");
		m.remove("Ducati");
		m.remove("Maserati");
		m.remove("Ferrari");
		m.remove("Lancia");
		m.remove("Alpha Romeo");
		m.printTable();
	}

	public static void testIntInt() {
		MyHashMap<Integer, Integer> m = new MyHashMap<Integer, Integer>();
		m.put(4, 1);
		m.put(7, 34);
		m.put(21, 67);
		m.put(56, 47);
		m.put(27,3);
		m.put(223, 1776);
		System.out.println(m.keySet());
		m.printTable();
		System.out.println("Size: " + m.size());
		System.out.println("Contains Key: " + m.containsKey(7));
		System.out.println("Contains Value: " + m.containsValue(1776));
		m.remove(4);
		m.remove(7);
		m.remove(21);
		m.remove(56);
		m.remove(27);
		m.remove(223);
		m.printTable();
	}

	public static void testStringString() {
		MyHashMap<String, String> m = new MyHashMap<String, String>();
		m.put("Pagani", "BMW");
		m.put("Ducati", "Audi");
		m.put("Maserati", "Porsche");
		m.put("Ferrari", "VW");
		m.put("Lancia","Opel");
		m.put("Alpha Romeo", "Alpina");
		System.out.println(m.keySet());
		m.printTable();
		System.out.println("Size: " + m.size());
		System.out.println("Contains Key: " + m.containsKey("Maserati"));
		System.out.println("Contains Value: " + m.containsValue("Porsche"));
		m.remove("Pagani");
		m.remove("Ducati");
		m.remove("Maserati");
		m.remove("Ferrari");
		m.remove("Lancia");
		m.remove("Alpha Romeo");
		m.printTable();
	}

}
