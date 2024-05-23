package com.example.spr;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView tytul = findViewById(R.id.tytul);
        TextView rezyser = findViewById(R.id.rezyser);
        ListView lista = findViewById(R.id.listView);

        ArrayList<String> filmy = new ArrayList<>();

        Button button = findViewById(R.id.dodaj);
        button.setOnClickListener((view) -> {
            CharSequence readyTytul = tytul.getText();
            CharSequence readyRezyser = rezyser.getText();

            String film = readyTytul + " " + readyRezyser;
            filmy.add(film);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getBaseContext(), android.R.layout.simple_list_item_1, filmy);
            lista.setAdapter(arrayAdapter);

            tytul.setText("");
            rezyser.setText("");
        });

        Intent intent = new Intent(getBaseContext(), DetailActivity.class);

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CharSequence film = filmy.get(position);
                intent.putExtra("film", film);

                setResult(RESULT_OK, intent);
                startActivity(intent);

            }
        });
    }
}