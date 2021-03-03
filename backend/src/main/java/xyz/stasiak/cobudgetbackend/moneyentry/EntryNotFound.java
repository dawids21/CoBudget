package xyz.stasiak.cobudgetbackend.moneyentry;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class EntryNotFound extends EntryException {

    public EntryNotFound(String message) {
        super(message);
    }
}
