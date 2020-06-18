package com.multilang.app.model;

import com.wizarius.orm.database.annotations.DBField;
import com.wizarius.orm.database.annotations.DBJoinModel;
import com.wizarius.orm.database.annotations.DBModel;

@DBModel(tableName = "pages")
public class PagesEntity
{
	@DBField(fieldName = "id", isAutoIncrement = true)
	private int id;

	@DBField(fieldName = "url")
	private String url;

	@DBField(fieldName = "id_static")
	private int isStatic;

	@DBField(fieldName = "active")
	private int active;

	@DBJoinModel(currentDBFieldName = "id", insideDBFieldName = "id_main")
	private PagesLocalEntity local;

	public PagesEntity()
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

	public int getIsStatic() {
		return isStatic;
	}

	public void setIsStatic(int isStatic) {
		this.isStatic = isStatic;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public PagesLocalEntity getLocal() {
		return local;
	}

	public void setLocal(PagesLocalEntity local) {
		this.local = local;
	}
}