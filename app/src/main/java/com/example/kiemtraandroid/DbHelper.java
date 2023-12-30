package com.example.kiemtraandroid;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public DbHelper(@Nullable Context context) {
        super(context, "QuanLySanPham", null, 4);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE SanPham(MaSP INTEGER PRIMARY KEY AUTOINCREMENT, " + "TenSP TEXT, GiaTien REAL, Image TEXT)";
        db.execSQL(sql);
        String data = "INSERT INTO SanPham VALUES(1, 'Veggie tomato mix', '#1,900', 'https://media.istockphoto.com/id/1035038546/vi/anh/s%E1%BB%91t-rau-v%C3%A0-c%C3%A0-chua-h%E1%BB%97n-h%E1%BB%A3p.jpg?s=1024x1024&w=is&k=20&c=7ZXt-c98jfU0JIeK4DLwArSjV-HjKoTU5JEGJs7ufTc=')," +
                "(2, 'Fish Fillets L’Orange', '#1,900', 'https://recipes.net/wp-content/uploads/2020/04/fish-fillets-lorange-recipe-scaled.jpg')," +
                "(3, 'Fish Fillets L’Orange', '#1,900', 'https://recipes.net/wp-content/uploads/2020/04/fish-fillets-lorange-recipe-scaled.jpg')," +
                "(4, 'Fish Fillets L’Orange', '#1,900', 'https://recipes.net/wp-content/uploads/2020/04/fish-fillets-lorange-recipe-scaled.jpg')," +
                "(5, 'Veggie tomato mix', '#1,900', 'https://media.istockphoto.com/id/1035038546/vi/anh/s%E1%BB%91t-rau-v%C3%A0-c%C3%A0-chua-h%E1%BB%97n-h%E1%BB%A3p.jpg?s=1024x1024&w=is&k=20&c=7ZXt-c98jfU0JIeK4DLwArSjV-HjKoTU5JEGJs7ufTc=')";
        db.execSQL(data);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        if (i != i1){
            db.execSQL("DROP TABLE IF EXISTS SanPham");
            onCreate(db);
        }
    }
}
