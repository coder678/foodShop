package com.school.foodShop.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.school.foodShop.dao.ShopCategoryDao;
import com.school.foodShop.entity.ShopCategory;
import com.school.foodShop.service.ShopCategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ShopCategoryServiceImpl implements ShopCategoryService {

	@Autowired
	private ShopCategoryDao shopCategoryDao;



	@Override
	public List<ShopCategory> getShopCategoryList(ShopCategory shopCategoryCondition) {

		return shopCategoryDao.queryShopCategory(shopCategoryCondition);
	}

}
