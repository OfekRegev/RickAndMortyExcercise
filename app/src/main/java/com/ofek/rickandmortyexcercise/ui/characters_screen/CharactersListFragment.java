package com.ofek.rickandmortyexcercise.ui.characters_screen;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.marshalchen.ultimaterecyclerview.UltimateRecyclerView;
import com.ofek.rickandmortyexcercise.R;
import com.ofek.rickandmortyexcercise.presentation.characters_list.CharactersListScreenVM;
import com.ofek.rickandmortyexcercise.presentation.characters_list.CharactersListState;
import com.ofek.rickandmortyexcercise.ui.di.GlobalDependencyProvider;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

public class CharactersListFragment extends Fragment {
    private CharactersListScreenVM.Factory vmFactory = GlobalDependencyProvider.getCharactersListVMFactory();
    private CharactersListScreenVM viewModel;
    private RecyclerView charactersRv;
    /**
     * handles swipe to refresh functionality
     */
    private SwipeRefreshLayout swipeRefreshLayout;
    /**
     * handles load more functionality
     */
    private SmartRefreshLayout smartRefreshLayout;
    private CharactersRvAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(getActivity(),vmFactory).get(CharactersListScreenVM.class);
        viewModel.stateLiveData.observe(this,this::onStateChanged);
        viewModel.loadFirstPage(false);

    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.characters_list_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        charactersRv = view.findViewById(R.id.charactersRv);
        charactersRv.setLayoutManager(new LinearLayoutManager(view.getContext()));
        smartRefreshLayout = view.findViewById(R.id.load_more_layout);
        view.findViewById(R.id.back_btn).setVisibility(View.GONE);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> {
            viewModel.loadFirstPage(true);
        });
        smartRefreshLayout.setOnLoadMoreListener(refreshLayout -> {
            viewModel.loadNextCharactersPage();
        });
    }

    private void onStateChanged(CharactersListState charactersListState) {
        if (!charactersListState.isLoading()) {
            smartRefreshLayout.finishLoadMore();
        }
        if (!charactersListState.isRefreshing()) {
            smartRefreshLayout.finishRefresh(true);
        }
        if (adapter == null) {
            // lets the activity handle character selection
            adapter = new CharactersRvAdapter(charactersListState.getCharacterObjs(),
                    (CharactersRvAdapter.OnCharacterSelectedListener) getActivity());
            charactersRv.setAdapter(adapter);
        } else {
            adapter.setUiCharacters(charactersListState.getCharacterObjs());
            adapter.notifyDataSetChanged();
        }
    }
}
