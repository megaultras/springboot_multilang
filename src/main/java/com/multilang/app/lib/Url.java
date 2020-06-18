package com.multilang.app.lib;

public class Url
{
    private String currentLanguage;

    public Url(String currentLanguage)
    {
        this.currentLanguage = currentLanguage;
    }

    public String l(String url)
    {
        AppContext context = AppContext.getInstance();
        String defaultLanguage = context.getData().getDefaultLanguage().getCode();

        StringBuilder sb = new StringBuilder();

        if (!this.currentLanguage.equals(defaultLanguage)) {
            sb.append("/" + this.currentLanguage);
        }

        sb.append(url);

        return sb.toString();
    }
}
