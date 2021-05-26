package dk.acsandras.mailtest;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import uk.co.jakebreen.sendgridandroid.SendGrid;
import uk.co.jakebreen.sendgridandroid.SendGridMail;
import uk.co.jakebreen.sendgridandroid.SendGridResponse;
import uk.co.jakebreen.sendgridandroid.SendTask;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendMail(View view) {
        Log.d("András","Mailen oprettes.");
        SendGrid sendGrid = SendGrid.create("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");
        SendGridMail mail = new SendGridMail();
        mail.addRecipient("lail5198@edu.easj.dk", "Laila");
        mail.setFrom("anac@zealand.dk", "András");
        mail.setSubject("Hej fra sendgrid");
        mail.setContent("Hej, det virker nu. Måske.");
        Log.d("András","Mailen er nu konstrueret.");
        Log.d("András","Mailen sendes.");
        SendTask task = new SendTask(sendGrid, mail);
        try {
            SendGridResponse response = task.execute().get();
            Log.d("András", "Mailen er sendt til APIet" + response.getErrorMessage() );
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}