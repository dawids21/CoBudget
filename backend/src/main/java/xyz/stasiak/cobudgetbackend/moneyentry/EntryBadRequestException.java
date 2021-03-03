package xyz.stasiak.cobudgetbackend.moneyentry;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EntryBadRequestException extends EntryException {

    public EntryBadRequestException(String message) {
        super(message);
    }
}
