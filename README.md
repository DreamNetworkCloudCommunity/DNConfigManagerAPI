# DNConfigManagerAPI

---

Configuration File's Type:
-
- Non-Encoded
- Encoded (In Base64)

Configuration File's class Managers:
-
- ConfigManager
- EncodedConfigManager

Configuration File's class Managers' Methods:
-
- `loadContent()` - Load the Config File content
- `getName()` - Return the Config File Name
- `read()` - Return the Config File content (Non-Encoded)
- `save()` - Save the File (After changing values)
- `writeDefault()` - Write "{}" in the file and erase all others data
- `set(String key, Object value)` - Write a value in the File (it doesn't save)
- `remove(String key)` - Remove a values from the File (it doesn't save)
- `getHashMap(String key)` - Return an HashMap<Object,Object> from a key
- `getString(String key)` - Return a String from a key
- `getList(String key)` - Return a List\<Object> from a key
- `getInt(String key)` - Return an Integer from a key
- `getFloat(String key)` - Return a Float from a key
- `getBoolean(String key)` - Return a Boolean from a key

---

---

## 1 - Initializing

To Initialize the API, you just have to create a new instance of the Config Class (`fr.benjimania74.configmanager.Config`)

```java
new Config(String addonName); // Initialize the API
```

---

## 2 - Create a Config File

You can create Encoded Config's Files and non Encoded Config's Files by getting the `Config()` instance

#### Non-Encoded Config File Creation

```java
Config.getInstance().createConfig(String name); // name = Config File's Name
// THAT RETURN a ConfigManager Instance
```

#### Encoded Config File Creation

```java
Config.getInstance().createEncodedConfig(String name); // name = Config File's Name
// THAT RETURN an EncodedConfigManager Instance
```

If the Config File is already existing, you will get an instance of it.

---

## 3 - Get a Config File

You can get Encoded and Non-Encoded Config File by getting the `Config()` instance.
You can use the name of the file or the File to get it (use the name is recommended)

#### Get a Non-Encoded Config File

```java
Config.getInstance().getConfig(String name);
Config.getInstance().getConfig(File file);
```

#### Get an Encoded Config File

```java
Config.getInstance().getEncodedConfig(String name);
Config.getInstance().getEncodedConfig(File file);
```

---

## 4 - Example

```java
import be.alexandre01.dreamnetwork.api.DNClientAPI;
import be.alexandre01.dreamnetwork.api.addons.Addon;
import be.alexandre01.dreamnetwork.api.addons.DreamExtension;
import fr.benjimania74.configmanager.Config;
import fr.benjimania74.configmanager.ConfigManager;
import fr.benjimania74.configmanager.EncodedConfigManager;

public class ExampleClass extends DreamExtension {
    public static DNClientAPI clientAPI;

    @Override
    public void onLoad() {
        super.onLoad();

        new Config(getAddon().getDreamyName()); // Initialize the API
    }

    @Override
    public void start() {
        super.start();
        clientAPI = getDnClientAPI();

        // Non-Encoded Config

        ConfigManager cm = Config.getInstance().createConfig("hello"); // Create "hello" Config File
        cm.writeDefault(); // Write "{}"
        cm.save(); // Save "{}"

        
        // Encoded Config

        EncodedConfigManager ecm = Config.getInstance().getEncodedConfig("encoded"); // Get "encoded" Encoded Config File
        ecm.set("isEncoded", true); // Set a boolean value in the file
        System.out.println(ecm.getString("hello")); // Print a String value
        ecm.save(); // Save the content
    }

    @Override
    public void stop() {
        super.stop();
    }

    public ExampleClass(Addon addon) {
        super(addon);
    }
}
```
