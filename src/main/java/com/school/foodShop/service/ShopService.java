package com.school.foodShop.service;


import com.school.foodShop.exceptions.ShopOperationException;
import com.school.foodShop.dto.ImageHolder;
import com.school.foodShop.dto.ShopExecution;
import com.school.foodShop.entity.Shop;

public interface ShopService {
	/**
	 * 根据shopCondition分页返回相应店铺列表
	 * 
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

	/**
	 * 通过店铺Id获取店铺信息
	 * 
	 * @param shopId
	 * @return
	 */
	Shop getByShopId(long shopId);

	/**
	 * 更新店铺信息，包括对图片的处理
	 * 
	 * @param shop
	 * @param shopImg
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution modifyShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;

	/**
	 * 注册店铺信息，包括图片处理
	 * 
	 * @param shop
	 * @param shopImgInputStream
	 * @param fileName
	 * @return
	 * @throws ShopOperationException
	 */
	ShopExecution addShop(Shop shop, ImageHolder thumbnail) throws ShopOperationException;
}
