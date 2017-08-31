package com.example.nati.natilevantamento;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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

                criandoPdf(edt_dep.getText().toString(), variaveis);

            }
        });
    }
    private void criandoPdf(String nome, String[] variaveis) {

        try {

            String filename = nome+".pdf";

            document = new Document(PageSize.A4);

            String path = Environment.getExternalStorageDirectory() + "/Documents";
            //String patth = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS) + "/Documents";

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
            document.add(new Paragraph("=========================================="));
            document.add(new Paragraph("Informações Básicas"));
            document.add(new Paragraph("    Departamento: "+variaveis[0]));
            document.add(new Paragraph("    Nome do Laboratório: "+variaveis[1]));
            document.add(new Paragraph("    Responsável pelo Laboratório: "+variaveis[2]));


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
