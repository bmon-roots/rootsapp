package ar.edu.ort.bmon.rootsapp.ui.event;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import ar.edu.ort.bmon.rootsapp.R;
import ar.edu.ort.bmon.rootsapp.constants.Constants;
import ar.edu.ort.bmon.rootsapp.model.Event;


public class EventCuttingAdapter extends FirestoreRecyclerAdapter<Event, EventCuttingAdapter.EventHolder> {


    public EventCuttingAdapter(@NonNull FirestoreRecyclerOptions<Event> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull EventHolder holder, int position, @NonNull Event model) {
        holder.eventCard.setVisibility(View.GONE);
        holder.eventCard.setLayoutParams(new LinearLayout.LayoutParams(0, 0));
        if(model.getTipo().equals(Constants.CUTTING)){
            holder.eventCard.setVisibility(View.VISIBLE);
            holder.eventCard.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.eventGroup.setText(model.getEspecie());
            holder.eventTypeImage.setBackgroundResource(R.drawable.ic_sprouts);
        }
    }

    @NonNull
    @Override
    public EventHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);

        return new EventCuttingAdapter.EventHolder(view);
    }

    public class EventHolder extends RecyclerView.ViewHolder {
        CardView eventCard;
        TextView eventGroup;
        ImageView eventTypeImage;

        public EventHolder(@NonNull View itemView) {
            super(itemView);
            eventGroup = itemView.findViewById(R.id.text_view_grupo_especie);
            eventTypeImage = itemView.findViewById(R.id.image_view_grupo);
            eventCard = itemView.findViewById(R.id.card_event_group);
        }
    }
}