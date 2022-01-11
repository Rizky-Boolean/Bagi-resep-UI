package com.katuliteam.bagiresep_ui;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConfig extends SQLiteOpenHelper {
    // konfigurasi database
    static final String db_name = "db_bagiresep.db";
    static final int db_version = 1;

    public DBConfig(@Nullable Context context) {
        super(context, db_name, null, db_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql;
        // buat tabel user
        sql = "CREATE TABLE tbl_user (email TEXT(100) NOT NULL, nama TEXT(100) NOT NULL, password TEXT(100) NOT NULL, PRIMARY KEY(email))";
        db.execSQL(sql);

        // buat tabel resep
        sql = "CREATE TABLE tbl_resep (keyword TEXT(100) NOT NULL, emailPenulis TEXT(100) NOT NULL, penulis TEXT(100) NOT NULL, judul TEXT(100) NOT NULL, deskripsi TEXT(255) NOT NULL, bahan TEXT(255) NOT NULL, cara TEXT(255) NOT NULL, PRIMARY KEY(keyword))";
        db.execSQL(sql);

        // buat tabel login
        sql = "CREATE TABLE tbl_login (email TEXT(100) NOT NULL, nama TEXT(100) NOT NULL, PRIMARY KEY(email))";
        db.execSQL(sql);

        // buat data
        sql = "INSERT INTO tbl_resep (keyword, emailPenulis, penulis, judul, deskripsi, bahan, cara) "
                + "VALUES ('nasigoreng', 'abc@example.com', 'Anak Baik', 'Nasi Goreng'" +
                ", 'Bagi yang ingin membuat makanan cepat saji, mari mencoba membuat nasi goreng kampung dengan resep nasi goreng kampung istimewa ala Blueband berikut ini.'" +
                ", '- nasi putih 600 gr\n" +
                "- telur ayam 2 butir\n" +
                "- bakso sapi potong-potong 4 butir\n" +
                "- tomat iris tipis 1 buah\n" +
                "- kecap manis Bango 1 sdm\n" +
                "- Blue Band 2 sdm\n" +
                "- Royco sapi 1 bungkus\n" +
                "- cabai merah keriting (tambahkan cabai rawit jika suka lebih pedas) 2 buah\n" +
                "- bawang merah 6 butir\n" +
                "- bawang putih 2 siung\n" +
                "- terasi 1/2 sdt\n" +
                "- garam 1/2 sdt\n" +
                "- bawang merah goreng untuk pelengkap secukupnya\n" +
                "- mentimun iris untuk pelengkap 1 buah'" +
                ", '1. Uleg bawang merah, bawang putih, cabai, terasi, dan garam hingga halus.\n" +
                "2. Tumis bumbu halus dengan Blue Band hingga harum. Tambahkan tomat, bakso, dan telur, aduk cepat dan masak hingga telur berbulir.\n" +
                "3. Masukkan nasi putih, kecap manis Bango, Royco, aduk hingga rata dan bumbu meresap.\n" +
                "4. Angkat, sajikan segera bersama pelengkapnya.'"

                + "), ('ayambakar', 'abc@example.com', 'Anak Baik', 'Ayam Bakar'" +
                ", 'Coba buat ayam bakar solo untuk menu makan siang keluarga. Cocok disantap dengan sambal terasi matang dan lalapan sayuran.'" +
                ", '- 1 ekor ayam, potong 8 bagian\n" +
                "- 2 batang serai, memarkan\n" +
                "- 3 lembar daun salam\n" +
                "- 4 cm lengkuas\n" +
                "- 3 sdm kecap manis\n" +
                "- ½ sdm garam\n" +
                "- ½ sdt gula pasir\n" +
                "- 400 ml air kelapa\n" +
                "- 1 sdm air asam jawa\n" +
                "- 2 sdm minyak untuk menumis'" +
                ", '1. 5 butir kemiri\n" +
                "2. 12 butir bawang merah\n" +
                "3. 4 siung bawang putih\n" +
                "4. 4 cm kunyit\n" +
                "5. 3 cm jahe\n" +
                "6. ¼ sdt jintan\n" +
                "7. 1 sdt ketumbar'"

                + "), ('keripiksongkong', 'abc@example.com', 'Anak Baik', 'Keripik Singkong'" +
                ", 'Makanan ringan seperti keripik memang menjadi camilan yang banyak disukai orang. Tak hanya orang dewasa, anak-anak sangat menyukai camilan keripik singkong. Selain keripik kentang, keripik singkong menjadi andalan banyak orang. Tidak sulit untuk menemukan keripik singkong.'" +
                ", '- 2 kg singkong (bersihkan kulitnya, kupas kemudian iris tipis)\n" +
                "- 1 sdt baking soda\n" +
                "- 1 liter air panas (bukan air yang baru mendidih)\n" +
                "- 1 sdt garam'" +
                ", '1. Cuci bersih singkong yang telah diiris tipis sampai bersih.\n" +
                "2. Siapkan bahan rendaman. Air panasnya bukan yang baru mendidih." +
                "3. Tambahkan garam dan baking soda aduk rata.\n" +
                "4. Masukkan singkong ke bahan rendaman. Rendam kurang lebih 45 menit-1 jam.\n" +
                "5. Tiriskan singkong kemudian bilas lagi dengan air bersih tiriskan lagi.\n" +
                "6. Panaskan minyak secukupnya harus terendam ya. Goreng singkong sampai kekuningan, sesekali diaduk-aduk supaya matang merata. Setelah matang langsung taburi bumbu sesuai selera.\n" +
                "7. Sajikan.'"

                + "), ('jusbuahnaga', 'abc@example.com', 'Anak Baik', 'Jus Buah Naga'" +
                ", 'Saat cuaca panas, paling cocok menikmati minuman yang segar. Minuman yang bisa menghilangkan dahaga dengan cepat. Kamu pasti mulai mencari minuman tersebut ke minimarket terdekat. Namun supaya kebersihan dan nutrisi baik tetap terjaga, buatlah sendiri minuman segar. Carilah berbagai resep jus buah, kalau masih bingung juga, resep jus buah naga merah bisa jadi pilihan tepat untuk masalahmu ini.'" +
                ", '- 1 buah naga merah ukuran besar\n" +
                "- 50 ml susu kental manis putih\n" +
                "- 1 sdm gula pasir\n" +
                "- air dingin secukupnya'" +
                ", '1. Potong kecil-kecil buah naga. Masukkan ke dalam blender, campurkan dengan bahan lainnya.\n" +
                "2. Blender buah semua campuran tersebut. Tuang ke gelas.'"
                + ")";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}
}
