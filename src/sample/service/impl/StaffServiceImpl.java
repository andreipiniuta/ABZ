package sample.service.impl;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.JSONWriter;
import sample.Staff;
import sample.service.StaffService;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class StaffServiceImpl implements StaffService {
    @Override
    public void saveStaff(Staff staff) throws IOException {
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
        jsonWriter.key("command-name").value("save-staff");
        jsonWriter.endObject();
        //второй объект json-a----бизнес объект
        jsonWriter.key("parameters");
        jsonWriter.object();
        if (staff.getID() != null) {
            jsonWriter.key("ID").value(staff.getID());
        }
        jsonWriter.key("name").value(staff.getName());
        jsonWriter.key("surname").value(staff.getSurname());
        jsonWriter.key("position").value(staff.getPosition());
        jsonWriter.key("salary").value(staff.getSalary());
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
    public List<Staff> getAllStaff() throws IOException {
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
        jsonWriter.key("command-name").value("get-all-staff");
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
        List <Staff> allStaff = new ArrayList<>();
        JSONArray responseArray = response.getJSONArray("response-data");//достали массив
        int length = responseArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject responseObject = responseArray.getJSONObject(i);//при каждом проходе достаем объект из массива

            Integer ID = responseObject.getInt("ID");
            String name = responseObject.getString("name");
            String surname = responseObject.getString("surname");
            String position = responseObject.getString("position");
            double salary = responseObject.getDouble("salary");

            Staff staff = new Staff();
            staff.setID(ID);
            staff.setName(name);
            staff.setSurname(surname);
            staff.setPosition(position);
            staff.setSalary(salary);

            allStaff.add(staff);//каждый объект закидываю в лист
        }

        writer.close();
        is.close();
        socket.close();
        return allStaff;
    }

    @Override
    public Staff getStaffByID(Integer ID) throws IOException {
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
        jsonWriter.key("command-name").value("get-staff-by-id");
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
        Staff staff = new Staff();

        int size = responseData.length();
        for (int i = 0; i<size; i++) {
            JSONObject responseDataObject = responseData.getJSONObject(i);

            Integer sID = responseDataObject.getInt("ID");
            String name = responseDataObject.getString("name");
            String surname = responseDataObject.getString("surname");
            String position = responseDataObject.getString("position");
            double salary = responseDataObject.getDouble("salary");

            staff.setID(sID);
            staff.setName(name);
            staff.setSurname(surname);
            staff.setPosition(position);
            staff.setSalary(salary);
        }
        writer.close();
        is.close();
        socket.close();
        return staff;
    }

    @Override
    public void deleteStaff(Integer ID) throws IOException {
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
        jsonWriter.key("command-name").value("delete-staff");
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
