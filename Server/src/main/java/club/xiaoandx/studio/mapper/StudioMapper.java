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
package club.xiaoandx.studio.mapper;

import java.util.List;

import club.xiaoandx.studio.entity.User;
import club.xiaoandx.studio.vo.AdminUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import club.xiaoandx.studio.entity.Studio;

/**
 * <p>
 * 工作室持久层接口
 * </p>
 * 
 * @ClassName:StudioMapper
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-14 11:12
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@Mapper
public interface StudioMapper {

	/**
	 * <p>
	 * 获取工作室列表
	 * </p>
	 * 
	 * @Title: getStudioList
	 * @version:V0.1
	 * @return:List<Studio>
	 */
	List<Studio> getStudioList();

	/**
	 * <p>
	 * 添加工作室
	 * </p>
	 * 
	 * @Title: addStudio
	 * @version:V0.1
	 * @param studio
	 * @return:void
	 */
	void addStudio(Studio studio);

	/**
	 * <p>
	 * 查询某一个工作室的信息
	 * </p>
	 * 
	 * @Title: findById
	 * @version:V0.1
	 * @param id
	 * @return
	 * @return:Studio
	 */
	Studio findById(@Param("sid") Integer id);

	/**
	 * <p>
	 * 更新工作室
	 * </p>
	 * 
	 * @Title: updateStudio
	 * @version:V0.1
	 * @param studio
	 * @return:void
	 */
	void updateStudio(Studio studio);

	/**
	 * <p>
	 * 删除工作室
	 * </p>
	 * 
	 * @Title: deleteStudio
	 * @version:V0.1
	 * @param sid
	 * @return:void
	 */
	void deleteStudio(@Param("sid") Integer sid);

	List<User> doLogin(AdminUser adu);
}
