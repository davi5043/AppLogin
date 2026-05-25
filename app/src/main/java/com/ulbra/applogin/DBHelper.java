package com.ulbra.applogin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "BancoDados.db";
    private static final int VERSAO = 1;

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE utilizador (" +
                "username TEXT PRIMARY KEY, " +
                "password TEXT)";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS utilizador");
        onCreate(db);
    }


    public boolean usuarioExiste(String userName) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT username FROM utilizador WHERE username=?",
                new String[]{userName}
        );

        boolean existe = cursor.getCount() > 0;
        cursor.close();
        return existe;
    }


    public long criarUtilizador(String userName, String password) {

        if (usuarioExiste(userName)) {
            return -1;
        }

        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("username", userName);
        values.put("password", password);

        long result = db.insert("utilizador", null, values);
        db.close();

        return result;
    }


    public boolean validarLogin(String userName, String password) {

        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.rawQuery(
                "SELECT * FROM utilizador WHERE username=? AND password=?",
                new String[]{userName, password}
        );

        boolean sucesso = cursor.getCount() > 0;
        cursor.close();

        return sucesso;
    }
}