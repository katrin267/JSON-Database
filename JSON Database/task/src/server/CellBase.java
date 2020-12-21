package server;

import com.google.gson.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CellBase {
    final static String ERROR = "{\"response\":\"ERROR\",\"reason\":\"No such key\"}";
    final static String OK = "{\"response\":\"OK\"}";
    final static String pathToFile = "C:\\Users\\Георгий\\IdeaProjects\\JSON Database\\JSON Database\\task\\src\\server\\data\\db.json";
//    private static final String pathToFile = System.getProperty("user.dir") + File.separator +
//            "JSON Database" + File.separator +
//            "task" + File.separator +
//            "src" + File.separator +
//            "server" + File.separator +
//            "data" + File.separator + "db.json";


    private JsonObject jsonBase;

    ReadWriteLock lock = new ReentrantReadWriteLock();
    Lock readLock = lock.readLock();
    Lock writeLock = lock.writeLock();

    public CellBase() {
        fillBase();
    }

    private void fillBase() {

        try (Scanner scanner = new Scanner(new File(pathToFile))) {
            StringBuilder stringBuilder = new StringBuilder();
            while (scanner.hasNext()) {
                stringBuilder.append(scanner.nextLine());
            }
            String base = stringBuilder.toString();
            JsonElement jElBase = JsonParser.parseString(base);
            jsonBase = jElBase.getAsJsonObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void updateBase() {
        writeLock.lock();
        try (FileWriter writer = new FileWriter(new File(pathToFile))) {
            writer.write(jsonBase.toString());
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
        }
        writeLock.unlock();
    }

    private JsonArray getArray(JsonObject jsonObject) {
        JsonArray requestArray;
        if (jsonObject.get("key").isJsonArray()) {
            requestArray = jsonObject.getAsJsonArray("key");
        } else {
            requestArray = new JsonArray(1);
            requestArray.add(jsonObject.get("key"));
        }
        return requestArray;
    }

    public String setString(JsonObject jsonObject) {
        JsonArray requestArray = getArray(jsonObject);

        int size = requestArray.size();
        JsonObject newJsonObj = jsonBase;
        for (int i = 0; i < size - 1; i++) {
            if (newJsonObj.has(requestArray.get(i).getAsString())) {
                newJsonObj = newJsonObj.getAsJsonObject(requestArray.get(i).getAsString());
            } else {
                newJsonObj.add(requestArray.get(i).getAsString(), JsonParser.parseString("{}"));
                newJsonObj = newJsonObj.getAsJsonObject(requestArray.get(i).getAsString());
            }
        }

        if (jsonObject.get("value").isJsonObject()) {
            JsonObject valueObj = jsonObject.get("value").getAsJsonObject();
            newJsonObj.add(requestArray.get(size - 1).getAsString(), valueObj);
        } else {
            String value = jsonObject.get("value").getAsString();
            newJsonObj.addProperty(requestArray.get(size - 1).getAsString(), value);
        }

        updateBase();
        return OK;
    }

    public String getString(JsonObject jsonObject) {
        JsonArray requestArray = getArray(jsonObject);

        String srtToReturn = "";

        JsonObject jsonBaseObj = jsonBase;

        try {
            for (var key : requestArray) {
                if (jsonBaseObj.get(key.getAsString()).isJsonObject()) {
                    jsonBaseObj = jsonBaseObj.get(key.getAsString()).getAsJsonObject();
                } else {
                    srtToReturn = "\"" + jsonBaseObj.get(key.getAsString()).getAsString() + "\"";
                    break;
                }
                srtToReturn = jsonBaseObj.toString();
            }
        } catch (Exception e) {
            return ERROR;
        }

        return "{\"response\":\"OK\",\"value\":" + srtToReturn + "}";
    }

    public String delString(JsonObject jsonObject) {
        JsonArray requestArray = getArray(jsonObject);
        JsonObject jsonBaseObj = jsonBase;

        try {
            for (var key : requestArray) {
                if (jsonBaseObj.get(key.getAsString()).isJsonObject()) {
                    jsonBaseObj = jsonBaseObj.get(key.getAsString()).getAsJsonObject();
                } else {
                    jsonBaseObj.remove(key.getAsString()).getAsString();
                    updateBase();
                    return OK;
                }
            }
            jsonBaseObj.remove(requestArray.get(requestArray.size() - 1).getAsString()).getAsJsonObject();
            updateBase();
            return OK;
        } catch (Exception e) {
            return ERROR;
        }
    }

    public String exit() {
        return OK;
    }
}

//--------------------------------------------------------------------
//        Map jsonJavaRootObject = new Gson().fromJson(sentMsg, Map.class);
//--------------------------------------------------------------------

