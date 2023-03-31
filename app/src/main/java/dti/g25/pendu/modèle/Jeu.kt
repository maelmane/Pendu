package dti.g25.pendu.modèle

class Jeu(listeDeMots : Array<String>) {
    var pointage : Int = 0
    var nbErreurs : Int = 0
    var lettresEssayées : CharArray = charArrayOf()
    var lettresÀRéactiver : CharArray = charArrayOf()
    var motÀDeviner : String
    lateinit var mots : Array<String>


    init {
        mots = listeDeMots
        if (mots.isEmpty()){
            throw IllegalArgumentException("La liste de mots est vide")
        }
        motÀDeviner = sélectionnerProchainMot()
    }


    /**
     * Sélectionne au hasard le prochain mot à deviner
     *
     * @return un mot sélectionné au hasard
     */
    fun sélectionnerProchainMot(): String {
        var numéroAléatoire = kotlin.random.Random.nextInt(mots.size)
        return mots[numéroAléatoire]
    }


    /**
     * vérifie si une lettre essayée se trouve dans le mot
     * à deviner et ajuste les propriétés pointage et nbErreurs
     *
     * @param lettre la lettre essayée
     *
     * @return vrai si et seulement si la lettre essayée se trouve dans le mot à deviner
     */
    fun essayerUneLettre(lettre : Char) : Boolean{
        //Ajouter la lettre essayée à la liste de lettres essayées
        lettresEssayées += lettre
        lettresÀRéactiver += lettre

        //Liste des lettres dans le mot à deviner
        var lettresMotÀDeviner : CharArray = motÀDeviner.toCharArray()

        //Booléen vérifiant si la lettre essayée est dans le mot
        var estDansLeMot = false

        for (l in lettresMotÀDeviner){
            if (lettre == l){
                estDansLeMot = true
                pointage++
            }
        }
        if (!estDansLeMot){
            nbErreurs++
        }

        //Log.d("Mot", motÀDeviner)
        return estDansLeMot
    }


    /**
     * @return vrai si et seulement si toutes les lettres du mot ont été découvertes
     */
    fun estRéussi() : Boolean{
        var réussi = false
        if(pointage == motÀDeviner.length){
            réussi = true
        }
        return réussi
    }

    /**
     * @return un tableau de caractères représentant chacun une lettre du mot à deviner par :
     *     - La lettre en question, en majuscule, si la lettre a été découverte
     *     - Le caractère souligné (_) si la lettre n’a pas été découverte
     *
     *     NE FONCTIONNE PAS COMME IL FAUT: LETTRE APPARAIT APRÈS AVOIR CLIQUER SUR UNE AUTRE LETTRE
     */
    fun étatLettres() : String{
        var souligné = StringBuilder()

        for (l in motÀDeviner.toCharArray().indices){
            souligné.append('_')
            souligné.append(' ')
            for(i in lettresEssayées.indices){
                if (motÀDeviner.toCharArray()[l] == lettresEssayées[i]){
                    souligné[l*2] = lettresEssayées[i]
                }
            }
        }
        return souligné.toString()
    }


    /**
     * Réinitialise le jeu
     */
    fun réinitialiser(){
        pointage = 0
        nbErreurs = 0
        motÀDeviner = sélectionnerProchainMot()
        lettresEssayées = charArrayOf()
    }
}