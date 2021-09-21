package com.hadimusthafa.retrofitget;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        Button b1 = findViewById(R.id.b1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getWhatWeNeedMethod();
            }
        });
    }

    private void getWhatWeNeedMethod() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL).addConverterFactory(ScalarsConverterFactory.create()).build();
        Api api = retrofit.create(Api.class);
        Call<String> call = api.getWhatWeNeed("9544", "AreYouOk");
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.e("call-->", "-->" + call.request());
                Log.e("response code-->", "-->" + response.code());
                Log.e("response body-->", "-->" + response.body());

                try {
                    JSONArray jsonArray = new JSONArray(response.body());
//                    Log.e("response-->", "----Hadi----->" + jsonArray);

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        Log.e("response-->", "----Image----->" + jsonObject.getJSONObject("image").getString("original"));
                        Log.e("response-->", "----Name----->" + jsonObject.getString("name"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.e("t-->", "-->" + t.toString());
            }
        });
    }
}