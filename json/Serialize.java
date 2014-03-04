package com.ll.wikimart.json;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Serialize {
    public static Gson createGson()
    {
        GsonBuilder gb = new GsonBuilder().serializeNulls().setPrettyPrinting();
        gb.registerTypeAdapter
                (
                        Date.class, new JsonSerializer<Date>()
                {
                    @Override
                    public JsonElement serialize(Date src, Type typeOfSrc, JsonSerializationContext context)
                    {
                        final DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'THH:mm:ss'+04:00'");  //FIXME: WTF IS THIS SHIT
                        return new JsonPrimitive(fmt.format(src));
                    }
                }
                );
        gb.registerTypeAdapter
                (
                        Date.class, new JsonDeserializer<Date>()
                {
                    @Override
                    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
                    {
                        final DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'+04:00'"); //FIXME: WTF IS THIS SHIT
                        Date d;
                        try
                        {
                            d =  fmt.parse(json.getAsString());
                        }
                        catch (ParseException e){ throw new JsonParseException("Date malformed ",e); }
                        return d;
                    }
                }
                );
        return gb.create();
    }
}
