package animacion;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.kike.lp3_ep3_rojaspal.MainActivity;
import com.example.kike.lp3_ep3_rojaspal.R;
import com.github.paolorotolo.appintro.AppIntro;


/**
 * Created by kikerojas on 22/10/2015.
 */
public class ProgressIndicator extends AppIntro {
   @Override
    public void init(Bundle savedInstanceState) {
        addSlide(SampleSlide.newInstance(R.layout.intro1));

        addSlide(SampleSlide.newInstance(R.layout.intro2));
        setProgressIndicator();


    }


    private void loadMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSkipPressed() {
        loadMainActivity();
        Toast.makeText(getApplicationContext(), getString(R.string.skip), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDonePressed() {
        loadMainActivity();
    }

    public void getStarted(View v){
        loadMainActivity();
    }
/*
    @Override
    public void init(Bundle savedInstanceState) {

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        boolean previouslyStarted = prefs.getBoolean("is_first", false);
        if (!previouslyStarted) {
            SharedPreferences.Editor edit = prefs.edit();
            edit.putBoolean("is_first", Boolean.TRUE);
            edit.commit();
            addSlide(SampleSlide.newInstance(R.layout.intro1));
            addSlide(SampleSlide.newInstance(R.layout.intro2));
            setSkipText("Saltar");
            setDoneText("Entendido");
        } else {
            startActivity(new Intent(getApplicationContext(), .class));
            finish();
        }


    }

    @Override
    public void onSkipPressed() {

    }

    @Override
    public void onDonePressed() {

    }*/
}
