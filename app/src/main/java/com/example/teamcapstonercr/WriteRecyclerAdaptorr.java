package com.example.teamcapstonercr;

import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.DocumentSnapshot;

public class WriteRecyclerAdaptorr extends FirestoreRecyclerAdapter<Writec, WriteRecyclerAdaptorr.NoteViewHolder> {

    private NoteListner noteListner;
    public WriteRecyclerAdaptorr(@NonNull FirestoreRecyclerOptions<Writec> options,NoteListner noteListner) {
        super(options);
        this.noteListner = noteListner;
    }

    public WriteRecyclerAdaptorr(FirestoreRecyclerOptions<Writec> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull NoteViewHolder noteViewHolder, int i, @NonNull Writec writec) {
        noteViewHolder.noteTextView.setText(writec.getText());

        CharSequence dateCharSeq = DateFormat.format("EEEE,MMM,d,yyyy h:mm:ss a",writec.getCreated().toDate());
        noteViewHolder.dateTextView.setText(dateCharSeq);
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.write_row,parent,false);
        return new NoteViewHolder(view);
    }


    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView noteTextView,dateTextView;
        public NoteViewHolder(@NonNull View itemView) {

            super(itemView);

            noteTextView = itemView.findViewById(R.id.writetextview);
            dateTextView = itemView.findViewById(R.id.datetextview);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                  DocumentSnapshot snapshot = getSnapshots().getSnapshot(getAdapterPosition());

                }
            });


        }
        public  void deleteitem(int position)
        {
            Log.d("TAG","delete item" + getAdapterPosition());
            Log.d("TAG","delete" + getSnapshots().getSnapshot(getAdapterPosition()));
            getSnapshots().getSnapshot(position).getReference().delete();
        }
    }
interface NoteListner {
        public void handledeleteitem(DocumentSnapshot snapshot);
}
}
