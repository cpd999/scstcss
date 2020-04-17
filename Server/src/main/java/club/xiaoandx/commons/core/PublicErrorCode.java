package club.xiaoandx.commons.core;

/**
 * <p>公共的错误码</p> 
 * @ClassName:PublicErrorCode   
 * @author: XIAOX.周巍 
 * @date: 2019年5月22日 上午10:57:42   
 * @since: JDK1.8 
 * @version V2.0
 * @Copyright: 注意：本内容仅限于学习传阅，禁止外泄以及用于其他的商业目
 */
public enum PublicErrorCode {


	QUERY_EXCEPTION(48001000),//查询异常
	SAVE_EXCEPTION(48002000),//保存/异常
	UPDATE_EXCEPTION(48006000),//修改异常
	DELETE_EXCEPTION(48003000),//删除异常
	PARAM_EXCEPTION(48004000),//参数异常
	AUTH_EXCEPTION(48005000),//权限异常
	OPERATION_EXCEPTION(4008909);//获取token异常

	private Integer intValue;
	private String strValue;

	PublicErrorCode(Integer stateCode) {
		this.intValue = stateCode;
		switch(stateCode) {
		case 48001000:
			this.strValue = "查询异常";
			break;
		case 48002000:
			this.strValue = "保存异常";
			break;
		case 48003000:
			this.strValue = "删除异常";
			break;
		case 48004000:
			this.strValue = "参数异常";
			break;
		case 48005000:
			this.strValue = "权限异常";
			break;
		case 48006000:
			this.strValue = "修改异常";
			break;
		case 4008909:
			this.strValue = "获取token异常";
			break;
		default:
			this.strValue = "未知异常";
		}
	}
	public Integer getIntValue() {
		return intValue;
	}
	public String getStrValue() {
		return strValue;
	}
}
