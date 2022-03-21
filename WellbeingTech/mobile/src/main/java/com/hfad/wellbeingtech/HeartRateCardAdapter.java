package com.hfad.wellbeingtech;



import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.icu.text.DecimalFormat;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HeartRateCardAdapter extends RecyclerView.Adapter<HeartRateCardAdapter.ViewHolder> {

    private static final String CHANNEL_ID = "Channel_1";
    HrvCardData[] mhrvcardData;
    Context context;
    public static String id = "test_channel_01";
    int notificationID = 1;


    DAOHeartRateDB daoHeartRateDB;
    double hrv2;
    double mbpn;



    public HeartRateCardAdapter(HrvCardData[] mhrvcardData, HRV hrv) {
        this.mhrvcardData = mhrvcardData;
        this.context = hrv;
    }

    @NonNull
    @Override
    public HeartRateCardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.hrv_recycler,parent,false);



        HeartRateCardAdapter.ViewHolder viewHolder = new HeartRateCardAdapter.ViewHolder(view);

        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull HeartRateCardAdapter.ViewHolder holder, int position) {
        final HrvCardData cardDataList = mhrvcardData[position];


        new AsyncTasks() {
            @Override
            public void onPreExecute() {
                // before execution
                if(holder.getAdapterPosition() ==0){
                    holder.heartStat.setText(cardDataList.getHeartRate());
                    holder.imageView.setImageResource(cardDataList.getImage());
                    holder.heartValue.setVisibility(View.VISIBLE);
                    holder.progressBar.setVisibility(View.VISIBLE);
                    holder.loading.setText("Loading ...");
                    holder.quit.setVisibility(View.GONE);


                }

                if(holder.getAdapterPosition() ==1){
                    holder.heartStat.setText(cardDataList.getHeartRate());
                    holder.imageView.setImageResource(cardDataList.getImage());
                    holder.heartValue.setVisibility(View.VISIBLE);
                    holder.progressBar.setVisibility(View.VISIBLE);
                    holder.loading.setText("Loading ...");
                    holder.quit.setVisibility(View.GONE);
                }

                if(holder.getAdapterPosition() ==2){
                    holder.heartStat.setText(cardDataList.getHeartRate());
                    holder.imageView.setImageResource(cardDataList.getImage());
                    holder.heartValue.setVisibility(View.GONE);
                    holder.loading.setVisibility(View.GONE);
                    holder.progressBar.setVisibility(View.GONE);
                    holder.quit.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void doInBackground() {
                // background task
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
                DatabaseReference tripsRef = rootRef.child("DBHeartRate");

                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        ArrayList<Integer> list = new ArrayList<>();


                        for(DataSnapshot ds : dataSnapshot.getChildren()) {
                            Integer heartBeat = ds.child("heartBeat").getValue(Integer.class);
                            Long timestamps = ds.child("serverTimestamp").getValue(Long.class);

                            Date d = new Date(timestamps );

                            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                            Date d3 = new Date();


                            long d4 = d3.getTime() - d.getTime();
                            int minutes = (int) (d4 / (1000 * 60));
                            int numOfDays = (int) (d4 / (1000 * 60 * 60 * 24));

                            // int hours = (int) d4 / (1000 * 60 * 60));



                            if(minutes<=5){
                                list.add(heartBeat);
                                Log.d("TAG", heartBeat + " / " + timestamps  + " / " + dateFormat.format(d.getTime() )+ " / " + dateFormat.format(d3.getTime()) + " / "+minutes+" / ");
                                Log.d("Standard Deviation ", String.valueOf(standarDeviation(list)));
                            }
                        }
                        double sum = 0;
                        for(Integer heartR:list){
                            sum = sum+heartR;
                        }



                        if(standarDeviation(list) >=0){
                            DecimalFormat dfZero = new DecimalFormat("0.0");

                            hrv2 = Double.parseDouble(dfZero.format(standarDeviation(list)));
                            mbpn = (int) (sum / list.size());
                            Log.d("MBPN",String.valueOf(mbpn));


                            if(holder.getAdapterPosition() ==0){
                                holder.heartStat.setText(cardDataList.getHeartRate());
                                holder.imageView.setImageResource(cardDataList.getImage());
                                holder.heartValue.setText(String.valueOf(mbpn));
                                holder.progressBar.setVisibility(View.GONE);
                                holder.loading.setVisibility(View.GONE);
                                holder.quit.setVisibility(View.GONE);
                            }

                            if(holder.getAdapterPosition() ==1){
                                holder.heartStat.setText(cardDataList.getHeartRate());
                                holder.imageView.setImageResource(cardDataList.getImage());
                                holder.heartValue.setText(String.valueOf(hrv2));
                                holder.progressBar.setVisibility(View.GONE);
                                holder.loading.setVisibility(View.GONE);
                                holder.quit.setVisibility(View.GONE);
                            }

                            if(holder.getAdapterPosition() ==2){
                                holder.heartStat.setText(cardDataList.getHeartRate());
                                holder.imageView.setImageResource(cardDataList.getImage());
                                holder.heartValue.setVisibility(View.GONE);
                                holder.loading.setVisibility(View.GONE);
                                holder.progressBar.setVisibility(View.GONE);
                                holder.quit.setVisibility(View.VISIBLE);
                                holder.quit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        System.exit(0);
                                    }
                                });


                            }

                            // Insert data into the HeartRate calculation database
                            HeartRateVariabilityDB heartRateVariabilityDB = new HeartRateVariabilityDB(hrv2,mbpn, ServerValue.TIMESTAMP);
                            daoHeartRateDB= new DAOHeartRateDB();
                            daoHeartRateDB.insert(heartRateVariabilityDB);

                            try {
                                Thread.sleep(1000);
                                notificationhrv(String.valueOf(hrv2) , String.valueOf(mbpn));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }else{

                            if(holder.getAdapterPosition() ==0){
                                holder.heartStat.setText(cardDataList.getHeartRate());
                                holder.imageView.setImageResource(cardDataList.getImage());
                                holder.heartValue.setText("No data, activate watch app");
                                holder.progressBar.setVisibility(View.GONE);
                                holder.loading.setVisibility(View.GONE);
                                holder.quit.setVisibility(View.GONE);
                            }

                            if(holder.getAdapterPosition() ==1){
                                holder.heartStat.setText(cardDataList.getHeartRate());
                                holder.imageView.setImageResource(cardDataList.getImage());
                                holder.heartValue.setText("No data, activate watch app");
                                holder.progressBar.setVisibility(View.GONE);
                                holder.loading.setVisibility(View.GONE);
                                holder.quit.setVisibility(View.GONE);
                            }

                            if(holder.getAdapterPosition() ==2){
                                holder.heartStat.setText(cardDataList.getHeartRate());
                                holder.imageView.setImageResource(cardDataList.getImage());
                                holder.heartValue.setVisibility(View.GONE);
                                holder.loading.setVisibility(View.GONE);
                                holder.progressBar.setVisibility(View.GONE);
                                holder.quit.setVisibility(View.VISIBLE);
                                holder.quit.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        System.exit(0);
                                    }
                                });


                            }

                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {}
                };
                tripsRef.addListenerForSingleValueEvent(valueEventListener);


            }

            @Override
            public void onPostExecute() {

            }
        }.execute();

    }

    @Override
    public int getItemCount() {
        int limit = 3;
        return Math.min(mhrvcardData.length, limit);
    }




    public  class ViewHolder extends RecyclerView.ViewHolder  {

        ImageView imageView;
        TextView heartStat;
        TextView heartValue;
        ProgressBar progressBar;
        TextView loading;
        Button quit;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageviewhrv);
            heartStat = itemView.findViewById(R.id.heart_rate_stats);
            heartValue = itemView.findViewById(R.id.hrv_value);
            progressBar = itemView.findViewById(R.id.pb_loading_indicator);
            loading = itemView.findViewById(R.id.progressbartext);
            quit = itemView.findViewById(R.id.quit_btn);

        }

    }


    public abstract class AsyncTasks {
        private final ExecutorService executors;

        public AsyncTasks() {
            this.executors = Executors.newSingleThreadExecutor();
        }

        private void startBackground() {
            onPreExecute();
            executors.execute(new Runnable() {
                @Override
                public void run() {
                    doInBackground();
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            onPostExecute();
                        }
                    });
                }
            });
        }

        public void execute() {
            startBackground();
        }

        public void shutdown() {
            executors.shutdown();
        }

        public boolean isShutdown() {
            return executors.isShutdown();
        }

        public abstract void onPreExecute();

        public abstract void doInBackground();

        public abstract void onPostExecute();
    }

    public  double standarDeviation(ArrayList<Integer> table)
    {
        double sum = 0;
        for(Integer heartR:table){
            sum = sum+heartR;

        }
        double mean = sum/table.size();
        double temp = 0;

        for (int i = 0; i < table.size(); i++)
        { int val = table.get(i);
            double squrDiffToMean = Math.pow(val - mean, 2);
            temp += squrDiffToMean;
        }

        double meanOfDiffs = (double) temp / (double) (table.size());
        return Math.sqrt(meanOfDiffs);
    }

    public void notificationhrv(String hrv, String mbpm) {
        /*
        Notification will be sent to the Watch using this function.
        * */
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(context, id)
                        .setSmallIcon(R.drawable.heartsmall)
                        .setContentTitle("HRV and MBPM")
                        .setContentText("Hrv :"+hrv +" "+"Mbpm :"+mbpm)
                        .setChannelId(id);


        // Get an instance of the NotificationManager service
        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);

        // Build the notification and issues it with notification manager.
        notificationManager.notify(notificationID, notificationBuilder.build());
        notificationID++;
    }

    }



