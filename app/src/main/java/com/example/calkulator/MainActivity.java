package com.example.calkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    String str;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        textView = (TextView) findViewById(R.id.textView2);
        str = "";

    }

    public void onMyClick(View v) {
        editText = (EditText) findViewById(R.id.textView);
        editText.setInputType(InputType.TYPE_NULL);
        switch (v.getId()) {
            case R.id.button1:
                str += 1;
                textView.setText(str);
                break;
            case R.id.button2:
                str += 2;
                textView.setText(str);
                break;
            case R.id.button3:
                str += 3;
                textView.setText(str);
                break;
            case R.id.button4:
                str += 4;
                textView.setText(str);
                break;
            case R.id.button5:
                str += 5;
                textView.setText(str);
                break;
            case R.id.button6:
                str += 6;
                textView.setText(str);
                break;
            case R.id.button7:
                str += 7;
                textView.setText(str);
                break;
            case R.id.button8:
                str += 8;
                textView.setText(str);
                break;
            case R.id.button9:
                str += 9;
                textView.setText(str);
                break;
            case R.id.button0:
                str += 0;
                textView.setText(str);
                break;
            case R.id.buttonFloat:
                str += ".";
                textView.setText(str);
                break;
            case R.id.buttonPlus:
                str += "+";
                textView.setText(str);
                break;
            case R.id.buttonMunus:
                str += "-";
                textView.setText(str);
                break;
            case R.id.buttonStar:
                str += "*";
                textView.setText(str);
                break;
            case R.id.buttonDelete:
                str += "/";
                textView.setText(str);
                break;
            case R.id.buttonErase:
                str = "";
                textView.setText(str);
                editText.setText(str);
                break;
            //подсчет выражения
            case R.id.buttonEqual:
                str += "=";
                textView.setText(str);
                if(checkStrCorrect(str)) {
                    str = result(str);
                    this.textView.invalidate();
                    editText.setText(str);
                }
                else editText.setText("Ошибка ввода или вы ввели более 2-х слагаемых");
                break;
        }
    }

    public String result(String str){
        String m = "";
        double result = 0;
        double rez1, rez2;
        char [] mass = str.toCharArray();
        int line = 0;
        for(int i = 0; i < mass.length;i++){
            if(mass[i]=='*' || mass[i]=='/' || mass[i] == '-' || mass[i] == '+'){
                String str1 = "", str2 = "";
                for(int j = i-1; j!=-1 || mass[j]!='*' || mass[j]!='/' || mass[j] != '-' || mass[j] != '+'; j--){
                    str1+=String.valueOf(mass[j]);
                    if(j == 0)
                        break;
                }
                for(int j = i+1; mass[j]!='*' || mass[j]!='/' || mass[j] != '-' || mass[j] != '+' || mass[j]!='='; j++){
                    str2+=String.valueOf(mass[j]);
                    if( mass[j + 1]=='*' || mass[j + 1]=='/' || mass[j + 1] == '-' || mass[j + 1] == '+' ||mass[j + 1]=='=' ){
                        line = j;
                        break;
                    }
                }

                String reverse = new StringBuffer(str1).reverse().toString();
                rez1 = Double.parseDouble(reverse);
                rez2 = Double.parseDouble(str2);

                if(mass[i] == '*'){
                    result = result + rez1 * rez2;
                }
                if(mass[i] == '/'){
                    result = result + rez1/rez2;
                }
                if(mass[i] == '-'){
                    result = result + rez1 - rez2;
                }
                if(mass[i] == '+'){
                    result = result + rez1 + rez2;
                }
            }
            else if(mass[i] == '=')
                break;
        }

        if(checkResult(result)){
            int k = (int)result;
            m = String.valueOf(k);
            return m;
        }
        m = String.valueOf(result);
        return m;
    }

    //проверяет коррекстность введённой строки
    public boolean checkStrCorrect(String str){
        char [] input = str.toCharArray();
        int k = 0;
        for(int i = 0; i < input.length; i++){
            if(input[i] >=42 && input [i] <=47 && input[i + 1] >=42 && input [i + 1] <=47 ) {
                return false;
            }
            if(input[i] >=42 && input [i] <=47 && input[i]!='.')
                k++;
            if(input[i] >=42 && input [i] <=47 && input[i]!='.' && input[i+1] == '=')
                return false;
            if(input[i] >=48 && input[i]<=57 && input[i+1] == '=')
                return false;
        }
        if(k<=1 && k!=0)
        return true;
        else return false;
    }
    //проверяет результат - дробный или целочисленный
    public boolean checkResult(double result){
        char [] input = String.valueOf(result).toCharArray();
            if(input[input.length - 2 ] == '.' && input [input.length - 1] =='0') {
                return true;
            }
        return false;
    }
}
