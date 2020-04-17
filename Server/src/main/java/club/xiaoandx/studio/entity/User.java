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

import lombok.Data;
import lombok.ToString;

/**
 * <p>
 *
 * </p>
 *
 * @version V0.1
 * @ClassName:AdminUser
 * @author: xiaoandx.zhouwei
 * @date: 2019/11/7 17:43
 * @since: JDK1.8
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
@ToString
@Data
public class User {
    private  Integer id;
    private  String userName;
    private  String passWord;
}
