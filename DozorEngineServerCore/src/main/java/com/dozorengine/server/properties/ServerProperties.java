package com.dozorengine.server.properties;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Файл свойств сервера
 *
 * @author IGOR-K
 */
public class ServerProperties {

    private static final ServerProperties property = new ServerProperties();

    public static ServerProperties getInstance() {
        return property;
    }

    /**
     * Путь к файлу с клиентскими настройками.
     */
    private final String local_dir = System.getProperty("user.dir");
    private final String file_separator = System.getProperty("file.separator");
    private final String file_path = local_dir + file_separator + "server.properties";


    private Map<String, String> properties;

    private ServerProperties() {
        try {
            init();
        } catch (IOException ex) {
            Logger.getLogger(ServerProperties.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * чтение из файла и заполнение мапы свойств
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void init() throws FileNotFoundException, IOException {
        File file = new File(file_path);
        if (!(file).exists()) {
            System.err.println("No Config File!!!");
            System.err.println(file_path);
        }

        properties = new HashMap();

        BufferedReader br = new BufferedReader(new FileReader(file));

        String eachLine = br.readLine();

        while (eachLine != null) {
            String[] arr = eachLine.split("=");
            if (arr.length == 2)
                properties.put(arr[0].trim(), arr[1].trim());
            eachLine = br.readLine();
        }
        br.close();
    }

    /**
     * получить свойство по названию
     *
     * @param propertyName название
     * @return свойство
     */
    public String getProperty(String propertyName) {
        return properties.get(propertyName);
    }
}
