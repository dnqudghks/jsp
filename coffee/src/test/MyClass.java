package test;

import java.util.ArrayList;

public class MyClass implements MyInter {

	
	@Override
	public void toPrint() {
		System.out.println("여기는 MyClass");
	}
	public static void main (String[] args) {
		ArrayList<MyInter> inter = new ArrayList<MyInter>();
		inter.add(new MyClass());
		inter.add(new MyCls());
		for(int i = 0 ; i < inter.size(); i++) {
			inter.get(i).toPrint();
	}
	}
	public void abc(){
		
	}
}

class MyCls implements MyInter {
	@Override
	public void toPrint() {
	System.out.println("여기는 MyCls");
}

}