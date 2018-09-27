package id.roufroufrouf.javaretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    Button button;
    EditText editText;
    List<String> items;
    Gson gson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);
        button = findViewById(R.id.button);
        editText = findViewById(R.id.editText);

        load("Arsenal");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText() != null) {
                    load(editText.getText().toString());
                }
            }
        });
    }

    private void load(String cari) {
        items = new ArrayList<>();
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

        apiInterface.searchPlayers(cari).enqueue(new Callback<Global>() {
            @Override
            public void onResponse(Call<Global> call, Response<Global> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getPlayer() != null) {
                    for (Player player : response.body().getPlayer()) {
//                        items.add(gson.toJson(player));
                        items.add("Id = " + player.getIdPlayer() + " : " + player.getStrTeam());
                    }
                    setListView();
                } else {
                    System.out.println();
                }
            }

            @Override
            public void onFailure(Call<Global> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void setListView() {
        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, items);
        listView.setAdapter(itemsAdapter);
    }
}
