package fr.benjimania74.configmanager;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class ConfigManager {
    private final File file;
    private JSONObject content;

    public ConfigManager(File file){
        if(!file.exists()){this.file = null;return;}
        this.file = file;
        loadContent();
    }

    private void loadContent(){
        try {
            StringBuilder sb = new StringBuilder();
            Scanner scan = new Scanner(this.file);

            while(scan.hasNextLine()){sb.append(scan.nextLine()).append("\n");}

            this.content = (JSONObject) new JSONParser().parse(sb.toString());
        }catch (Exception e){e.printStackTrace();}
    }

    public String getName(){return file.getName();}

    public String read(){return content.toJSONString();}

    public HashMap<Object, Object> getHashMap(Object key){return (JSONObject) this.content.get(key);}
    public List<Object> getList(Object key){return (JSONArray) this.content.get(key);}
    public String getString(Object key){return (String) this.content.get(key);}
    public Integer getInt(Object key){return (Integer) this.content.get(key);}
    public Float getFloat(Object key){return (Float) this.content.get(key);}
    public boolean getBoolean(Object key){return (boolean) this.content.get(key);}

    public boolean set(Object key, Object value){
        try{
            this.content.put(key, value);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean remove(Object key){
        try{
            this.content.remove(key);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean containKey(Object key){return this.content.containsKey(key);}
    public boolean containValue(Object value){return this.content.containsValue(value);}

    public boolean writeDefault(){
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write("{}");
            fw.flush();
            fw.close();
            return true;
        }catch (Exception e){e.printStackTrace();return false;}
    }

    public boolean save(){
        try {
            FileWriter fw = new FileWriter(this.file);
            fw.write(this.content.toJSONString());
            fw.flush();
            fw.close();
            return true;
        }catch (Exception e){e.printStackTrace();return false;}
    }
}