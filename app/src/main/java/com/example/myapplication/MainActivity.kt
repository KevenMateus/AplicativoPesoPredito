package com.example.myapplication

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var heightInput: EditText
    private lateinit var resultsTable: TableLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        heightInput = findViewById(R.id.height_input)
        resultsTable = findViewById(R.id.results_table)
    }

    fun calculateWeight(view: View) {
        val heightText = heightInput.text.toString()

        if (heightText.isNotEmpty()) {
            val heightCm = heightText.toFloat()
            val weightMale = 50 + 0.91 * (heightCm - 152.4)
            val weightFemale = 45.5 + 0.91 * (heightCm - 152.4)

            val decimalFormat = DecimalFormat("0.#")

            // Limpar a tabela de resultados antes de adicionar novos dados
            resultsTable.removeAllViews()

            // Tabela para Homens
            val tableMale = createTable("Homens")
            addRowToTable(tableMale, "Peso Ideal:", decimalFormat.format(weightMale) + ("kg"))
            addRowToTable(tableMale, "4 ml/kg:", decimalFormat.format(weightMale * 4)+ ("ml"))
            addRowToTable(tableMale, "5 ml/kg:", decimalFormat.format(weightMale * 5)+ ("ml"))
            addRowToTable(tableMale, "6 ml/kg:", decimalFormat.format(weightMale * 6)+ ("ml"))

            // Tabela para Mulheres
            val tableFemale = createTable("Mulheres")
            addRowToTable(tableFemale, "Peso Ideal:", decimalFormat.format(weightFemale)+ ("kg"))
            addRowToTable(tableFemale, "4 ml/kg:", decimalFormat.format(weightFemale * 4)+ ("ml"))
            addRowToTable(tableFemale, "5 ml/kg:", decimalFormat.format(weightFemale * 5)+ ("ml"))
            addRowToTable(tableFemale, "6 ml/kg:", decimalFormat.format(weightFemale * 6)+ ("ml"))

            // Adicionar as tabelas à tabela de resultados
            resultsTable.addView(tableMale)
            resultsTable.addView(createSpaceRow())
            resultsTable.addView(tableFemale)

            // Tornar a tabela de resultados visível
            resultsTable.visibility = View.VISIBLE
        }
    }

    private fun createTable(title: String): TableLayout {
        val table = TableLayout(this)
        table.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        table.isShrinkAllColumns = true
        table.isStretchAllColumns = true

        val titleRow = TableRow(this)
        titleRow.addView(createTableCell(title, true))

        table.addView(titleRow)

        return table
    }

    private fun createTableCell(text: String, isTitle: Boolean = false): TextView {
        val cell = TextView(this)
        val params = TableRow.LayoutParams(
            TableRow.LayoutParams.WRAP_CONTENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(8, 8, 8, 8)
        cell.layoutParams = params
        cell.text = text
        cell.setTextColor(ContextCompat.getColor(this, if (isTitle) R.color.black else R.color.black))
        cell.gravity = Gravity.CENTER
        cell.setBackgroundResource(if (isTitle) R.drawable.table_row_background else R.drawable.table_cell_background)
        return cell
    }

    private fun addRowToTable(table: TableLayout, label: String, value: String) {
        val row = TableRow(this)
        row.addView(createTableCell(label))
        row.addView(createTableCell(value))
        table.addView(row)
    }

    private fun createSpaceRow(): TableRow {
        val row = TableRow(this)
        row.layoutParams = TableRow.LayoutParams(
            TableRow.LayoutParams.MATCH_PARENT,
            TableRow.LayoutParams.WRAP_CONTENT
        )
        row.addView(createTableCell("", true))
        return row
    }
}
