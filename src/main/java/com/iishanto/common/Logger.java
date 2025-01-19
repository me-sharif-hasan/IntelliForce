package com.iishanto.common;

public class Logger {
    private static org.slf4j.Logger logger;
    private static String prefix;
    private Logger(){
        logger=org.slf4j.LoggerFactory.getLogger(Logger.class);
        prefix="Apex-IDE";
    }

    public static void log(String message){
        logger.info("{}: {}", prefix, message);
    }
    public static void log(Throwable e){
        logger.error("{}: {}", prefix,e.getMessage(), e);
    }
}
