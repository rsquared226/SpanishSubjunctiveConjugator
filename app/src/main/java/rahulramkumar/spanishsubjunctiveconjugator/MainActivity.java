package rahulramkumar.spanishsubjunctiveconjugator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
{
	class forms
	{
		String yo;
		String tu;
		String el;
		String nosotros;
		String vosotros;
		String ellos;

		public forms(String yo, String tu, String el, String nosotros, String vosotros, String ellos)
		{
			this.yo = yo;
			this.tu = tu;
			this.el = el;
			this.nosotros = nosotros;
			this.vosotros = vosotros;
			this.ellos = ellos;
		}

		public forms(String yo, String nosotros)
		{
			this.yo = yo;
			this.tu = yo;
			this.el = yo;
			this.nosotros = nosotros;
			this.vosotros = nosotros;
			this.ellos = yo;
		}

		public forms(String yo)
		{
			this.yo = yo;
			this.tu = yo;
			this.el = yo;
			this.nosotros = yo;
			this.vosotros = yo;
			this.ellos = yo;
		}
	}

	HashMap<String, forms> subjunctiveIrregulars;
	HashMap<String, String> presentIrregulars;
	HashMap<String, forms> shoe;

	public void formSubjunctive(View view)
	{
		EditText editTextInfinitive = (EditText) findViewById(R.id.edittxtInputInfinitive);
		TextView formedVerb = (TextView) findViewById(R.id.txtviewFormedVerb);
		Spinner spinnerSubject = (Spinner) findViewById(R.id.spinSubject);

		String verb = editTextInfinitive.getText().toString();
		String subject = spinnerSubject.getSelectedItem().toString();
		String ending;

		formedVerb.setText("");

		if (subjunctiveIrregulars.containsKey(verb))
		{
			switch (subject)
			{
				case "yo":
					verb = subjunctiveIrregulars.get(verb).yo;
					break;
				case "tú":
					verb = subjunctiveIrregulars.get(verb).tu;
					break;
				case "él/ella/ud.":
					verb = subjunctiveIrregulars.get(verb).el;
					break;
				case "nosotros":
					verb = subjunctiveIrregulars.get(verb).nosotros;
					break;
				case "vosotros":
					verb = subjunctiveIrregulars.get(verb).vosotros;
					break;
				case "ellos/ellas/uds.":
					verb = subjunctiveIrregulars.get(verb).ellos;
					break;

				default:
					Toast.makeText(getApplicationContext(), R.string.subjunctive_error_subject, Toast.LENGTH_LONG).show();
					return;
			}
		}
		else
		{
			if (presentIrregulars.containsKey(verb))
			{
				verb = presentIrregulars.get(verb);
			}

			if (shoe.containsKey(verb))
			{
				if (subject.equals("yo") || subject.equals("tú") || subject.equals("él/ella/ud.") || subject.equals("ellos/ellas/uds."))
				{
					verb = shoe.get(verb).yo;
				}
				else
				{
					verb = shoe.get(verb).nosotros;
				}
			}

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
		}
		formedVerb.setText(verb);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		addItemsToSpinner();
		initializeIrregulars();

		Toast.makeText(getApplicationContext(), R.string.initialize_done, Toast.LENGTH_SHORT).show();
	}

	private void initializeIrregulars()
	{
		subjunctiveIrregulars = new HashMap<>();
		presentIrregulars = new HashMap<>();
		shoe = new HashMap<>();

		subjunctiveIrregulars.put("dar", new forms("dé", "des", "dé", "demos", "deis", "den"));
		subjunctiveIrregulars.put("estar", new forms("esté", "estés", "esté", "estemos", "estéis", "estén"));
		subjunctiveIrregulars.put("haber", new forms("haya", "hayas", "haya", "hayamos", "hayáis", "hayan"));
		subjunctiveIrregulars.put("ir", new forms("vaya", "vayas", "vaya", "vayamos", "vayáis", "vayan"));
		subjunctiveIrregulars.put("saber", new forms("sepa", "sepas", "sepa", "sepamos", "sepáis", "sepan"));
		subjunctiveIrregulars.put("ser", new forms("sea", "seas", "sea", "seamos", "seáis", "sean"));

		presentIrregulars.put("caer", "caiger");
		presentIrregulars.put("hacer", "hager");
		presentIrregulars.put("poner", "ponger");
		presentIrregulars.put("salir", "salgir");
		presentIrregulars.put("traer", "traiger");
		presentIrregulars.put("valer", "valger");
		presentIrregulars.put("decir", "digir");
		presentIrregulars.put("oír", "oigir");
		presentIrregulars.put("tener", "tenger");
		presentIrregulars.put("venir", "vengir");
		presentIrregulars.put("caber", "queper");
		presentIrregulars.put("ver", "veer");

		presentIrregulars.put("agradecer", "agradezcer");
		presentIrregulars.put("aparecer", "aparezcer");
		presentIrregulars.put("conducir", "conduzcir");
		presentIrregulars.put("conocer", "conozcer");
		presentIrregulars.put("crecer", "crezcer");
		presentIrregulars.put("desaparecer", "desaparezcer");
		presentIrregulars.put("establecer", "establezcer");
		presentIrregulars.put("merecer", "merezcer");
		presentIrregulars.put("ofrecer", "ofrezcer");
		presentIrregulars.put("parecer", "parezcer");
		presentIrregulars.put("permanecer", "permanezcer");
		presentIrregulars.put("pertenecer", "pertenezcer");
		presentIrregulars.put("producir", "produzcir");
		presentIrregulars.put("reconocer", "reconozcer");
		presentIrregulars.put("reducir", "reduzcir");
		presentIrregulars.put("traducir", "traduzcir");
		presentIrregulars.put("obedecer", "obedezcer");

		presentIrregulars.put("convencer", "convenzer");
		presentIrregulars.put("ejercer", "ejerzer");
		presentIrregulars.put("torcer", "tuerzer");
		presentIrregulars.put("vencer", "venzo");
		presentIrregulars.put("cocer", "cuezer");

		presentIrregulars.put("coger", "cojer");
		presentIrregulars.put("corregir", "corrijir");
		presentIrregulars.put("elegir", "elijir");
		presentIrregulars.put("escoger", "escojer");
		presentIrregulars.put("exigir", "exijir");
		presentIrregulars.put("fingir", "finjir");
		presentIrregulars.put("proteger", "protejer");
		presentIrregulars.put("recoger", "recojer");

		presentIrregulars.put("distinguir", "distingir");
		presentIrregulars.put("conseguir", "consigir");
		presentIrregulars.put("extinguir", "extingir");
		presentIrregulars.put("perseguir", "persigir");
		presentIrregulars.put("proseguir", "prosigir");
		presentIrregulars.put("seguir", "sigir");

		presentIrregulars.put("atribuir", "atribuyir");
		presentIrregulars.put("concluir", "concluyir");
		presentIrregulars.put("construir", "construyir");
		presentIrregulars.put("contribuir", "contribuyo");
		presentIrregulars.put("destruir", "destruyir");
		presentIrregulars.put("distribuir", "distribuyir");
		presentIrregulars.put("huir", "huyir");
		presentIrregulars.put("incluir", "incluyir");
		presentIrregulars.put("influir", "influyir");
		presentIrregulars.put("sustituir", "sustituyir");

		presentIrregulars.put("confiar", "confíar");
		presentIrregulars.put("enviar", "envíar");
		presentIrregulars.put("espiar", "espíar");
		presentIrregulars.put("esquiar", "esquíar");
		presentIrregulars.put("fiar", "fíar");
		presentIrregulars.put("guiar", "guíar");
		presentIrregulars.put("resfriar", "resfríar");

		presentIrregulars.put("actuar", "actúar");
		presentIrregulars.put("continuar", "continúar");
		presentIrregulars.put("graduar", "gradúar");

		shoe.put("atravesar", new forms("atraviesar", "atravesar"));
		shoe.put("cerrar", new forms("cierrar", "cerrar"));
		shoe.put("comenzar", new forms("comienzar", "comenzar"));
		shoe.put("confesar", new forms("confiesar", "confesar"));
		shoe.put("defender", new forms("defiender", "defender"));
		shoe.put("despertar", new forms("despiertar", "despertar"));
		shoe.put("empezar", new forms("enciender", "encender"));
		shoe.put("encender", new forms("enciender", "encender"));
		shoe.put("encerrar", new forms("encierrar", "encerrar"));
		shoe.put("entender", new forms("entiender", "entender"));
		shoe.put("merendar", new forms("meriendar", "merendar"));
		shoe.put("nevar", new forms("nievar", "nevar"));
		shoe.put("pensar", new forms("piensar", "pensar"));
		shoe.put("perder", new forms("pierder", "perder"));
		shoe.put("quebar", new forms("quiebar", "quebar"));
		shoe.put("querer", new forms("quierer", "querer"));
		shoe.put("recomendar", new forms("recomiendar", "recomendar"));
		shoe.put("sentar", new forms("sientar", "sentar"));
		shoe.put("acordar", new forms("acuerdar", "acordar"));
		shoe.put("acostar", new forms("acuestar", "acostar"));
		shoe.put("almorzar", new forms("almuerzar", "almorzar"));
		shoe.put("contar", new forms("cuentar", "contar"));
		shoe.put("costar", new forms("cuestar", "costar"));
		shoe.put("demostrar", new forms("demuestrar", "demostrar"));
		shoe.put("devolver", new forms("devuelver", "devolver"));
		shoe.put("doler", new forms("dueler", "doler"));
		shoe.put("encontrar", new forms("encuentrar", "encontrar"));
		shoe.put("envolver", new forms("envuelver", "envolver"));
		shoe.put("jugar", new forms("juegar", "jugar"));
		shoe.put("llover", new forms("lluever", "llover"));
		shoe.put("mostrar", new forms("muestrar", "mostrar"));
		shoe.put("oler", new forms("hueler", "oler"));
		shoe.put("poder", new forms("pueder", "poder"));
		shoe.put("probar", new forms("pruebar", "probar"));
		shoe.put("recordar", new forms("recuerdar", "recordar"));
		shoe.put("resolver", new forms("resuelver", "resolver"));
		shoe.put("revolver", new forms("revuelver", "revolver"));
		shoe.put("rogar", new forms("ruegar", "rogar"));
		shoe.put("soler", new forms("sueler", "soler"));
		shoe.put("sonar", new forms("suenar", "sonar"));
		shoe.put("tronar", new forms("truenar", "tronar"));
		shoe.put("volar", new forms("vuelar", "volar"));
		shoe.put("volver", new forms("vuelver", "volver"));

		shoe.put("advertir", new forms("adviertir", "advirtir"));
		shoe.put("convertir", new forms("conviertir", "convirtir"));
		shoe.put("divertir", new forms("diviertir", "divirtir"));
		shoe.put("hervir", new forms("hiervir", "hirvir"));
		shoe.put("mentir", new forms("mientir", "mintir"));
		shoe.put("preferir", new forms("prefierir", "prefirir"));
		shoe.put("referir", new forms("refierir", "refirir"));
		shoe.put("sentir", new forms("sientir", "sintir"));
		shoe.put("sugerir", new forms("sugierir", "sugirir"));

		shoe.put("dormir", new forms("duermir","durmir"));
		shoe.put("morir", new forms("muerir", "murir"));

		shoe.put("despedir", new forms("despidir"));
		shoe.put("reñir", new forms("riñir"));
		shoe.put("freír", new forms("fríir"));
		shoe.put("gemir", new forms("gimir"));
		shoe.put("repetir", new forms("repitir"));
		shoe.put("impedir", new forms("impidir"));
		shoe.put("medir", new forms("midir"));
		shoe.put("pedir", new forms("pidir"));
		shoe.put("reír", new forms("ríir"));
		shoe.put("servir", new forms("sirvir"));
		shoe.put("sonreír", new forms("sonríir"));
		shoe.put("vestir", new forms("vistir"));
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
