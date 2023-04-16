package fr.benjimania74.configmanager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class EncodedConfigManager {
    private final File file;
    private JSONObject content;

    public EncodedConfigManager(File file){
        if(!file.exists()){this.file = null;return;}
        this.file = file;
        try {
            if (Files.size(file.toPath()) == 0) {return;}
            loadContent();
        }catch (Exception e){e.printStackTrace();}


    }

    private void loadContent() {
        try {
            StringBuilder sb = new StringBuilder();
            Scanner scan = new Scanner(this.file);

            while(scan.hasNextLine()){sb.append(scan.nextLine());}

            String content = new String(Base64.getDecoder().decode(sb.toString()));
            if(content.length() == 0){this.content = new JSONObject();return;}
            this.content = (JSONObject) new JSONParser().parse(content);
        }catch (Exception e){e.printStackTrace();}
    }

    public String getName(){return file.getName();}

    public String read(){return content.toJSONString();}

    public HashMap<Object, Object> getHashMap(String key){return (HashMap<Object, Object>) this.content.get(key);}
    public List<Object> getList(String key){return (List<Object>) this.content.get(key);}
    public String getString(String key){return (String) this.content.get(key);}
    public Integer getInt(String key){return (Integer) this.content.get(key);}
    public Float getFloat(String key){return (Float) this.content.get(key);}
    public boolean getBoolean(String key){return (boolean) this.content.get(key);}

    public boolean set(String key, Object value){
        try{
            this.content.put(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(String key){
        try{
            this.content.remove(key);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean containKey(String key){return this.content.containsKey(key);}
    public boolean containValue(Object value){return this.content.containsValue(value);}

    public boolean writeDefault(){
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(Base64.getEncoder().encodeToString("{}".getBytes()));
            fw.flush();
            fw.close();
            this.content = new JSONObject();
            return true;
        }catch (Exception e){e.printStackTrace();return false;}
    }

    public boolean save(){
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(Base64.getEncoder().encodeToString(this.content.toJSONString().getBytes()));
            fw.flush();
            fw.close();
            return true;
        }catch (Exception e){e.printStackTrace();return false;}
    }
}
