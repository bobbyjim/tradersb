package net.eagle.tas.tradersb.util;

import java.nio.ByteBuffer;
import java.util.UUID;

public class UniqueGenerator {

    public static String hashingAlgorithm = "SHA-256";

    public static String generateUniqueID() {
        UUID uuid = UUID.randomUUID();
        long l = ByteBuffer.wrap(uuid.toString().getBytes()).getLong();
        return Long.toString(l, Character.MAX_RADIX);
    }

    public static String generateShortID() {
        String uid = generateUniqueID();
        if (uid.length() > 6)
            uid = uid.substring(0, 6);
        return uid;
    }
}
