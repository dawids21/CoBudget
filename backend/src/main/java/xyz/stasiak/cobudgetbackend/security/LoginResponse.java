package xyz.stasiak.cobudgetbackend.security;

import java.util.Objects;

public class LoginResponse {

    private final SuccessFailure status;
    private final String message;

    public LoginResponse(SuccessFailure status, String message) {
        this.status = status;
        this.message = message;
    }

    public SuccessFailure getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        LoginResponse that = (LoginResponse) o;
        return getStatus() == that.getStatus() && Objects.equals(getMessage(), that.getMessage());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStatus(), getMessage());
    }

    @Override
    public String toString() {
        return "LoginResponse{" + "status=" + status + ", message='" + message + '\'' + '}';
    }

    public enum SuccessFailure {
        SUCCESS, FAILURE
    }

}
