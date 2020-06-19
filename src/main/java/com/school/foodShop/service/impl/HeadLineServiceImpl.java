package com.school.foodShop.service.impl;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.school.foodShop.dao.HeadLineDao;
import com.school.foodShop.entity.HeadLine;
import com.school.foodShop.service.HeadLineService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class HeadLineServiceImpl implements HeadLineService{
	@Autowired
	private HeadLineDao headLineDao;
	@Override
	public List<HeadLine> getHeadLineList(HeadLine headLineCondition) {
		return headLineDao.queryHeadLine(headLineCondition);
	}
}
























