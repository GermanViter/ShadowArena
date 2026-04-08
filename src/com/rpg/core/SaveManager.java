package com.rpg.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.*;

public class SaveManager {
    private static Gson gson;

    static {
        gson = new GsonBuilder()
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
            gson.toJson(data, writer);
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
