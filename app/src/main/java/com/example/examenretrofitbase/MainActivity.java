package com.example.examenretrofitbase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examenretrofitbase.modelo.NewPost;
import com.example.examenretrofitbase.modelo.Post;
import com.example.examenretrofitbase.service.ApiClient;
import com.example.examenretrofitbase.service.IPostService;

import java.util.concurrent.ThreadLocalRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etTitle, etBody;
    private TextView tvPost;
    private Button btnGetPost, btnSendPost;

    private IPostService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar los componentes de la vista
        etTitle = findViewById(R.id.et_title);
        etBody = findViewById(R.id.et_body);
        tvPost = findViewById(R.id.tv_post);
        btnGetPost = findViewById(R.id.btn_get_post);
        btnSendPost = findViewById(R.id.btn_send_post);

        apiService = ApiClient.getInstance().create(IPostService.class);

        // Configurar el botón de traer post
        btnGetPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Post> call = apiService.getPost(ThreadLocalRandom.current().nextInt(1, 100 + 1));
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Post post = response.body();
                        tvPost.setText(post.getBody());
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error al traer el post", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        // Configurar el botón de enviar post
        btnSendPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewPost newPost = new NewPost(etTitle.getText().toString(), etBody.getText().toString(), "1");
                Call<Post> call = apiService.addPost(newPost);
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        Toast.makeText(MainActivity.this, Integer.toString(response.code()), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Error al enviar el post", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}