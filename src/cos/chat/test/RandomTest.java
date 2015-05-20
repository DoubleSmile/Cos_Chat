package cos.chat.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;

public class RandomTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random random=new Random(17);
		
		System.out.println(RandomUtils.nextInt(1));
		List list=new ArrayList();
		list.add("sdf");
		System.out.println(list.get(0));

	}

}
