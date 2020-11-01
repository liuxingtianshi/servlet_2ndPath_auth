package com.icss.dto;

public class TurnPage {
	public int currentPageNum = 1;             //当前页号             -----------------入参
	public int OnePageCount = 10;              //一页显示的记录数 ----------------入参
	public int allRecordCount;                 //查询结果的所有记录数--------返回值
    public int allPages;                       //查询结果的所有页数   --------返回值
}
