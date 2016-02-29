package rahulramkumar.spanishsubjunctiveconjugator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
	public void formSubjunctive(View view)
	{
		EditText inputInfinitive = (EditText) findViewById(R.id.edittxtInputInfinitive);
		TextView formedVerb = (TextView) findViewById(R.id.txtviewFormedVerb);

		formedVerb.setText(inputInfinitive.getText().toString()); //Display what is in EditText
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
}
