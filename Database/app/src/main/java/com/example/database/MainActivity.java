package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    DBHelper helper;
    SQLiteDatabase db;
    ListView book_list;
    SimpleCursorAdapter adapter;
    ToggleButton toogleButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        book_list = findViewById(R.id.book_list);
        DBHelper helper = new DBHelper(this);
        db = helper.getWritableDatabase();


        toogleButton = (ToggleButton) findViewById(R.id.toggleButton);
        toogleButton.setOnCheckedChangeListener(this);

        Cursor book = db.rawQuery("SELECT * FROM book_list", null);

        String[] book_list_fields = book.getColumnNames();

        // int[] - ссылки на id элементов разметки playlist_item
        // полученный Cursor использовать для создания адаптера
        // готовый адаптер назначить для ListView
        int[] views = { R.id.id, R.id.title, R.id.author, R.id.age_limit, R.id.year };

        //cUrsor содержит выборку всех записей
        adapter = new SimpleCursorAdapter(this, R.layout.booklist, book ,book_list_fields, views,0);
        book_list.setAdapter(adapter);
    }

    public void onClick(View view){
        EditText title, author, age_limit, year;
        title = findViewById(R.id.Etitle);
        author = findViewById(R.id.Eauthor);
        age_limit = findViewById(R.id.Eage_limit);
        year = findViewById(R.id.Eyear);
        Object[] args = {title.getText(),author.getText(),age_limit.getText(),year.getText()};
        int i = 0;
        for(Object ar:args){
            if (ar.toString().equals("")) {
                i++;
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Есть пустые ячейки",
                        Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.show();
            }
        }

        if (i==0) {
            db.execSQL("INSERT INTO book_list (title, author, age_limit, year) values (?, ?, ?, ?)",args);
            //execSQL подставляет значение
            adapter.changeCursor(db.rawQuery("SELECT * FROM book_list", null));
        }

        /*
        db.execSQL("INSERT INTO book_list (title, author, age_limit, year) values (?, ?, ?, ?)",args);
        //execSQL подставляет значение
        adapter.changeCursor(db.rawQuery("SELECT * FROM book_list ORDER BY year", null));*/
    }

    public void onClickClear(View view){
        db.execSQL("DELETE FROM book_list");
        //execSQL подставляет значения
        adapter.changeCursor(db.rawQuery("SELECT * FROM book_list", null));
    }

    public void onClickCount(View view){
        int count;
        String Count;
        TextView ans;
        ans = findViewById(R.id.Count);
        Cursor book = db.rawQuery("SELECT * FROM book_list", null);
        count = book.getCount();
        Count = Integer.toString(count);
        ans.setText(Count);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        if (isChecked)
            adapter.changeCursor(db.rawQuery("SELECT * FROM book_list ORDER BY year ASC", null ));
        else
            adapter.changeCursor(db.rawQuery("SELECT * FROM book_list ORDER BY id ASC", null ));
            //adapter.changeCursor(db.rawQuery("SELECT * FROM book_list ORDER BY year DESC", null ));
    }




}
