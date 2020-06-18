package com.multilang.app.lib;

public class SessionEntity
{
    public SessionEntity(String uuid)
    {
        this.setUuid(uuid);
    }

    private String uuid = null;
    private String language = null;
    private String redirectUrl = null;

    public String getUuid() {
        return this.uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public String getLanguage() {
        return this.language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
