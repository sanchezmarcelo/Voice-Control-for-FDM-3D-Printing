package com.marcelo.android.voiceactivated3dprinter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.VideoView;

import java.net.MalformedURLException;
import java.net.URL;

import androidx.fragment.app.Fragment;
/*
@Author: Marcelo Sanchez
@Version: 12/3/2019
 */
public class PrinterStatusFragment extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public PrinterStatusFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PrinterStatusFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PrinterStatusFragment newInstance(String param1, String param2) {
        PrinterStatusFragment fragment = new PrinterStatusFragment();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.printer_status_fragment, container, false);
        createWebcamView(view);
        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater){
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_actions, menu);
    }

    public void createWebcamView(View view){
        WebView webView = view.findViewById(R.id.webcam);
        webView.loadUrl("http://xxxxx/webcam/?action=stream");
        webView.setRotation(-90);
        webView.setInitialScale(165);
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



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

}
