package com.lgz.springBoot.service.http;

import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by ligaozhao on 06/10/17.
 */
@Service
public class OkHttpService {
    private OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .writeTimeout(150, TimeUnit.SECONDS)
            .build();

    private final static String URL = "http://localhost:3001/test/receive_file";

    final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    public String postWithFile(MultipartFile file) throws Exception {
        uploadImage(URL, file, file.getName());
        return "Uploading....";
    }

    public void uploadImage(String url, MultipartFile image, String imageName) throws IOException {
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart(imageName, imageName, RequestBody.create(MEDIA_TYPE_PNG, convertFrom(image)))
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                System.out.println(e.toString());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("------------------------------------");
                System.out.println(response.body().string());
                System.out.println("------------------------------------");
            }
        });
    }

    private File convertFrom(MultipartFile origin) throws IOException {
        File result = new File(origin.getOriginalFilename());
        result.createNewFile();
        FileOutputStream fos = new FileOutputStream(result);
        fos.write(origin.getBytes());
        fos.close();
        return result;
    }
}
