package client;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;


public class RequestParser {
//    private static final String path = System.getProperty("user.dir") + File.separator +
//            "JSON Database" + File.separator +
//            "task" + File.separator +
//            "src" + File.separator +
//            "client" + File.separator +
//            "data" + File.separator;

    @Parameter(names = "-t", description = "the type of the request")
    private String requestType;
    @Parameter(names = "-k", description = "the index of the cell")
    private String indexOfCeil;
    @Parameter(names = "-v", description = "the value to save in the database")
    private String value;
    @Parameter(names = "-in", description = "file name to read request")
    private String fileName;

    public RequestParser(String[] iCommand) {
        JCommander.newBuilder()
                .addObject(this)
                .build()
                .parse(iCommand);
    }

    public String serializeToJson() {

        if (fileName != null) {
//            String pathToFile = path + fileName;
            String pathToFile = "C:\\Users\\Георгий\\IdeaProjects\\JSON Database\\JSON Database\\task\\src\\client\\data\\" + fileName;
            try (Scanner scanner = new Scanner(new File(pathToFile))) {
                StringBuilder stringBuilder = new StringBuilder();
                while (scanner.hasNext()) {
                    stringBuilder.append(scanner.nextLine());
                }
                return stringBuilder.toString();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        Map<String, String> request = new LinkedHashMap<>();
        request.put("type", requestType);
        request.put("key", indexOfCeil);
        request.put("value", value);

        return new Gson().toJson(request);

    }
}