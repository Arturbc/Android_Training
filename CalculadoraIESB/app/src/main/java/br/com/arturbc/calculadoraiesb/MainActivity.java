package br.com.arturbc.calculadoraiesb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btn_nr0, btn_nr1, btn_nr2, btn_nr3, btn_nr4, btn_nr5, btn_nr6,
           btn_nr7, btn_nr8, btn_nr9, btn_neg, btn_dot, btn_percent, btn_sqrt,
           btn_pwsq, btn_inv, btn_ce, btn_c, btn_divide, btn_mult, btn_sub,
           btn_sum, btn_equ, btn_bksp;

    TextView txv_calc, txv_calc_res;

    float resp, calc, ultimoCalc;

    int operacao, fator;
    String op, calculoStr, ultimaStr, decimalDot = ",";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_nr0 = findViewById(R.id.btn_nr0);
        btn_nr1 = findViewById(R.id.btn_nr1);
        btn_nr2 = findViewById(R.id.btn_nr2);
        btn_nr3 = findViewById(R.id.btn_nr3);
        btn_nr4 = findViewById(R.id.btn_nr4);
        btn_nr5 = findViewById(R.id.btn_nr5);
        btn_nr6 = findViewById(R.id.btn_nr6);
        btn_nr7 = findViewById(R.id.btn_nr7);
        btn_nr8 = findViewById(R.id.btn_nr8);
        btn_nr9 = findViewById(R.id.btn_nr9);
        btn_neg = findViewById(R.id.btn_neg);
        btn_dot = findViewById(R.id.btn_dot);
        btn_percent = findViewById(R.id.btn_percent);
        btn_sqrt = findViewById(R.id.btn_sqrt);
        btn_pwsq = findViewById(R.id.btn_pwsq);
        btn_inv = findViewById(R.id.btn_inv);
        btn_ce = findViewById(R.id.btn_ce);
        btn_c = findViewById(R.id.btn_c);
        btn_divide = findViewById(R.id.btn_divide);
        btn_mult = findViewById(R.id.btn_mult);
        btn_sub = findViewById(R.id.btn_sub);
        btn_sum = findViewById(R.id.btn_sum);
        btn_equ = findViewById(R.id.btn_equ);
        btn_bksp = findViewById(R.id.btn_bksp);

        txv_calc = findViewById(R.id.txv_calc);
        txv_calc_res = findViewById(R.id.txv_calc_res);

        if(txv_calc_res.getText().length() == 0) {
            txv_calc_res.setText("0");
            txv_calc.setText("");

            ultimaStr = "0";
            calculoStr = "";
            op = "+";
            calc = 0;
            resp = 0;
            ultimoCalc = 0;
            fator = 0;
        }

        btn_nr0.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numero(0);
            }
        });

        btn_nr1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numero(1);
            }
        });

        btn_nr2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numero(2);
            }
        });

        btn_nr3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numero(3);
            }
        });

        btn_nr4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numero(4);
            }
        });

        btn_nr5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numero(5);
            }
        });

        btn_nr6.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numero(6);
            }
        });

        btn_nr7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numero(7);
            }
        });

        btn_nr8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numero(8);
            }
        });

        btn_nr9.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                numero(9);
            }
        });

        btn_dot.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boolean dotInStr = ultimaStr.matches("^(\\-)?\\d+\\"+decimalDot+"(.+)?");

                if(!dotInStr) {
                    if (operacao != 0) {
                        if(operacao == 2) {
                            resp = 0;
                            txv_calc.setText("");
                        }

                        operacao = 0;
                        calc = 0;
                        ultimaStr = "0";
                    }

                    if(ultimaStr.length() == 0)
                        ultimaStr = "0";

                    ultimaStr = ultimaStr + ".";

                    calc = Float.parseFloat(ultimaStr);
                    ultimaStr = ultimaStr.replace('.', decimalDot.charAt(0));
                    txv_calc_res.setText(ultimaStr);
                }
            }
        });

        btn_neg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacaoAvancada('-');
            }
        });

        btn_inv.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacaoAvancada('/');
            }
        });

        btn_pwsq.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacaoAvancada('²');
            }
        });

        btn_sqrt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacaoAvancada('√');
            }
        });

        btn_percent.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacaoAvancada('%');
            }
        });

        btn_divide.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacaoBasica('÷');
            }
        });

        btn_mult.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacaoBasica('×');
            }
        });

        btn_sub.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacaoBasica('-');
            }
        });

        btn_sum.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacaoBasica('+');
            }
        });

        btn_equ.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                operacaoBasica('=');
            }
        });

        btn_bksp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(operacao == 0) {
                    int tam = ultimaStr.length();

                    if ((calc > 0 && ultimaStr.length() > 1) ||
                        (calc < 0 && ultimaStr.length() > 2))
                        ultimaStr = ultimaStr.subSequence(0, tam - 1).toString();
                    else
                        ultimaStr = "0";

                    calc = Float.parseFloat(ultimaStr.replace(decimalDot.charAt(0),'.'));
                    txv_calc_res.setText(ultimaStr);
                }
            }
        });

        btn_ce.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                txv_calc_res.setText("0");
                ultimaStr = "";
                operacao = 0;
                calc = 0;
            }
        });

        btn_c.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                txv_calc_res.setText("0");
                txv_calc.setText("");
                ultimaStr = "";
                operacao = 0;
                ultimoCalc = 0;
                calc = 0;
                resp = 0;
            }
        });
    }

    public void numero(int nr) {
        boolean strOk = txv_calc_res.getText().toString().matches("^(\\-)?((0\\"+decimalDot+")|[1-9])(.+)?");

        if(operacao != 0) {
            if(operacao == 2) {
                resp = 0;
                txv_calc.setText("");
            }

            calc = 0;
            operacao = 0;
            strOk = false;
        }

        if(strOk)
            ultimaStr = txv_calc_res.getText()+Integer.toString(nr);
        else
            ultimaStr = Integer.toString(nr);

        ultimaStr = ultimaStr.replace(decimalDot.charAt(0),'.');
        calc = Float.parseFloat(ultimaStr);
        ultimaStr = ultimaStr.replace('.',decimalDot.charAt(0));
        txv_calc_res.setText(ultimaStr);
    }

    public void operacaoAvancada(char op_desejo) {
        boolean negExibe = true;

        if(operacao != 2 && operacao != 3) {
            if(operacao != 1) {
                if(operacao == 0) {
                    ultimoCalc = resp;
                    resp = calc;
                    calc = ultimoCalc;
                }

                negExibe = false;
            }

            if(resp == 0)
                ultimaStr = "0";

            calculoStr = txv_calc.getText().toString();
        }

        switch(op_desejo) {
            case '%':
                resp = resp / 100;
                ultimaStr = convertFloatToStr(resp);
                operacao = 2;
            break;

            case '²':
                ultimaStr = "sqr("+ultimaStr+")";
                resp = resp * resp;
                operacao = 2;
            break;

            case '/':
                ultimaStr = "1/("+ultimaStr+")";
                resp = 1 / resp;
                operacao = 2;
            break;

            case '√':
                ultimaStr = "√("+ultimaStr+")";
                resp = (float)Math.sqrt(resp);
            break;

            case '-':
                if(negExibe) {
                    ultimaStr = "negate(" + ultimaStr + ")";

                    if(operacao != 3)
                        calc = resp;

                    operacao = 3;
                } else {
                    ultimaStr = "";
                    operacao = -1;
                }

                if(resp != 0)
                    resp = -resp;
            break;
        }

        txv_calc.setText(calculoStr+ultimaStr);
        txv_calc_res.setText(convertFloatToStr(resp));
    }

    public void operacaoBasica(char op_desejo) {
        int tam = txv_calc.getText().length();
        char opAtual;
        String opStr = " "+op_desejo+" ";

        opAtual = op.charAt(0);

        switch(op_desejo) {
            case '=':
                if(operacao == 1 && tam > 0)
                    calc = resp;
                else if(operacao == 0 && tam == 0) {
                    resp = calc;
                    calc = ultimoCalc;
                }

                if(operacao != 2)
                    ultimoCalc = calc;
                else {
                    ultimoCalc = resp;
                    resp = calc;
                    calc = ultimoCalc;
                }
            break;

            default:
                op = Character.toString(op_desejo);

                if (tam == 0) {
                    if (operacao != 0)
                        calc = resp;

                    resp = 0;
                }
        }

        if(operacao != 1 || tam == 0 || op_desejo == '=') {
            if(tam == 0 && op_desejo != '=')
                opAtual = '+';

            if(operacao != 2 && (operacao != 3 || fator > 0))
                switch(opAtual) {
                    case '-':
                        resp = resp - calc;
                        break;

                    case '×':
                        resp = resp * calc;
                        break;

                    case '÷':
                        resp = resp / calc;
                        break;

                    default:
                        resp = resp + calc;
                }

            if(op_desejo != '=') {
                txv_calc.setText(txv_calc.getText() + ((operacao != 2 && operacao != 3 ? txv_calc_res.getText() : "") + opStr));
                fator++;
            } else {
                txv_calc.setText("");
                fator = 0;
            }

            ultimaStr = convertFloatToStr(resp);
            txv_calc_res.setText(ultimaStr);

            operacao = 1;
        } else
            txv_calc.setText(txv_calc.getText().subSequence(0,tam-3) + opStr);
    }

    public String convertFloatToStr(float nr) {
        return Float.toString(nr).replaceAll("\\.0$", "").replace('.', decimalDot.charAt(0));
    }
}
