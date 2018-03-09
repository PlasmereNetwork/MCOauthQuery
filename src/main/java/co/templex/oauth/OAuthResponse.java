package co.templex.oauth;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class OAuthResponse {
    @NonNull
    String status;
    @NonNull
    String message;
}
