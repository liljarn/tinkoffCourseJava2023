package edu.hw8.task3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class AbstractPasswordHacker implements PasswordHacker {
    protected static final Logger LOGGER = LogManager.getLogger();
    protected final static int MIN_PASSWORD_LENGTH = 4;
    protected final static int MAX_PASSWORD_LENGTH = 6;
    protected final static char[] ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
}
