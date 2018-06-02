import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public abstract class JSONHelper {

    private static final String TAG = JSONHelper.class.getSimpleName();

    public static <T> List<T> parseJsonToList(Context context, String fileName, String typeString, Class<T> clazz) {
        try {
            return fromJsonList(convertJSONtoEntity(loadJsonFromAsset(context, fileName), typeString), clazz);
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
        return new ArrayList<>();
    }

    private static String loadJsonFromAsset(Context context, String fileName) {
        String json;
        try {
            InputStream is = context.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
            return json;
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
        return null;
    }

    private static String convertJSONtoEntity(String jsonString, String typeString) {
        String jsonObjectString = null;
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            jsonObjectString = jsonObject.get(typeString).toString();
        } catch (Exception e) {
            Log.e(TAG, e.getLocalizedMessage());
        }
        return jsonObjectString;
    }

    private static <T> List<T> fromJsonList(String json, Class<T> clazz) {
        Object[] array = (Object[]) java.lang.reflect.Array.newInstance(clazz, 0);
        array = new Gson().fromJson(json, array.getClass());
        List<T> list = new ArrayList<>();
        if (array != null && array.length > 0) {
            for (Object anArray : array) {
                list.add(clazz.cast(anArray));
            }
        }
        return list;
    }
}
