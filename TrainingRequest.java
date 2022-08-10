package com.example.kfmc;



        import androidx.annotation.NonNull;
        import androidx.annotation.Nullable;
        import androidx.appcompat.app.AppCompatActivity;

        import android.app.ProgressDialog;
        import android.content.Intent;
        import android.net.Uri;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.AdapterView;
        import android.widget.ArrayAdapter;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Spinner;
        import android.widget.Toast;
        import android.os.Bundle;



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


    Button btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Spinner spin = (Spinner) findViewById(R.id.unversity);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Unversity);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);



        Spinner spinn = (Spinner) findViewById(R.id.majors);
        spinn.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,Majors);
        aaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinn.setAdapter(aaa);


        Spinner spinnn = (Spinner) findViewById(R.id.spinner3);
        spinnn.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aaaa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,GPA);
        aaaa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spinnn.setAdapter(aaaa);

    }

    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {

    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }}
