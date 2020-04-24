package com.sandbox.delivery.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerNoExistExeception extends Exception {
	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(CustomerNoExistExeception.class);

	public CustomerNoExistExeception(String message) {
		LOG.error(message);
	}

}
