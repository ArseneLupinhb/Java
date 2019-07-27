package com.adou.syds.service;

import java.sql.SQLException;

import com.adou.syds.domain.Praise;

public abstract interface PraiseService {


	boolean praise(Praise praise) throws SQLException;

	boolean cancelPraise(Praise attribute)throws SQLException;

	
}
