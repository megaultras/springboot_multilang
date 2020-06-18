package com.multilang.app.model;

import com.wizarius.orm.database.annotations.DBField;
import com.wizarius.orm.database.annotations.DBJoinModel;
import com.wizarius.orm.database.annotations.DBModel;

@DBModel(tableName = "texts")
public class TextsEntity
{
	@DBField(fieldName = "id", isAutoIncrement = true)
	private int id;

	@DBField(fieldName = "key")
	private String key;

	@DBJoinModel(currentDBFieldName = "id", insideDBFieldName = "id_main")
	private TextsLocalEntity local;

	public TextsEntity()
	{
		
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public TextsLocalEntity getLocal() {
		return local;
	}

	public void setLocal(TextsLocalEntity local) {
		this.local = local;
	}
}