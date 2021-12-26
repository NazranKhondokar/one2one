package com.one2one.utils;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class FileDeleteUtils {

    public static void deleteFromDisc(String path, String param, Object message) throws IOException {
        final Logger LOGGER = Logger.getLogger(message.toString());
        File file = new File(path + param);
        if (file.delete()) {
            LOGGER.info("****************FILE Deleted From:" + LOGGER.getName());
        } else {
            LOGGER.info("***************Delete operation Is Failed !!!");
        }
    }
}
