package com.multilang.app.model;

import com.wizarius.orm.database.DBException;
import com.wizarius.orm.database.DatabaseStorage;
import com.wizarius.orm.database.connection.DBConnectionPool;

public class PagesStorage extends DatabaseStorage<PagesEntity>
{
	public PagesStorage(DBConnectionPool pool) throws DBException
	{
        super(pool, PagesEntity.class);
    }
}