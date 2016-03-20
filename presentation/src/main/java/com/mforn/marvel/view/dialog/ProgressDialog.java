package com.mforn.marvel.view.dialog;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;

import com.mforn.marvel.R;

/**
 * Progress Dialog to show when background task is done and UI feedback is required.
 */
public class ProgressDialog extends AlertDialog {
    private Context context;


    public ProgressDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.dialog_progress, null);

        setCancelable(false);
        setView(view);

        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        super.onCreate(savedInstanceState);
    }
}
