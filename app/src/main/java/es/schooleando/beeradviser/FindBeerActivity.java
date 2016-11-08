package es.schooleando.beeradviser;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class FindBeerActivity extends AppCompatActivity {
    private BeerExpert expert = new BeerExpert();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_beer);
    }

    public void onClickFindBeer(View view) throws ExecutionException, InterruptedException {

        TextView brands = (TextView) findViewById(R.id.brands);
        Spinner color = (Spinner) findViewById(R.id.color);

        // Simulamos una tarea larga (acceso a la red, cálculo, base de datos) y forzamos un ANR.
        // Nunca debemos hacer esto en el UI Thread!
        String beerType = String.valueOf(color.getSelectedItem());
        FindBeerAsyncTask task = new FindBeerAsyncTask();
        task.execute(14000);
        task.get();
        /*
        SystemClock.sleep(14000);
        */
        List<String> brandList = expert.getBrands(beerType);
        StringBuilder brandsFormatted = new StringBuilder();
        for (String brand: brandList) {
            brandsFormatted.append(brand).append("\n");
        }

        brands.setText(brandsFormatted);
    }

}
