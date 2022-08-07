package com.tencent.wxcloudrun.msg;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.tencent.wxcloudrun.dao.MessagesMapper;
import com.tencent.wxcloudrun.model.MessagesEntity;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Component
@Slf4j
public class msgHandler {

	public String openId;
	public String mpId;
	public String CreateTime;
	public WechatMsgType MsgType;
	public String msgContent;
	public String MsgId;
	public JSONObject responseJson;
	public String url;
	public String mediaId;
	
	@Autowired
	private  MessagesMapper messageMapper;
	
	public enum WechatMsgType{
		Text("text"),//文本
		Image("image"),//图像
		Voice("voice"),//声音
		Video("video"),//视频
		Music("music"),//音乐
		News("news"),//图文消息
		Location("location"),//位置消息
		Link("link");//链接消息
		
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
	static List<String> ans=Arrays.asList(
			"老妖婆",
			"老不死的",
			"唯恐天下不乱",
			"滚出地球",
			"为老不尊",
			"利益至上"
				);	
	
	private static List<String> bringUpMsg=Arrays.asList(
			"教育孩子不要“数罪并罚”\r\n"
			+ "\r\n"
			+ "\"数罪并罚\"首先说明家长平时与孩子相处的时间太少，关心得太少。\r\n"
			+ "\r\n"
			+ "其次，也说明家长比较爱\"记仇\"，喜欢翻旧账。\r\n"
			+ "\r\n"
			+ "有时不是你的话没有道理，而是你的方式不对。方式不对，教育一定没有效果。\r\n"
			+ "\r\n"
			+ "为人父母，所有的权力都意味着责任，这样的责任逼着我们要改变。",
			"",
			"不能生气了就教育\r\n"
			+ "\r\n"
			+ "在教育中，克制比宣泄更重要。\r\n"
			+ "\r\n"
			+ "当孩子惹我们生气时，一定要忍一忍，先不急着发火，因为我们无论做什么事，首先冒出来的\"第一念头\"往往是有问题的。\r\n"
			+ "\r\n"
			+ "有时候孩子犯的错误可能也不算什么事，只不过不合我们的意，或者时间不对，正好撞在了家长情绪的枪口上了。\r\n"
			+ "\r\n"
			+ "对父母而言，所谓的克制，就是避免\"第一念头\"。对待孩子\"理直气壮、义正辞严\"应该改为\"理直气和，义正词婉\"。\r\n"
			+ "\r\n"
			+ "管教从严，讲的也是原则从严，心思从严，态度则要尽可能的诚恳、温和、耐心。",
			"不能想起了才教育\r\n"
			+ "\r\n"
			+ "教育孩子一定要持之以恒，教育孩子一定不能忽视细节。\r\n"
			+ "\r\n"
			+ "一位朋友在英国看见一位奶奶带着小孙子上公交车，小男孩先踏了上去，结果被奶奶叫了下来，奶奶要自己先走，还对小男孩说了句：“女士优先！”\r\n"
			+ "\r\n"
			+ "绅士教育大概就是这样从小培养的吧！\r\n"
			+ "\r\n"
			+ "美国著名教师保罗·克拉克写过一本《优秀是培养出来》的书，书中特别重视对孩子的细节教育，比如不能含着食物说话，推门时如果后面还有人就要为他把门，要用善意的眼睛看人等等，都是孩子成长中的细节。\r\n"
			+ "\r\n"
			+ "如果你没有时刻谨记教育孩子的责任，错过了孩子的成长关键期，后面再想教育就难了。",
			"不能把交谈都变成教育\r\n"
			+ "\r\n"
			+ "和孩子的交流越少，你就越不懂得怎样和孩子交流，不懂怎样和孩子交流，你肯定就越不理解孩子。\r\n"
			+ "\r\n"
			+ "平时有机会和孩子交谈的时候，你都会和他说什么呢？\r\n"
			+ "\r\n"
			+ "一位读初中的孩子告诉我，家长对他说得最多的话就是：饭吃快点，吃完赶快做作业，做完作业赶快睡觉！\r\n"
			+ "\r\n"
			+ "作为父母，你知道孩子内心的压力与苦楚吗？你能走进孩子的内心世界吗？\r\n"
			+ "\r\n"
			+ "父母逐渐从孩子的情感世界中淡出甚至缺席，和孩子的交谈更容易变成说教、批评与斥责。等孩子到了13岁以后，甚至连批评的机会也不留给你了。",
			"不能当众教育孩子\r\n"
			+ "\r\n"
			+ "即使孩子做了最糟糕的事情，你也应该把孩子带回家教育，当众责骂甚至殴打孩子，往往后果非常严重。\r\n"
			+ "\r\n"
			+ "教育最重要的是要尊重人的人格尊严，要保护孩子的心灵，做不到这一点，就没有真正的教育可言。\r\n"
			+ "\r\n"
			+ "对孩子的表扬与批评都是一种情感互动，父母太强势，孩子往往没出息；父母太粗暴，孩子往往性情狂躁。\r\n"
			+ "\r\n"
			+ "有智慧的父母不会对孩子严辞斥责，他们能够意识到教育孩子不能追求立竿见影的效果。",
			"避免吃饭和睡觉时教育孩子\r\n"
			+ "\r\n"
			+ "吃饭是一种享受，是一次交流，是一场聚会。\r\n"
			+ "\r\n"
			+ "而美好的聚会，首先应该在家里面。一些孩子吃饭的时候战战兢兢，吃得非常快，为什么快呢？就为了让家长来不及批评他。\r\n"
			+ "\r\n"
			+ "饭吃得太快对胃不好，对食物的吸收也不好，更谈不上什么餐桌礼仪了。\r\n"
			+ "\r\n"
			+ "而在孩子睡前进行教育，不但无效，还会影响孩子的睡眠。\r\n"
			+ "\r\n"
			+ "孩子进入梦乡的时候，满脑袋都是这些批评的信息，常常做噩梦。\r\n"
			+ "\r\n"
			+ "所以，让孩子吃好睡好，即使他犯了错，你也要找到一个更为恰当的时机进行教育。这就是对孩子做善事。",
			"避免消极教育\r\n"
			+ "\r\n"
			+ "中国文化中缺乏对人真挚的赞扬和鼓励，缺乏从一件小事中体会快乐的传统，缺乏“做一些没有意义的事情人生才有意义”的认识。\r\n"
			+ "\r\n"
			+ "我们习惯“做大事”，取得“大成就”，与之相对应的，就是喜欢批评、否定和消极暗示。\r\n"
			+ "\r\n"
			+ "一件事情你还没尝试，有人就会提醒你肯定做不好，一件事情不合父母心意，很可能就会遭到挖苦与讽刺。\r\n"
			+ "\r\n"
			+ "作为父母，改变自己就要从克制批评的欲望开始，不是对孩子的不足、缺点、短处视而不见，而是在孩子有勇气去尝试、去改变时，助他一臂之力。\r\n"
			+ "\r\n"
			+ "",
			"不拿别人家的孩子对比\r\n"
			+ "\r\n"
			+ "每个孩子都有巨大的差异，每个孩子都有成为\"这个样子\"的理由。教育孩子，最好就是针对他的\"这个样子\"进行。\r\n"
			+ "\r\n"
			+ "莫扎特五岁拉得一手好琴，七岁就会谱曲，那是莫扎特。\r\n"
			+ "\r\n"
			+ "对孩子而言，简单的对比解决不了问题，不妨耐心地和他分析，这样孩子既不会自卑，也不会对他人产生妒忌和敌意。"
			);	
	private List<JSONObject> imageBuffer= new ArrayList<JSONObject>();
	
