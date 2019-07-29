package com.feyon.myapplication.glide;

import java.io.IOException;

import io.reactivex.annotations.Nullable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/**
 * Created by DS on 2018/7/20.
 */

public class okResponseBody extends ResponseBody {

    private BufferedSource bufferedSource;
    private ResponseBody responseBody;

    private ProgressListener progressListener;

    public okResponseBody(String url,ResponseBody responseBody) {
        this.progressListener =ProgressInterceptor.getHashMap().get(url) ;
        this.responseBody=responseBody;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return responseBody.contentType();
    }

    @Override
    public long contentLength() {
        return responseBody.contentLength();
    }

    @Override
    public BufferedSource source() {
        if (bufferedSource==null)
        {
            bufferedSource= Okio.buffer(new ProgressSource(responseBody.source()));
        }
        return bufferedSource;
    }
    private class  ProgressSource extends ForwardingSource{
        private long totalBytesRead=0;
        private long curProgress=0;
        public ProgressSource(Source delegate) {
            super(delegate);
        }

        @Override
        public long read(Buffer sink, long byteCount) throws IOException {
//            return super.read(sink, byteCount);

            long bytesRead=super.read(sink,byteCount);

            long fullLength=responseBody.contentLength();
            if (bytesRead==-1)
            {
                totalBytesRead=fullLength;
            }else {
                totalBytesRead+=bytesRead;
            }
            if (progressListener!=null)
            progressListener.pro(fullLength,totalBytesRead);
            return bytesRead;
        }
    }
}
