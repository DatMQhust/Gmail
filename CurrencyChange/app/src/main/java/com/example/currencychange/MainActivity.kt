package com.example.currencychange

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var isSourceSp1: Boolean = true
    private var isSourceSp2: Boolean = false
    lateinit var ed1: EditText
    lateinit var ed2: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val usd = MoneyModel("United States - Dollar", 25400.00)
        val vnd = MoneyModel("Viet Nam - VND", 1.0)
        val euro = MoneyModel("Europe - Euro", 27476.00)
        val won = MoneyModel("Korea - Won", 18.0)
        val yen = MoneyModel("Japan - Yen", 167.0)
        val rupee = MoneyModel("India - Rupee", 302.0)
        val kip = MoneyModel("Laos - Kip", 1.0)
        val ringgit = MoneyModel("Malaysia - Ringgit", 5840.0)
        val pound = MoneyModel("United Kingdom - Pound", 32921.0)
        val baht = MoneyModel("ThaiLands - Baths", 753.0)

        val items = arrayOf(usd, vnd, euro, won, yen, rupee, kip, ringgit, pound, baht)
        val arrayAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_dropdown_item_1line,
            items.map { it.name }
        )

        val sp1 = findViewById<Spinner>(R.id.sp1)
        val sp2 = findViewById<Spinner>(R.id.sp2)
        ed1 = findViewById(R.id.ed1)
        ed2 = findViewById(R.id.ed2)

        // Cài đặt adapter cho spinner
        sp1.adapter = arrayAdapter
        sp2.adapter = arrayAdapter

        // Biến lưu lại đối tượng MoneyModel đã chọn
        var sp1selected: MoneyModel = usd
        var sp2selected: MoneyModel = usd

        // Lắng nghe sự kiện chọn từ spinner
        sp1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                sp1selected = items[position]
                convertCurrency(sp1selected, sp2selected)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }
        sp2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                sp2selected = items[position]
                convertCurrency(sp1selected, sp2selected)
            }
            override fun onNothingSelected(parent: AdapterView<*>) {}
        }

        // Lắng nghe sự thay đổi của ed1
        ed1.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (isSourceSp1 && !isSourceSp2) convertCurrency(sp1selected, sp2selected)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Lắng nghe sự thay đổi của ed2
        ed2.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if (!isSourceSp1) convertCurrency(sp1selected, sp2selected)
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        // Thiết lập ed1 làm nguồn nhập
        ed1.setOnClickListener {
            isSourceSp1 = true
            convertCurrency(sp1selected,sp2selected)

        }

        // Thiết lập ed2 làm nguồn nhập
        ed2.setOnClickListener {
            isSourceSp1 = false
            convertCurrency(sp1selected,sp2selected)
        }
    }

    // Hàm chuyển đổi tiền tệ
    private fun convertCurrency(fromCurrency: MoneyModel, toCurrency: MoneyModel) {
        val input = if (isSourceSp1) ed1.text.toString().toDoubleOrNull() else ed2.text.toString().toDoubleOrNull()
        if (input != null) {
            val result = if (isSourceSp1) {
                (input * fromCurrency.rateVND) / toCurrency.rateVND
            } else {
                (input * toCurrency.rateVND) / fromCurrency.rateVND
            }
            if (isSourceSp1) {
                ed2.setText(String.format("%.2f", result))
            } else {
                ed1.setText(String.format("%.2f", result))
            }
        } else {
            if (isSourceSp1) {
                ed2.text.clear()
            } else {
                ed1.text.clear()
            }
        }
    }
}
