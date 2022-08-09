package com.tencent.wxcloudrun.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.tencent.wxcloudrun.model.MessagesEntity;


@Mapper
public interface MessagesMapper {

	List<MessagesEntity> selectByType(@Param("msgType")String msg_type);//单个参数可以不使用@param,这边的参数和数据库表字段的名称没有关系
	int insertData(MessagesEntity message);
	int deleteByIds(int[]ids);
	int updateMsg(@Param("id")int id,@Param("object")String object);
	String selectContentByObject(String object);
	String selectCreateTimeByObject(String object);
}