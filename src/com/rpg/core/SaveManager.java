package com.rpg.core;

import com.google.gson.*;
import java.io.*;
import java.lang.reflect.Type;
import com.rpg.entities.*;

public class SaveManager {
    private static Gson gson;

    static {
        JsonDeserializer<Hero> heroDeserializer = new JsonDeserializer<Hero>() {
            @Override
            public Hero deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                String className = jsonObject.get("className").getAsString();
                try {
                    return context.deserialize(jsonObject, Class.forName(className));
                } catch (ClassNotFoundException e) {
                    throw new JsonParseException("Could not find class: " + className, e);
                }
            }
        };

        JsonDeserializer<GameItem> itemDeserializer = new JsonDeserializer<GameItem>() {
            @Override
            public GameItem deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                    throws JsonParseException {
                JsonObject jsonObject = json.getAsJsonObject();
                String className = jsonObject.get("className").getAsString();
                try {
                    return context.deserialize(jsonObject, Class.forName(className));
                } catch (ClassNotFoundException e) {
                    throw new JsonParseException("Could not find class: " + className, e);
                }
            }
        };

        gson = new GsonBuilder()
                .registerTypeAdapter(Hero.class, heroDeserializer)
                .registerTypeAdapter(GameItem.class, itemDeserializer)
                .setPrettyPrinting()
                .create();

        File directory = new File("users_info");
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static boolean playerExists(String username) {
        File saveFile = new File("users_info/" + username + ".json");
        return saveFile.exists();
    }

    public static void savePlayer(PlayerSave data, String username) {
        try (FileWriter writer = new FileWriter("users_info/" + username + ".json")) {
            JsonElement element = gson.toJsonTree(data);
            if (element.isJsonObject()) {
                JsonObject root = element.getAsJsonObject();

                // Add className to Hero
                JsonObject heroObj = root.getAsJsonObject("hero");
                if (heroObj != null) {
                    heroObj.addProperty("className", data.getHero().getClass().getName());
                }

                // Add className to each item in inventory
                JsonArray inventoryArray = root.getAsJsonArray("inventory");
                if (inventoryArray != null) {
                    java.util.ArrayList<GameItem> items = data.getInventory();
                    for (int i = 0; i < items.size(); i++) {
                        JsonObject itemObj = inventoryArray.get(i).getAsJsonObject();
                        itemObj.addProperty("className", items.get(i).getClass().getName());
                    }
                }
            }
            gson.toJson(element, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PlayerSave loadPlayer(String username) {
        try (FileReader reader = new FileReader("users_info/" + username + ".json")) {
            return gson.fromJson(reader, PlayerSave.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
