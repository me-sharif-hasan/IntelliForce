package com.iishanto.common;

public class Logger {
    private static final org.slf4j.Logger logger=org.slf4j.LoggerFactory.getLogger(Logger.class);
    private static String prefix;
    private Logger(){;
        prefix="Apex-IDE";
    }

    public static void log(String message){
//        logger.info("{}: {}", prefix, message);
        System.out.println("%s: %s".formatted(prefix,message));
    }
    public static void log(Throwable e){
        logger.error("{}: {}", prefix,e.getMessage(), e);
    }
}
