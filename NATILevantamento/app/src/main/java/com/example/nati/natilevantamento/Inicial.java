package com.example.nati.natilevantamento;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Inicial extends AppCompatActivity {

    Document document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicial);
        Button btn_pasta = (Button) findViewById(R.id.past);
        Button btn_cria = (Button) findViewById(R.id.btn_criapdf);
        final EditText edt_dep = (EditText) findViewById(R.id.etx_dep);
        final EditText edt_lab = (EditText) findViewById(R.id.etx_lab);
        final EditText edt_rep = (EditText) findViewById(R.id.etx_resp);
        final EditText edt_rvis = (EditText) findViewById(R.id.etx_rvis);
        final EditText edt_dt = (EditText) findViewById(R.id.etx_dt);
        final EditText edt_qtdcomp = (EditText) findViewById(R.id.etx_qtdcomp);
        final EditText edt_qtdcompP = (EditText) findViewById(R.id.etx_qtdcompP);
        final EditText edt_qtdcompS = (EditText) findViewById(R.id.etx_qtdcompS);
        final EditText edt_dtshow = (EditText) findViewById(R.id.etx_DataShow);
        final EditText edt_dmFisicas = (EditText) findViewById(R.id.etx_dmFisicas);
        final EditText edt_estCapComp = (EditText) findViewById(R.id.etx_estCapComp);
        final EditText edt_cpcdPess = (EditText) findViewById(R.id.etx_cpcdPess);
        final EditText edt_qtdPredes = (EditText) findViewById(R.id.etx_qtdPredes);
        final EditText edt_wifi = (EditText) findViewById(R.id.etx_wifi);
        final EditText edt_acess = (EditText) findViewById(R.id.etx_acess);
        final EditText edt_qtdArCond = (EditText) findViewById(R.id.etx_qtdArCond);
        final EditText edt_typeArCond = (EditText) findViewById(R.id.etx_typeArCond);
        final EditText edt_cpcArCond = (EditText) findViewById(R.id.etx_cpcArCond);
        final EditText edt_obs = (EditText) findViewById(R.id.etx_obs);
        btn_pasta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 openFolder();
            }
        });
        btn_cria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] variaveis = new String[19];
                variaveis[0]=edt_dep.getText().toString();
                variaveis[1]=edt_lab.getText().toString();
                variaveis[2]=edt_rep.getText().toString();
                variaveis[3]=edt_rvis.getText().toString();
                variaveis[4]=edt_dt.getText().toString();
                variaveis[5]=edt_qtdcomp.getText().toString();
                variaveis[6]=edt_qtdcompP.getText().toString();
                variaveis[7]=edt_qtdcompS.getText().toString();
                variaveis[8]=edt_dtshow.getText().toString();
                variaveis[9]=edt_dmFisicas.getText().toString();
                variaveis[10]=edt_estCapComp.getText().toString();
                variaveis[11]=edt_cpcdPess.getText().toString();
                variaveis[12]=edt_qtdPredes.getText().toString();
                variaveis[13]=edt_wifi.getText().toString();
                variaveis[14]=edt_acess.getText().toString();
                variaveis[15]=edt_qtdArCond.getText().toString();
                variaveis[16]=edt_typeArCond.getText().toString();
                variaveis[17]=edt_cpcArCond.getText().toString();
                variaveis[18]=edt_obs.getText().toString();

                criandoPdf(edt_dep.getText().toString(), variaveis);
                Toast.makeText(Inicial.this, "Arquivo Gerado",Toast.LENGTH_LONG).show();
                edt_dep.setText("");
                edt_lab.setText("");
                edt_rep.setText("");
                edt_rvis.setText("");
                edt_dt.setText("");
                edt_qtdcomp.setText("");
                edt_qtdcompP.setText("");
                edt_qtdcompS.setText("");
                edt_dtshow.setText("");
                edt_dmFisicas.setText("");
                edt_estCapComp.setText("");
                edt_cpcdPess.setText("");
                edt_qtdPredes.setText("");
                edt_wifi.setText("");
                edt_acess.setText("");
                edt_qtdArCond.setText("");
                edt_typeArCond.setText("");
                edt_cpcArCond.setText("");
                edt_obs.setText("");

            }
        });
    }
    public void openFolder()
    {
        String path;
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);

        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
            path = Environment.getExternalStorageDirectory() + "/NatiPDFS";
        }
        else {
            path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/NatiPDFS";
        }

        Uri uri = Uri.parse(path);
        intent.setDataAndType(uri,"*/*");
        startActivity(Intent.createChooser(intent, "Abrindo pasta"));
    }
    private void criandoPdf(String nome, String[] variaveis) {
        String path;

        try {

            String filename = nome.replace(" ","")+"_"+variaveis[1].replace(" ","")+".pdf";

            document = new Document(PageSize.A4);

            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
                path = Environment.getExternalStorageDirectory() + "/NatiPDFS";
            }
            else {
                path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/NatiPDFS";
            }
            File dir = new File(path, filename);
            if (!dir.exists()) {
                dir.getParentFile().mkdirs();
            }

            FileOutputStream fOut = new FileOutputStream(dir);
            fOut.flush();

            PdfWriter.getInstance(document, fOut);
            document.open();
            document.add(new Paragraph("Universidade Federal de Pernambuco"));
            document.add(new Paragraph("Núcleo de tecnologia da Informação - CCS"));
            document.add(new Paragraph("Cadastramento dos Laboratórios da Graduação"));
            document.add(new Paragraph("     "));
            document.add(new Paragraph("=========================================="));
            document.add(new Paragraph("     "));
            document.add(new Paragraph("Informações Básicas"));
            document.add(new Paragraph("    Departamento: "+variaveis[0]));
            document.add(new Paragraph("    Nome do Laboratório: "+variaveis[1]));
            document.add(new Paragraph("    Responsável pelo Laboratório: "+variaveis[2]));
            document.add(new Paragraph("    Responsável pela Visita: "+variaveis[3]));
            document.add(new Paragraph("    Data: "+variaveis[4]));
            document.add(new Paragraph("     "));
            document.add(new Paragraph("Dados do Laboratório"));
            document.add(new Paragraph("Computadores e Equipamentos"));
            document.add(new Paragraph("    Quantidade de Computadores: "+variaveis[5]));
            document.add(new Paragraph("    Computadores em Produção: "+variaveis[6]));
            document.add(new Paragraph("    Computadores Parados: "+variaveis[7]));
            document.add(new Paragraph("    DataShow: "+variaveis[8]));
            document.add(new Paragraph("     "));
            document.add(new Paragraph("Dados Estruturais"));
            document.add(new Paragraph("    Dimensões Físicas: "+variaveis[9]));
            document.add(new Paragraph("    Estimativa Capacidade de Computadores: "+variaveis[10]));
            document.add(new Paragraph("    Capacidade de Pessoas: "+variaveis[11]));
            document.add(new Paragraph("    Quantidade de pontos de rede: "+variaveis[12]));
            document.add(new Paragraph("    Wifi: "+variaveis[13]));
            document.add(new Paragraph("    Acessibilidade: "+variaveis[14]));
            document.add(new Paragraph("     "));
            document.add(new Paragraph("Ar-Condicionado"));
            document.add(new Paragraph("    Quantidade: "+variaveis[15]));
            document.add(new Paragraph("    Tipo: "+variaveis[16]));
            document.add(new Paragraph("    Capacidade*: "+variaveis[17]));
            document.add(new Paragraph("    Observações: "+variaveis[18]));


        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

    }
}
