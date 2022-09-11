package com.customer.rewards.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)//, reason = "Could not find entity with id."
public class RecordExistsException extends Exception
{
    static final long serialVersionUID = -3387516993334229948L;

    public RecordExistsException(String message)
    {
        super(message);
    }

}
