package com.huang.test.net;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.internal.bind.ObjectTypeAdapter;
import com.huang.test.utils.LogUtil;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by EDZ on 2020/3/30.
 */

public class BaseParamsInterceptor implements Interceptor {

    Gson gson;
    public BaseParamsInterceptor(){
        gson = new GsonBuilder().create();
    }
    public Gson getGson() {
        Gson gson = new GsonBuilder().create();
        try {
            Field factories = Gson.class.getDeclaredField("factories");
            factories.setAccessible(true);
            Object o = factories.get(gson);
            Class<?>[] declaredClasses = Collections.class.getDeclaredClasses();
            for (Class c : declaredClasses) {
                if ("java.util.Collections$UnmodifiableList".equals(c.getName())) {
                    Field listField = c.getDeclaredField("list");
                    listField.setAccessible(true);
                    List<TypeAdapterFactory> list = (List<TypeAdapterFactory>) listField.get(o);
                    int i = list.indexOf(ObjectTypeAdapter.FACTORY);
                    list.set(i, GsonTypeAdapter.FACTORY);
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gson;
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
        if (request.method().equals("POST")) {
            RequestBody requestBody = request.body();
            if (requestBody instanceof PostJsonBody) {
                String content = ((PostJsonBody) requestBody).getContent();
//                HashMap<String, Object> hashMap = gson.fromJson(content, new TypeToken<HashMap<String, Object>>(){}.getType());

                //fastjson  牛逼
                JSONObject  jsonObject = JSONObject.parseObject(content);
                Map<String,Object> hashMap = (Map<String,Object>)jsonObject;
                TreeMap<String, Object> treeMap = new TreeMap<>();
                treeMap.putAll(hashMap);
                StringBuilder sb = new StringBuilder();
                Set<String> keySet = treeMap.keySet();
                Iterator<String> iter = keySet.iterator();
                while (iter.hasNext()) {
                    String key = iter.next();
                    sb.append(key+"="+treeMap.get(key)+"&");
                }
                sb.append("key=0snPmXYRo48YtiFqdYZsnHHR9dyjzS3y");//
                LogUtil.LogShitou(sb.toString());
                String s3 = MD5Util.MD5(sb.toString());
                LogUtil.LogShitou(s3.toString());
                String s4 = s3.toUpperCase();
                treeMap.put("sign",s4);
                LogUtil.LogShitou(s4.toString());
                requestBuilder.post( RequestBody.create(MediaType.parse("application/json; charset=utf-8"), gson.toJson(treeMap)));
            }
        }

        return chain.proceed(requestBuilder.build());
    }


}
