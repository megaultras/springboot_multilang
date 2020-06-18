package com.multilang.app.model;

import com.wizarius.orm.database.annotations.DBField;
import com.wizarius.orm.database.annotations.DBJoinModel;
import com.wizarius.orm.database.annotations.DBModel;

@DBModel(tableName = "menus")
public class MenusEntity
{
	@DBField(fieldName = "id", isAutoIncrement = true)
	private int id;

	@DBField(fieldName = "url")
	private String url;

	@DBField(fieldName = "active")
	private int active;

	@DBField(fieldName = "ordering")
	private int ordering;

	@DBJoinModel(currentDBFieldName = "id", insideDBFieldName = "id_main")
	private MenusLocalEntity local;

	public MenusEntity()
	{
		
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getOrdering() {
		return ordering;
	}

	public void setOrdering(int ordering) {
		this.ordering = ordering;
	}

	public MenusLocalEntity getLocal() {
		return local;
	}
}