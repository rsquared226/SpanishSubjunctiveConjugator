package rahulramkumar.spanishsubjunctiveconjugator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
	public void formSubjunctive(View view)
	{
		EditText editTextInfinitive = (EditText) findViewById(R.id.edittxtInputInfinitive);
		TextView formedVerb = (TextView) findViewById(R.id.txtviewFormedVerb);
		Spinner spinnerSubject = (Spinner) findViewById(R.id.spinSubject);

		String verb = editTextInfinitive.getText().toString();
		String subject = spinnerSubject.getSelectedItem().toString();
		String ending;

		formedVerb.setText("");

		try
		{
			ending = verb.substring(verb.length() - 2);
		}
		catch (StringIndexOutOfBoundsException ex)
		{
			Toast.makeText(getApplicationContext(), R.string.no_verb_found, Toast.LENGTH_SHORT).show();
			return;
		}

		int lastIndex;
		switch (ending)
		{
			case "ar":
				lastIndex = verb.lastIndexOf("ar");
				switch (subject)
				{
					case "yo":
						verb = (new StringBuilder(verb).replace(lastIndex, lastIndex + 2, "e")).toString();
						break;
					case "tú":
						verb = (new StringBuilder(verb).replace(lastIndex, lastIndex + 2, "es")).toString();
						break;
					case "él/ella/ud.":
						verb = (new StringBuilder(verb).replace(lastIndex, lastIndex + 2, "e")).toString();
						break;
					case "nosotros":
						verb = (new StringBuilder(verb).replace(lastIndex, lastIndex + 2, "emos")).toString();
						break;
					case "vosotros":
						verb = (new StringBuilder(verb).replace(lastIndex, lastIndex + 2, "éis")).toString();
						break;
					case "ellos/ellas/uds.":
						verb = (new StringBuilder(verb).replace(lastIndex, verb.lastIndexOf("ar") + 2, "en")).toString();
				}
				break;

			case "ir":
			case "er":
				lastIndex = verb.lastIndexOf((ending.equals("er") ? "er" : "ir"));
				switch (subject)
				{
					case "yo":
						verb = (new StringBuilder(verb).replace(lastIndex, lastIndex + 2, "a")).toString();
						break;
					case "tú":
						verb = (new StringBuilder(verb).replace(lastIndex, lastIndex + 2, "as")).toString();
						break;
					case "él/ella/ud.":
						verb = (new StringBuilder(verb).replace(lastIndex, lastIndex + 2, "a")).toString();
						break;
					case "nosotros":
						verb = (new StringBuilder(verb).replace(lastIndex, lastIndex + 2, "amos")).toString();
						break;
					case "vosotros":
						verb = (new StringBuilder(verb).replace(lastIndex, lastIndex + 2, "áis")).toString();
						break;
					case "ellos/ellas/uds.":
						verb = (new StringBuilder(verb).replace(lastIndex, lastIndex + 2, "an")).toString();
				}
				break;

			default:
				Toast.makeText(getApplicationContext(), R.string.not_verb, Toast.LENGTH_SHORT).show();
				return;
		}

		formedVerb.setText(verb);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		addItemsToSpinner();
	}

	private void addItemsToSpinner()
	{
		Spinner spinner = (Spinner) findViewById(R.id.spinSubject);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
				R.array.subject_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
	}
}
