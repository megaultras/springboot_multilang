package com.multilang.app.model;

import com.wizarius.orm.database.annotations.DBModel;
import com.wizarius.orm.database.annotations.DBField;

@DBModel(tableName = "languages")
public class LanguagesEntity
{
	@DBField(fieldName = "id", isAutoIncrement = true)
	private int id;
	
	@DBField(fieldName = "name")
	private String name;
	
	@DBField(fieldName = "code")
	private String code;
	
	@DBField(fieldName = "code_full")
	private String codeFull;
	
	@DBField(fieldName = "basic")
	private int basic;

	@DBField(fieldName = "active")
	private int active;

	public LanguagesEntity() 
	{
		
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCodeFull() {
		return codeFull;
	}

	public void setCodeFull(String codeFull) {
		this.codeFull = codeFull;
	}

	public int getBasic() {
		return basic;
	}

	public void setBasic(int basic) {
		this.basic = basic;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}
}