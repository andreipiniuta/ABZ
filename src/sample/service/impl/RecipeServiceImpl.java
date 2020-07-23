package sample.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;
import sample.Recipe;
import sample.service.RecipeService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class RecipeServiceImpl implements RecipeService {
    @Override
    public void saveRecipe(Recipe recipe) throws IOException {
        String serverIpStr = "127.0.0.1";
        InetAddress serverIp = InetAddress.getByName(serverIpStr);
        Socket socket = new Socket(serverIp, 8848);
//отправка файла JSON
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        JSONWriter jsonWriter = new JSONWriter(writer);
        jsonWriter.object();
        //первый объект json-a----название команды
        jsonWriter.key("headers");
        jsonWriter.object();
        jsonWriter.key("command-name").value("save-recipe");
        jsonWriter.endObject();
        //второй объект json-a----бизнес объект
        jsonWriter.key("parameters");
        jsonWriter.object();
        jsonWriter.key("ID").value(recipe.getID());
        jsonWriter.key("productName").value(recipe.getProductName());
        jsonWriter.key("sandPercent").value(recipe.getSandPercent());
        jsonWriter.key("gravelPercent").value(recipe.getGravelPercent());
        jsonWriter.key("bitumPercent").value(recipe.getBitumPercent());
        jsonWriter.endObject();

        jsonWriter.endObject();
        writer.flush();
//получение ответа файла JSON
// читаем json файл с двумя объектами: первый--код ошибки, второй-сам бизнесс объект (если нужен)
        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);
        //второй-сам бизнесс объект не нужен
    }

    @Override
    public List<Recipe> getAllRecipe() throws IOException {
        String serverIpStr = "127.0.0.1";
        InetAddress serverIp = InetAddress.getByName(serverIpStr);
        Socket socket = new Socket(serverIp, 8848);
//отправка файла JSON
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        JSONWriter jsonWriter = new JSONWriter(writer);
        jsonWriter.object();
        //первый объект json-a----название команды
        jsonWriter.key("headers");
        jsonWriter.object();
        jsonWriter.key("command-name").value("get-all-recipe");
        jsonWriter.endObject();
        // второй объекта jsona не нужен
        jsonWriter.endObject();
        writer.flush();
//получение ответа файла JSON
// читаем json файл с двумя объектами: первый--код ошибки, второй-сам бизнесс объект (если нужен)

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);

//второй json объект-сам бизнесс объект
        List <Recipe> allRecipe = new ArrayList<>();
        JSONArray responseData = response.getJSONArray("response-data");
        int length = responseData.length();
        for (int i = 0; i < length; i++) {
            Recipe recipe = (Recipe) responseData.get(i);
            allRecipe.add(recipe);
        }

        writer.close();
        is.close();
        socket.close();
        return allRecipe;
    }

    @Override
    public Recipe getRecipeByID(Integer ID) throws IOException {
        String serverIpStr = "127.0.0.1";
        InetAddress serverIp = InetAddress.getByName(serverIpStr);
        Socket socket = new Socket(serverIp, 8848);
//отправка файла JSON
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        JSONWriter jsonWriter = new JSONWriter(writer);
        jsonWriter.object();
        //первый объект json-a----название команды
        jsonWriter.key("headers");
        jsonWriter.object();
        jsonWriter.key("command-name").value("get-recipe-by-id");
        jsonWriter.endObject();
        // второй объекта jsona не нужен
        jsonWriter.endObject();
        writer.flush();
//получение ответа файла JSON
// читаем json файл с двумя объектами: первый--код ошибки, второй-сам бизнесс объект (если нужен)

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);

//второй json объект-сам бизнесс объект
        JSONObject responseData = response.getJSONObject("response-data");
        Recipe recipe = new Recipe();

        Integer rID = responseData.getInt("ID");
        String productName = responseData.getString("productName");
        double sandPercent = responseData.getDouble("sandPercent");
        double gravelPercent = responseData.getDouble("gravelPercent");
        double bitumPercent = responseData.getDouble("bitumPercent");

        recipe.setID(rID);
        recipe.setProductName(productName);
        recipe.setSandPercent(sandPercent);
        recipe.setGravelPercent(gravelPercent);
        recipe.setBitumPercent(bitumPercent);

        writer.close();
        is.close();
        socket.close();
        return recipe;
    }

    @Override
    public void deleteRecipe(Integer ID) throws IOException {
        String serverIpStr = "127.0.0.1";
        InetAddress serverIp = InetAddress.getByName(serverIpStr);
        Socket socket = new Socket(serverIp, 8848);
//отправка файла JSON
        OutputStream outputStream = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);
        JSONWriter jsonWriter = new JSONWriter(writer);
        jsonWriter.object();
        //первый объект json-a----название команды
        jsonWriter.key("headers");
        jsonWriter.object();
        jsonWriter.key("command-name").value("delete-recipe");
        jsonWriter.endObject();
        //второй объект json-a----бизнес объект
        jsonWriter.key("parameters");
        jsonWriter.object();
        jsonWriter.key("ID").value(ID);
        jsonWriter.endObject();

        jsonWriter.endObject();
        writer.flush();
//получение ответа файла JSON
// читаем json файл с двумя объектами: первый--код ошибки, второй-сам бизнесс объект (если нужен)
        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);
        //второй-сам бизнесс объект не нужен
    }
}
