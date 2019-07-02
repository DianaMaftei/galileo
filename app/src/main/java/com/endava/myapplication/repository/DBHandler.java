package com.endava.myapplication.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.endava.myapplication.model.Employee;
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

public class DBHandler extends SQLiteAssetHelper {

    private static final String DATABASE_NAME = "galileo.db";
    private static final int DATABASE_VERSION = 1;

    public DBHandler(Context context) {
        super(context, DATABASE_NAME, null, null, DATABASE_VERSION);
    }

    public List<Employee> getAllEmployees() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"id", "name", "floor", "seat", "seatBeaconIdentifier", "project"};
        String tableName = "Employee";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);

        List<Employee> employees = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(cursor.getInt(cursor.getColumnIndex("id")));
                employee.setName(cursor.getString(cursor.getColumnIndex("name")));
                employee.setFloor(cursor.getString(cursor.getColumnIndex("floor")));
                employee.setSeat(cursor.getString(cursor.getColumnIndex("seat")));
                employee.setSeatBeaconIdentifier(cursor.getString(cursor.getColumnIndex("seatBeaconIdentifier")));
                employee.setProject(cursor.getString(cursor.getColumnIndex("project")));
                employees.add(employee);
            } while (cursor.moveToNext());
        }
        return employees;
    }

    public List<String> getAllEmployeesName() {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"name"};
        String tableName = "Employee";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, null, null, null, null, null);

        List<String> employeesNames = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                employeesNames.add(cursor.getString(cursor.getColumnIndex("name")));
            } while (cursor.moveToNext());
        }
        return employeesNames;
    }

    public List<Employee> getEmployeeByName(String name) {
        SQLiteDatabase db = getReadableDatabase();
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] sqlSelect = {"id", "name", "floor", "seat", "seatBeaconIdentifier", "project"};
        String tableName = "Employee";

        qb.setTables(tableName);
        Cursor cursor = qb.query(db, sqlSelect, "name LIKE ?", new String[] {"%" + name + "%"}, null, null, null);

        List<Employee> employees = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                Employee employee = new Employee();
                employee.setId(cursor.getInt(cursor.getColumnIndex("id")));
                employee.setName(cursor.getString(cursor.getColumnIndex("name")));
                employee.setFloor(cursor.getString(cursor.getColumnIndex("floor")));
                employee.setSeat(cursor.getString(cursor.getColumnIndex("seat")));
                employee.setSeatBeaconIdentifier(cursor.getString(cursor.getColumnIndex("seatBeaconIdentifier")));
                employee.setProject(cursor.getString(cursor.getColumnIndex("project")));
                employees.add(employee);
            } while (cursor.moveToNext());
        }
        return employees;
    }
}
