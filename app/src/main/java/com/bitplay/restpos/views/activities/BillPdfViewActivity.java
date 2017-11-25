package com.bitplay.restpos.views.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bitplay.restpos.R;
import com.github.barteksc.pdfviewer.PDFView;


import static java.lang.String.format;

public class BillPdfViewActivity extends AppCompatActivity {

    public static final String SAMPLE_FILE = "raw/sample.pdf";
    String pdfName = SAMPLE_FILE;
    Integer pageNumber = 1;
    private PDFView pdfView;
 //   private ScrollBar scrollBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_pdf_view);

        pdfView = (PDFView) findViewById(R.id.pdfView);
     //   scrollBar = (ScrollBar) findViewById(R.id.scrollBar);
        pdfView.fromAsset("pdfurl_guide.pdf").load();

    }




}
