package cos.chat.exception;

public class NullPointerException extends Exception{

    /**
	 * 自定义自己使用的Exception，用与在数据库中没有更新时候发出
	 */
	private static final long serialVersionUID = 1L;

	public NullPointerException(String error){
    	   super(error);
       }
	
	public NullPointerException(String error,Exception e){
		super(error,e);
	}
}
