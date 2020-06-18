package com.multilang.app.model;

import com.wizarius.orm.database.DBException;
import com.wizarius.orm.database.DatabaseStorage;
import com.wizarius.orm.database.connection.DBConnectionPool;

public class MenusStorage extends DatabaseStorage<MenusEntity>
{
	public MenusStorage(DBConnectionPool pool) throws DBException
	{
        super(pool, MenusEntity.class);
    }
}