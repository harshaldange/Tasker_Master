package com.mohdeva.learn.tasker;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Recycler_Adapter extends RecyclerView.Adapter<Recycler_Adapter.MyViewHolder> {

    private List<Tasks>tasklist;
    public class MyViewHolder extends RecyclerView.ViewHolder{

        public TextView tname,ttype,tdate;
        public MyViewHolder(View itemView) {
            super(itemView);
            tname=(TextView)itemView.findViewById(R.id.taskTitle);
            ttype=(TextView)itemView.findViewById(R.id.tasktype);
            tdate=(TextView)itemView.findViewById(R.id.taskDate);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //Confirm Delete
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(Todo.this);
//                // Setting Dialog Title
//                alertDialog.setTitle("Confirm Delete...");
//                // Setting Dialog Message
//                alertDialog.setMessage("Are you sure you want delete this?");
//                // Setting Positive "Yes" Button
//                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog,int which) {
//                        // Write your code here to invoke YES event
//                        String type[]=new String[1];
//                        String item = tasks_list.get(position);
//                        int tid=controller.getId(item);
//                        Cursor cursor=controller.gettype(tid);
//                        if(cursor.moveToFirst())
//                        {
//                            type[0]= cursor.getString(0);
//                        }
////                        Toast.makeText(getApplicationContext()," "+cursor.getCount(),Toast.LENGTH_SHORT).show();
//                        controller.deleteTask(tid,type[0]);
//                        controller.deleteTask(tid,"tasks");
//                        tasks_list.remove(position);
//                        arrayAdapter.notifyDataSetChanged();
//                        Toast.makeText(Todo.this, "You Deleted : " + item, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    public Recycler_Adapter(List<Tasks> taskList){
        this.tasklist=taskList;
    }

    @Override
    public Recycler_Adapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.tasks_list_row,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(Recycler_Adapter.MyViewHolder holder, int position) {
        Tasks tasks=tasklist.get(position);
        holder.tname.setText(tasks.getName());
        holder.ttype.setText(tasks.getType());
        holder.tdate.setText(tasks.getDate());
    }

    @Override
    public int getItemCount() {
        return tasklist.size();
    }
}
