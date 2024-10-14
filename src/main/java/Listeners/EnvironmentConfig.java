package Listeners;


import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:${user.dir}/src/main/resources/project.properties"
})
public interface EnvironmentConfig extends Config {

    @DefaultValue("prod")
    String environment();

    @DefaultValue("chrome")
    String browser();

    @Key("${environment}.baseURL")
    String baseURL();

    @Key("${environment}.userName")
    String userName();

    @Key("${environment}.passKey")
    String passKey();

    @Key("${environment}.lockedOutUser")
    String lockedOutUser();

    @Key("${environment}.problemUser")
    String problemUser();

    @Key("${environment}.performanceGlitchUser")
    String performanceGlitchUser();

    @Key("${environment}.errorUser")
    String errorUser();

    @Key("${environment}.visualUser")
    String visualUser();

    @Key("${environment}.baseURI")
    String baseURI();



}
