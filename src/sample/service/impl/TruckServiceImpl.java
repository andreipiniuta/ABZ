package sample.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;
import sample.*;
import sample.service.TruckService;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class TruckServiceImpl implements TruckService {
    @Override
    public void saveTruck(Truck truck) throws IOException {
        String serverIpStr = "127.0.0.1";
        InetAddress serverIp = InetAddress.getByName(serverIpStr);
        Socket socket = new Socket(serverIp, 8848);

        OutputStream ostr = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(ostr);
        JSONWriter jsonWriter = new JSONWriter(writer);
//
        jsonWriter.object();
        //команда
        jsonWriter.key("headers");
        jsonWriter.object();
        jsonWriter.key("command-name").value("save-truck");
        jsonWriter.endObject();
//сам объект
        jsonWriter.key("parameters");
        jsonWriter.object();
        if (truck.getID() != null) {
            jsonWriter.key("ID").value(truck.getID());
        }
        jsonWriter.key("truckType").value(truck.getTruckType());
        jsonWriter.key("truckNumber").value(truck.getTruckNumber());
        jsonWriter.key("payload").value(truck.getPayload());
        jsonWriter.endObject();

        jsonWriter.endObject();
        writer.flush();

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);
    }

    @Override
    public List<Truck> getAllTruck() throws IOException {
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
        jsonWriter.key("command-name").value("get-all-truck");
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
        List <Truck> allTruck = new ArrayList<>();
        JSONArray responseArray = response.getJSONArray("response-data");//достали массив
        int length = responseArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject responseObject = responseArray.getJSONObject(i);//при каждом проходе достаем объект из массива

            Integer ID = responseObject.getInt("ID");
            String truckType = responseObject.getString("truckType");
            String truckNumber = responseObject.getString("truckNumber");
            double payload = responseObject.getDouble("payload");

            Truck truck = new Truck();
            truck.setID(ID);
            truck.setTruckType(truckType);
            truck.setTruckNumber(truckNumber);
            truck.setPayload(payload);

            allTruck.add(truck);//каждый объект закидываю в лист
        }

        writer.close();
        is.close();
        socket.close();
        return allTruck;
    }

    @Override
    public Truck getTruckByID(Integer ID) throws IOException {
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
        jsonWriter.key("command-name").value("get-truck-by-id");
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
        Truck truck = new Truck();

        int size = responseData.length();
        for (int i = 0; i<size; i++) {
            JSONObject responseDataObject = responseData.getJSONObject(i);
            Integer tID = responseDataObject.getInt("ID");
            String truckType = responseDataObject.getString("truckType");
            String truckNumber = responseDataObject.getString("truckNumber");
            double payload = responseDataObject.getDouble("payload");

            truck.setID(tID);
            truck.setTruckType(truckType);
            truck.setTruckNumber(truckNumber);
            truck.setPayload(payload);
        }
        writer.close();
        is.close();
        socket.close();
        return truck;
    }

    @Override
    public void deleteTruck(Integer ID) throws IOException {
        String serverIpStr = "127.0.0.1";
        InetAddress serverIp = InetAddress.getByName(serverIpStr);
        Socket socket = new Socket(serverIp, 8848);

        OutputStream ostr = socket.getOutputStream();
        OutputStreamWriter writer = new OutputStreamWriter(ostr);
        JSONWriter jsonWriter = new JSONWriter(writer);
//
        jsonWriter.object();
        //команда
        jsonWriter.key("headers");
        jsonWriter.object();
        jsonWriter.key("command-name").value("delete-truck");
        jsonWriter.endObject();
//сам объект
        jsonWriter.key("parameters");
        jsonWriter.object();
        jsonWriter.key("ID").value(ID);
        jsonWriter.endObject();

        jsonWriter.endObject();
        writer.flush();

        InputStream is = socket.getInputStream();
        JSONTokener tokener = new JSONTokener(is);

        JSONObject response = (JSONObject) tokener.nextValue();
        JSONObject headers = response.getJSONObject("headers");

        int code = headers.getInt("status-code");
        String message = headers.getString("status-message");

        System.out.println(code + " - " + message);
    }
}
