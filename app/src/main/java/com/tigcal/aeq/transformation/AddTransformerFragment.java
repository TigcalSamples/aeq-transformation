package com.tigcal.aeq.transformation;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;

public class AddTransformerFragment extends DialogFragment {
    private TextView strengthText;
    private TextView intelligenceText;
    private TextView speedText;
    private TextView enduranceText;
    private TextView rankText;
    private TextView courageText;
    private TextView firepowerText;
    private TextView skillText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_add_transformer, container, false);

        ImageButton closeButton = view.findViewById(R.id.close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        Button addButton = view.findViewById(R.id.add_transformer_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "TODO: Add Transformer", Snackbar.LENGTH_SHORT).show();
            }
        });

        strengthText = view.findViewById(R.id.transformer_strength_text);
        intelligenceText = view.findViewById(R.id.transformer_intelligence_text);
        speedText = view.findViewById(R.id.transformer_speed_text);
        enduranceText = view.findViewById(R.id.transformer_endurance_text);
        rankText = view.findViewById(R.id.transformer_rank_text);
        courageText = view.findViewById(R.id.transformer_courage_text);
        firepowerText = view.findViewById(R.id.transformer_firepower_text);
        skillText = view.findViewById(R.id.transformer_skill_text);

        SeekBar.OnSeekBarChangeListener seekBarChangeListener = new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId()) {
                    case R.id.transformer_strength_seekbar:
                        strengthText.setText(String.valueOf(progress));
                        break;
                    case R.id.transformer_intelligence_seekbar:
                        intelligenceText.setText(String.valueOf(progress));
                        break;
                    case R.id.transformer_speed_seekbar:
                        speedText.setText(String.valueOf(progress));
                        break;
                    case R.id.transformer_endurance_seekbar:
                        enduranceText.setText(String.valueOf(progress));
                        break;
                    case R.id.transformer_rank_seekbar:
                        rankText.setText(String.valueOf(progress));
                        break;
                    case R.id.transformer_courage_seekbar:
                        courageText.setText(String.valueOf(progress));
                        break;
                    case R.id.transformer_firepower_seekbar:
                        firepowerText.setText(String.valueOf(progress));
                        break;
                    case R.id.transformer_skill_seekbar:
                        skillText.setText(String.valueOf(progress));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        };

        SeekBar strengthSeekbar = view.findViewById(R.id.transformer_strength_seekbar);
        strengthSeekbar.setOnSeekBarChangeListener(seekBarChangeListener);
        SeekBar intelligenceSeekbar = view.findViewById(R.id.transformer_intelligence_seekbar);
        intelligenceSeekbar.setOnSeekBarChangeListener(seekBarChangeListener);
        SeekBar speedSeekbar =  view.findViewById(R.id.transformer_speed_seekbar);
        speedSeekbar.setOnSeekBarChangeListener(seekBarChangeListener);
        SeekBar enduranceSeekbar =  view.findViewById(R.id.transformer_endurance_seekbar);
        enduranceSeekbar.setOnSeekBarChangeListener(seekBarChangeListener);
        SeekBar rankSeekbar =  view.findViewById(R.id.transformer_rank_seekbar);
        rankSeekbar.setOnSeekBarChangeListener(seekBarChangeListener);
        SeekBar courageSeekbar =  view.findViewById(R.id.transformer_courage_seekbar);
        courageSeekbar.setOnSeekBarChangeListener(seekBarChangeListener);
        SeekBar firepowerSeekbar =  view.findViewById(R.id.transformer_firepower_seekbar);
        firepowerSeekbar.setOnSeekBarChangeListener(seekBarChangeListener);
        SeekBar skillSeekbar =  view.findViewById(R.id.transformer_skill_seekbar);
        skillSeekbar.setOnSeekBarChangeListener(seekBarChangeListener);

        return view;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return dialog;
    }

}