	public String responseMsg() {
		
		log.info("responseMsg function");
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
			
			
			if(msgContent.contains("tiger")) {
				responseJson.put("Image",responseImage(1));
			}else {
				switch(MsgType) {
				case Text:
					log.info("receive text");
					responseJson.put("Content",responseText(msgContent));
					break;
				case Image:
					log.info("receive image");
					responseJson.put("Image",responseImage(0));
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
			}
				
			responseJson.put("result","success");
		}
		
		return responseJson.toString();
	}
	private static String responseText(String recvMsg) {
		String msg="你好,先生!";
		Random idx=new Random();
		if(recvMsg.contains("你好")) {
			msg="你好";
		}else if(recvMsg.contains("帅哥")) {
			msg="美女";
		}else if(recvMsg.contains("佩洛西")) {
			msg=ans.get(idx.nextInt(ans.size()));
		}else if(recvMsg.contains("儿童教育")) {
			msg=bringUpMsg.get(idx.nextInt(bringUpMsg.size()));
		}
		
		return msg;
	}
	private  JSONObject responseImage(int id) {
		JSONObject json=new JSONObject();
		
		if(id==0) {
			//add image to db	
			MessagesEntity message=new MessagesEntity();
			message.setMsg_type("image");
			message.setUser_name(openId);
			message.setCreate_time(CreateTime);
			json.put("url",url);
			json.put("MediaId",mediaId);
			message.setContent(json.toString());
			
			if(messageMapper==null) {
				json.put("result","mapper is null,no insert!");
			}else {
				messageMapper.insertData(message);
			}
			
		}else if(id==1) {
			//check image and feedback
			String result=messageMapper.selectContentByObject(msgContent);
			log.info("get image:{}",result);
			if(result.contains("MediaId")) {
				JSONObject data=new JSONObject(result);
				json.put("MediaId",data.optString("MediaId"));
			}
		}
		
		//String url="cbpk30Yh1FgqjYy13oZI7svrQO0mx6urOdeMsJMmBsklyw9rPwMMBtozJnJj3Nrg";
		//json.put("MediaId",url);
		
		log.info("json :{}",json);
		
		return json;
	}
}
