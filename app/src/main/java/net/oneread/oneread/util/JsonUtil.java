package net.oneread.oneread.util;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonSyntaxException;

/**
 * Created by sermilion on 9/12/17.
 */

public class JsonUtil {
    private static Gson gson;

    public static Gson getDefaultGson() {
        if (gson == null) {
            gson = new Gson();
        }
        return gson;
    }

    public static String toJson(Object object) {
        return getDefaultGson().toJson(object);
    }

    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonSyntaxException {
        return getDefaultGson().fromJson(json, classOfT);
    }

    public static String childToString(JsonElement element, String childName) {
        if (element == null || element.isJsonNull()) {
            return null;
        }
        final JsonElement child = element.getAsJsonObject().get(childName);
        if (child == null || child.isJsonNull()) {
            return null;
        }
        return child.getAsString();
    }
}