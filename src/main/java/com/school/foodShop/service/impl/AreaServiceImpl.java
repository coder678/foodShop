package com.school.foodShop.service.impl;

import com.school.foodShop.entity.Area;
import com.school.foodShop.dao.AreaDao;
import com.school.foodShop.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;


	@Override
	public List<Area> getAreaList() {
		return areaDao.queryArea();
	}

}
