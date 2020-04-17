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
package club.xiaoandx.studio.entity;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**  
 * <p> 
 * 工作室实体
 * </p> 
 * @ClassName:Studio   
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-14 10:29
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@ApiModel("工作室实体")
@Data
public class Studio {
	@ApiModelProperty(name = "sid",value = "工作室id")
	private Integer sid;
	
	@ApiModelProperty(value = "工作室名称", name = "sname")
	private String sname;
	
	@ApiModelProperty(value = "工作室人数", name = "snumber")
	private Integer snumber;
	
	@ApiModelProperty(value = "工作室所在地址", name = "saddress")
	private String saddress;
	
	@ApiModelProperty(value = "工作室负责人QQ", name = "sqq")
	private String sqq;
	
	@ApiModelProperty(value = "工作室简介", name = "ssummary")
	private String ssummary;
	
	@ApiModelProperty(value = "工作室logo地址", name = "slogoPath")
	private String slogoPath;
	
	@ApiModelProperty(value = "工作室审核状态(0未通过、1通过)", name = "sstatus")
	private Integer sstatus;
	
	@ApiModelProperty(value = "提交工作室时间", name = "Date")
	@DateTimeFormat(pattern = "yyyy-MM-dd ")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date stime;
}
