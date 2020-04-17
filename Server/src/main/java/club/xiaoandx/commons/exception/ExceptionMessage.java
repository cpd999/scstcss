/**
 * 
 */
package club.xiaoandx.commons.exception;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @ClassName:ExceptionMessage   
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-14 12:58
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@ApiModel(description = "微信用户信息")
@NoArgsConstructor
@ToString
public class ExceptionMessage {
	@ApiModelProperty(value = "错误编码")
	@Getter
	@Setter
	private Integer code;
	@ApiModelProperty(value = "错误详细信息")
	@Getter
	@Setter
	private String message;

	public ExceptionMessage(Integer code, String message) {
		this.code = code;
		this.message = message;
	}
}
