package com.marcelo.android.voiceactivated3dprinter;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class FAQRecyclerViewAdapter extends RecyclerView.Adapter<FAQRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<String> questionList;
    private final ArrayList<String> answerList;

    public FAQRecyclerViewAdapter(ArrayList<String> q, ArrayList<String> a) {
        questionList = q;
        answerList = a;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_faq, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.question.setText(questionList.get(position));
        holder.answer.setText(answerList.get(position));
    }



    @Override
    public int getItemCount() {
        return questionList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView question;
        public final TextView answer;

        public ViewHolder(View view) {
            super(view);
            question = (TextView) view.findViewById(R.id.question);
            answer = (TextView) view.findViewById(R.id.answer);
        }
    }
}
