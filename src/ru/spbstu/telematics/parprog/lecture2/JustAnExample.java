package ru.spbstu.telematics.parprog.lecture2;

public class JustAnExample {
	
	private String name = "abc";
	private int number = 1;
	private String smth = null;
	
	{
		System.out.println(name);
		name = "abcd";
	}
	
	public JustAnExample() {
		super();
		System.out.println(name);
		name = "abcde";
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static void main(String[] args) {
		JustAnExample example = new JustAnExample();
		System.out.println(example.getName());
		System.out.println(example.getSmth());
		
		int a = 5;
		int b = 2 + 3;
		
		if ( a == b ){
			System.out.println("very true");
		}
		
		Integer aa = new Integer(50000);
		Integer bb = new Integer(50000);
		
		if (aa == bb ) {
			System.out.println("what do you think?");
		}
		
		System.out.println(aa.equals(bb));
		
		aa = bb;
		System.out.println(aa == bb);
		bb = bb + 50001;
		System.out.println(aa == bb);
		
		String str1 = "abcd";
		String str2 = "abc" + "d";
		System.out.println(str1 == str2);
		
		
	}

	public String getSmth() {
		return smth;
	}
	
}
