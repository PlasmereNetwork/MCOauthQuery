package co.templex.oauth;

import lombok.Getter;

public class SuccessfulOAuthResponse extends OAuthResponse {

    @Getter
    private final String username;
    @Getter
    private final String uuid;

    SuccessfulOAuthResponse(String status, String message, String username, String uuid) {
        super(status, message);
        this.username = username;
        this.uuid = uuid;
    }
}
