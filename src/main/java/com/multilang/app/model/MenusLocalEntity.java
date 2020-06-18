package com.multilang.app.model;

import com.wizarius.orm.database.annotations.DBField;
import com.wizarius.orm.database.annotations.DBModel;

@DBModel(tableName = "menus_l")
public class MenusLocalEntity
{
	@DBField(fieldName = "id_main")
	private int idMain;

	@DBField(fieldName = "lang")
	private String lang;

	@DBField(fieldName = "label")
	private String label;

	public MenusLocalEntity()
	{
		
    }

	public int getIdMain() {
		return idMain;
	}

	public void setIdMain(int idMain) {
		this.idMain = idMain;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}