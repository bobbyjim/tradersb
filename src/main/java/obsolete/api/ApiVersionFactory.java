package obsolete.api;

/**
 * Since REST services appear to go through version numbers, plan ahead and
 * create a factory class for fetching the version you want.
 */
public class ApiVersionFactory {
    private static API v0factory = new APIv0CommandFactory();

    public static API apiV0factory() {
        return v0factory;
    }

    public static API getApiVersion(String ver) {
        switch (ver) {
            default:
            case "v0":
                return new APIv0CommandFactory();
        }
    }
}
