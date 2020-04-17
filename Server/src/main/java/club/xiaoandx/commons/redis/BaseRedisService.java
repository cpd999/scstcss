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
package club.xiaoandx.commons.redis;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**  
 * <p> 
 *	redis 操作方法：插入key-value，并设置失效时长、查询，通过key查询对应的value值、删除，通过可以删除redis中的数据
 * </p> 
 * @ClassName:BaseRedisService   
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-21 11:12
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@Component
public class BaseRedisService {
 
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

 	/**
 	 * @Title: setString
 	 * @Description 插入redis数据具体方法（通过StringRedisTemplate进行具体实现）
 	 * @Date 8:55 2019/11/19
 	 * @version:V0.1
 	 * @Author: zhouwei
 	 * @Param [key, data, timeout]
 	 * @return void
 	 **/
	public void setString(String key, Object data, Long timeout) {
		if (data instanceof String) {
			String value = (String) data;
			stringRedisTemplate.opsForValue().set(key, value);
		}
		if (timeout != null) {
			stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
		}
	}

	/**
	 * @Title: getString
	 * @Description 通过key 查询redis中的value数据
	 * @Date 8:57 2019/11/19
	 * @version:V0.1
	 * @Author: zhouwei
	 * @Param [key]
	 * @return java.lang.Object
	 **/
	public Object getString(String key) {
		return stringRedisTemplate.opsForValue().get(key);
	}

	/**
	 * @Title: delKey
	 * @Description 通过key删除redis缓存中的数据，注意：删除的是对应key的value值，并非所有数据
	 * @Date 8:58 2019/11/19
	 * @version:V0.1
	 * @Author: zhouwei
	 * @Param [key]
	 * @return void
	 **/
	public void delKey(String key) {
		stringRedisTemplate.delete(key);
	}
}
