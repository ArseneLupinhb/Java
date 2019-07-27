package com.adou.syds.service.impl;

import java.sql.SQLException;

import com.adou.syds.dao.PraiseDao;
import com.adou.syds.dao.impl.PraiseDaoImpl;
import com.adou.syds.domain.Praise;
import com.adou.syds.service.PraiseService;

public class PraiseServiceImpl implements PraiseService {

	PraiseDao praiseDao = new PraiseDaoImpl();
	@Override
	public boolean praise(Praise praise) throws SQLException {
		
		return praiseDao.priase(praise);
	}
	@Override
	public boolean cancelPraise(Praise praise) throws SQLException {
		
		return praiseDao.cancelPriase(praise);
	}

}
