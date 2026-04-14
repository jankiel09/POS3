package com.example.pos3;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    //dbname and vesion
    private static final String DATABASE_NAME = "pos3.db";
    private static final int DATABASE_VERSION = 1;

    // tables
    static final String TABLE_EMPLOYEE = "employee";
    private static final String TABLE_FOOD = "food";
    private static final String TABLE_CATEGORY = "category";
    private static final String TABLE_ORDER = "orders";
    private static final String TABLE_INVENTORY = "inventory";
    private static final String TABLE_CART = "cart";
    private static final String TABLE_AUDIT = "audit";

    // column employee
    private static final String COLUMN_EMP_ID = "id";
    static final String COLUMN_EMP_USER = "name";
    static final String COLUMN_EMP_PASS = "pass";
    static final String COLUMN_EMP_ROLE = "role";

    // column food
    private static final String COLUMN_FOOD_ID = "id";
    private static final String COLUMN_FOOD_NAME = "name";
    private static final String COLUMN_FOOD_PRICE = "price";
    private static final String COLUMN_FOOD_CATEGORY = "category";

    // column category
    private static final String COLUMN_CATEGORY_ID = "id";
    private static final String COLUMN_CATEGORY_NAME = "name";

    // column order
    private static final String COLUMN_ORDER_ID = "id";
    private static final String COLUMN_ORDER_STATUS = "status";
    private static final String COLUMN_ORDER_DATE = "date";

    // column inventory
    private static final String COLUMN_INVENTORY_ID = "id";
    private static final String COLUMN_INVENTORY_NAME = "name";
    private static final String COLUMN_INVENTORY_PRICE = "price";
    private static final String COLUMN_INVENTORY_QTY = "qty";

    // column cart
    private static final String COLUMN_CART_ID = "id";
    private static final String COLUMN_CART_NAME = "name";
    private static final String COLUMN_CART_PRICE = "price";

    // column audit
    private static final String COLUMN_AUDIT_ID = "id";
    private static final String COLUMN_AUDIT_ACTION = "action";
    private static final String COLUMN_AUDIT_TIMESTAMP = "timestamp";


    //sql query
    private static final String CREATE_TABLE_EMPLOYEE = "CREATE TABLE " + TABLE_EMPLOYEE + "("
            + COLUMN_EMP_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_EMP_USER + " TEXT,"
            + COLUMN_EMP_PASS + " TEXT,"
            + COLUMN_EMP_ROLE + " TEXT"
            + ")";

    private static final String CREATE_TABLE_FOOD = "CREATE TABLE " + TABLE_FOOD + "("
            + COLUMN_FOOD_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_FOOD_NAME + " TEXT,"
            + COLUMN_FOOD_PRICE + " REAL,"
            + COLUMN_FOOD_CATEGORY + " TEXT"
            + ")";

    private static final String CREATE_TABLE_CATEGORY = "CREATE TABLE " + TABLE_CATEGORY + "("
            + COLUMN_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_CATEGORY_NAME + " TEXT"
            + ")";

    private static final String CREATE_TABLE_ORDER = "CREATE TABLE " + TABLE_ORDER + "("
            + COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_ORDER_STATUS + " TEXT,"
            + COLUMN_ORDER_DATE + " TEXT"
            + ")";

    private static final String CREATE_TABLE_INVENTORY = "CREATE TABLE " + TABLE_INVENTORY + "("
            + COLUMN_INVENTORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_INVENTORY_NAME + " TEXT,"
            + COLUMN_INVENTORY_PRICE + " REAL,"
            + COLUMN_INVENTORY_QTY + " INTEGER"
            + ")";

    private static final String CREATE_TABLE_CART = "CREATE TABLE " + TABLE_CART + "("
            + COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_CART_NAME + " TEXT,"
            + COLUMN_CART_PRICE + " REAL"
            + ")";

    private static final String CREATE_TABLE_AUDIT = "CREATE TABLE " + TABLE_AUDIT + "("
            + COLUMN_AUDIT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_AUDIT_ACTION + " TEXT,"
            + COLUMN_AUDIT_TIMESTAMP + " TEXT"
            + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_EMPLOYEE);
        db.execSQL(CREATE_TABLE_FOOD);
        db.execSQL(CREATE_TABLE_CATEGORY);
        db.execSQL(CREATE_TABLE_ORDER);
        db.execSQL(CREATE_TABLE_INVENTORY);
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_AUDIT);
        addInitialInventory(db);


        SeedData(db);

    }

    public void updateStock(int inventoryId, int qtySold){
        SQLiteDatabase db = this.getWritableDatabase();
String query = "UPDATE " + TABLE_INVENTORY + " SET " + COLUMN_INVENTORY_QTY
        + " = " + COLUMN_INVENTORY_QTY + " - " + qtySold + " WHERE "
        + COLUMN_INVENTORY_ID + " = " + inventoryId;
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EMPLOYEE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FOOD);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INVENTORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_AUDIT);
        onCreate(db);
    }

    private void SeedData(SQLiteDatabase db) {
        //admin acc
        ContentValues values = new ContentValues();
        values.put(COLUMN_EMP_USER, "admin");
        values.put(COLUMN_EMP_PASS, "admin");
        values.put(COLUMN_EMP_ROLE, "admin");
        db.insert(TABLE_EMPLOYEE, null, values);

        //inventory acc
        ContentValues vales = new ContentValues();
        values.put(COLUMN_EMP_USER, "inventory");
        values.put(COLUMN_EMP_PASS, "inventory");
        values.put(COLUMN_EMP_ROLE, "inventory");
        db.insert(TABLE_EMPLOYEE, null, values);

        //sales acc
        ContentValues value = new ContentValues();
        values.put(COLUMN_EMP_USER, "sales");
        values.put(COLUMN_EMP_PASS, "sales");
        values.put(COLUMN_EMP_ROLE, "sales");
        db.insert(TABLE_EMPLOYEE, null, values);

    }

    //adding inventory items to database
    private void addInitialInventory(SQLiteDatabase db) {
        String[] items = {"Burger", "Fries", "Soda", "Chicken Nuggets", "Salad"};
        double[] prices = {5.99, 2.99, 1.99, 4.99, 3.99};
        int initialQty = 50;

        for (int i = 0; i < items.length; i++) {
            ContentValues values = new ContentValues();
            values.put(COLUMN_INVENTORY_NAME, items[i]);
            values.put(COLUMN_INVENTORY_PRICE, prices[i]);
            values.put(COLUMN_INVENTORY_QTY, initialQty);
            db.insert(TABLE_INVENTORY, null, values);
        }
    }

    //audit log db
    public void AuditLog(String action) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_AUDIT_ACTION, action);
        values.put(COLUMN_AUDIT_TIMESTAMP, System.currentTimeMillis());
        db.insert(TABLE_AUDIT, null, values);
    }
}
