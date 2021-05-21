package com.martinmei.kiroshihealth.activities;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.MenuItem;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.material.snackbar.Snackbar;
import com.martinmei.kiroshihealth.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    protected void showMessage(String message){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.setAction(getString(R.string.tick_message), v -> { snackbar.dismiss(); });
        snackbar.show();
    }
    protected void showErrorMessage(String message){
        Snackbar snackbar = Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_LONG);
        snackbar.setAction(getString(R.string.error_message), v -> { snackbar.dismiss(); });
        snackbar.show();
    }

}
