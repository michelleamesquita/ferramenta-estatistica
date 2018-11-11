package com.example.michellemesquita.ferramentasestatisticas;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import java.lang.Math;



public class MainActivity extends AppCompatActivity {

    EditText nn,x11,x22,x33,y11,y22,y33,pp,kk,nono,dbin,rlrl,clcl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nn = (EditText) findViewById(R.id.editText);//declaracao das variaveis de entrada do app(label)
        x11 = (EditText) findViewById(R.id.editText2);
        x22 = (EditText) findViewById(R.id.editText3);
        x33 = (EditText) findViewById(R.id.editText4);
        y11 = (EditText) findViewById(R.id.editText5);
        y22 = (EditText) findViewById(R.id.editText6);
        y33 = (EditText) findViewById(R.id.editText7);
        pp = (EditText) findViewById(R.id.editText8);
        kk = (EditText) findViewById(R.id.editText9);
        nono = (EditText) findViewById(R.id.editText10);
        dbin = (EditText) findViewById(R.id.editText11);
        rlrl = (EditText) findViewById(R.id.editText12);
        clcl = (EditText) findViewById(R.id.editText13);


    }

    public void calcular (View v){
        double x1,x2,x3,y1,y2,y3,n,somax,somay,dpx,dpy,mediax,mediay,r,rx,b0,b1,b,a,somaxx,somayy,somaxy,somaxq,mediaxy;
        double p,k,no,fat,fat2,fat3,c,nk,dbin1;//declaracao das variaveis do programa

        n=Double.parseDouble(nn.getText().toString());
        x1=Double.parseDouble(x11.getText().toString());
        x2=Double.parseDouble(x22.getText().toString());
        x3=Double.parseDouble(x33.getText().toString());
        y1=Double.parseDouble(y11.getText().toString());
        y2=Double.parseDouble(y22.getText().toString());
        y3=Double.parseDouble(y33.getText().toString());
        p=Double.parseDouble(pp.getText().toString());
        k=Double.parseDouble(kk.getText().toString());
        no=Double.parseDouble(nono.getText().toString());


        // Inicio Correlacao Linear

        mediax= (x1+x2+x3)/3;
        mediay=(y1+y2+y3)/3;

        dpx= Math.sqrt((Math.pow(x1-mediax,2)+ Math.pow(x2-mediax,2) + Math.pow(x3-mediax,2))/(n-1));
        dpy= Math.sqrt((Math.pow(y1-mediay,2)+ Math.pow(y2-mediay,2) + Math.pow(y3-mediay,2))/(n-1));

        r = ((x1-mediax)*(y1-mediay)+(x2-mediax)*(y2-mediay)+(x3-mediax)*(y3-mediay))/((n-1)*(dpx)*(dpy));

        if(r<0.3){
            clcl.setText("Correlação Linear Fraca");

        }else if(r<0.6){

            clcl.setText("Correlação Linear Média");
        }else if(r>0.9){
            clcl.setText("Correlação Linear Forte");
        }

        //Inicio Regressao Linear

        somaxy = (x1*y1)+(x2*y2)+(x3*y3);
        somaxx= x1+x2+x3;
        somaxq= Math.pow(x1,2)+Math.pow(x2,2)+Math.pow(x3,2);
        somayy= y1+y2+y3;
        mediaxy= somaxy/3;

        b= (somaxy- (n)* mediax * mediay)/(somaxq - n * Math.pow(mediax,2));
        a= mediay- b * mediax;


        if(a>0){
            rlrl.setText("Y="+b+"x+"+a);//+ coloca a variavel com texto

        } else if (a==0) {
            rlrl.setText("Y="+b+"x");

        }else if(a<0){
            rlrl.setText("Y="+b+"x-"+a);
        }

        //Inicio Distribuição Binomial

        fat = 1;
        c=1;

        while(c<=no){
            fat=fat*c;
            c=c+1;
        }

        fat2=1;

        while(c<=k){
            fat2=fat2*c;
            c=c+1;
        }

        nk= no-k;
        fat3=1;

        while(c<=nk){
            fat3=fat3*c;
            c=c+1;
        }

        dbin1 = ((fat)/((fat2)*(fat3)))* Math.pow(p,k)*Math.pow(1-p,nk);

        dbin.setText(String.valueOf(dbin1));



    }
}
