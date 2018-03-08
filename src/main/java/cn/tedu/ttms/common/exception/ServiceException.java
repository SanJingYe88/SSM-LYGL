package cn.tedu.ttms.common.exception;

//细化运行时异常,自己定义一个业务异常 
public class ServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceException() {
		super();
	}

	public ServiceException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

}
