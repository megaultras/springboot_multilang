package com.multilang.app.model;

import com.wizarius.orm.database.annotations.DBField;
import com.wizarius.orm.database.annotations.DBModel;

@DBModel(tableName = "texts_l")
public class TextsLocalEntity
{
	@DBField(fieldName = "id_main")
	private int idMain;

	@DBField(fieldName = "lang")
	private String lang;

	@DBField(fieldName = "content")
	private String content;

	public TextsLocalEntity()
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}