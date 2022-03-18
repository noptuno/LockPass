package com.codekolih.lockpass.Principal;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import com.codekolih.lockpass.R;

public class RegistroDialog extends DialogFragment {

    final Dialog dialog;
    final ImageButton guardar;
Toolbar tolbar;


    public RegistroDialog(Context contexto){

        dialog = new Dialog(contexto);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_registro_cuentas);
        setDialogWidthAndHeight(dialog,contexto);
        tolbar = dialog.findViewById(R.id.toolbar_registro);
        tolbar.inflateMenu(R.menu.menu_registro);
        tolbar.setTitle(null);

        tolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.action_salir:

                        dialog.dismiss();

                        break;

                }
                return true;
            }
        });


        guardar = dialog.findViewById(R.id.imgguardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        dialog.show();
    }

    public void setDialogWidthAndHeight(Dialog dialog,Context contexto){

        Window window = dialog.getWindow();
        window.setWindowAnimations(R.style.BottomDialogAnimation);

        /*
        WindowManager wm = (WindowManager) contexto.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.gravity = Gravity.RIGHT;
        window.setAttributes(layoutParams);
        */

    }


}
