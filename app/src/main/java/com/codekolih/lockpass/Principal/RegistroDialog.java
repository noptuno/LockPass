package com.codekolih.lockpass.Principal;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;

import com.codekolih.lockpass.R;

public class RegistroDialog {

    public RegistroDialog(Context contexto)

    {

        final Dialog dialog = new Dialog(contexto);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.activity_registrar_cuenta);
        setDialogWidthAndHeight(dialog,contexto);
        dialog.show();



        final ImageButton guardar = dialog.findViewById(R.id.imgguardar);
        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });




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
