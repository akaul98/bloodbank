package com.example.bloodbank.activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.LayoutManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.widget.ToolbarWidgetWrapper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bloodbank.Adapters.RequestAdapter;
import com.example.bloodbank.R;
import com.example.bloodbank.datamodeling.RequestDataModel;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List<RequestDataModel> requestDataModels;
    private RequestAdapter requestAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView makerequest=findViewById(R.id.makerequest);
        makerequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerintent = new Intent(MainActivity.this, MakeRequestActivity.class);
                startActivity(registerintent);
                                           }
                                       });
        requestDataModels = new ArrayList<>()   ;
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (item.getItemId() == R.id.search_button) {
                    startActivity(new Intent(MainActivity.this, Search.class));
                }
                return false;
            }
        });

        recyclerView =findViewById(R.id.recycleview);
        LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        requestAdapter=new RequestAdapter(requestDataModels,this);
        recyclerView.setAdapter(requestAdapter);
        popluateHomepage();
    }

    private void popluateHomepage(){
        RequestDataModel requestDataModel =new RequestDataModel("Hi need O+ for my son","https://media-exp1.licdn.com/dms/image/C4D03AQH9xZ6wHxQLsg/profile-displayphoto-shrink_400_400/0?e=1609977600&v=beta&t=Twv4mtsFowefvnNEk-L2-nkpW2jr9f9aKZFTxsheXHE");
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);
        requestDataModels.add(requestDataModel);
        requestAdapter.notifyDataSetChanged();
    }


    }