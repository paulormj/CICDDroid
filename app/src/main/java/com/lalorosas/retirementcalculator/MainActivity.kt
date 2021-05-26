package com.lalorosas.retirementcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCenter.start(application, "d7672144-5299-4b9d-abbe-c55e69a90456", Analytics::class.java, Crashes::class.java);

        calculateButton.setOnClickListener {
            //throw Exception("Algo deu errado");
            //Crashes.generateTestCrash();
         try {
             val taxaDeJuros = interestEditText.text.toString().toFloat();
             val idadeAtual = ageEditText.text.toString().toInt();
             val idadeAposentar = retirementEditText.text.toString().toInt();
             val aplicacaoMensal = monthlySavingsEditText.text.toString().toFloat()
             val aplicacaoAtual = currentEditText.text.toString().toFloat();

             val properties: HashMap<String, String> = HashMap<String, String>()
             properties.put("taxaDeJuros", taxaDeJuros.toString())
             properties.put("idadeAtual", idadeAtual.toString())
             properties.put("idadeAposentar", idadeAposentar.toString())
             properties.put("aplicacaoMensal", aplicacaoMensal.toString())
             properties.put("aplicacaoAtual", aplicacaoAtual.toString())

             if (taxaDeJuros <= 0) {
                 Analytics.trackEvent("taxa_de_juros_errada",properties);
             }
             if (idadeAposentar <= idadeAtual) {
                 Analytics.trackEvent("idade_errada",properties);
             }
             resultTextView.text = "At the current rate of  $taxaDeJuros, saving \$$aplicacaoMensal a month"

         }catch (ex: Exception){
             Analytics.trackEvent(ex.message);
         }
      }
    }
}
