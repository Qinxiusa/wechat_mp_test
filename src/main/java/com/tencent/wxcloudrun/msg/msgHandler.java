package com.tencent.wxcloudrun.msg;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class msgHandler {

	public String openId;
	public String mpId;
	public String CreateTime;
	public WechatMsgType MsgType;
	public String Content;
	public String MsgId;
	public JSONObject responseJson;
	
	public enum WechatMsgType{
		Text("text"),//文本
		Image("image"),//图像
		Voice("voice"),//声音
		Video("video"),//视频
		Music("music"),//音乐
		News("news");//图文消息
		
		private String type;
		private WechatMsgType(String type) {
			this.type=type;
		}
		@Override
		public String toString() {
			return this.type;
		}
		public static WechatMsgType getEnum(String value) {
			for(WechatMsgType m:WechatMsgType.values()) {
				if(m.type.equalsIgnoreCase(value)) {
					return m;
				}
			}
			throw new IllegalArgumentException();
		}
	}
	
	public String responseMsg() {
		
		if(responseJson==null){
			responseJson=new JSONObject();
		}
			
		if(MsgType==null) {
			responseJson.put("result","msgtype is null");
		}else {
			responseJson.put("ToUserName",openId);
			responseJson.put("FromUserName", mpId);
			responseJson.put("CreateTime",System.currentTimeMillis());
			responseJson.put("MsgType",MsgType.toString());
			
			switch(MsgType) {
			case Text:
				responseJson.put("Content",responseText());
				break;
			case Image:
				
				break;
			case Voice:
				break;
			case Video:
				break;
			case Music:
				break;
			case News:
				break;
			default:
				break;
			}	
			
			responseJson.put("result","success");
		}
		
		return responseJson.toString();
	}
	private static String responseText() {
		String msg="你好,先生!";

		return msg;
	}
}
