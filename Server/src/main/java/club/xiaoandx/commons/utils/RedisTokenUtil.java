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

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import club.xiaoandx.commons.redis.BaseRedisService;

/**  
 * <p> 
 *
 * </p> 
 * @ClassName:RedisTokenUtil   
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-21 11:26
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@Component
public class RedisTokenUtil {
	private long timeout = 60 * 10;
	@Autowired
	private BaseRedisService baseRedisService;

	// 将token存入在redis
	public String getToken() {
		String token = "token" + UUID.randomUUID().toString();
		baseRedisService.setString(token, token, timeout);
		return token;
	}

	public boolean findToken(String tokenKey) {
		String token = (String) baseRedisService.getString(tokenKey);
		if (StringUtils.isEmpty(token)) {
			return false;
		}
		// token 获取成功后 删除对应tokenMapstoken
		baseRedisService.delKey(token);
		return true;
	}

}