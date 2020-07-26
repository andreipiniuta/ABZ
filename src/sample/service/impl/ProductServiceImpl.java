package sample.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;
import sample.Product;
import sample.service.ProductService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    @Override
    public void saveProduct(Product product) throws IOException {
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
        jsonWriter.key("command-name").value("save-product");
        jsonWriter.endObject();
        //второй объект json-a----бизнес объект
        jsonWriter.key("parameters");
        jsonWriter.object();
        jsonWriter.key("ID").value(product.getID());
        jsonWriter.key("productName").value(product.getProductName());
        jsonWriter.key("productAmount").value(product.getProductAmount());
        jsonWriter.key("constructionAddress").value(product.getConstructionAddress());
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
    public List<Product> getAllProduct() throws IOException {
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
        jsonWriter.key("command-name").value("get-all-product");
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
        List <Product> allProduct = new ArrayList<>();
        JSONArray responseArray = response.getJSONArray("response-data");//достали массив
        int length = responseArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject responseObject = responseArray.getJSONObject(i);//при каждом проходе достаем объект из массива

            Integer ID = responseObject.getInt("ID");
            String productName = responseObject.getString("productName");
            double productAmount = responseObject.getDouble("productAmount");
            String constructionAddress = responseObject.getString("constructionAddress");

            Product product = new Product();
            product.setID(ID);
            product.setProductName(productName);
            product.setProductAmount(productAmount);
            product.setConstructionAddress(constructionAddress);
            allProduct.add(product);//каждый объект закидываю в лист
        }
        writer.close();
        is.close();
        socket.close();
        return allProduct;
    }

    @Override
    public Product getProductByID(Integer ID) throws IOException {
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
        jsonWriter.key("command-name").value("get-product-by-id");
        jsonWriter.endObject();
        // второй объекта jsona отправляем на Server сам ID-шник
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

//второй json объект-сам бизнесс объект
        JSONArray responseData = response.getJSONArray("response-data");
        Product product = new Product();

        int size = responseData.length();
        for (int i=0; i< size; i++) {
            JSONObject responseDataObject = responseData.getJSONObject(i);
            Integer pID = responseDataObject.getInt("ID");
            String productName = responseDataObject.getString("productName");
            double productAmount = responseDataObject.getDouble("productAmount");
            String constructionAddress = responseDataObject.getString("constructionAddress");

            product.setID(pID);
            product.setProductName(productName);
            product.setProductAmount(productAmount);
            product.setConstructionAddress(constructionAddress);
        }
            writer.close();
            is.close();
            socket.close();
            return product;

    }

    @Override
    public void deleteProduct(Integer ID) throws IOException {
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
        jsonWriter.key("command-name").value("delete-product");
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
