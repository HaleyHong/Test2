package com.feyon.myapplication.retrofit;

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

/**
 * Created by DS on 2018/7/2.
 * 上传
 */

public class RequestBody<T>  extends okhttp3.RequestBody{

    private RequestBody requestBody;
    private testCa<T> ttestCa;

    private BufferedSink bufferedSink;
    public RequestBody(RequestBody requestBody, testCa<T> ttestCa) {
        super();
        this.requestBody = requestBody;
        this.ttestCa = ttestCa;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        bufferedSink= Okio.buffer(sink);


        requestBody.writeTo(bufferedSink);
        bufferedSink.flush();
    }

    private Sink sink(Sink sink)
    {
        return new ForwardingSink(sink) {
            long bytesWritten=0L;
            long contentLength=0L;

            @Override
            public void write(Buffer source, long byteCount) throws IOException {
                super.write(source, byteCount);
                if (contentLength==0)
                {
                    contentLength=contentLength();
                }

                bytesWritten+=byteCount;
                ttestCa.loadding(contentLength,bytesWritten);
            }
        };
    }
}
