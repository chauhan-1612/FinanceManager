package com.navneet.finance;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
     Databasehelper my_db;
     EditText editName,editMoney_lent,editMoney_recieved;
     Button btnAddData;
     Button btnViewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_replace);
        my_db = new Databasehelper(this);
        editName=(EditText)findViewById(R.id.editText_name);
        editMoney_lent=(EditText)findViewById(R.id.editText_money_lent);
        editMoney_recieved=(EditText)findViewById((R.id.editText_money_recieved));
        btnAddData=(Button)findViewById(R.id.button_AddData);
        btnViewAll=(Button)findViewById(R.id.View_All);
        AddData();
        viewAll();
    }
    public void AddData()
    {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       boolean isInserted= my_db.insertData(editName.getText().toString(),editMoney_lent.getText().toString(),editMoney_recieved.getText().toString());
                        if(isInserted==true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void  viewAll()
    {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res=my_db.getAllData();
                        if(res.getCount()==0){
                            //show message
                            showMessage("Error","nothing found");
                            return;
                        }
                        StringBuffer buffer =new StringBuffer();
                        while(res.moveToNext()){
                            buffer.append("Id:"+res.getString(0)+"\n");
                            buffer.append("Name:"+res.getString(1)+"\n");
                            buffer.append("money_lent:"+res.getString(2)+"\n");
                            buffer.append("money_recieved:"+res.getString(3)+"\n");
                            buffer.append("balance:"+res.getString(4)+"\n\n");
                        }
                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }

        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
