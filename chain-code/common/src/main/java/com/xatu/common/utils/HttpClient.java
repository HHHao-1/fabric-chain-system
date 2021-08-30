package com.xatu.common.utils;

import okhttp3.*;

import java.io.IOException;

/**
 * @Author hao
 * @create 2021/6/29 4:10 下午
 */
public class HttpClient {
  public static final MediaType JSON
      = MediaType.get("multipart/form-data");
  
  public static OkHttpClient client = new OkHttpClient();
  
  public static String post(String url, String json) throws IOException {
    RequestBody body = new MultipartBody.Builder()
        .setType(MultipartBody.FORM)
        .addFormDataPart("file", json)
        .build();
    Request request = new Request.Builder()
        .url(url)
        .post(body)
        .build();
    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }
  public static String get(String url) throws IOException {
    Request request = new Request.Builder()
        .url(url)
        .get()
        .build();
    try (Response response = client.newCall(request).execute()) {
      return response.body().string();
    }
  }

//  public static void main(String[] args) throws IOException {
//    String value1 = post("http://192.168.3.12:9094/add", "测试数据");
//    Gson gson = new Gson();
//    IpfsRs ipfsRs = gson.fromJson(value1, IpfsRs.class);
//    String hash = ipfsRs.getCid().getHash();
//    System.out.println(hash);
//  }
}
