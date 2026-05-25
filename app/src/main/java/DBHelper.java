import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String NOME_BANCO = "BancoDados.db";
    private static final int VERSAO = 1;

    public DBHelper(Context context) {
        super(context, NOME_BANCO, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE utilizador (" +
                "username TEXT PRIMARY KEY, " +
                "password TEXT);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS utilizador;");
        onCreate(db);
    }

    public long criarUtilizador(String userName, String password) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("username", userName);
        cv.put("password", password);

        return db.insert("utilizador", null, cv);
    }

    public String validarLogin(String userName, String password) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM utilizador WHERE username=? AND password=?",
                new String[]{userName, password}
        );

        String result = (c.getCount() > 0) ? "OK" : "ERRO";
        c.close();

        return result;
    }
}
