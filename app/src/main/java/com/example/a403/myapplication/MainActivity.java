package com.example.a403.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.graphics.Color;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

public class reserve extends AppCompatActivity {
    Switch start_switch;
    Chronometer reserve_chrono;
    RadioGroup rd_discount, rd_datetime;
    RadioButton rbtn_discount1, rbtn_discount2, rbtn_discount3, rbtn_date, rbtn_time;
    EditText adult, young, child;
    CalendarView d_pick;
    TimePicker t_pick;
    LinearLayout customer, datetimepick;
    Button costregist, gotimepick, timeregist, backpage;
    TextView totalreserved, total_discount, total_cost;
    ImageView images;
    int total_customer;
    double total_price;
    int adult_total, young_total, child_total;
    double discount_price;
    boolean custom_completeflag = false;
    String selected_date;
    String selected_time;
    String datetime;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);
        start_switch = (Switch)findViewById(R.id.reserve_switch);
        reserve_chrono = (Chronometer)findViewById(R.id.chronometer2);
        rd_discount = (RadioGroup)findViewById(R.id.rad_discount);
        rd_datetime = (RadioGroup)findViewById(R.id.rad_datetime);
        rbtn_discount1 = (RadioButton)findViewById(R.id.rbtn_default);
        rbtn_discount2 = (RadioButton)findViewById(R.id.rbtn_cash);
        rbtn_discount3 = (RadioButton)findViewById(R.id.rbtn_membership);
        rbtn_date = (RadioButton)findViewById(R.id.rbtn_datepick);
        rbtn_time = (RadioButton)findViewById(R.id.rbtn_timepick);
        adult = (EditText)findViewById(R.id.inputAdult);
        young = (EditText)findViewById(R.id.inputYoung);
        child = (EditText)findViewById(R.id.inputChild);
        d_pick = (CalendarView)findViewById(R.id.calendarView);
        t_pick = (TimePicker)findViewById(R.id.timePicker);
        customer = (LinearLayout)findViewById(R.id.reserve_customer);
        datetimepick = (LinearLayout)findViewById(R.id.reserve_time);
        costregist = (Button)findViewById(R.id.btn_regist);
        gotimepick = (Button)findViewById(R.id.btn_timereserve);
        timeregist = (Button)findViewById(R.id.btn_timeregist);
        backpage = (Button)findViewById(R.id.btn_back);
        images = (ImageView)findViewById(R.id.imageView);
        customer.setVisibility(View.INVISIBLE);
        datetimepick.setVisibility(View.INVISIBLE);
        totalreserved = (TextView)findViewById(R.id.txtTotalperson);
        total_discount = (TextView)findViewById(R.id.txtDiscountedcost);
        total_cost = (TextView)findViewById(R.id.txtRealcost);




        start_switch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b) {
                    reserve_chrono.setBase(SystemClock.elapsedRealtime());
                    reserve_chrono.start();
                    String strColor = "#0000FF";
                    reserve_chrono.setTextColor(Color.parseColor(strColor));
                    customer.setVisibility(View.VISIBLE);
                }
                else {
                    reserve_chrono.setBase(SystemClock.elapsedRealtime());
                    reserve_chrono.stop();
                    String strColor = "#000000";
                    reserve_chrono.setTextColor(Color.parseColor(strColor));
                    customer.setVisibility(View.INVISIBLE);
                    adult.setText("");
                    young.setText("");
                    child.setText("");
                }
            }
        });

        rd_discount.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.rbtn_default :
                        discount_price = total_price * 0.05;
                        images.setImageResource(R.drawable.park);
                        break;
                    case R.id.rbtn_cash :
                        discount_price = total_price * 0.1;
                        images.setImageResource(R.drawable.park2);
                        break;
                    case R.id.rbtn_membership :
                        discount_price = total_price * 0.2;
                        images.setImageResource(R.drawable.park3);
                        break;
                }
            }
        });
        costregist.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                adult_total = Integer.parseInt(adult.getText().toString());
                young_total = Integer.parseInt(young.getText().toString());
                child_total = Integer.parseInt(child.getText().toString());
                total_price = (adult_total * 15000) + (young_total * 12000) + (child_total * 6000);
                total_customer = adult_total + young_total + child_total;
                custom_completeflag = true;

                totalreserved.setText("총 명수 : " + total_customer);
                total_discount.setText("할인금액 : " + (int)discount_price);
                total_cost.setText("결제금액 : " + (int)(total_price - discount_price));
            }
        });

        gotimepick.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                customer.setVisibility(View.INVISIBLE);
                datetimepick.setVisibility(View.VISIBLE);
            }
        });

        rd_datetime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i) {
                    case R.id.rbtn_datepick :
                        d_pick.setVisibility(View.VISIBLE);
                        t_pick.setVisibility(View.INVISIBLE);
                        break;
                    case R.id.rbtn_timepick :
                        t_pick.setVisibility(View.VISIBLE);
                        d_pick.setVisibility(View.INVISIBLE);
                        break;
                }
            }
        });
        backpage.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                datetimepick.setVisibility(View.INVISIBLE);
                customer.setVisibility(View.VISIBLE);
            }
        });

        timeregist.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(custom_completeflag) {
                    datetime = selected_date + selected_time;
                    Toast.makeText(getApplicationContext(), datetime +" 예약이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                    reserve_chrono.stop();
                    String strColor = "#FF0000";
                }
                else {
                    Toast.makeText(getApplicationContext(), "인원예약을 먼저하세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        d_pick.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int date) {
                selected_date = year + "년 ";
                selected_date += (month+1) + "월 ";
                selected_date += date + "일 ";
            }
        });

        t_pick.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker timePicker, int i, int i1) {
                selected_time = i + "시 ";
                selected_time += i1 + "분";
            }
        });

    }
}