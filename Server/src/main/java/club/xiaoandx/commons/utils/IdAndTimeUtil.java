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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;


/**  
 * <p>工具类</p> 
 * @ClassName:IdAndTimeUtil   
 * @author: XIAOX.周巍 
 * @date: 2019年5月15日 下午8:37:02   
 * @since: JDK1.8 
 * @version V2.0
 * @Copyright: 注意：本内容仅限于学习传阅，禁止外泄以及用于其他的商业目
 */
public class IdAndTimeUtil {
	
	/**
	 *<p>生成试题编号（ID）</p> 
	 * @Title: getQuestionId    
	 * @version:V2.0     
	 * @return:String	随机id
	 */
	public static String getQuestionId() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	/**
	 *<p>获取当前时间</p> 
	 * @Title: getNewDate    
	 * @version:V2.0     
	 * @return:Timestamp 当前日期
	 */
	public static Timestamp getNewDate(){
		return new Timestamp( System.currentTimeMillis());
	}
	
	/**
	 *<p>获取当前事件格式为（yyyy-mm-dd）</p> 
	 * @Title: getDate    
	 * @version:V2.0     
	 * @return:String  日期字符串
	 */
	public static String getDate () {
		Date day = new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		return df.format(day);
	}
	
	/**
	 *<p>随机生成题号（1980-15726）</p> 
	 * @Title: getQuestion    
	 * @version:V2.0     
	 * @return:String	试题id
	 */
	public static String getQuestion() {
		Integer number =  (int)(Math.random()*(13747)+1980);
		return String.valueOf(number);
	}
	
	/**
	 *<p>计算时间差（yyyy-MM-dd）</p> 
	 * @Title: startToEnd    
	 * @version:V0.1     
	 * @author: XIAOX.周巍 
	 * @param startDate 小时间
	 * @param endDate	大时间
	 * @return:long	返回相差天数（不推荐使用）
	 */
	public static long startToEnd(Date startDate, Date endDate){
        String[] startStr = new SimpleDateFormat("yyyy-MM-dd").format(startDate).split("-");
        String[] endStr = new SimpleDateFormat("yyyy-MM-dd").format(endDate).split("-");
        Integer startYear = Integer.parseInt(startStr[0]);
        Integer startMonth = Integer.parseInt(startStr[1]);
        Integer startDay = Integer.parseInt(startStr[2]);
        Integer endYear = Integer.parseInt(endStr[0]);
        Integer endMonth = Integer.parseInt(endStr[1]);
        Integer endDay = Integer.parseInt(endStr[2]);
        LocalDate endLocalDate = LocalDate.of(endYear,endMonth,endDay);
        LocalDate startLocalDate = LocalDate.of(startYear,startMonth,startDay);
        return startLocalDate.until(endLocalDate,ChronoUnit.DAYS);
    }
	
	/**
	 *<p>计算时间差（yyyy-MM-dd HH:mm:ss）</p> 
	 * @Title: timeSubtraction    
	 * @version:V0.1     
	 * @author: XIAOX.周巍 
	 * @param time1		小的时间（格式 yyyy-MM-dd HH:mm:ss）
	 * @param time2		大的时间（格式 yyyy-MM-dd HH:mm:ss）
	 * @throws Exception    异常抛出，全局处理
	 * @return:int（day 相差天数）		返回时间差（可以更改返回类型）
	 */
	@SuppressWarnings("unused")
	public static long timeSubtraction(String time1, String time2) throws Exception {
	    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
	    long newTime1 = simpleDateFormat.parse(time2).getTime();
	    long newTime2 = simpleDateFormat.parse(time1).getTime();
	    Long result = newTime1 - newTime2;    //获取两时间相差的毫秒数
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    long hour = result % nd / nh;     //获取相差的小时数
	    long min = result % nd % nh / nm;  //获取相差的分钟数
	    long day = result / nd;
	    SimpleDateFormat formatter = new SimpleDateFormat("HH:mm");//初始化Formatter的转换格式。
	    long hMiles = hour * 3600000;  //小时数转换成毫秒
	    long mMiles = min * 60000;    //分钟数转换成毫秒
	    long resulMiles = (hMiles + mMiles);
	    //下面这段很重要 ,计算之后设置时区,不然会差几小时   
	    formatter.setTimeZone(TimeZone.getTimeZone("GMT+00:00"));
	    String resultFormat = formatter.format(resulMiles);
	    //我这段是在一天内计算的 如果大于一天 就把下面的 day*24加到小时上就可以了
	    //return resultFormat + "," + day;
	    return day;
	}
}
