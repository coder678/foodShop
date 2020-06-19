package com.school.foodShop.service;


import com.school.foodShop.entity.Area;

import java.util.List;

public interface AreaService {
	public static final String AREALISTKEY = "arealist";

	/**
	 * 获取区域列表，优先从缓存获取
	 * 
	 * @return
	 */
	List<Area> getAreaList();
}
