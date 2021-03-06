package ar.edu.ort.bmon.rootsapp.ui.event;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import ar.edu.ort.bmon.rootsapp.R;
import ar.edu.ort.bmon.rootsapp.constants.Constants;
import ar.edu.ort.bmon.rootsapp.model.Event;

public class EventFragment extends Fragment {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private RecyclerView recyclerViewGermination;
    private RecyclerView recyclerViewCutting;
    private EventAdapter eventAdapter;
    private EventCuttingAdapter eventCuttingAdapter;
    private MenuItem createNewEvent;
    private View viewReference;
    private ConstraintLayout expandableLayout;
    private ConstraintLayout expandableLayoutCutting;
    private CardView germinationsCard;
    private CardView cuttingCard;
    private boolean expanded;
    private boolean cuttingExpanded;
    private EventDetailViewModel model;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewReference = inflater.inflate(R.layout.event_fragment, container, false);

        recyclerViewGermination = viewReference.findViewById(R.id.recyclerGerminacion);
        recyclerViewCutting = viewReference.findViewById(R.id.recyclerCutting);

        recyclerViewGermination.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewCutting.setLayoutManager(new LinearLayoutManager(getContext()));

        Query query = db.collection(Constants.EVENTS_COLLECTION);

        FirestoreRecyclerOptions<Event> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Event>()
                        .setQuery(query, Event.class)
                        .build();
        Query queryCutting = query.whereEqualTo("tipo", Constants.CUTTING);
        Task<QuerySnapshot> cutting = queryCutting.get();

        cutting.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int cuttingQuantity = task.getResult().size();
                TextView cuttingTV = viewReference.findViewById(R.id.text_view_grupos_cutting);
                if (cuttingQuantity > 1) {
                    cuttingTV.setText(String.valueOf(cuttingQuantity)  +" " +  Constants.GRUPOS);
                } else {
                    cuttingTV.setText(String.valueOf(cuttingQuantity)  +" " +  Constants.GRUPO);
                }
            }
        });

        Query queryGermination = query.whereEqualTo("tipo", Constants.GERMINATION);
        Task<QuerySnapshot> germination = queryGermination.get();
        germination.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                int germinationQuantity = task.getResult().size();
                TextView germinationTV = viewReference.findViewById(R.id.text_view_grupos_germination);
                if (germinationQuantity > 1) {
                    germinationTV.setText(String.valueOf(germinationQuantity) + " " + Constants.GRUPOS);
                } else {
                    germinationTV.setText(String.valueOf(germinationQuantity) + " " + Constants.GRUPO);
                }
            }
        });

        model = new ViewModelProvider(requireActivity()).get(EventDetailViewModel.class);

        eventAdapter = new EventAdapter(firestoreRecyclerOptions, new EventOnTextClickListener() {
            @Override
            public EventDetailViewModel onTextClick() {
                return model;
            }
        });


        eventCuttingAdapter = new EventCuttingAdapter(firestoreRecyclerOptions, new EventOnTextClickListener() {
            @Override
            public EventDetailViewModel onTextClick() {
                return model;
            }
        });

        recyclerViewGermination.setAdapter(eventAdapter);
        recyclerViewCutting.setAdapter(eventCuttingAdapter);

        expandableLayout = viewReference.findViewById(R.id.expandableLayout);
        expandableLayoutCutting = viewReference.findViewById(R.id.expandableLayoutCutting);

		germinationsCard = viewReference.findViewById(R.id.card_germination);
		cuttingCard = viewReference.findViewById(R.id.card_cutting);

        expandableLayout.setVisibility(View.GONE);
        expandableLayoutCutting.setVisibility(View.GONE);
        germinationsCard.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				expanded = !expanded;
			 	expandableLayout.setVisibility(expanded ? View.VISIBLE : View.GONE);
			}
		});


        cuttingCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cuttingExpanded = !cuttingExpanded;
                expandableLayoutCutting.setVisibility(cuttingExpanded ? View.VISIBLE : View.GONE);
            }
        });

        return viewReference;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_events_list, menu);
        createNewEvent = menu.findItem(R.id.menu_create_event);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int selectionId = item.getItemId();
        if (selectionId == R.id.menu_create_event) {
            createNewEventDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    private void createNewEventDialog() {
        MaterialAlertDialogBuilder createNewEventDialog = new MaterialAlertDialogBuilder(getActivity());
        createNewEventDialog.setBackground(getResources().getDrawable(R.drawable.alert_dialog_bg));
        createNewEventDialog.setTitle(Constants.CREATE_NEW_EVENT_TITLE);
        String[] eventOptions = new String[] { Constants.GERMINATION, Constants.CUTTING };
        createNewEventDialog.setSingleChoiceItems(eventOptions, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ListView selectionList = ((AlertDialog) dialog).getListView();
                selectionList.setTag(Integer.valueOf(which));
            }
        });
        createNewEventDialog.setNegativeButton(Constants.CANCEL_BUTTON, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        createNewEventDialog.setPositiveButton(Constants.ACCEPT_BUTTON, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Bundle bundle = new Bundle();
                ListView selectionList = ((AlertDialog) dialog).getListView();
                Integer selectedItemId = (Integer)selectionList.getTag();
                if (null != selectedItemId){
                    bundle.putString(Constants.SELECTED_EVENT, selectedItemId.toString());
                }else{
                    Toast.makeText(getContext(), "Seleccioná un Tipo de evento", Toast.LENGTH_LONG).show();
                }
                bundle.putString(Constants.SELECTED_EVENT, selectedItemId.toString());
                Navigation.findNavController(viewReference).navigate(R.id.action_nav_event_to_nav_create_event, bundle);
                dialog.dismiss();
            }
        });
        createNewEventDialog.create().show();
    }


	@Override
	public void onStart() {
		super.onStart();
		eventCuttingAdapter.startListening();
		eventAdapter.startListening();
	}

	@Override
	public void onStop() {
		super.onStop();
		eventAdapter.stopListening();
		eventCuttingAdapter.stopListening();
	}
}