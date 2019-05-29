package com.adp.interview.billstocoins.model;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BillsToCoinsException extends Exception {

	private static final long serialVersionUID = 1L;

	public BillsToCoinsException() {
    }

    public BillsToCoinsException(String message) {
        super(message);
    }

    public BillsToCoinsException(Throwable cause) {
        super(cause);
    }

    public BillsToCoinsException(String message, Throwable cause) {
        super(message, cause);
    }

}
