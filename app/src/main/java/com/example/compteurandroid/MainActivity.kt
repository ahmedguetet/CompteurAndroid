package com.example.compteurandroid

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

/**
 * Activité principale de l'application Compteur.
 *
 * L'écran affiche un titre, la valeur courante d'un compteur et plusieurs
 * boutons permettant de l'incrémenter, de le décrémenter et de le remettre à zéro.
 */
class MainActivity : AppCompatActivity() {

    // 5. Déclaration de la variable entière compteur, initialisée à 0.
    private var compteur = 0

    // Clé utilisée pour conserver la valeur lors de la rotation de l'écran (amélioration).
    private val CLE_COMPTEUR = "cle_compteur"

    // Références vers les composants graphiques.
    private lateinit var textViewCompteur: TextView
    private lateinit var buttonIncrementer: Button
    private lateinit var buttonDecrementer: Button
    private lateinit var buttonReinitialiser: Button
    private lateinit var buttonIncrementer5: Button
    private lateinit var buttonDecrementer5: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Amélioration : restaurer la valeur du compteur après une rotation d'écran.
        if (savedInstanceState != null) {
            compteur = savedInstanceState.getInt(CLE_COMPTEUR, 0)
        }

        // 6. Récupération des composants graphiques à partir de leurs identifiants.
        textViewCompteur = findViewById(R.id.textViewCompteur)
        buttonIncrementer = findViewById(R.id.buttonIncrementer)
        buttonDecrementer = findViewById(R.id.buttonDecrementer)
        buttonReinitialiser = findViewById(R.id.buttonReinitialiser)
        buttonIncrementer5 = findViewById(R.id.buttonIncrementer5)
        buttonDecrementer5 = findViewById(R.id.buttonDecrementer5)

        // 7. Programmation du clic sur chaque bouton.
        buttonIncrementer.setOnClickListener {
            compteur++
            actualiserAffichage()
        }

        buttonDecrementer.setOnClickListener {
            compteur--
            actualiserAffichage()
        }

        buttonIncrementer5.setOnClickListener {
            compteur += 5
            actualiserAffichage()
        }

        buttonDecrementer5.setOnClickListener {
            compteur -= 5
            actualiserAffichage()
        }

        buttonReinitialiser.setOnClickListener {
            compteur = 0
            actualiserAffichage()
            // Amélioration : afficher un message Toast lors de la réinitialisation.
            Toast.makeText(this, getString(R.string.message_reinitialisation), Toast.LENGTH_SHORT).show()
        }

        // Affichage initial (le compteur vaut 0 au démarrage).
        actualiserAffichage()
    }

    /**
     * 8. Actualise le TextView après chaque modification de la variable compteur.
     * Amélioration : la couleur du texte dépend du signe de la valeur
     * (vert si positif, rouge si négatif, noir si nul).
     */
    private fun actualiserAffichage() {
        textViewCompteur.text = compteur.toString()

        val couleur = when {
            compteur > 0 -> ContextCompat.getColor(this, R.color.vert)
            compteur < 0 -> ContextCompat.getColor(this, R.color.rouge)
            else -> ContextCompat.getColor(this, R.color.noir)
        }
        textViewCompteur.setTextColor(couleur)
    }

    /**
     * Amélioration : conserver la valeur du compteur après la rotation de l'écran.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CLE_COMPTEUR, compteur)
    }
}
