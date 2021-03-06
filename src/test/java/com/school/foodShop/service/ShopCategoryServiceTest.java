package com.school.foodShop.service;


import com.school.foodShop.BaseTest;
import com.school.foodShop.entity.ShopCategory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ShopCategoryServiceTest extends BaseTest {
	@Autowired
	private ShopCategoryService shopCategoryService;

	@Test
	public void testGetShopCategoryList() {
		List<ShopCategory> categoryList = shopCategoryService.getShopCategoryList(null);
		System.out.println(categoryList.get(0).getShopCategoryName());
		ShopCategory shopCategoryCondition = new ShopCategory();
		ShopCategory parent = new ShopCategory();
		parent.setShopCategoryId(10L);
		shopCategoryCondition.setParent(parent);
		categoryList = shopCategoryService.getShopCategoryList(shopCategoryCondition);
		System.out.println(categoryList.get(0).getShopCategoryName());
	}
}
