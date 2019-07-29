package com.feyon.myapplication.retrofit.customGson;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.nio.charset.Charset;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Created by DS on 2018/6/29.
 */

public class CGsonResponseBodyConverter <T> implements Converter<ResponseBody, T>{
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");


    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CGsonResponseBodyConverter(Gson gson, TypeAdapter<T> adapter) {
            this.gson = gson;
            this.adapter = adapter;
        }

    @Override public T convert(ResponseBody value) throws IOException {
     /*   String response = value.string();
        HttpResult httpStatus = gson.fromJson(response, HttpResult.class);
        if (httpStatus.isCodeInvalid()) {
            value.close();
            throw new ApiError(httpStatus.getCode(), httpStatus.getMessage());
        }

        MediaType contentType = value.contentType();
        Charset charset = contentType != null ? contentType.charset(UTF_8) : UTF_8;
        InputStream inputStream = new ByteArrayInputStream(response.getBytes());
        Reader reader = new InputStreamReader(inputStream, charset);
        JsonReader jsonReader = gson.newJsonReader(reader);*/
        JsonReader jsonReader = gson.newJsonReader(value.charStream());
        try {
            return adapter.read(jsonReader);
        } finally {
            value.close();
        }
    }
}
