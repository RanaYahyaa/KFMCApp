package com.seu.kfmcapp;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TrainingRequest extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {

    String[] Unversity = { "جامعة أم القرى",
            "الجامعة الإسلامية",
            "جامعة الإمام محمد بن سعود",
            "جامعة الملك عبدالعزيز",
            "جامعة الملك سعود",
            "جامعة الملك فهد للبترول والمعادن",
            "جامعة نايف العربية للعلوم الأمنية",
            "جامعة الملك فيصل",
            "جامعة الملك خالد",
            "جامعة القصيم",
            "جامعة طيبة",
            "جامعة الطائف",
            "جامعة حائل",
            "جامعة جازان",
            "جامعة الجوف",
            "جامعة الباحة",
            "جامعة تبوك",
            "جامعة نجران",
            "جامعةالحدود الشمالية",
            "جامعة الأميرة نورة بنت عبدالرحمن",
            "جامعة الملك سعود للعلوم الصحية",
            "جامعة الإمام عبدالرحمن بن فيصل",
            "جامعة الملك عبدالله للعلوم والتقنية",
            "جامعة الأمير سطام بن عبدالعزيز",
            "جامعة شقراء",
            "جامعة المجمعة",
            "الجامعة السعودية الالكترونية",
            "جامعة جدة",
            "جامعة بيشة",
            "كلية المدربين التقنيين",
            "جامعة حفر الباطن",
            "كلية جبيل الصناعية",
            "كلية ينبع الجامعية",
            "كلية الأمير محمد بن سلمان للإدارة وريادة الأعمال",
            "جامعة رياض العلم",
            "جامعة المستقبل",
            "جامعة سليمان الراجحي الأهلية",
            "كلية ابن سينا للعلوم الطبية",
            "كلية البرتجي للعلوم الطبية والتقنية",
            "كلية الفارابي الأهلية",
            "كلية المعرفة",  "كليات الرياض لطب الأسنان والصيدلة",
            "جامعة دار الحكمة",
            "جامعة الأمير مقرن بن عبدالعزيز",  "جامعة دار العلوم",
            "جامعة الأمير سلطان",
            "جامعة عفت الأهلية",

            "الجامعة العربية المفتوحة",
            "جامعة اليمامة",  "جامعة الأمير فهد بن سلطان",
            "جامعة الأعمال والتكنولوجيا",

            "جامعة الفيصل",
            "كلية الريان الأهلية",  "كلية جدة العالمية",
            "كليات الخليج",

            "كلية لينكون",

            "اخرى"

    };
    String[] Majors={
            "التمريض/تعليم التمريض/إدارة التمريض","معدات طبية" ,"طب أسنان","الصحة العامة/التثقيف الصحي/صحة المجتمع"
            ,"التعليم الطبي","نظم المعلومات الإدارية",
            "علوم حاسب","تقنية معلومات",
            "إدارة الموارد البشرية", "إدارة اعمال",
            "إدارة عامة"
            ,"الهندسة/الإدارة الهندسية/الهندسة الطبية/الهندسة الصناعية","",
            "إدارة مستشفيات","إدارة الرعاية الصحية",
            "محاسبة","الإحصاء",
            "اللغة الإنجليزية/الترجمة"
            ,"دراسات قانونية/قانون","",
            "محاكاة",
            "", "",
            "",""
            ,"","",
            "","",
            "", "",
            "",""
            ,"","",
            "","",
            "", "",
            "",""
            ,"","",
            "","",
            "", "",
            "",""
            ,"","",
            "","",
            "", "",
            "",""
            ,"","",
            "","",
            "", "",
            "اخرى"};


    String [] GPA={"4/1.5",
            "4/2","4/2.5" , "4/2.75" , "4/3","4/3.5","4/3.75","4/4"
            ,"5/1.5","5/1.75" ,"5/2","5/2.5","5/2.75" , "5/3","5/3.5","5/3.75","5/4","5/4.5","5/4.75","5/5"};

    Spinner spin , spinn, spinnn;
    ProgressDialog pd;
    Button btn;

    ArrayList<Major> majorsArrayList,majors;
    ArrayList<String> majorsNames;
    ArrayAdapter majorsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_request);
        spin = (Spinner) findViewById(R.id.unversity);
        spinnn = (Spinner) findViewById(R.id.spinner3);
        spinn = (Spinner) findViewById(R.id.majors);
        //Creating the ArrayAdapter instance having the country list
        majors = new ArrayList<>();
        majorsArrayList = new ArrayList<>();
        majorsNames = new ArrayList<>();
        majors = getAllMajors();


        spinn.setOnItemSelectedListener(this);
        spin.setOnItemSelectedListener(this);
        spinnn.setOnItemSelectedListener(this);
//        pd = new ProgressDialog(MainActivity.this);
//        pd.setMessage("Please wait");
//        pd.setCancelable(false);
//        pd.show();
//        // creating new array list.
//        majorsArrayList = new ArrayList<>();
//        getAllMajors();


       // spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Unversity);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);



        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aaaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,GPA);
        aaaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnn.setAdapter(aaaa);
        majorsAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,majorsNames);
        majorsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinn.setAdapter( majorsAdapter);
    }

    private ArrayList<Major> getAllMajors() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://537e-2001-16a2-c0cc-933d-245c-8854-cf80-14ca.eu.ngrok.io/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        // below line is to create an instance for our retrofit api class.
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        // on below line we are calling a method to get all the courses from API.
        Call<ArrayList<Major>> call = retrofitAPI.getAllMajors();

        // on below line we are calling method to enqueue and calling
        // all the data from array list.
        call.enqueue(new Callback<ArrayList<Major>>() {
            @Override
            public void onResponse(Call<ArrayList<Major>> call, Response<ArrayList<Major>> response) {
                // inside on response method we are checking
                // if the response is success or not.
                if (response.isSuccessful()) {

                    // below line is to add our data from api to our array list.
                    majorsArrayList = response.body();
                    majorsAdapter.notifyDataSetChanged();
                    // below line we are running a loop to add data to our adapter class.
                    for (int i = 0; i < majorsArrayList.size(); i++) {
                        majorsNames.add(majorsArrayList.get(i).toString());
                    }

                }

            }

            @Override
            public void onFailure(Call<ArrayList<Major>> call, Throwable t) {
                Toast.makeText(TrainingRequest.this, "Fail to get data", Toast.LENGTH_SHORT).show();
                pd.dismiss();
            }
        });
        return majorsArrayList;
    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }}
