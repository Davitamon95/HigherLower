package com.example.higherlower

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.higherlower.databinding.ActivityHigherLowerBinding

class HigherLowerActivity : AppCompatActivity() {

    private var currentThrow: Int = 1
    private var lastThrow: Int = 1
    private lateinit var binding: ActivityHigherLowerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHigherLowerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViews()
    }

    /**
     *  methode voor initialiseren van de start-layout
     *
     *  definiert de methodes voor setOnClickListener bij de 3 buttons
     */
    private fun initViews() {
        binding.btnHigher.setOnClickListener { onHigherClick() }
        binding.btnLower.setOnClickListener { onLowerClick() }
        binding.btnEqual.setOnClickListener { onEqualClick() }
        updateUI()
    }
    /**
     * Update methode om de dobbelsteen bij elke worp mee te updaten
     */
    private fun updateUI() {
        binding.tvLastThrow.text = getString(R.string.last_throw, lastThrow)

        when (currentThrow) {
            1 -> binding.Dice.setImageResource(R.drawable.dice1)
            2 -> binding.Dice.setImageResource(R.drawable.dice2)
            3 -> binding.Dice.setImageResource(R.drawable.dice3)
            4 -> binding.Dice.setImageResource(R.drawable.dice4)
            5 -> binding.Dice.setImageResource(R.drawable.dice5)
            6 -> binding.Dice.setImageResource(R.drawable.dice6)
        }
    }


    /**
     * methode om de dobbelsteen elke worp te laten veranderen en om de oude worp waarde te vernieuwen
     */
    private fun rollDice() {
        lastThrow = currentThrow
        currentThrow = (1..6).random()
        updateUI()
    }

    /**
     * 3 methodes om bij een higher, lower en equal buttonclick de rollDice methode uit te voeren
     * en daarvan te bepalen of de gebruiker op de juiste heeft geklikt
     */
    private fun onHigherClick() {
        rollDice()

        if (currentThrow > lastThrow) onAnswerCorrect()
        else onAnswerIncorrect()
    }

    private fun onLowerClick() {
        rollDice()

        if (currentThrow < lastThrow) onAnswerCorrect()
        else onAnswerIncorrect()
    }

    private fun onEqualClick() {
        rollDice()

        if (currentThrow == lastThrow) onAnswerCorrect()
        else onAnswerIncorrect()
    }

    /**
     * Methodes om bij een goed of fout antwoord de juiste toastmessage te laten zien
     */
    private fun onAnswerCorrect() {
        Toast.makeText(this, getString(R.string.correct), Toast.LENGTH_SHORT).show()
    }

    private fun onAnswerIncorrect() {
        Toast.makeText(this, getString(R.string.incorrect), Toast.LENGTH_SHORT).show()
    }
}