package com.example.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    final static String DB_NAME = "book.db";
    final static String TABLE_NAME = "book_list";
    final static String CREATE = "CREATE TABLE "+TABLE_NAME+ "( `_id` INTEGER PRIMARY KEY AUTOINCREMENT, `title` TEXT NOT NULL, `author` TEXT NOT NULL, `age_limit` INTEGER NOT NULL DEFAULT 0, `year` INTEGER NOT NULL )";
    // при изменении структуры БД меняется и версия
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // выполняется, если базы данных нет
        db.execSQL(CREATE);
        db.execSQL("INSERT INTO book_list VALUES (1, 'Завтрак у Sotheby`s', 'Хук Ф.', 16, 2013 )");
        db.execSQL("INSERT INTO book_list VALUES (2, 'Хроники Заводной Птицы', 'Харуки Мураками', 16, 1995 )");
        db.execSQL("INSERT INTO book_list VALUES (3, 'Гордость и предубеждение', 'Джейн Остин', 16, 1813 )");
        db.execSQL("INSERT INTO book_list VALUES (4, 'Человек, который смеется', 'Виктор Мари Гюго', 16, 1869 )");
        db.execSQL("INSERT INTO book_list VALUES (5, 'Накануне', 'Иван Тургенев', 12, 1859 )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // выполняется, если версия базы данных отличается
        db.execSQL("DROP DATABASE "+DB_NAME);
        this.onCreate(db);
    }
}