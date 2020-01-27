package com.marcelo.android.voiceactivated3dprinter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class APIKeyDialog extends DialogFragment {

    //private EditText userServerIP = getActivity().findViewById(R.id.user_enter_server_ip);
    private EditText e;
    TextView text;

    private EditText editKey;
    private TextView viewKey;
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Log.d(TAG, "onCreateDialog: ");
        //e.setText("test"); CANNOT SET TEXT HERE; THROWS NULL
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("API Key").setMessage("Enter the API key provided by OctoPrint.")
                .setPositiveButton("UPDATE", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try{
                            e.addTextChangedListener(new TextWatcher() {
                                @Override
                                public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                                    Log.d(TAG, "beforeTextChanged: ");
                                }

                                @Override
                                public void onTextChanged(CharSequence s, int start, int before, int count) {
                                    Log.d(TAG, "onTextChanged: ");
                                }

                                @Override
                                public void afterTextChanged(Editable s) {
                                    e.setText(s);
                                    Log.d(TAG, "afterTextChanged: " + e.toString());
                                }
                            });
                        }catch (Exception e){
                            Log.d(TAG, "Exception thrown: ");
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

//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//
//        editKey = getActivity().findViewById(R.id.user_enter_api_key);
//        viewKey = getActivity().findViewById(R.id.current_key);
//        text.setText(Home.key);
//        Log.d(TAG, "onAttach: Called");
//    }
//
//    @Override
//    public void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        editKey = getActivity().findViewById(R.id.user_enter_api_key);
//        viewKey = getActivity().findViewById(R.id.current_key);
//        viewKey.setText(Home.key);
//        Log.d(TAG, "onCreate: Called");
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View v = inflater.inflate(R.layout.dialog_api_key, container, false);
//
//        editKey = getActivity().findViewById(R.id.user_enter_api_key);
//        viewKey = getActivity().findViewById(R.id.current_key);
//        text.setText(Home.key);
//        Log.d(TAG, "onCreateView: Called");
//        return v;
//    }
//
//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        editKey = getActivity().findViewById(R.id.user_enter_api_key);
//        viewKey = getActivity().findViewById(R.id.current_key);
//        text.setText(Home.key);
//        Log.d(TAG, "onActivityCreated: Called");
//    }
//
//    @Override
//    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
//        super.onViewStateRestored(savedInstanceState);
//        editKey = getActivity().findViewById(R.id.user_enter_api_key);
//        viewKey = getActivity().findViewById(R.id.current_key);
//        text.setText(Home.key);
//        Log.d(TAG, "onViewStateRestored: Called");
//    }
//
//    @Override
//    public void onStart(){
//        super.onStart();
//        editKey = getActivity().findViewById(R.id.user_enter_api_key);
//        viewKey = getActivity().findViewById(R.id.current_key);
//        text.setText(Home.key);
//        Log.d(TAG, "onStart: Called");
//        //e.setText("test"); CANNOT SET TEXT HERE; THROWS INCORRECT VALUE;
//    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        editKey = getActivity().findViewById(R.id.user_enter_api_key);
//        viewKey = getActivity().findViewById(R.id.current_key);
//        text.setText(Home.key);
//        Log.d(TAG, "onResume: Called");
//    }
}
