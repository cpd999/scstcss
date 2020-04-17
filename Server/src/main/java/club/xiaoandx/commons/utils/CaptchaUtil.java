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

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * <p>
 * 验证码
 * </p>
 * 
 * @ClassName:CaptchaUtil
 * @author: xiaoandx.zhouwei
 * @date: 2019-10-24 08:51
 * @since: JDK1.8
 * @version V0.1
 * @Copyright: Note: This prohibition leaks and for other commercial projects
 */
public class CaptchaUtil {
	/**
	 * 验证码图片
	 **/
	private BufferedImage image;

	/**
	 * 验证码内容
	 **/
	private String str;

	private static char code[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789".toCharArray();

	public static final String SESSION_CODE_NAME = "code";

	private CaptchaUtil() {
		init();// 初始化属性
	}

	/**
	 * @Title: Instance
	 * @Description 取得CaptchaUtil实例
	 * @Date 10:51 2019/11/20
	 * @version:V0.1
	 * @Author: zhouwei
	 * @Param []
	 * @return club.xiaoandx.commons.utils.CaptchaUtil
	 **/
	public static CaptchaUtil Instance() {
		return new CaptchaUtil();
	}

	/**
	 * @Title: getImage
	 * @Description 取得验证码图片
	 * @Date 10:51 2019/11/20
	 * @version:V0.1
	 * @Author: zhouwei
	 * @Param []
	 * @return java.awt.image.BufferedImage
	 **/
	public BufferedImage getImage() {
		return this.image;
	}

	/*
	 *
	 */
	/**
	 * @Title: getString
	 * @Description 取得图片的验证码内容：如YHjd
	 * @Date 10:52 2019/11/20
	 * @version:V0.1
	 * @Author: zhouwei
	 * @Param []
	 * @return java.lang.String
	 **/
	public String getString() {
		return this.str;
	}

	/**
	 * @Title: init
	 * @Description 初始化生成验证码并生成图片
	 * @Date 10:52 2019/11/20
	 * @version:V0.1
	 * @Author: zhouwei
	 * @Param []
	 * @return void
	 **/
	private void init() {
		// 在内存中创建图象
		int width = 100, height = 40;
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		// 获取图形上下文
		Graphics g = image.getGraphics();
		// 生成随机类
		Random random = new Random();
		// 设定背景色
		g.setColor(getRandColor(200, 250));
		g.fillRect(0, 0, width, height);
		// 设定字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		// 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
		g.setColor(getRandColor(160, 200));
		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);
			g.drawLine(x, y, x + xl, y + yl);
		}
		// 取随机产生的认证码(4位数字)
		String sRand = "";
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(code[random.nextInt(code.length)]);
			sRand += rand;
			// 将认证码显示到图象中
			g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成设置显示位置
			g.drawString(rand, 13 * i + 6, 30);
		}
		// 赋值验证码
		this.str = sRand;

		// 图象生效
		g.dispose();
		// ByteArrayInputStream input = null;
		// ByteArrayOutputStream output = new ByteArrayOutputStream();
		// try {
		// ImageOutputStream imageOut = ImageIO.createImageOutputStream(output);
		// ImageIO.write(image, "JPEG", imageOut);
		// imageOut.close();
		// input = new ByteArrayInputStream(output.toByteArray());
		// } catch (Exception e) {
		// System.out.println("验证码图片产生出现错误：" + e.toString());
		// }
		// this.image = input
		/* 赋值图像 */
		this.image = image;
	}

	/**
	 * @Title: getRandColor
	 * @Description 给定范围获得随机颜色
	 * @Date 10:53 2019/11/20
	 * @version:V0.1
	 * @Author: zhouwei
	 * @Param [fc, bc]
	 * @return java.awt.Color
	 **/
	private Color getRandColor(int fc, int bc) {
		Random random = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int r = fc + random.nextInt(bc - fc);
		int g = fc + random.nextInt(bc - fc);
		int b = fc + random.nextInt(bc - fc);
		return new Color(r, g, b);
	}
}
