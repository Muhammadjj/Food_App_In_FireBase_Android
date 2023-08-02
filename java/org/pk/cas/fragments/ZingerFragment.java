package org.pk.cas.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import org.pk.cas.fastfood1.FragmentRecyclerViewAdapter;
import org.pk.cas.fastfood1.FragmentRecyclerViewModelClass;
import org.pk.cas.fastfood1.R;

import java.util.ArrayList;
import java.util.List;


public class ZingerFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<FragmentRecyclerViewModelClass> dataList1;
    FragmentRecyclerViewAdapter adapter;
    ProgressBar progressBar_Zinger;

    public ZingerFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zinger, container, false);


        progressBar_Zinger = view.findViewById(R.id.progress_Bar_Zinger);
        FirebaseFirestore db =FirebaseFirestore.getInstance();
        recyclerView =view.findViewById(R.id.ZingerRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        dataList1 = new ArrayList<>();
        adapter = new FragmentRecyclerViewAdapter(dataList1,getContext());
        recyclerView.setAdapter(adapter);


        db.collection("Burger").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> fetchingInDataFromFireStore =queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d:fetchingInDataFromFireStore){
                    FragmentRecyclerViewModelClass obj1 =d.toObject(FragmentRecyclerViewModelClass.class);
                    dataList1.add(obj1);
                    progressBar_Zinger.setVisibility(View.GONE);

                }
                adapter.notifyDataSetChanged();
            }
        });


        return view;
    }
}