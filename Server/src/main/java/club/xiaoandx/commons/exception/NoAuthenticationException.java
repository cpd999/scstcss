/**
 * 
 */
package club.xiaoandx.commons.exception;


import club.xiaoandx.commons.core.PublicErrorCode;
import lombok.Getter;

/**
 * 未授权非检查异常
 * @author zhen.pan
 *
 */
public class NoAuthenticationException extends RuntimeException {
	private static final long serialVersionUID = -1760130004947794055L;
	@Getter
    protected Integer code;
	
	public NoAuthenticationException() {
		super("Token invalid. Please re-obtain the latest token");
		this.code = PublicErrorCode.OPERATION_EXCEPTION.getIntValue();
	}
	
	public NoAuthenticationException(Integer code, String message) {
		super(message);
		this.code = code;
	}
}
