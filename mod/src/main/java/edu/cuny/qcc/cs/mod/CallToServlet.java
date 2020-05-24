package edu.cuny.qcc.cs.mod;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public class CallToServlet {
    private final String base_url = "http://axess.inc.gs:3320";
    private final String TAG = "CallToServlet";


    public QuestionObject[] getQuestions(int getRank1, int getRank2, int getRank3) throws Exception {
        OkHttpClient client = new OkHttpClient();
        HttpUrl.Builder urlBuilder
                = HttpUrl.parse(base_url + "/getQuestions").newBuilder();
        if(getRank1 == 1)
        urlBuilder.addQueryParameter("ranks", "1");
        if(getRank2 == 1)
            urlBuilder.addQueryParameter("ranks", "2");
        if(getRank3 == 1)
            urlBuilder.addQueryParameter("ranks", "3");

        String url = urlBuilder.build().toString();

        Request request = new Request.Builder()
                .url(url)
                .build();
//        Call call = client.newCall(request);
//        Response response = call.execute();
        final String[] responseBodyString = new String[1];
        client.newCall(request).enqueue(new Callback() {
            @Override public void onFailure(Call call, IOException e) {
                Log.d("URL", "fail");
                e.printStackTrace();
            }

            @Override public void onResponse(Call call, Response response) throws IOException {
                try (ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
                    Log.d("URL", "success");
                    Headers responseHeaders = response.headers();
                    for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                        //System.out.println(responseHeaders.name(i) +  i + ": " + responseHeaders.value(i));
                    }
                    responseBodyString[0] = responseBody.string();
                    //System.out.println("hello " + responseBodyString[0]);
                }
            }
        });
        Gson gson = new Gson();
        //QuestionObject[] entity = new QuestionObject[1];
        try {
            Thread.sleep(1 * 700);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        QuestionObject[] entity = gson.fromJson(responseBodyString[0], QuestionObject[].class);
//        for(QuestionObject q : entity) {
//            Log.d("Works", q.toString());
//        }
        return entity;
    }

    public String getAnswer(String id, String answer, int questionID) throws Exception {
        OkHttpClient client = new OkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("ID", id)
                .add("answer", answer)
                .add("questionID", String.valueOf(questionID))
                .build();

        Request request = new Request.Builder()
                .url(base_url + "/answer")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        try {
            Thread.sleep(1 * 700);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        Response response = call.execute();
        try {
            Thread.sleep(1 * 700);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        ResponseBody responseBody = response.body();
        try {
            Thread.sleep(1 * 700);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        String responseBodyString = responseBody.string();
        try {
            Thread.sleep(1 * 700);
        } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        System.out.println(responseBodyString);
        return responseBodyString;
    }


}
