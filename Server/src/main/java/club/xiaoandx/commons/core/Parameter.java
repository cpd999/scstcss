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
package club.xiaoandx.commons.core;

/**  
 * <p>常用输入变量值</p>
 * @ClassName:Parameter   
 * @author: XIAOX.周巍 
 * @date: 2019年5月22日 上午11:05:39   
 * @since: JDK1.8 
 * @version V2.0
 * @Copyright: 注意：本内容仅限于学习传阅，禁止外泄以及用于其他的商业目
 */
public interface Parameter {
	
	/**
	 * 入参的值0
	 */
	static final Integer ENTER_NUMBER = 0;
	
	/**
	 * 空的字符串""
	 */
	static final String NO_ZIFUCUAN = "";
	
	/**
	 * 入参的值0.00
	 */
	static final Double DOUBLE_NUMBER = 0.00;
	
	/**
	 * 入参的值1
	 */
	static final Integer ONE_NUMBER = 1;
	/**
	 * 成功200200
	 */
	static final Integer RESPOSE_SUCCESS = 200200;
	/**
	 * 失败 400400
	 */
	static final Integer RESPOSE_ERROR = 400400;
	
	/**
	 * 默认工作室logo
	 */
	static final String DEFAULT_PATH = "http://image.xiaoandx.club/xiaoandx.png";
	
	/**
	 * 头部
	 */
	static final String EXTAPIHEAD = "head";
	 
	/**
	 * 表单
	 */
    static final String EXTAPIFROM = "from";

    /**
     * redis 保存时间
     */
    static final long REDISTIME = 60 * 5;
}
