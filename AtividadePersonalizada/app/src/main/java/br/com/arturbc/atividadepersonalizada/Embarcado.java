package br.com.arturbc.atividadepersonalizada;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

public class Embarcado extends View {
    boolean chovendo = true;
    boolean rectAnimado = true;

    int pxTaxaAnimacao = 100;
    int pxRaioChuva = 5;
    int pxTaxaAnimacaoMetade = pxTaxaAnimacao/2;
    boolean[][] chuva;
    int lado = pxTaxaAnimacao;
    int altura = lado;
    int tamX = 100 + pxTaxaAnimacaoMetade;
    int tamY = tamX;

    Paint paint = new Paint();

    Bitmap fundo, f;

    Rect r = new Rect(pxTaxaAnimacaoMetade, pxTaxaAnimacaoMetade, tamX, tamY);

    public Embarcado(Context context) {
        super(context);
    }

    public Embarcado(Context context, AttributeSet attrs) {
        super(context, attrs);

        fundo = BitmapFactory.decodeResource(getResources(), R.drawable.bg);
    }

    public Embarcado(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Embarcado(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int maxLado = getMeasuredWidth();
        int maxAltura = getMeasuredHeight();

        if(paint.getColor() != Color.BLACK)
            paint.setColor(Color.BLACK);

        if(chovendo) {
            int maxLChuva = maxLado/pxTaxaAnimacao;
            int maxAChuva = maxAltura/pxTaxaAnimacao;

            if (chuva == null) {
                chuva = new boolean[maxLChuva][maxAChuva];

                for (int i = maxLChuva - 1; i >= 0; i--) {
                    for (int j = maxAChuva - 1; j >= 0; j--) {
                        chuva[i][j] = false;
                    }
                }
            }

            if (f == null)
                f = Bitmap.createScaledBitmap(fundo, getMeasuredWidth(), getMeasuredHeight(), false);

            for (int i = maxLChuva - 1; i >= 0; i--) {
                for (int j = maxAChuva - 1; j >= 0; j--) {
                    chuva[i][j] = j > 0 ? chuva[i][j - 1]:new Random().nextBoolean();

                    if (chuva[i][j]) {
                        int iLAnimado = i * pxTaxaAnimacao;
                        int jLAnimado = j * pxTaxaAnimacao;
                        int iOffset = (maxLado - (maxLChuva * pxTaxaAnimacao) + pxTaxaAnimacao) / 2;
                        int jOffset = (maxAltura - (maxAChuva * pxTaxaAnimacao) + pxTaxaAnimacao) / 2;

                        for (int k = 0; (iLAnimado + k + iOffset) <= maxLado && k < pxRaioChuva; k++) {
                            for (int l = 0; (jLAnimado + l + jOffset) <= maxAltura && l < pxRaioChuva; l++) {
                                f.setPixel(iLAnimado + k + iOffset, jLAnimado + l + jOffset, Color.WHITE);
                            }
                        }

                        for (int k = pxRaioChuva - 1; (iLAnimado - k + iOffset) >= 0 && k > 0; k--) {
                            for (int l = pxRaioChuva - 1; (jLAnimado - l + jOffset) >= 0 && l > 0; l--) {
                                f.setPixel(iLAnimado - k + iOffset, jLAnimado - l + jOffset, Color.WHITE);
                            }
                        }
                    }
                }
            }

            canvas.drawBitmap(f, 0, 0, paint);

            f = null;
        }

        if(rectAnimado) {
            if (((r.right + lado) > maxLado && lado > 0) ||
                    ((r.left + lado) < 0 && lado < 0))
                lado = -lado;

            if (((r.bottom + altura) > maxAltura && altura > 0) ||
                    ((r.top + altura) < 0 && altura < 0))
                altura = -altura;

            r.offsetTo((r.left + lado), (r.top + altura));
            canvas.drawRect(r, paint);
        }

        invalidate();
    }
}
