package com.andriodleaf.json.tools;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

public class UtilDateDeserializer implements JsonDeserializer<java.util.Date> {  
	  
    @Override  
    public java.util.Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {  
        return new java.util.Date(json.getAsJsonPrimitive().getAsLong());  
    }  
}  
