package com.tigcal.aeq.transformation;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.tigcal.aeq.transformation.adapter.TransformerAdapter;
import com.tigcal.aeq.transformation.model.Transformer;
import com.tigcal.aeq.transformation.util.BattleManager;

import java.util.ArrayList;
import java.util.Collections;
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
                transformers.remove(index);
                transformerAdapter.notifyItemRemoved(index);
                transformerAdapter.notifyItemRangeChanged(index, transformers.size());
                if (transformers.isEmpty()) {
                    emptyTransformersText.setVisibility(View.VISIBLE);
                }
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
                startBattle();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void displayAddTransformerDialog() {
        AddTransformerFragment addTransformerDialog = new AddTransformerFragment();
        addTransformerDialog.show(getSupportFragmentManager(), getString(R.string.transformer_add));
    }

    private void startBattle() {
        List<Transformer> autobots = new ArrayList<>();
        List<Transformer> decepticons = new ArrayList<>();
        for (Transformer transformer : transformers) {
            if (transformer.isAutobot()) {
                autobots.add(transformer);
            } else {
                decepticons.add(transformer);
            }
        }

        //TODO check: there must be at least 1 per "team"

        Collections.sort(autobots);
        Collections.sort(decepticons);
        battle(autobots, decepticons);
    }

    private void battle(List<Transformer> autobots, List<Transformer> decepticons) {
        int autobotsWin = 0;
        int decepticonsWin = 0;

        int battles = Math.min(autobots.size(), decepticons.size());
        for (int i = 0; i < battles; i++) {
            int result = BattleManager.startBattle(autobots.get(i), decepticons.get(i));
            if (result == BattleManager.Result.END) {
                displayImmediateBattleEndMessage();
                break;
            } else if (result == BattleManager.Result.DRAW) {
                autobots.get(i).die();
                decepticons.get(i).die();
            } else if (result == BattleManager.Result.AUTOBOT_WINS) {
                autobotsWin++;
                decepticons.get(i).die();
            } else if (result == BattleManager.Result.DECEPTICON_WINS) {
                decepticonsWin++;
                autobots.get(i).die();
            }
        }

        if (autobotsWin > decepticonsWin) {
            displayAutobotsWonMessage(battles, autobots, decepticons);
        } else if (decepticonsWin > autobotsWin) {
            displayDecepticonsWonMessage(battles, autobots, decepticons);
        } else {
            displayMessage(getString(R.string.battle_tie));
        }
    }

    private void displayImmediateBattleEndMessage() {
        displayMessage(getString(R.string.battle_immediate_end));
    }

    private void displayAutobotsWonMessage(int battles, List<Transformer> autobots, List<Transformer> decepticons) {
        StringBuilder message = new StringBuilder();
        //TODO 1 battles -> 1 battle
        message.append(getString(R.string.battle_number, battles));
        message.append("\n");
        message.append(getString(R.string.battle_winners_autobots, getSurvivors(autobots)));
        message.append("\n");
        message.append(getString(R.string.battle_loser_decepticon_survivors, getSurvivors(decepticons)));
        //TODO no survivors
        displayMessage(message.toString());
    }

    private void displayDecepticonsWonMessage(int battles, List<Transformer> autobots, List<Transformer> decepticons) {
        StringBuilder message = new StringBuilder();
        //TODO 1 battles -> 1 battle
        message.append(getString(R.string.battle_number, battles));
        message.append("\n");
        message.append(getString(R.string.battle_winners_decepticons, getSurvivors(decepticons)));
        message.append("\n");
        message.append(getString(R.string.battle_loser_autobots_survivors, getSurvivors(autobots)));
        //TODO no survivors
        displayMessage(message.toString());
    }

    private String getSurvivors(List<Transformer> transformers) {
        List<String> transformersAlive = new ArrayList<>();
        for (Transformer transformer : transformers) {
            if (transformer.isAlive()) {
                transformersAlive.add(transformer.getName());
            }
        }
        return TextUtils.join(",", transformersAlive);
    }

    private void displayMessage(String message) {
        new AlertDialog.Builder(this)
                .setTitle(getString(R.string.app_name))
                .setMessage(message)
                .setPositiveButton(getString(R.string.battle_message_ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .create()
                .show();
        ;
    }

    @Override
    public void addTransformer(Transformer transformer) {
        transformers.add(transformer);
        transformerAdapter.notifyItemInserted(transformers.size() - 1);

        emptyTransformersText.setVisibility(View.INVISIBLE);

        Snackbar.make(recyclerView, getString(R.string.transformer_add_success, transformer.getName()), Snackbar.LENGTH_SHORT).show();
    }
}
