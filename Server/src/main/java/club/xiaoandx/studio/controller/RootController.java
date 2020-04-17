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
import club.xiaoandx.studio.vo.StatusMessage;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title:
 * @Description 请求根目录(http://api.xiaoandx.club)时提示访问者信息
 * @Date 11:00 2019/11/20
 * @version:V0.1
 * @Author: zhouwei
 **/
@RestController
public class RootController implements Parameter {

    /**
     * @Title: denfult
     * @Description 请求服务器根目录（"/"）请求路径
     * @Date 11:45 2019/11/19
     * @version:V0.1
     * @Author: zhouwei
     * @Param []
     * @return club.xiaoandx.studio.vo.StatusMessage
     **/
    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public StatusMessage deafest(){ return new StatusMessage(RESPOSE_SUCCESS, "Welcome to the SCSTSC Studio API"); }
}
