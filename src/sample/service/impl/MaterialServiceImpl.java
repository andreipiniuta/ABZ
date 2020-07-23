package sample.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;
import sample.Material;
import sample.MaterialName;
import sample.Product;
import sample.ProviderName;
import sample.service.MaterialService;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
//класс с реализацией методов Servise Interface
public class MaterialServiceImpl implements MaterialService {
//общий принцип:открыть socket и создать json файл с двумя объектами: первый--команда для сервера, второй-сам бизнесс объект  (если нужен)
    @Override
    public void saveMaterial(Material material) throws IOException {
        String serverIpStr = "127.0.0.1";
        InetAddress serverIp = InetAddress.getByName(serverIpStr);
        Socket socket = new Socket(serverIp, 8848);
//отправка файла JSON
        OutputStream outputStream = socket.getOutputStream();//поток вывода
        OutputStreamWriter writer = new OutputStreamWriter(outputStream);//поток преобразователь из текста в байты
        JSONWriter jsonWriter = new JSONWriter(writer);
        //начали json состоит из 2х json-объектов
        jsonWriter.object();
        //первый объект json-a
        // ключ--"headers", значение--json-объект
        // ключ--"command-name", значение----название команды
        jsonWriter.key("headers");
        jsonWriter.object();
        jsonWriter.key("command-name").value("save-material");
        jsonWriter.endObject();
        //второй объект json-a
        // ключ--"parameters", значение--json-объект
        // ключи--"назв полей", значение----значения полей
        jsonWriter.key("parameters");
        jsonWriter.object();
        jsonWriter.key("ID").value(material.getID());
        jsonWriter.key("materialName").value(material.getMaterialName());
        jsonWriter.key("providerName").value(material.getProviderName());
        jsonWriter.key("amount").value(material.getAmount());
        jsonWriter.key("costPerOne").value(material.getCostPerOne());
        jsonWriter.key("totalCost").value(material.getTotalCost());
        jsonWriter.endObject();

        //закончили json состоит из 2х json-объектов
        jsonWriter.endObject();
        writer.flush();//закрыли поток вывода


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
    public List<Material> getAllMaterial() throws IOException {
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
        jsonWriter.key("command-name").value("get-all-material");
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
        List <Material> allMaterials = new ArrayList<>();
        JSONArray responseData = response.getJSONArray("response-data");
        int length = responseData.length();
        for (int i = 0; i < length; i++) {
           Material material = (Material) responseData.get(i);
           allMaterials.add(material);
        }

        writer.close();
        is.close();
        socket.close();
        return allMaterials;
    }

    @Override
    public Material getMaterialByID(Integer ID) throws IOException {
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
        jsonWriter.key("command-name").value("get-material-by-id");
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
        Material material = new Material();

        Integer mID = responseData.getInt("ID");
        String materialNameStr = responseData.getString("materialName");
        MaterialName materialName = MaterialName.valueOf(materialNameStr);
        String providerNameStr = responseData.getString("providerName");
        ProviderName providerName = ProviderName.valueOf(providerNameStr);
        double amount = responseData.getDouble("amount");
        double costPerOne = responseData.getDouble("costPerOne");

        material.setID(mID);
        material.setMaterialName(materialName);
        material.setProviderName(providerName);
        material.setAmount(amount);
        material.setCostPerOne(costPerOne);
        material.getTotalCost();

        writer.close();
        is.close();
        socket.close();
        return material;
}


    @Override
    public void deleteMaterial(Integer ID) throws IOException {
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
        jsonWriter.key("command-name").value("delete-material");
        jsonWriter.endObject();
        //второй объект json-a----бизнес объект(только ID)
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
    }
}
