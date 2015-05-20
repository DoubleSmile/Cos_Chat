package cos.chat.test;

public class ExceptionTest {
	
	public static void test(){
		System.out.println("抛出异常之前");
		throw new NullPointerException("有错误喽");
	}
	
	public static void testAfter(){
		test();
		System.out.println("抛出异常之后");
	}
	
	public static void main(String[] args){
		try{
			testAfter();
		}catch(NullPointerException e){
			System.out.println("异常被捕捉到 ");
		}
			
	}

}
