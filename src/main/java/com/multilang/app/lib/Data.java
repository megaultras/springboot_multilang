package com.multilang.app.lib;

import java.util.ArrayList;
import java.util.HashMap;

import com.wizarius.orm.database.DBException;
import com.wizarius.orm.database.actions.WizDBSelect;
import com.wizarius.orm.database.data.DBOrderType;

import com.multilang.app.model.*;
import com.wizarius.orm.database.data.JoinTypes;

public class Data {
    AppContext context;

//	Storages
    LanguagesStorage languagesStorage;
    MenusStorage menusStorage;
    TextsStorage textsStorage;
    PagesStorage pagesStorage;

//	Lists
    private static ArrayList<LanguagesEntity> languages = new ArrayList();
    private static ArrayList<String> languagesCodes = new ArrayList();

    private static HashMap<String, ArrayList<MenusEntity>> menu = new HashMap();
    private static HashMap<String, HashMap<String, String>> texts = new HashMap();
    private static HashMap<String, HashMap<String, PagesEntity>> staticPages = new HashMap();
    private static HashMap<String, HashMap<String, PagesEntity>> dynamicPages = new HashMap();

//	Default language
    private static LanguagesEntity defaultLanguage;

//	Alert
    protected ActionAlert actionAlert;

    public Data() {
        this.context = AppContext.getInstance();

        try {
            this.languagesStorage = new LanguagesStorage(this.context.getConnectionPool());
            this.menusStorage = new MenusStorage(this.context.getConnectionPool());
            this.textsStorage = new TextsStorage(this.context.getConnectionPool());
            this.pagesStorage = new PagesStorage(this.context.getConnectionPool());
        } catch (DBException ex) {
            System.out.println("Error: " + ex.getMessage());
            System.exit(0);
        }
    }

    public void loadLanguages() {
        try {
            this.languages = (ArrayList<LanguagesEntity>) this.languagesStorage.getSession().getSelectQuery()
                .where("active", 1)
                .orderBy("basic", DBOrderType.DESC)
                .execute();
        } catch (DBException ex) {
            System.out.println("Error: " + ex.getMessage());
            System.exit(0);
        }

        for (LanguagesEntity lang : this.languages) {
            if (lang.getBasic() == 1) {
                Data.defaultLanguage = lang;
            }

            languagesCodes.add(lang.getCode());
        }
    }

    public ArrayList<LanguagesEntity> getLanguages() {
        return this.languages;
    }

    public LanguagesEntity getDefaultLanguage() {
        return this.defaultLanguage;
    }

    public ArrayList<String> getLanguagesCodes() {
        return languagesCodes;
    }

    public void loadMenu() {
        for (String lang : this.languagesCodes) {
            try {
                WizDBSelect<MenusEntity> selectQuery = this.menusStorage.getSession().getSelectQuery();
                ArrayList<MenusEntity> menu = (ArrayList<MenusEntity>) selectQuery
                    .joinTables(JoinTypes.INNER)
                    .where(selectQuery.getFieldName("active", MenusEntity.class), 1)
                    .where("lang", lang)
                    .orderBy("ordering", DBOrderType.ASC)
                    .execute();
                this.menu.put(lang, menu);
            } catch (DBException ex) {
                System.out.println("Error: " + ex.getMessage());
                System.exit(0);
            }
        }
    }

    public static HashMap<String, ArrayList<MenusEntity>> getMenu() {
        return menu;
    }

    public void loadTexts() {
        for (String lang : this.languagesCodes) {
            try {
                ArrayList<TextsEntity> texts = (ArrayList<TextsEntity>) this.textsStorage.getSession().getSelectQuery()
                    .joinTables(JoinTypes.INNER)
                    .where("lang", lang)
                    .execute();
                HashMap<String, String> textsProcessed = new HashMap();
                for (TextsEntity text : texts) {
                    textsProcessed.put(text.getKey(), text.getLocal().getContent());
                }
                this.texts.put(lang, textsProcessed);
            } catch (DBException ex) {
                System.out.println("Error: " + ex.getMessage());
                System.exit(0);
            }
        }
    }

    public HashMap<String, HashMap<String, String>> getTexts() {
        return texts;
    }

    public String getText(String key, String lang) {
        return Data.texts.get(lang).get(key);
    }

    public void loadPages() {
        for (String lang : this.languagesCodes) {
            try {
                ArrayList<PagesEntity> pages = (ArrayList<PagesEntity>) this.pagesStorage.getSession().getSelectQuery()
                    .joinTables(JoinTypes.INNER)
                    .where("active", 1)
                    .where("lang", lang)
                    .execute();
                HashMap<String, PagesEntity> staticPagesProcessed = new HashMap();
                HashMap<String, PagesEntity> dynamicPagesProcessed = new HashMap();
                for (PagesEntity page : pages) {
                    if (page.getIsStatic() == 1) {
                        staticPagesProcessed.put(page.getUrl(), page);
                    } else {
                        dynamicPagesProcessed.put(page.getUrl(), page);
                    }
                }
                this.staticPages.put(lang, staticPagesProcessed);
                this.dynamicPages.put(lang, dynamicPagesProcessed);
            } catch (DBException ex) {
                System.out.println("Error: " + ex.getMessage());
                System.exit(0);
            }
        }
    }

    public HashMap<String, HashMap<String, PagesEntity>> getStaticPages() {
        return staticPages;
    }

    public PagesEntity getStaticPage(String lang, String url) {
        return staticPages.get(lang).get(url);
    }

    public HashMap<String, HashMap<String, PagesEntity>> getDynamicPages() {
        return dynamicPages;
    }

    public PagesEntity getDynamicPage(String lang, String url) {
        return dynamicPages.get(lang).get(url);
    }
}