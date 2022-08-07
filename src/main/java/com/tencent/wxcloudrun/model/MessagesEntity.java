package com.tencent.wxcloudrun.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class MessagesEntity  implements Serializable {
	private int id;
	private String user_name;
	private String msg_type;
	private String content;
	private String create_time;
	private String object;
}
