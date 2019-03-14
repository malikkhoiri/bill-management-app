package com.code5.kb5.feature.tagihan;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.code5.kb5.R;
import com.code5.kb5.adapter.BillAdapter;
import com.code5.kb5.engine.AppController;
import com.code5.kb5.engine.interfaces.RetrofitCallback;
import com.code5.kb5.engine.session.LoginSession;
import com.code5.kb5.model.bill.BillData;
import com.code5.kb5.model.bill.BillResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TagihanFragment extends Fragment implements BillAdapter.BillAdapterListener {
    @BindView(R.id.rv_bill) RecyclerView rvBill;
    @BindView(R.id.toolbar_bill) Toolbar toolbar;
    private SearchView searchView;
    private BillAdapter billAdapter;

    public TagihanFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tagihan, container, false);
        setHasOptionsMenu(true);
        ButterKnife.bind(this, view);

        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(R.string.title_tagihan);

        final List<BillData> billData = new ArrayList<>();

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());

        rvBill.setHasFixedSize(true);
        rvBill.setLayoutManager(layoutManager);
        rvBill.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
                super.onDraw(c, parent, state);
            }
        });
        rvBill.setItemAnimator(new DefaultItemAnimator());

        billAdapter = new BillAdapter(getContext(), billData, this);
        rvBill.setAdapter(billAdapter);

        LoginSession loginSession = new LoginSession(getContext());

        AppController app = new AppController();
        app.getBill(loginSession.getLoginSession(LoginSession.ACCESS_TOKEN), new RetrofitCallback() {
            @Override
            public void onResponse(Response<?> response) {
                if (response.isSuccessful() && response.body() != null){
                    BillResponse billResponse = (BillResponse) response.body();
                    if(billResponse.getStatus() == 200){
                        int size = billResponse.getData().size();

                        for(int i=0; i<size; i ++){
                            billData.add(billResponse.getData().get(i));
                        }
                        billAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(String result) {

            }
        });

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_main, menu);

        // Associate searchable configuration with the SearchView
        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getActivity().getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                billAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                billAdapter.getFilter().filter(query);

                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        } else if(id == R.id.action_add){
            startActivity(new Intent(getContext(), AddTagihanActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();

        /* if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    if (!searchView.isIconified()) {
                        searchView.setIconified(true);
                        return true;
                    }
                }
                return  false;
            }
        }); */
    }

    @Override
    public void onBillSelected(BillData billData) {
        Intent intent = new Intent(getContext(), DetailTagihanActivity.class);
        intent.putExtra("bill_data", (Serializable) billData);
        startActivity(intent);
    }
}
