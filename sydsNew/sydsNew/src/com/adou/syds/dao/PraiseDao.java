package com.adou.syds.dao;

import java.sql.SQLException;

import com.adou.syds.domain.Praise;

public abstract interface PraiseDao {

	boolean priase(Praise praise) throws SQLException;

	boolean cancelPriase(Praise praise)throws SQLException;

}
