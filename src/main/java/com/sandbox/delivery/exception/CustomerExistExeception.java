package com.sandbox.delivery.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomerExistExeception extends Exception {

	private static final long serialVersionUID = 1L;

	private static final Logger LOG = LoggerFactory.getLogger(CustomerExistExeception.class);

	public CustomerExistExeception(String message) {
		LOG.error(message);

	}

}
