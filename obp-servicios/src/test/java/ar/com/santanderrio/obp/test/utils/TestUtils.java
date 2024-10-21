package ar.com.santanderrio.obp.test.utils;

import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StreamUtils;
import sun.nio.cs.StandardCharsets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class TestUtils {

    public static File getResourceFile(String path) throws IOException {
        URL filePath = TestUtils.class.getClassLoader().getResource(path);
        if(filePath == null) { throw new IOException("File " + path +" not found"); }
        return new File(filePath.getFile());
    }

    public static String getResourceFileAsString(String path) throws Exception {
        InputStream fileStream = null;
        try {
            fileStream = TestUtils.class.getClassLoader().getResourceAsStream(path);
            return StreamUtils.copyToString(fileStream, new StandardCharsets().charsetForName("UTF8"));
        } catch (Exception e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(fileStream);
        }
    }

    public static <T> T readAndClose(File file, Class<T> classOfT) throws Exception{
        Gson gson = new Gson();
        FileReader jsonReader = null;
        try {
            jsonReader = new FileReader(file);
            return gson.fromJson(jsonReader, classOfT);
        } catch (FileNotFoundException e) {
            throw e;
        } finally {
            IOUtils.closeQuietly(jsonReader);
        }
    }
}
