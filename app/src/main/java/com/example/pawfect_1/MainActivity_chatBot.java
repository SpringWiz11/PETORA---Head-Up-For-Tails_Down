package com.example.pawfect_1;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity_chatBot extends AppCompatActivity {
    private RecyclerView botChat;
    private EditText userMessageEdit;
    private FloatingActionButton sendButtonFAB;
    private final String BOT_KEY = "bot";
    private final String USER_KEY = "user";
    private ArrayList<com.example.pawfect_1.ResponseStorage> responseStorageArrayList;
    private com.example.pawfect_1.ChatAdapter chatAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_chatbot);
        botChat = findViewById(R.id.idRLChat);
        userMessageEdit = findViewById(R.id.idEditMessage);
        sendButtonFAB = findViewById(R.id.idFABSend);
        responseStorageArrayList = new ArrayList<>();
        chatAdapter = new com.example.pawfect_1.ChatAdapter(responseStorageArrayList,this);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        botChat.setLayoutManager(manager);
        botChat.setAdapter(chatAdapter);

        sendButtonFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userMessageEdit.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity_chatBot.this, "Zzz... Start typing to wake me up!!", Toast.LENGTH_SHORT).show();
                    return;
                }
                getResponse(userMessageEdit.getText().toString());
                userMessageEdit.setText("");
            }
        });

    }
    private void getResponse(String message){
        responseStorageArrayList.add(new ResponseStorage(message, USER_KEY));
        chatAdapter.notifyDataSetChanged();
        String url = "http://api.brainshop.ai/get?bid=165018&key=olDtXXF4h8rYggpJ&uid=[uid]&msg="+message;
        String BASE_URL = "http://api.brainshop.ai/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<ResponseModel> call = retrofitAPI.getMessage(url);
        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                if(response.isSuccessful()) {
                    ResponseModel model = response.body();
                    responseStorageArrayList.add(new ResponseStorage(model.getCnt(),BOT_KEY));
                    chatAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                responseStorageArrayList.add(new ResponseStorage("Oops, I don't know what you are talking about! Try asking another question.", BOT_KEY));
                chatAdapter.notifyDataSetChanged();

            }
        });

    }
}