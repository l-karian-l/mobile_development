package com.mirea.karyakina.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private ActivityResultLauncher<Intent> activityResultLauncher;
    static final String BOOK_NAME_KEY = "book_name";
    static final String QUOTES_KEY = "quotes_name";
    static final String USER_BOOK="USER_BOOK";
    static final String USER_QUOTE="USER_QUOTE";
    private TextView TextBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        TextBook = findViewById(R.id.txt_AboutBook);

        ActivityResultCallback<ActivityResult> callback = new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Intent data = result.getData();
                    String userBook = data.getStringExtra(USER_BOOK);
                    String quoteBook = data.getStringExtra(USER_QUOTE);
                    TextBook.setText("Название Вашей любимой книги: " + userBook
                            + "\n\nЦитата: " + quoteBook);
                }
            }
        };

        activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                callback);
    }

    public void onClickNewTextAboutBook(View view){
        Intent intent = new Intent(this, ShareActivity.class);
        intent.putExtra(BOOK_NAME_KEY, "Держи Марку");
        intent.putExtra(QUOTES_KEY, "Спасаешь кота из горящего здания. И двух человек в придачу, но все знают, что кот здесь важнее.");
        activityResultLauncher.launch(intent);
    }

}