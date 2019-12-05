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
package club.xiaoandx.studio.controller;

import club.xiaoandx.commons.core.Parameter;
import club.xiaoandx.commons.core.PublicErrorCode;
import club.xiaoandx.commons.exception.CommonException;
import club.xiaoandx.commons.redis.BaseRedisService;
import club.xiaoandx.commons.redis.ExtApiIdempotent;
import club.xiaoandx.commons.utils.CaptchaUtil;
import club.xiaoandx.commons.utils.RedisTokenUtil;
import club.xiaoandx.studio.entity.Studio;
import club.xiaoandx.studio.service.StudioService;
import club.xiaoandx.studio.vo.AdminUser;
import club.xiaoandx.studio.vo.StatusMessage;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**  
 * <p>
  *  工作室管理相关模块
 * </p> 
 * @ClassName:StudioController   
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-12 14:12
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@RestController
@Api(tags = "工作室管理相关模块")
@RequestMapping("/v1/open/studio")
public class StudioController implements Parameter {
	
	@Autowired
	private StudioService studioService;
	@Autowired
	private RedisTokenUtil redisTokenUtil;
	@Autowired
	private BaseRedisService baseRedisService;

	/**
	 * <p>
	 * 查询所有的工作室
	 * </p>
	 * 
	 * @Title: getStudioList
	 * @version:V0.1
	 * @return:List<Studio> 包含所有的工作室集合
	 */
	@GetMapping(value = "/getStudioList", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "查询所有的工作室", notes = "无需参数<br><br><b>@author xiaox.周巍</b>")
	@ApiResponses({ @ApiResponse(code = 400, message = "未传入指定参数"), @ApiResponse(code = 404, message = "未找到指定页面") })
	public List<Studio> getStudioList() {
		return studioService.getStudioList();
	}
	
