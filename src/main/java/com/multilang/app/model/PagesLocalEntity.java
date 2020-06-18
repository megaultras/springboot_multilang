package com.multilang.app.model;

import com.wizarius.orm.database.annotations.DBField;
import com.wizarius.orm.database.annotations.DBModel;

@DBModel(tableName = "pages_l")
public class PagesLocalEntity
{
	@DBField(fieldName = "id_main")
	private int idMain;

	@DBField(fieldName = "lang")
	private String lang;

	@DBField(fieldName = "content")
	private String content;

	@DBField(fieldName = "meta_title")
	private String metaTitle;

	public PagesLocalEntity()
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

	public String getMetaTitle() {
		return metaTitle;
	}

	public void setMetaTitle(String metaTitle) {
		this.metaTitle = metaTitle;
	}
}