package sj.mblog.parser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonParser implements Parser {

    protected static final int JSON_INDENT = 4;

    @Override
    public String parse(Object object) {
        if (object == null) {
            return null;
        }

        String result = object.toString();
        StringBuilder builder = new StringBuilder();
        try {
            if (result.trim().startsWith("{") && result.trim().endsWith("}")) {
                JSONObject jsonObject = new JSONObject(result);
                builder.append(result.substring(0, result.indexOf("{")));
                builder.append(jsonObject.toString(JSON_INDENT));
                builder.append(result.substring(result.lastIndexOf("}") + 1, result.length()));
                return builder.toString();
            }
            if (result.trim().startsWith("[") && result.trim().endsWith("]")) {
                JSONArray jsonArray = new JSONArray(result);
                builder.append(result.substring(0, result.indexOf("[")));
                builder.append(jsonArray.toString(JSON_INDENT));
                builder.append(result.substring(result.lastIndexOf("]") + 1, result.length()));
                return builder.toString();
            }
        } catch (JSONException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
