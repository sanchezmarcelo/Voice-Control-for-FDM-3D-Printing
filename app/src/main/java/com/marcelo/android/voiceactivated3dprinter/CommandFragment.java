package com.marcelo.android.voiceactivated3dprinter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */
public class CommandFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    View view;


    public CommandFragment() {
        // Required empty public constructor
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of speech fragment
     */
    // TODO: Rename and change types and number of parameters
    public static CommandFragment newInstance(String param1, String param2) {
        CommandFragment fragment = new CommandFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);

        View view = inflater.inflate(R.layout.command_fragment, container, false);

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_actions, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.action_add_printer:
                Log.d("Menu", "add printer selected");
                ServerDialog serverDialog = new ServerDialog();
                serverDialog.show(getFragmentManager(), "server_dialog");
                return true;
            case R.id.action_api_key:
                APIKeyDialog apiDialog = new APIKeyDialog();
                apiDialog.show(getFragmentManager(), "api_dialog");
                return true;
            case R.id.action_compute_server:
                ComputeServerDialog computeServerDialog = new ComputeServerDialog();
                computeServerDialog.show(getFragmentManager(), "compute_server_dialog");
                return true;
            case R.id.action_faq:
                Intent FAQActivity = new Intent(getActivity(), com.marcelo.android.voiceactivated3dprinter.FAQActivity.class);
                getActivity().startActivity(FAQActivity);
                return true;
            case R.id.action_settings:
                Intent SettingsActivity = new Intent(getActivity(), com.marcelo.android.voiceactivated3dprinter.SettingsActivity.class);
                getActivity().startActivity(SettingsActivity);
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }
}
