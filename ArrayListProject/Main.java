import java.util.List;

public class Main {

	public Main() {
		
		ArrayList<String> arr = new ArrayList<String>();


		//arr.add(1, "aaaaa");
		
		//arr.set(5, "dddddddddd");
		arr.add("aaa");
		arr.add("bbb");
		arr.add("ccc");
		arr.add("ddd");
		arr.add("aaa");
		arr.add("zzz");
		arr.add("vvv");
		
		System.out.println(arr.size());
		
		arr.printArray();
		
		//arr.set(1, "dddddddddd");
		
		arr.remove(5);
		
		System.out.println(arr.size());
		
		//arr.printArray();
		
		//arr.clear();
		
		
		
		
	
		List<String> test = new java.util.ArrayList<String>();
		test.add("aaa");
		test.add("bbb");
		test.add("xxx");
		//test.add("bbb");
		
		arr.removeAll(test);
		//System.out.println(arr.containsAll(test));
		
		//System.out.println(arr.contains("bbb"));
		
		//arr.removeAll(test);

		arr.printArray();
		
		
	}

	public static void main(String[] args) {
		new Main();

	}

}
