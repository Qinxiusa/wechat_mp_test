package com.tencent.wxcloudrun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping(value="/api")
public class MpController {
	
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

		return backMsg;	
	}


}