	/**
	 * <p>
	 * 查询指定id的工作室
	 * </p>
	 * 
	 * @Title: findById
	 * @version:V0.1
	 * @param id id
	 * @return:Studio 工作室数据
	 */
	@GetMapping(value = "/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "查询指定id的工作室", notes = "id参数<br><br><b>@author xiaox.周巍</b>")
	@ApiResponses({ @ApiResponse(code = 400, message = "未传入指定参数"), @ApiResponse(code = 404, message = "未找到指定页面") })
	public Studio findById(@ApiParam(value = "工作室id*必填", required = true) @PathVariable String id) {
		if (null != id && NO_ZIFUCUAN != id) {
			return studioService.findById(id);
		}
		throw new CommonException(PublicErrorCode.PARAM_EXCEPTION.getIntValue(), "error ID IS NULL");
	}
	
	/**
	 * <p>
	 * 获取七牛云上传图片的凭证token
	 * </p>
	 * 
	 * @Title: getToken
	 * @version:V0.1
	 * @return:Map<String,String> 返回tokenjson字符串
	 */
	@GetMapping(value = "/getToken")
	@ApiOperation(httpMethod = "GET", value = "获取七牛云上传文件token凭证", notes = "返回前端含有token属性的json字符串<b>@author xiaox.周巍</b>")
	@ApiResponses({ @ApiResponse(code = 400, message = "未传入指定参数"), @ApiResponse(code = 404, message = "未找到指定页面") })
	public Map<String, String> getToken() {
		return studioService.getToken();
	}
	
	/**
	 * <p>
	 * 提交保存新的工作室
	 * </p>
	 * 
	 * @Title: addStudio
	 * @version:V0.1
	 * @param studio 上传数据实体
	 * @return:StatusMessage 保存状态
	 */
	@PostMapping(value = "/addStudio", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "提交保存新的工作室", notes = "需要传入工作室数据，封装为一个对象传给后端）<br><b>@author xiaox.周巍</b>")
	@ApiResponses({ @ApiResponse(code = 400, message = "未传入指定参数"), @ApiResponse(code = 404, message = "未找到指定页面") })
	@ExtApiIdempotent(value = Parameter.EXTAPIHEAD)
	public StatusMessage addStudio(@ApiParam(value = "工作室数据对象*必填", required = true) @RequestBody Studio studio) {
		if (null != studio.getSname() && NO_ZIFUCUAN != studio.getSname()) {
			if (!ENTER_NUMBER.equals(studio.getSnumber())) {
				studioService.addStudio(studio);
				return new StatusMessage(RESPOSE_SUCCESS, "SUCCESS");
			}
			return new StatusMessage(RESPOSE_ERROR, "PeopleNumber is zero.");
		}
		return new StatusMessage(RESPOSE_ERROR, "Name is null.");
	}

	/**
	 * <p>
	 * 修改工作室
	 * </p>
	 * 
	 * @Title: updateStudio
	 * @version:V0.1
	 * @param studio 工作室更新数据对象
	 * @return:StatusMessage 操作结果
	 */
	@PutMapping(value = "/updateStudio", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "PUT", value = "修改工作室", notes = "修改工作室（根据id）<br><br><b>@author xiaox.周巍</b>")
	@ApiResponses({ @ApiResponse(code = 400, message = "未传入指定参数"), @ApiResponse(code = 404, message = "未找到指定页面") })
	public StatusMessage updateStudio(@ApiParam(value = "修改工作室的数据对象*必填", required = true) @RequestBody Studio studio) {
		if (null != studio && !ENTER_NUMBER.equals(studio.getSid())) {
			try {
				studioService.updateStudio(studio);
				return new StatusMessage(RESPOSE_SUCCESS, "SUCCESS");
			} catch (Exception e) {
				return new StatusMessage(RESPOSE_ERROR, "Update Error");
			}
		}
		return new StatusMessage(RESPOSE_ERROR, "Parameter error");
	}

	/**
	 * <p>
	 * 删除工作室
	 * </p>
	 * 
	 * @Title: deleteStudio
	 * @version:V0.1
	 * @param sid 要删除的工作室id
	 * @return:StatusMessage 操作结果
	 */
	@DeleteMapping(value = "/deleteStudio/{sid}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "DELETE", value = "删除工作室", notes = "删除工作室（根据sid）<br><br><b>@author xiaox.周巍</b>")
	@ApiResponses({ @ApiResponse(code = 400, message = "未传入指定参数"), @ApiResponse(code = 404, message = "未找到指定页面") })
	public StatusMessage deleteStudio(
			@ApiParam(value = "删除工作室（根据sid）*必填", required = true) @PathVariable("sid") Integer sid) {
		if (null != sid && !ENTER_NUMBER.equals(sid)) {
			try {
				studioService.deleteStudio(sid);
				return new StatusMessage(RESPOSE_SUCCESS, "SUCCESS");
			} catch (Exception e) {
				return new StatusMessage(RESPOSE_ERROR, "Delete Error");
			}
		}
		return new StatusMessage(RESPOSE_ERROR, "Parameter error");
	}
	
	/**
	 *<p>
	 *	获取操作token
	 *</p> 
	 * @Title: RedisToken    
	 * @version:V0.1     
	 * @return    
	 * @return:String
	 */
	@GetMapping("/redisToken")
	@ApiOperation(httpMethod = "GET", value = "获取操作token", notes = "获取操作token<br><br><b>@author xiaox.周巍</b>")
	@ApiResponses({ @ApiResponse(code = 400, message = "未传入指定参数"), @ApiResponse(code = 404, message = "未找到指定页面") })
    public String RedisToken() {
        return redisTokenUtil.getToken();
    }
	
	/**
	 *<p> 
	 *	获取验证码
	 *</p> 
	 * @Title: doGet    
	 * @version:V0.1     
	 * @param request
	 * @param response
	 * @throws Exception    
	 * @return:void
	 */
	@GetMapping("/user/check.jpg")
	@ApiOperation(httpMethod = "GET", value = "获取验证码", notes = "获取验证码<br><br><b>@author xiaox.周巍</b>")
	@ApiResponses({ @ApiResponse(code = 400, message = "未传入指定参数"), @ApiResponse(code = 404, message = "未找到指定页面") })
	public void check(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().removeAttribute("code");
		try {
			// 通知浏览器不要缓存
			response.setHeader("Expires", "-1");
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "-1");
			CaptchaUtil util = CaptchaUtil.Instance();
			// 将验证码输入到session中，用来验证
			String code = util.getString();
			request.getSession().setAttribute("code", code);
			//将code写入redis
			baseRedisService.setString(code, code, REDISTIME);
			// 输出打web页面
			ImageIO.write(util.getImage(), "jpg", response.getOutputStream());
		} catch (IOException e) {
			throw new CommonException(PublicErrorCode.PARAM_EXCEPTION.getIntValue(), "check generate error");
		}
	}

	/**
	 * @Title: doLogin
	 * @Description 后台管理系统登录
	 * @Date 17:53 2019/11/7
	 * @version:V0.1
	 * @Author: zhouwei
	 * @return club.xiaoandx.studio.vo.StatusMessage
	 **/
	@PostMapping(value = "/doLogin", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "POST", value = "管理员登录", notes = "管理员登录<br><br><b>@author xiaox.周巍</b>")
	@ApiResponses({ @ApiResponse(code = 400, message = "未传入指定参数"), @ApiResponse(code = 404, message = "未找到指定页面") })
	public StatusMessage doLogin(@RequestBody AdminUser adu , HttpServletRequest req){
		if (null != adu.getUserName() && !NO_ZIFUCUAN.equals(adu.getUserName())){
			if (null != adu.getPassWord() && !NO_ZIFUCUAN.equals(adu.getPassWord())){
				if(adu.getCheck().equalsIgnoreCase((String)req.getSession().getAttribute("code")) || adu.getCheck().equalsIgnoreCase((String) baseRedisService.getString(adu.getCheck()))){
					req.getSession().removeAttribute("code");
					baseRedisService.delKey(adu.getCheck());
					if( ONE_NUMBER.equals(studioService.doLogin(adu, req))){
						return new StatusMessage(RESPOSE_SUCCESS, "SUCCESS");
					}
					return new StatusMessage(RESPOSE_ERROR, "NOUser");
				}
				return new StatusMessage(RESPOSE_ERROR, "check error");
			}
			return new StatusMessage(RESPOSE_ERROR, "Parameter error");
		}
		return new StatusMessage(RESPOSE_ERROR, "Parameter error");
	}

	/**
	 * @Title: getUserSession
	 * @Description 获取管理员登录session
	 * @Date 11:45 2019/11/8
	 * @version:V0.1
	 * @Author: zhouwei
	 * @Param [req, userName]
	 * @return java.lang.String
	 **/
	@GetMapping(value = "/getUserSession/{userName}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(httpMethod = "GET", value = "获取管理员SESSION", notes = "获取管理员SESSION<br><br><b>@author xiaox.周巍</b>")
	@ApiResponses({ @ApiResponse(code = 400, message = "未传入指定参数"), @ApiResponse(code = 404, message = "未找到指定页面") })
	public String getUserSession(HttpServletRequest req, @PathVariable String userName) throws CommonException {
		String userSession;
		try {
			userSession = (String)req.getSession().getAttribute(userName);
			if(null != userSession || null != baseRedisService.getString(userName)){return userSession;}
			throw new CommonException(PublicErrorCode.QUERY_EXCEPTION.getIntValue(), "NOSession");
		}catch (Exception e){throw new CommonException(PublicErrorCode.QUERY_EXCEPTION.getIntValue(), "GetSessionError");}
	}
}
