package br.com.jolameira.aplicacaogcp;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements FragmentA.Communicator,FragmentManager.OnBackStackChangedListener {

    FragmentA f1;
    FragmentB f2;
    FragmentManager manager;
    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        manager = getFragmentManager();
        f1 =(FragmentA) manager.findFragmentById(R.id.fragment);
        f1.setCommunicator(this);
        text = (TextView) findViewById(R.id.textView);
        manager.addOnBackStackChangedListener(this);

        if(savedInstanceState == null){
            FragmentA fragmentA = new FragmentA();
            FragmentB fragmentB = new FragmentB();
            FragmentTransaction ft = manager.beginTransaction();
            ft.add(R.id.fragment,fragmentA,"fragmentA");
        }
    }

    @Override
    public void respond(int index) {

        f2 = (FragmentB) manager.findFragmentById(R.id.fragment2);
        if(f2 != null && f2.isVisible()){
            f2.mudarDados(index);
//            addB(f2.getView());
        }else{

            Intent intent = new Intent(this,AnotherActivity.class);
            intent.putExtra("index", index);
            startActivity(intent);
        }

    }

    public void addB(View v){
        FragmentB fragmentB = new FragmentB();
        FragmentTransaction ft = manager.beginTransaction();
        ft.add(R.id.fragment,fragmentB,"fragmentB");
        ft.addToBackStack("addB");
        ft.commit();
    }


    @Override
    public void onBackStackChanged() {
        text.setText(text.getText()+"\n");
        text.setText(text.getText() + "Texto da pilha");

        int count= manager.getBackStackEntryCount();
        for(int i = count-1 ; i>0 ;i--){
            FragmentManager.BackStackEntry entry =  manager.getBackStackEntryAt(i);
            text.setText(text.getText()+" "+entry.getName()+" \n");
        }
        text.setText(text.getText()+"\n");
    }
}
