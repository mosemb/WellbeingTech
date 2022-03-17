package com.hfad.wellbeingtech;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ServerValue;

import java.util.HashMap;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {

    CardData [] cardData;
    Context context;
    DAOMentalDB daoMentalDB;
    HashMap<String,Integer> seekbarProgressValues = new HashMap<>();
    Integer pos;
    Integer progressGlobal;
    Integer stressValue;
    Integer difficultyValue;
    Integer competenceValue;

    public CardAdapter(CardData[] cardData, MainActivity activity) {
        this.cardData = cardData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.stress_competence_difficultly,parent,false);



        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {


        final CardData cardDataList = cardData[position];

        if(position==getItemCount()-1) {

            holder.mentalStateText.setText(cardDataList.getMentalState());
            holder.seekBar.setVisibility(View.GONE);
            holder.mentalStateImage.setVisibility(View.GONE);
            holder.mSeekBarValue.setVisibility(View.GONE);
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   // Toast.makeText(context, "Been Clicked ", Toast.LENGTH_SHORT).show();

                    if(seekbarProgressValues.get("Stress")==null){

                        stressValue = 0;

                    }else{

                        stressValue = seekbarProgressValues.get("Stress");
                    }

                    if(seekbarProgressValues.get("Difficulty")==null){
                        difficultyValue = 0;
                    }else{
                        difficultyValue = seekbarProgressValues.get("Difficulty");
                    }


                    if(seekbarProgressValues.get("Competence")==null){
                        competenceValue = 0;
                    }else{
                        competenceValue = seekbarProgressValues.get("Competence");

                    }


                    MentalDB mentalDB = new MentalDB( stressValue,difficultyValue,competenceValue, ServerValue.TIMESTAMP);
                    Log.d("Stress Value",String.valueOf(seekbarProgressValues.get("Stress")));
                     daoMentalDB = new DAOMentalDB();
                     daoMentalDB.insert(mentalDB).addOnSuccessListener(suc->{

                      Toast.makeText(context, "Value inserted", Toast.LENGTH_SHORT).show();


                       }).addOnFailureListener(err->{
                      Toast.makeText(context, err.getMessage(), Toast.LENGTH_SHORT).show();
                       });


                   // On click start a new activity from the current context
                   Intent intent = new Intent(context,HRV.class);
                   context.startActivity(intent);


                }
            });

        }else{

           // final CardData cardDataList = cardData[position];
            holder.mentalStateText.setText(cardDataList.getMentalState());
            holder.mentalStateImage.setImageResource(cardDataList.getImageMentalState());
            holder.seekBar.setEnabled(true);
            holder.seekBar.setProgress(0);
            holder.seekBar.setTag(position);
            holder.button.setVisibility(View.GONE);


        }
    }

    @Override
    public int getItemCount() {
        int limit = 4;
        return Math.min(cardData.length, limit);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, SeekBar.OnSeekBarChangeListener{
        ImageView mentalStateImage;
        TextView mentalStateText;
        TextView  mSeekBarValue;
        SeekBar   seekBar;
        Button button;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mentalStateImage = itemView.findViewById(R.id.imageview);
            mentalStateText = itemView.findViewById(R.id.textName);
            mSeekBarValue = itemView.findViewById(R.id.seekbarValue);
            seekBar = itemView.findViewById(R.id.seekbar);
            seekBar.setOnSeekBarChangeListener(this);
            button = itemView.findViewById(R.id.submit_btn);

        }

        @Override
        public void onClick(View v) {

        }



        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            progressGlobal = progress;
            if(fromUser){
                Log.d("", "Selected"+ seekBar.getTag());
            }

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

            mSeekBarValue.setText(String.valueOf(progressGlobal));
            Integer seek = (Integer) seekBar.getTag();

            if(seek == 0 ){
                seekbarProgressValues.put("Stress",progressGlobal);
            }else if(seek ==1){
                seekbarProgressValues.put("Competence",progressGlobal);
            }else if(seek ==2) {
                seekbarProgressValues.put("Difficulty",progressGlobal);
            }


        }
    }



}
