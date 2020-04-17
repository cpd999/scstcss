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
package club.xiaoandx.studio.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import club.xiaoandx.studio.entity.Studio;
import club.xiaoandx.studio.entity.User;
import club.xiaoandx.studio.vo.AdminUser;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 工作室接口
 * </p>
 * 
 * @ClassName:StudioService
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-14 11:05
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
public interface StudioService {

	/**
	 * <p>
	 * </p>
	 * 
	 * @Title: getStudioList
	 * @version:V0.1
	 * @return:List<Studio>
	 */
	public List<Studio> getStudioList();

	/**
	 * <p>
	 * </p>
	 * 
	 * @Title: getToken
	 * @version:V0.1
	 * @return:Map<String,String>
	 */
	public Map<String, String> getToken();

	/**
	 * <p>
	 * </p>
	 * 
	 * @Title: addStudio
	 * @version:V0.1
	 * @param studio
	 * @return:void
	 */
	public void addStudio(Studio studio);

	/**
	 * <p>
	 * </p>
	 * 
	 * @Title: findById
	 * @version:V0.1
	 * @param id
	 * @return
	 * @return:Studio
	 */
	public Studio findById(String id);

	/**
	 * <p>
	 * </p>
	 * 
	 * @Title: updateStudio
	 * @version:V0.1
	 * @param studio
	 * @return:void
	 */
	public void updateStudio(Studio studio);

	/**
	 * <p>
	 * </p>
	 * 
	 * @Title: deleteStudio
	 * @version:V0.1
	 * @param sid
	 * @return:void
	 */
	public void deleteStudio(Integer sid);

	Integer doLogin(AdminUser adu, HttpServletRequest req);
}
