package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ArrayList<Employee> mEmployeesList = new ArrayList<>();
    private ArrayList<Banner> mBanner = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //writeJSON();

        getEmployeeList();

        setUIRef();

    }

    private void setUIRef()
    {
        RecyclerView recyclerView = findViewById(R.id.myRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(mEmployeesList);

        recyclerView.setAdapter(myRecyclerViewAdapter);


    }

    private void getEmployeeList()
    {
        String myJSONStr = loadJSONFromAsset();

        Log.i("message", "Success");

        try
        {
            //Get root JSON object node
            JSONObject rootJSONObject = new JSONObject(myJSONStr);

            JSONObject obj =rootJSONObject.getJSONObject("data");

            for(int i=0; i<obj.length(); i++){
                JSONArray jsonArray = obj.getJSONArray(String.valueOf(i+1));
                if(i%2 != 0) {
                    for(int k=0; k<jsonArray.length(); k++){
                        JSONObject bannerObj=jsonArray.getJSONObject(k);
                        Banner banner=new Banner();
                        banner.setBannerId(bannerObj.getInt("banner_id"));
                        banner.setBannerImage(bannerObj.getString("image"));
                        banner.setBannerRedirUrl(bannerObj.getString("banner_redirect_url"));
                        mBanner.add(banner);
                    }
                }else{
                    for(int j=0; j<obj.length(); j++) {
                        JSONObject empObj=jsonArray.getJSONObject(j);
                        Employee employee = new Employee();
                        employee.setName(empObj.getString("product_name"));
                        employee.setPrice(empObj.getInt("actual_price"));
                        employee.setMaxprice(empObj.getInt("maximum_retail_price"));
                        mEmployeesList.add(employee);
                    }
                }
            }





            //Get employee array node
//            JSONArray employeeJSONArray = rootJSONObject.getJSONArray("data");
//            Log.i(TAG, "getEmployeeList: "+employeeJSONArray.length());
//
//            for (int i = 0; i < employeeJSONArray.length(); i++)
//            {
//                //Create a temp object of the employee model class
//                Employee aEmployee = new Employee();
//
//                //Get employee JSON object node
//                JSONObject jsonObject = employeeJSONArray.getJSONObject(i);
//
//                //Get employee details
//
//                Log.i("message", "Sucessmmmm");
//                aEmployee.setName(jsonObject.getString("product_name"));
//                aEmployee.setPrice(jsonObject.getInt("actual_price"));
//                aEmployee.setMaxprice(jsonObject.getInt("maximum_retail_price"));
//
//                //Add employee object to the list
//                mEmployeesList.add(aEmployee);
//            }

        } catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

//    public void writeJSON() {
//        try {
//            JSONObject json = new JSONObject(loadJSONFromAsset());
//            JSONArray employeeJSONArray = json.getJSONObject("response")
//                    .getJSONObject("data").getJSONArray("1");
//
//            for (int i = 0; i < employeeJSONArray.length(); i++) {
//
//                if(i%2==0){
//
//                }else{
//                    Employee aEmployee = new Employee();
//                    JSONObject jsonObject = employeeJSONArray.getJSONObject(i);
//
//                    aEmployee.setName(jsonObject.getString("product_name"));
//                    aEmployee.setPrice(jsonObject.getInt("actual_price"));
//                    aEmployee.setMaxprice(jsonObject.getInt("maximum_retail_price"));
//                }
//
//            }
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
////        Adapter adapter = new Adapter(MainActivity.this, Employee);
////        recyclerView.setAdapter(adapter);
//    }

    @SuppressLint("NewApi")
    public String loadJSONFromAsset()
    {
        String json = null;
        try
        {
            InputStream inputStream = getAssets().open("sample_json.json");
            int size = inputStream.available();

            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            json = new String(buffer, StandardCharsets.UTF_8);

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }
        return json;
    }
}