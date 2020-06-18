package com.multilang.app.model;

import com.wizarius.orm.database.DBException;
import com.wizarius.orm.database.DatabaseStorage;
import com.wizarius.orm.database.connection.DBConnectionPool;

public class TextsStorage extends DatabaseStorage<TextsEntity>
{
	public TextsStorage(DBConnectionPool pool) throws DBException
	{
        super(pool, TextsEntity.class);
    }
}