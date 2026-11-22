package com.spdyn1.aide

import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.app.Activity

/**
 * Bouton « Aide à distance » des applis spdyn1 : lance l'appli Aide si elle est installée,
 * sinon propose d'ouvrir sa page de téléchargement.
 *
 * Usage : `AideDistance.lancer(contexte)` depuis un bouton ou une ligne de menu ;
 * le texte du bouton est `getString(R.string.aide_libelle)` (fr/en).
 */
object AideDistance {
    const val PAQUET = "fr.legift.aide"

    fun installee(ctx: Context): Boolean = ctx.packageManager.getLaunchIntentForPackage(PAQUET) != null

    /** Lance Aide, ou affiche la proposition de téléchargement. Renvoie true si Aide a été lancée. */
    fun lancer(ctx: Context): Boolean {
        val intent = ctx.packageManager.getLaunchIntentForPackage(PAQUET)
        if (intent != null) {
            if (ctx !is Activity) intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ctx.startActivity(intent)
            return true
        }
        proposer(ctx)
        return false
    }

    private fun proposer(ctx: Context) {
        val activite = activite(ctx)
        val site = ctx.getString(R.string.aide_site)
        val ouvrir = {
            val voir = Intent(Intent.ACTION_VIEW, Uri.parse(site))
            if (activite == null) voir.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ctx.startActivity(voir)
        }
        if (activite == null) { ouvrir(); return }
        AlertDialog.Builder(activite)
            .setTitle(R.string.aide_absente_titre)
            .setMessage(R.string.aide_absente_texte)
            .setPositiveButton(R.string.aide_telecharger) { _, _ -> ouvrir() }
            .setNegativeButton(R.string.aide_annuler, null)
            .show()
    }

    private fun activite(ctx: Context): Activity? {
        var c: Context? = ctx
        while (c is ContextWrapper) { if (c is Activity) return c; c = c.baseContext }
        return null
    }
}
