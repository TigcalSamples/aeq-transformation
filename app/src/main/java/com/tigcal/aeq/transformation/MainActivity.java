package com.tigcal.aeq.transformation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tigcal.aeq.transformation.adapter.TransformerAdapter;
import com.tigcal.aeq.transformation.model.Transformer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AddTransformerFragment.AddTransformerListener {

    private RecyclerView recyclerView;
    private TextView emptyTransformersText;

    private TransformerAdapter transformerAdapter;

    private List<Transformer> transformers = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayAddTransformerDialog();
            }
        });

        emptyTransformersText = findViewById(R.id.list_empty_text);

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        transformerAdapter = new TransformerAdapter(this, transformers, new TransformerAdapter.OnClickListener() {
            @Override
            public void onClick(int index) {
                //TODO remove
            }
        });
        recyclerView.setAdapter(transformerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch ((item.getItemId())) {
            case R.id.action_battle:
                //TODO battle
                Snackbar.make(recyclerView, "TODO: Start Battle", Snackbar.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayAddTransformerDialog() {
        AddTransformerFragment addTransformerDialog = new AddTransformerFragment();
        addTransformerDialog.show(getSupportFragmentManager(), getString(R.string.transformer_add));
    }

    @Override
    public void addTransformer(Transformer transformer) {
        transformers.add(transformer);
        transformerAdapter.notifyItemInserted(transformers.size() - 1);

        emptyTransformersText.setVisibility(View.INVISIBLE);

        Snackbar.make(recyclerView, getString(R.string.transformer_add_success, transformer.getName()), Snackbar.LENGTH_SHORT).show();
    }
}
