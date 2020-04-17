/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package club.xiaoandx.studio.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**  
 * <p> 
 *	执行状态
 * </p> 
 * @ClassName:StatusMessage   
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-14 13:00
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@ApiModel("操作状态")
@Data
public class StatusMessage {
	@ApiModelProperty(value = "操作状态码")
	private Integer statusCode;
	@ApiModelProperty(value = "操作状态信息")
	private String Message;
	
	/**   
	 * @Title:StatusMessage   
	 * @param statusCode
	 * @param message
	 */
	public StatusMessage(Integer statusCode, String message) {
		this.statusCode = statusCode;
		Message = message;
	}
	
}
