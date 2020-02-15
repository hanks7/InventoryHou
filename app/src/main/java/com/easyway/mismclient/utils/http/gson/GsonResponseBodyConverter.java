package com.easyway.mismclient.utils.http.gson;

import android.text.TextUtils;

import com.easyway.mismclient.utils.Ulog;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import okhttp3.ResponseBody;
import retrofit2.Converter;

final class GsonResponseBodyConverter<T> implements Converter<ResponseBody, T> {
    private final Gson gson;
    private final TypeAdapter<T> adapter;

    GsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
//        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        String json = value.string();

        if (TextUtils.isEmpty(json))
            json = "{}";
//        else {
//            json = EncryptUtil.DES3Decode(json);
//        }
        Ulog.i("json" , json);
        JsonReader jsonReader = new JsonReader(new InputStreamReader(new ByteArrayInputStream(json.getBytes())));
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}