package com.multilang.app.model;

import com.wizarius.orm.database.connection.DBConnectionPool;
import com.wizarius.orm.database.DBException;
import com.wizarius.orm.database.DatabaseStorage;

public class LanguagesStorage extends DatabaseStorage<LanguagesEntity>
{
	public LanguagesStorage(DBConnectionPool pool) throws DBException
	{
        super(pool, LanguagesEntity.class);
    }
}