package com.school.foodShop.util;

public class PageCalculator {
	//将要查询页数转换为  起始的记录条数
	public static int calculateRowIndex(int pageIndex, int pageSize) {
		return (pageIndex > 0) ? (pageIndex - 1) * pageSize : 0;
	}
}
