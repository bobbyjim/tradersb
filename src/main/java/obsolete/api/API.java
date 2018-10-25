package obsolete.api;

public interface API {
    ApiCommand getDefaultCommand();

    ApiCommand getCommand(String stemNoun);
}
