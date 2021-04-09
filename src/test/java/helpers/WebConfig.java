package helpers;

import org.aeonbits.owner.Config;

@Config.Sources("classpath:${env}.properties")
public interface WebConfig extends Config {

    @Key("webDriverBrowser")
    public String getWebDriverBrowser();

    @Key("webDriverBrowserVersion")
    public String getWebDriverBrowserVersion();

    @Key("remoteUrl")
    public String getRemoteUrl();

    @Key("videoStorageUrl")
    public String getVideoStorageUrl();
}
