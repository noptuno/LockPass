package com.codekolih.lockpass.Principal;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.codekolih.lockpass.Fragments.CategoriaFragment;
import com.codekolih.lockpass.Fragments.FavoritosFragment;
import com.codekolih.lockpass.Fragments.ListasFragment;
import com.codekolih.lockpass.R;
import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MenuPrincipal extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private MeowBottomNavigation bnv_Main;
    private FloatingActionButton floatingActionButton;
    Context contexto;
    private ListasFragment fragmentlista;
    ListasFragment listFragment = new ListasFragment();
    CategoriaFragment favoriFragment = new CategoriaFragment();
    FavoritosFragment categoriFragment = new FavoritosFragment();
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        contexto = this;

        floatingActionButton = findViewById(R.id.floatingActionButton);

        bnv_Main = findViewById(R.id.Navigation_Bar);
        bnv_Main.add(new MeowBottomNavigation.Model(1,R.drawable.ic_favoritos));
        bnv_Main.add(new MeowBottomNavigation.Model(2,R.drawable.ic_listas));
        bnv_Main.add(new MeowBottomNavigation.Model(3,R.drawable.ic_categorias));

        bnv_Main.show(2,true);
       // bnv_Main.setCount(1,"15");

        replace(listFragment);

        bnv_Main.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()){
                    case 1:
                        replace(favoriFragment);

                        listFragment.onFilterFuncionFragment();

                        break;

                    case 2:
                        replace(listFragment);
                        listFragment.onFilterFuncionFragment();

                            // This method does not exist

                        break;

                    case 3:
                        replace(categoriFragment);
                        listFragment.onFilterFuncionFragment();

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

                // bnv_Main.setCount(1,"15");

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
    protected void onStart() {
        super.onStart();

        listFragment.onFilterFuncionFragment();

    }

    @Override
    public void finishActivity(int requestCode) {
        super.finishActivity(requestCode);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.action_search:

                searchView = (SearchView) item.getActionView();
                searchView.setOnQueryTextListener(this);


                item.setOnActionExpandListener(new MenuItem.OnActionExpandListener() {
                    @Override
                    public boolean onMenuItemActionCollapse(MenuItem item) {
                        // Do something when collapsed
                       replace(listFragment);
                       bnv_Main.show(2,true);
                        listFragment.onFilterFuncionFragment();

                        return true; // Return true to collapse action view
                    }

                    @Override
                    public boolean onMenuItemActionExpand(MenuItem item) {
                        replace(listFragment);
                        bnv_Main.show(2,true);
                        return true; // Return true to expand action view
                    }
                });

                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_buscador, menu);


        return true;
    }



//averiguar que es esto SuppresLint

    @Override
    public boolean onQueryTextSubmit(String query) {


        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        listFragment.onFilterFuncionFragment(newText);
        return false;
    }
    //



    private void replace(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Frame_Container,fragment);
        transaction.commit();

    }



}