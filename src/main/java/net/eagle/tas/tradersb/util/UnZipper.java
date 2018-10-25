package net.eagle.tas.tradersb.util;

//import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class UnZipper {

    public static void unzipMultipleFiles(InputStream inputStream, File destinationDir, boolean removeDirectoryStructure) throws IOException {
        if (destinationDir.exists()) destinationDir.delete(); // FileUtils.deleteQuietly(destinationDir);
        if (!destinationDir.exists()) destinationDir.mkdirs();

        byte[] buffer = new byte[1024];
        ZipInputStream zis = new ZipInputStream(inputStream);
        try {
            ZipEntry zipEntry = zis.getNextEntry();
            while (zipEntry != null) {
                if (!zipEntry.isDirectory() || !removeDirectoryStructure) {
                    String fileName = zipEntry.getName();
                    if (removeDirectoryStructure) fileName = removeDirectoryFromName(fileName);
                    File newFile = new File(destinationDir, fileName);
                    FileOutputStream fos = new FileOutputStream(newFile);
                    int len;
                    try {
                        while ((len = zis.read(buffer)) > 0) {
                            fos.write(buffer, 0, len);
                        }
                    } finally {
                        fos.close();
                    }
                }
                zipEntry = zis.getNextEntry();
            }
        } finally {
            zis.closeEntry();
            zis.close();
        }
    }

    private static String removeDirectoryFromName(String fileName) {
        return new File(fileName).getName();
    }

}
