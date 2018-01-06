package com.bizsoft.fmcgv2;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bizsoft.fmcgv2.dataobject.Store;
import com.bizsoft.fmcgv2.service.SignalRService;

public class DownloadDataActivity extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView,customers,products,categories,accounts,percentage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_data);

        textView = (TextView) findViewById(R.id.textView);
        progressBar = (ProgressBar) findViewById(R.id.progressBar3);

        customers = (TextView) findViewById(R.id.customers);
        products = (TextView) findViewById(R.id.products);
        categories = (TextView) findViewById(R.id.stock);
        accounts = (TextView) findViewById(R.id.accounts);
        percentage = (TextView) findViewById(R.id.percentage);


        new DownloaddData().execute();




    }
    private class DownloaddData extends AsyncTask<Integer,Integer,String>
    {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {

            textView.setText("Download Starting...");
            progressDialog = new ProgressDialog(DownloadDataActivity.this);
            progressDialog.setTitle("Sync status");
            progressDialog.setMessage("Downloading data..");
           // progressDialog.show();

        }


        @Override
        protected String doInBackground(Integer... integers) {


            runOnUiThread(new Runnable() {
                @Override
                public void run() {

//stuff that updates ui
                    textView.setText("Downloading customers..");
                }
            });

            SignalRService.customerList();
            progressBar.setProgress(25);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    percentage.setText("25%");
                    customers.setText(String.valueOf(Store.getInstance().customerList.size()));

//stuff that updates ui
                    textView.setText("Downloading products..");
                }
            });

            SignalRService.productList();
            progressBar.setProgress(50);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    products.setText(String.valueOf(Store.getInstance().productList.size()));
                    percentage.setText("50%");
//stuff that updates ui
                    textView.setText("Downloading categories..");
                }
            });

            SignalRService.stockHomeList();
            progressBar.setProgress(75);
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    percentage.setText("75%");
                    categories.setText(String.valueOf(Store.getInstance().stockGroupList.size()));
//stuff that updates ui
                    textView.setText("Downloading accounts group..");
                }
            });

            SignalRService.accountGroupList();
            progressBar.setProgress(100);
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            percentage.setText("100%");


            accounts.setText(String.valueOf(Store.getInstance().accountsGroupList.size()));
            textView.setText(result);
           // progressDialog.dismiss();
            Intent intent = new Intent(DownloadDataActivity.this,DashboardActivity.class);
            startActivity(intent);


        }

        @Override
        protected void onProgressUpdate(Integer... values) {

        System.out.println("----------Status-----------------------"+values[0]);
        textView.setText("Running..."+ values[0]);
        progressBar.setProgress(values[0]);

    }
    }
}
