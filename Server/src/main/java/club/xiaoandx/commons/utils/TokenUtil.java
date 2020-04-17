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
package club.xiaoandx.commons.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *	Token操作
 * </p>
 * 
 * @ClassName:TokenUtil
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-21 11:09
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
public class TokenUtil {

	private static Map<String, Object> tokenMap = new ConcurrentHashMap<String, Object>();

	/**
	 *<p> 
	 *	获取token
	 *</p> 
	 * @Title: getToken    
	 * @version:V0.1     
	 * @return:String
	 */
	public static synchronized String getToken() {
		// 1.生成令牌
		String token = "token-" + System.currentTimeMillis();
		// 2.存入tokenMap
		tokenMap.put(token, token);
		return token;
	}

	/**
	 *<p>
	 *	验证token,并且删除对应的token
	 *</p> 
	 * @Title: exisToken    
	 * @version:V0.1     
	 * @param token
	 * @return:Boolean
	 */
	public static Boolean exisToken(String token) {
		// 1.从集合中获取token
		Object result = tokenMap.get(token);
		if (result == null) {
			return false;
		}
		// 2.删除对应的token
		tokenMap.remove(token);
		return true;
	}
}