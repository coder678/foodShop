package com.school.foodShop.service;


import com.school.foodShop.entity.ShopCategory;

import java.util.List;

public interface ShopCategoryService {
	public static final String SCLISTKEY = "shopcategorylist";
	/**
	 * 根据查询条件获取ShopCategory列表
	 * 
	 * @param shopCategoryCondition
	 * @return
	 */
	List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition);
}
