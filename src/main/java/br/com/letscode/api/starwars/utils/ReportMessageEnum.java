package br.com.letscode.api.starwars.utils;

public enum ReportMessageEnum {
    NOT_VALIDATED("You are not a validated rebel."),
    ALREADY_TRAITOR("This rebel is already a traitor."),
    NOT_POTENTIAL_TRAITOR("This is not a potential traitor."),
    ALREADY_REPORTED("You have already report this rebel."),
    REPORT_ACCEPTED("Your report was accepted.");

    private final String response;

    ReportMessageEnum(String response) {
        this.response = response;
    }

    @Override
    public String toString() {
        return this.response;
    }
}
