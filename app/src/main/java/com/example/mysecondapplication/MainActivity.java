package com.example.mysecondapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mysecondapplication.helper.InternetHelper;
import com.example.mysecondapplication.helper.RunOnSuccessCallback;
import com.example.mysecondapplication.helper.SimpleCallback;
import com.example.mysecondapplication.repository.Person;
import com.example.mysecondapplication.service.PersonBO;
import com.example.mysecondapplication.service.SampleAPIService;
import com.example.mysecondapplication.service.impl.SampleAPiServiceImpl;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/*
Problemas
- Não exibe toast
 */
public class MainActivity extends AppCompatActivity {

    private Button saveButton;
    private EditText iptName;
    private EditText iptAge;

    private TextView totalRecords;
    private TextView syncRecords;
    private TextView unsyncRecords;

    private PersonBO personBO;
    private SampleAPiServiceImpl sampleAPIService = new SampleAPiServiceImpl();
    private InternetHelper internetHelper = new InternetHelper();

    private Timer syncTimer = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setup();
        clearInputs();
        updateTxtView();
    }

    private void setup() {
        saveButton = findViewById(R.id.btn_save);
        iptName = findViewById(R.id.ipt_name);
        iptAge = findViewById(R.id.ipt_age);

        totalRecords = findViewById(R.id.txtv_totalRecords);
        syncRecords = findViewById(R.id.txtv_syncRecords);
        unsyncRecords = findViewById(R.id.txtv_unsyncRecords);

        totalRecords.setText("0");
        syncRecords.setText("0");
        unsyncRecords.setText("0");

        personBO = new PersonBO(getApplication());

        saveButton.setOnClickListener(this::saveButtonClick);
        syncTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                sync();
            }
        }, 60, TimeUnit.SECONDS.toMillis(30));
    }

    private void saveButtonClick(View v) {
        String name = iptName.getText().toString();
        String age = iptAge.getText().toString();
        Person person = new Person(name, age, false);
        personBO.save(person, new SimpleCallback<Person>() {
            @Override
            public void call() {
                if(this.success) {
                    Person savedPerson = this.object;
                    sendPersonToApi(savedPerson);
                    runOnUiThread(() -> {
                        clearInputs();
                        updateTxtView();
                        Toast.makeText(getBaseContext(), "Salvo com sucesso.", Toast.LENGTH_SHORT);
                    });
                }
                else
                    runOnUiThread(() -> {
                        Toast.makeText(getBaseContext(), "Erro ao salvar, tente novamente mais tarde", Toast.LENGTH_SHORT);
                    });
            }
        });

    }

    private void sync() {
        List<Person> person = personBO.getUnsync();
        person.forEach(this::sendPersonToApi);
        runOnUiThread(this::updateTxtView);
    }

    private void sendPersonToApi(Person person) {
        if(internetHelper.isInternetAvailable()) {
            sampleAPIService.sendData(person, new RunOnSuccessCallback().runOnSuccess(() -> setPersonToSyncronized(person)));
        }
    }

    private void setPersonToSyncronized(Person person) {
        person.setSyncronized(true);
        personBO.update(person);
    }

    private void updateTxtView() {
        totalRecords.setText(personBO.count().toString());
        syncRecords.setText(personBO.countSync().toString());
        unsyncRecords.setText(personBO.countUnsync().toString());
    }

    private void clearInputs() {
        iptName.setText("");
        iptAge.setText("");
    }
}
