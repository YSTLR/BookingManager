import com.yst.entity.Constant;
import com.yst.entity.Database;
import com.yst.entity.HttpResponse;
import com.yst.entity.config.DateBaseConfig;
import com.yst.service.AvailableRoomService;
import com.yst.service.BookingService;

import java.util.HashMap;

public class BookingServiceTest {

    static HashMap<String,Object> request = null;
    static {
        //init database
        Database.build(new DateBaseConfig(Constant.INIT_ROOM_NUM,Constant.RUN_TIME));
        request = new HashMap(){{
            put("method","POST");
            put("Accept","*/*");
            put("User-Agent","PostmanRuntime/7.29.0");
            put("Connection","keep-alive");
            put("Host","127.0.0.1");
            put("Accept-Encoding","gzip, deflate, br");
            put("url","/booking");
            put("protocol","HTTP/1.1");
            put("Cache-Control","no-cache");
            put("Collection","close");
            put("Content-Length",null);
            put("","");
        }};

    }

    //booking a room
    @org.junit.Test
    public void test01(){
        System.out.println(Database.roomBookingStatus);
        AvailableRoomService availableRoomService = new AvailableRoomService();
        BookingService bookingService = new BookingService();
        request.put("RequestBody" ,new HashMap<String,String>(){{
            put("date","20220201");
            put("name","yst");
            put("roomNum","44");
        }});
        HttpResponse res1 = bookingService.method(request);
        System.out.println(res1);
    }

    //booking a room repeat
    @org.junit.Test
    public void test02(){
        System.out.println(Database.roomBookingStatus);
        AvailableRoomService availableRoomService = new AvailableRoomService();
        BookingService bookingService = new BookingService();
        request.put("RequestBody" ,new HashMap<String,String>(){{
            put("date","20220201");
            put("name","yst");
            put("roomNum","44");
        }});
        HttpResponse res1 = bookingService.method(request);
        System.out.println(res1);
        HttpResponse res2 = bookingService.method(request);
        System.out.println(res2);
    }

    //booking two rooms in one day
    @org.junit.Test
    public void test03(){
        System.out.println(Database.roomBookingStatus);
        AvailableRoomService availableRoomService = new AvailableRoomService();
        BookingService bookingService = new BookingService();
        request.put("RequestBody" ,new HashMap<String,String>(){{
            put("date","20220201");
            put("name","yst");
            put("roomNum","44");
        }});
        HttpResponse res1 = bookingService.method(request);
        System.out.println(res1);
        request.put("RequestBody" ,new HashMap<String,String>(){{
            put("date","20220201");
            put("name","yst");
            put("roomNum","43");
        }});
        HttpResponse res2 = bookingService.method(request);
        System.out.println(res2);
    }

    //booking one room for many days
    @org.junit.Test
    public void test04(){
        System.out.println(Database.roomBookingStatus);
        AvailableRoomService availableRoomService = new AvailableRoomService();
        BookingService bookingService = new BookingService();
        request.put("RequestBody" ,new HashMap<String,String>(){{
            put("date","20220201");
            put("name","yst");
            put("roomNum","44");
        }});
        HttpResponse res1 = bookingService.method(request);
        System.out.println(res1);
        request.put("RequestBody" ,new HashMap<String,String>(){{
            put("date","20220202");
            put("name","yst");
            put("roomNum","44");
        }});
        HttpResponse res2 = bookingService.method(request);
        System.out.println(res2);
        request.put("RequestBody" ,new HashMap<String,String>(){{
            put("date","20220203");
            put("name","yst");
            put("roomNum","44");
        }});
        HttpResponse res3 = bookingService.method(request);
        System.out.println(res3);
    }
}
