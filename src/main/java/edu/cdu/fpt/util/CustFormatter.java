package edu.cdu.fpt.util;

import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;


public class CustFormatter extends SimpleFormatter { 
    @Override 
    public synchronized String format(LogRecord record) { 
            return record.getMessage()+"\r\n" ; 
    } 
}

