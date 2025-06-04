package com.mirea.karyakina.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class ShareActivity extends AppCompatActivity {

    private EditText FavoriteBook;
    private EditText QuoteFromBook;
    private TextView LoveDevBook;
    private TextView LoveDevQuote;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_share);

        LoveDevBook = findViewById(R.id.txt_loveDevBook);
        LoveDevQuote = findViewById(R.id.txt_loveDevQuote);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String book_name = extras.getString(MainActivity.BOOK_NAME_KEY);
            String quotes_name = extras.getString(MainActivity.QUOTES_KEY);
            LoveDevBook.setText("Моя любимая книга: " + book_name);
            LoveDevQuote.setText("Цитата из книги: " + quotes_name);
        }
    }

    public void InputDataAboutBook(View view) {
        FavoriteBook = findViewById(R.id.ed_FavoriteBook);
        QuoteFromBook = findViewById(R.id.ed_quoteFromBook);

        String Book = FavoriteBook.getText().toString();
        String Quote = QuoteFromBook.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("USER_BOOK", Book);
        resultIntent.putExtra("USER_QUOTE", Quote);
        setResult(Activity.RESULT_OK, resultIntent);
        finish();
    }
}