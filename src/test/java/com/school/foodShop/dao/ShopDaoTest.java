package com.school.foodShop.dao;

import com.school.foodShop.BaseTest;
import com.school.foodShop.dto.ImageHolder;
import com.school.foodShop.dto.ShopExecution;
import com.school.foodShop.entity.Area;
import com.school.foodShop.entity.PersonInfo;
import com.school.foodShop.entity.Shop;
import com.school.foodShop.entity.ShopCategory;
import com.school.foodShop.exceptions.ShopOperationException;
import com.school.foodShop.service.ShopService;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ShopDaoTest extends BaseTest {
	@Autowired
	private ShopDao shopDao;


	@Autowired
    private ShopService shopService;





	@Test
	public void testQueryShopListAndCount() {
		Shop shopCondition = new Shop();
		ShopCategory childCategory = new ShopCategory();
		ShopCategory parentCategory = new ShopCategory();
		parentCategory.setShopCategoryId(1L);
		childCategory.setParent(parentCategory);
		shopCondition.setShopCategory(childCategory);
		List<Shop> shopList = shopDao.queryShopList(shopCondition, 0, 6);
		int count = shopDao.queryShopCount(shopCondition);
		System.out.println(shopList);
		System.out.println("店铺列表的大小：" + shopList.size());
		System.out.println("店铺总数：" + count);
	}

	@Test
	public void testModifyShop() throws ShopOperationException, FileNotFoundException {
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopName("修改后的店铺名称");
		File shopImg = new File("D:/中转站/foodShop/img/dabai.jpg");
		InputStream is = new FileInputStream(shopImg);
		ImageHolder imageHolder = new ImageHolder("dabai.jpg", is);
		ShopExecution shopExecution = shopService.modifyShop(shop, imageHolder);
		System.out.println("新的图片地址为：" + shopExecution.getShop().getShopImg());
	}

	@Test
	public void testInsertShop() {
		Shop shop = new Shop();
		PersonInfo owner = new PersonInfo();
		Area area = new Area();
		ShopCategory shopCategory = new ShopCategory();
		owner.setUserId(1L);
		area.setAreaId(1);
		shopCategory.setShopCategoryId(1L);
		shop.setOwner(owner);
		shop.setArea(area);
		shop.setShopCategory(shopCategory);
		shop.setShopName("测试的店铺");
		shop.setShopDesc("test");
		shop.setShopAddr("test");
		shop.setPhone("test");
		shop.setShopImg("test");
		shop.setCreateTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("审核中");
		int effectedNum = shopDao.insertShop(shop);
		System.out.println("测试成功");
		assertEquals(1, effectedNum);
	}

	@Test
	public void testUpdateShop() {
		Shop shop = new Shop();
		shop.setShopId(1L);
		shop.setShopDesc("测试描述");
		shop.setShopAddr("测试地址");
		shop.setLastEditTime(new Date());
		int effectedNum = shopDao.updateShop(shop);
		assertEquals(1, effectedNum);
		System.out.println("更新完成");
	}


	@Test
	public void testQueryByShopId() {
		long shopId = 1;
		Shop shop = shopDao.queryByShopId(shopId);
		System.out.println(shop);
		System.out.println("areaId:" + shop.getArea().getAreaId());
		System.out.println("areaName" + shop.getArea().getAreaName());

	}

}


















