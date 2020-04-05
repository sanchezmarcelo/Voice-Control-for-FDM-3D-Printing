package com.marcelo.android.voiceactivated3dprinter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Map;
import java.util.Set;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class APIKeyDialog extends DialogFragment {

    EditText e;
    TextView apiKey;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_api_key, container, false);
        e = (EditText) view.findViewById(R.id.user_enter_api_key);
        apiKey = view.findViewById(R.id.current_key);
        apiKey.setText("testKey123442");
        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        Log.d(TAG, "onCreateDialog: ");

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("API Key").setMessage("Enter the API key provided by OctoPrint.")
                .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(e == null){
                            Toast.makeText(getActivity(), "EditText is NULL", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(getActivity(), "EditText is NOT null", Toast.LENGTH_SHORT).show();
                        }
                        try{
                            e.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    Toast.makeText(getActivity(), "beforeTextChange: " + s, Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "beforeTextChanged: ");
                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    String x = (String) s;
                                    Save(x);
                                    Log.d(TAG, "onTextChanged: ");
                                    Toast.makeText(getActivity(), "onTextChange: " + s, Toast.LENGTH_SHORT).show();
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    Toast.makeText(getActivity(), "afterTextChange: " + s.toString(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "afterTextChanged: ");
                                }
                            });
                        }catch (Exception e){
                            Log.d(TAG, "Exception thrown: " + e);
                        }
                    }
                })
                .setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_api_key, null));

        String octoPrintIP = NetworkRequestGenerator.getOctoPrintIP();

        return builder.create();
    }

    public void Save(String s){
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("keys", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("apikey", s);
        editor.commit();
        Toast.makeText(this.getActivity(), "API key: " + sharedPreferences.getString("apikey", ""), Toast.LENGTH_SHORT).show();
    }

}
