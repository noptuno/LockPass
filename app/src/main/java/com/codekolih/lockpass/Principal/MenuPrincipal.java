package com.codekolih.lockpass.Principal;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.codekolih.lockpass.Fragments.CategoriaFragment;
import com.codekolih.lockpass.Fragments.FavoritosFragment;
import com.codekolih.lockpass.Fragments.ListasFragment;
import com.codekolih.lockpass.R;
import com.codekolih.lockpass.adapters.AdapterCategorias;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MenuPrincipal extends AppCompatActivity  implements SearchView.OnQueryTextListener  {

    private MeowBottomNavigation bnv_Main;
    private FloatingActionButton floatingActionButton;

    Context contexto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contexto = this;

        floatingActionButton = findViewById(R.id.floatingActionButton);

        bnv_Main = findViewById(R.id.Navigation_Bar);
        bnv_Main.add(new MeowBottomNavigation.Model(1,R.drawable.ic_favoritos));
        bnv_Main.add(new MeowBottomNavigation.Model(2,R.drawable.ic_categorias));
        bnv_Main.add(new MeowBottomNavigation.Model(3,R.drawable.ic_listas));

        bnv_Main.show(2,true);
        bnv_Main.setCount(1,"15");

        replace(new CategoriaFragment());


        bnv_Main.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(new FavoritosFragment());
                        break;

                    case 2:
                        replace(new CategoriaFragment());

                        break;

                    case 3:
                        replace(new ListasFragment());
                        break;

                }
                return null;
            }
        });


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MenuPrincipal.this, RegistroCuentas.class);
                startActivity(intent);
               // overridePendingTransition(R.anim.dialog_in, R.anim.dialog_out);

         //   new RegistroDialog(contexto);
/*
                Bundle args = new Bundle();
                args.putString("title", "Dialog with Action Bar");
                RegistroDialog actionbarDialog = new RegistroDialog();
                actionbarDialog.setArguments(args);
                actionbarDialog.show(getSupportFragmentManager(),
                        "action_bar_frag");
*/
            }
        });



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_buscador, menu);
        return true;
    }


//averiguar que es esto SuppresLint
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:

                item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed

                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        // Do something when expanded
                        return true; // Return true to expand action view
                    }
                });

                break;

        }
        return super.onOptionsItemSelected(item);
    }





    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Frame_Container,fragment);
        transaction.commit();

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

      //  onFilterFuncionFragment(query);



        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}