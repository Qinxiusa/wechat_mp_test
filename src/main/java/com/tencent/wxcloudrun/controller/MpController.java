package com.tencent.wxcloudrun.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.tencent.wxcloudrun.dao.MessagesMapper;
import com.tencent.wxcloudrun.model.MessagesEntity;
import com.tencent.wxcloudrun.msg.msgHandler;
import com.tencent.wxcloudrun.msg.msgHandler.WechatMsgType;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value="/api")
public class MpController {
	
	private static String mpId;
	private static String openId;
	private static String msgContent;
	
	@Autowired
	private RestTemplate http;
	@Autowired
	private msgHandler wechatMsg;
	@Autowired
	private MessagesMapper messageMapper;
	
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
		String result="success";
		log.info("recv wechat post msg:{}",requestBody);
		
		JSONObject content=new JSONObject(requestBody);
		
		if(content!=null) {
			if(content.has("action")) {
				if("CheckContainerPath".equalsIgnoreCase(content.optString("action"))){
					return result;
				}else {
					return content.optString("action");
				}
			}else if(content.has("MsgType")) {
				
				//被动回复消息，用户发消息到公众号，微信会将消息转发至此，可以在此进行拦截回复
				log.info("decode msg");
				
				if(wechatMsg!=null) {
					result=wechatMsg.responseMsg(content);				
				}else {
					result=wechatMsg.responseException("wechatMsg is null");
				}
			}
		}
		
		log.info("response:{}",result);
		
		return result;
	}
	@GetMapping("/clearmsg")
	public String clearMessage(int beg,int end) {
	
		int []ids=new int[end-beg+1];
		
		for(int i=beg,j=0;i<=end;i++) {
			ids[j]=i;
			j++;
		}
		
		int res=messageMapper.deleteByIds(ids);
		log.info("clear msg:{}",res);
		return "success:"+Integer.toString(res);
	}
	@GetMapping("/updatemsg")
	public String updateMsg(int id,String object) {
		
		int res=messageMapper.updateMsg(id, object);
		log.info("update msg:{},id:{},content:{}",res,id,object);
		return "success";
		
	}
	@GetMapping("/selectmsg")
	public String selectMsg(String object) {
		
		String result=messageMapper.selectContentByObject(object);
		
		if(result==null) {
			return "find nothing";
		}
		
		log.info("select msg:{}",result);
		
		return result.toString();
	}
}
