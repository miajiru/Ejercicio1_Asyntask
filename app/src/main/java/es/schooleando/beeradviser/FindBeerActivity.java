package es.schooleando.beeradviser;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FindBeerActivity extends AppCompatActivity {
    
    private BeerExpert expert = new BeerExpert();
    private TextView brands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }

    public void onClickFindBeer(View view) throws ExecutionException, InterruptedException {

        FindBeerAsyncTask task = new FindBeerAsyncTask();//Clase asincrona
        brands = (TextView) findViewById(R.id.brands);
        Spinner color = (Spinner) findViewById(R.id.color);

        // Simulamos una tarea larga (acceso a la red, cálculo, base de datos) y forzamos un ANR.
        // Nunca debemos hacer esto en el UI Thread!
        String beerType = String.valueOf(color.getSelectedItem());
        task.execute("14000",beerType);

    }

        private class FindBeerAsyncTask extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground(String... params) {
                int tiempo = Integer.parseInt(params[0]);
                SystemClock.sleep(tiempo);
                return params[1];
            }

            @Override
            protected void onPostExecute(String seleccionado) {
                List<String> brandList = expert.getBrands(seleccionado);
                StringBuilder brandsFormatted = new StringBuilder();
                for (String brand : brandList) {
                    brandsFormatted.append(brand).append("\n");
                }

                brands.setText(brandsFormatted);
            }
        }

}
