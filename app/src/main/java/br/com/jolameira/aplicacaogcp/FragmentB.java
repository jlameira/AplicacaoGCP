package br.com.jolameira.aplicacaogcp;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A placeholder fragment containing a simple view.
 */
public class FragmentB extends Fragment {

    TextView text;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_b, container, false);

        text = (TextView) view.findViewById(R.id.textView);

        return view;
    }

    public void mudarDados(int index){
      String[] descricao = getResources().getStringArray(R.array.descricao);
        text.setText(descricao[index]);
    }
}
