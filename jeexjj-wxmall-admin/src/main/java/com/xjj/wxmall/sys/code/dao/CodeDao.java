package com.xjj.wxmall.sys.code.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.xjj.wxmall.sys.code.entity.ColumnInfo;
import com.xjj.wxmall.sys.code.entity.TableInfo;

public interface CodeDao  {
	public List<String> findTableList();
	public String getDataBaseName();
	public TableInfo getTableInfoByName(@Param("tableName") String tableName,@Param("dbName") String dbName);
	public List<ColumnInfo> findColumnsByTable(@Param("tableName") String tableName,@Param("dbName") String dbName);
}
