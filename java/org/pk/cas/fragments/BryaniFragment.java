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

public class BryaniFragment extends Fragment {
RecyclerView recyclerView;
ArrayList<FragmentRecyclerViewModelClass> dataList;
FragmentRecyclerViewAdapter adapter;
ProgressBar progressBar;


    public BryaniFragment() {
        // Required empty public constructor
    }


    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bryani, container, false);

        FirebaseFirestore db =FirebaseFirestore.getInstance();
        progressBar = view.findViewById(R.id.progress_Bar);
        recyclerView =view.findViewById(R.id.BryaniRecyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),2));
        dataList = new ArrayList<>();
        adapter = new FragmentRecyclerViewAdapter(dataList,getContext());
        recyclerView.setAdapter(adapter);

        db.collection("Bryani").get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                List<DocumentSnapshot> fetchingInDataFromFireStore =queryDocumentSnapshots.getDocuments();
                for (DocumentSnapshot d:fetchingInDataFromFireStore){
                    FragmentRecyclerViewModelClass obj =d.toObject(FragmentRecyclerViewModelClass.class);
                    dataList.add(obj);
                    progressBar.setVisibility(View.GONE);
                }
                adapter.notifyDataSetChanged();
            }
        });

        return view;
    }
}