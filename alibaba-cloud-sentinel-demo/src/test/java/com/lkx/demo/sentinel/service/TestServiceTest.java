package com.lkx.demo.sentinel.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

//@SpringBootTest(classes = {TestService.class})
public class TestServiceTest extends TestCase {

    @Test
    public void test() {
        String files =
            "[ERROR] [FATAL] Non-parseable POM D:\\lib\\maven\\io\\netty\\netty-bom\\4.1.31.Final\\netty-bom-4.1.31.Final.pom: unexpected markup <!d (position: START_DOCUMENT seen \\r\\n<!d... @2:4)  @ D:\\lib\\maven\\io\\netty\\netty-bom\\4.1.31.Final\\netty-bom-4.1.31.Final.pom, line 2, column 4\n"
                + "[ERROR] [FATAL] Non-parseable POM D:\\lib\\maven\\io\\projectreactor\\reactor-bom\\Californium-SR3\\reactor-bom-Californium-SR3.pom: unexpected markup <!d (position: START_DOCUMENT seen \\r\\n<!d... @2:4)  @ D:\\lib\\maven\\io\\projectreactor\\reactor-bom\\Californium-SR3\\reactor-bom-Californium-SR3.pom, line 2, column 4\n";
        String[] split = files.split("\n");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            int start = s.indexOf("@ ") + 2;
            int end = s.indexOf(",", start);
            String file = s.substring(start, end);

            if (file.endsWith("pom")) {
                System.out.println(file);
                File f = new File(file);
                f.delete();
            }
        }
        System.out.println(split);
    }

    @Test
    public void testDPom() throws Exception {
        String pom =
            "[WARNING] The POM for org.springframework.cloud:spring-cloud-starter-config:jar:2.0.0.RELEASE is invalid, transitive dependencies (if any) will not be available, enable debug logging for more details\n"
                + "[WARNING] The POM for org.springframework.boot:spring-boot-starter-web:jar:2.0.0.RELEASE is invalid, transitive dependencies (if any) will not be available, enable debug logging for more details\n"
                + "[WARNING] The POM for org.springframework.boot:spring-boot-starter-test:jar:2.0.0.RELEASE is invalid, transitive dependencies (if any) will not be available, enable debug logging for more details\n"
                + "[WARNING] The POM for org.springframework.cloud:spring-cloud-config-server:jar:2.0.0.RELEASE is invalid, transitive dependencies (if any) will not be available, enable debug logging for more details\n";
        String[] split = pom.split("\n");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            int start = s.indexOf("for ") + 4;
            int end = s.indexOf(" is ", start);
            String file = s.substring(start, end);
            StringBuilder sb = new StringBuilder("D:\\lib\\maven\\");
            String[] fileList = file.split(":");
            sb.append(fileList[0].replaceAll("\\.", "\\\\"));
            sb.append("\\" + fileList[1]);
            sb.append("\\" + fileList[3]);

            File f = new File(sb.toString());
            deleteFile(f);
            f.delete();
            System.out.println(sb.toString() + " : \t " + f.exists());
        }
    }

    private static void deleteFile(File file) throws Exception {
        /**
         * File[] listFiles()
         *  返回一个抽象路径名数组，这些路径名表示此抽象路径名表示的目录中的文件。
         */
        File[] files = file.listFiles();
        if (files != null) {//如果包含文件进行删除操作
            for (int i = 0; i < files.length; i++) {
                if (files[i].isFile()) {
                    //删除子文件
                    files[i].delete();
                } else if (files[i].isDirectory()) {
                    //通过递归的方法找到子目录的文件
                    deleteFile(files[i]);
                }
                files[i].delete();//删除子目录
            }
        }
    }

    @Test
    public void testJson() {

        JSONObject nodeJson = new JSONObject();
        nodeJson.put("白天常驻人口数量", "10000");
        nodeJson.put("白天常驻人口密度", "100");
        nodeJson.put("职住比", "2.3");
        nodeJson.put("白天常驻人口数量TGI", "140");
        nodeJson.put("白天常驻人口密度TGI", "140");
        nodeJson.put("职住比TGI", "140");

        JSONObject nodeJson1 = new JSONObject();
        nodeJson1.put("晚上常驻人口数量", "10000");
        nodeJson1.put("晚上常驻人口密度", "100");
        nodeJson1.put("职住比", "2.3");
        nodeJson1.put("晚上常驻人口数量TGI", "140");
        nodeJson1.put("晚上常驻人口密度TGI", "140");
        nodeJson1.put("职住比TGI", "140");

        JSONObject sex = new JSONObject();
        sex.put("男", "1");
        sex.put("女", "2");

        JSONObject age = new JSONObject();
        age.put("0-17", "10");
        age.put("18-24", "20");
        age.put("25-35", "30");
        age.put("36-40", "40");

        List<MapObject> areaMapData = new ArrayList<>();
        areaMapData.add(new MapObject("12.12", "13.13", 10));
        List<MapObject> areaMapData1 = new ArrayList<>();
        areaMapData1.add(new MapObject("12.12", "13.13", 7));
        List<MapObject> areaMapData2 = new ArrayList<>();
        areaMapData2.add(new MapObject("12.12", "13.13", 3));
        List<MapObject> areaMapData3 = new ArrayList<>();
        areaMapData3.add(new MapObject("12.12", "13.13", 1));

        JSONObject area = new JSONObject();
        area.put("核心战略片区", areaMapData);
        area.put("重点发展片区", areaMapData1);
        area.put("战略潜力片区", areaMapData2);
        area.put("持续关注片区", areaMapData3);

        JSONObject grid = new JSONObject();
        grid.put("核心战略片区", areaMapData);
        grid.put("重点发展片区", areaMapData1);
        grid.put("战略潜力片区", areaMapData2);
        grid.put("持续关注片区", areaMapData3);



        JSONObject rootJson = new JSONObject();
        rootJson.put("城市名称", "武汉");
        rootJson.put("月份", "6");
        rootJson.put("类型", "均衡版\\高溢价\\快周转");
        rootJson.put("数据类型", "区县\\栅格");
        rootJson.put("城区类型", "核心战略区\\重点发展区\\战略潜片区\\持续关注区");
        rootJson.put("工作人群画像", nodeJson);
        rootJson.put("居住人群画像", nodeJson1);
        rootJson.put("性别", sex);
        rootJson.put("地区", area);
        rootJson.put("栅格数据", area);
        //        JSONObject rootJson = new JSONObject();
        //        rootJson.put("城市名称", "武汉");
        //        rootJson.put("月份", "6");
        //        rootJson.put("类型", "均衡版\\高溢价\\快周转");
        //        rootJson.put("数据类型", "区县\\栅格");
        //        rootJson.put("城区类型", "核心战略区\\重点发展区\\战略潜片区\\持续关注区");
        //        rootJson.put("组标识","工作人群画像");
        //        rootJson.put("组织", nodeJson);

        System.out.println(JSON.toJSONString(rootJson, SerializerFeature.MapSortField, SerializerFeature.PrettyFormat));
    }

    class MapObject {
        private String x;
        private String y;
        private int score;

        public MapObject(String x, String y, int score) {
            this.x = x;
            this.y = y;
            this.score = score;
        }

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getX() {
            return x;
        }

        public void setX(String x) {
            this.x = x;
        }

        public String getY() {
            return y;
        }

        public void setY(String y) {
            this.y = y;
        }
    }
}