package com.blucargo.dao;

import com.blucargo.model.CarBody;
import com.wideplay.warp.persist.Transactional;

@Transactional
public class CarBodyDao extends BaseDao<CarBody,Long> {

	protected CarBodyDao() {
		super(CarBody.class);
	}

}
