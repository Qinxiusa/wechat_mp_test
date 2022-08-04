package com.tencent.wxcloudrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tencent.wxcloudrun.controller.MpController.MsgType;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value="/api")
public class MpController {
	
	
	public enum MsgType{
		Text("text"),//文本
		Image("image"),//图像
		Voice("voice"),//声音
		Video("video"),//视频
		Music("music"),//音乐
		News("news");//图文消息
		
		private String type;
		private MsgType(String type) {
			this.type=type;
		}
		@Override
		public String toString() {
			return this.type;
		}
	}
	private static MsgType msgType;
	
	@Autowired
	private RestTemplate http;
	
	@GetMapping("/test")
	public String testMsg() {
		log.info("test api");
		return "welcome to wechat api test!";
	}
	@GetMapping("/getToken")
	public  String getCloudToken() {
		String url="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxd443973223af430d&secret=54b2add49dc1aed04ae09a0927b27bd2";
		String token=http.getForObject(url, String.class);
		log.info("getCloudToken:{}",token);
		return token;
	}
	@GetMapping("/getip")
	public String getIplist() {
		log.info("getip api");
		String url="https://api.weixin.qq.com/cgi-bin/get_api_domain_ip";
		String iplist=http.getForObject(url,String.class);
		log.info("ip list:{}",iplist);
		return iplist;
	}
	@GetMapping("/getcallbackip")
	public String getCallbackIplist() {
		log.info("get callback ip api");
		String url="https://api.weixin.qq.com/cgi-bin/getcallbackip";
		String iplist=http.getForObject(url,String.class);
		log.info("callback ip:{}",iplist);
		return iplist;
	}
	
	//无作用，取消
//	@GetMapping("/recv")
//	public String getWechatMessage(@RequestBody String requestBody) {
//		
//		log.info("recv wechat get msg:{}",requestBody);
//		
//		return "success";
//	}
	
	/**
	 * use to receive wechat's message
	 * @param requestBody
	 * @return
	 */
	@PostMapping("/recv")
	public String getWechatPostMsg(@RequestBody String requestBody) {
		log.info("recv wechat post msg:{}",requestBody);
		String backMsg="success";

		try {
			JSONObject content=new JSONObject(requestBody);
			
			if(!"".equalsIgnoreCase(content.optString("ToUserName"))) {
//				msgReceiver.put("ToUserName",content.optString("ToUserName"));//小程序、公众号的原始id
//				msgReceiver.put("FromUserName",content.optString("FromUserName"));//用户身份openId
//				msgReceiver.put("CreateTime",content.optString("CreateTime"));//消息时间
//				msgReceiver.put("MsgType",content.optString("MsgType"));//消息类型
//				msgReceiver.put("Content",content.optString("Content"));//消息内容
//				msgReceiver.put("MsgId",content.optString("MsgId"));//消息id
				
				log.info("ToUserName:{}",content.optString("ToUserName"));
				log.info("FromUserName:{}",content.optString("FromUserName"));
				log.info("MsgType:{}",content.optString("MsgType"));
				log.info("Content:{}",content.optString("Content"));
				log.info("MsgId:{}",content.optString("MsgId"));
				
			}else if("CheckContainerPath".equalsIgnoreCase(content.optString("action"))) {
				backMsg="success";
			}
				
		} catch (JSONException e) {
			log.error("string to jsonobject error:{}",e.getMessage());
		}
		
		
		return backMsg;	
	}
}
