package com.example.nsc.realm.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nsc.realm.Model.Student;
import com.example.nsc.realm.R;
import com.example.nsc.realm.adapter.MyAdapter;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;


public class MainActivity extends Activity {

    private RecyclerView mRecyclerView;
    private MyAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button btnAdd;
    private Button btnDelete;
    private EditText edtName;
    private EditText edtScore;
    Realm realm;
    ArrayList<Student> studentArrayList = new ArrayList<>();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInstance();

    }

    private void initInstance() {
        realm = Realm.getDefaultInstance();

        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycleView);
        edtName = (EditText) findViewById(R.id.edtName);
        edtScore = (EditText) findViewById(R.id.edtScore);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(getApplicationContext(), studentArrayList);
        mRecyclerView.setAdapter(mAdapter);
        showData();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    writeToDB(edtName.getText().toString().trim(), Integer.parseInt(edtScore.getText().toString().trim()));
                } catch (Exception e){
                    Toast.makeText(MainActivity.this, "Input Data!", Toast.LENGTH_SHORT).show();
                }
                showData();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteData();
                showData();
            }
        });
    }

    private void deleteData() {
        final RealmResults<Student> result = realm.where(Student.class).findAll();
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                result.deleteFromRealm(0);
            }
        });
    }

    private void showData() {
        RealmResults<Student> result = realm.where(Student.class).findAll();
        studentArrayList = new ArrayList<>();
        studentArrayList.addAll(result);
        mAdapter.setStudentArrayList(studentArrayList);
    }

    private void writeToDB(final String name, final int score) {

        realm.beginTransaction();
        Student student = realm.createObject(Student.class);
        student.setStudentName(name);
        student.setStudentScore(score);
        realm.commitTransaction();

//        realm.executeTransactionAsync(new Realm.Transaction() {
//            @Override
//            public void execute(Realm realm) {
//                Student student = realm.createObject(Student.class);
//                student.setStudentName(name);
//                student.setStudentScore(score);
//                Log.e("execute", "execute: " + student.getStudentName() + " " + student.getStudentScore());
//            }
//        }, new Realm.Transaction.OnSuccess()
//
//        {
//            @Override
//            public void onSuccess() {
//                Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                Log.e("Success", "onSuccess: ");
//            }
//        }, new Realm.Transaction.OnError()
//
//        {
//            @Override
//            public void onError(Throwable error) {
//                error.printStackTrace();
//                Log.e("Error", error.getMessage());
//                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();
//            }
//        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        realm.close();
    }
}
