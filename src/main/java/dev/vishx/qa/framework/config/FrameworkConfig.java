package dev.vishx.qa.framework.config;

public final class FrameworkConfig {

    private static final ConfigReader CONFIG_READER = new ConfigReader();

    private FrameworkConfig(){}

    public static ConfigReader getConfigReader(){
        return CONFIG_READER;
    }
}
