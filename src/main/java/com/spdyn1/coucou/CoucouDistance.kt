package com.spdyn1.coucou

import android.app.AlertDialog
import android.content.Context
import android.content.ContextWrapper
import android.content.Intent
import android.net.Uri
import android.app.Activity

/**
 * Bouton « Aide à distance » des applis spdyn1 : lance l'appli Coucou si elle est installée,
 * sinon propose d'ouvrir sa page de téléchargement.
 *
 * Usage : `CoucouDistance.lancer(contexte)` depuis un bouton ou une ligne de menu ;
 * le texte du bouton est `getString(R.string.coucou_libelle)` (fr/en).
 */
object CoucouDistance {
    const val PAQUET = "fr.legift.coucou"

    fun installee(ctx: Context): Boolean = ctx.packageManager.getLaunchIntentForPackage(PAQUET) != null

    /** Lance Coucou, ou affiche la proposition de téléchargement. Renvoie true si Coucou a été lancée. */
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
        val site = ctx.getString(R.string.coucou_site)
        val ouvrir = {
            val voir = Intent(Intent.ACTION_VIEW, Uri.parse(site))
            if (activite == null) voir.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            ctx.startActivity(voir)
        }
        if (activite == null) { ouvrir(); return }
        AlertDialog.Builder(activite)
            .setTitle(R.string.coucou_absente_titre)
            .setMessage(R.string.coucou_absente_texte)
            .setPositiveButton(R.string.coucou_telecharger) { _, _ -> ouvrir() }
            .setNegativeButton(R.string.coucou_annuler, null)
            .show()
    }

    private fun activite(ctx: Context): Activity? {
        var c: Context? = ctx
        while (c is ContextWrapper) { if (c is Activity) return c; c = c.baseContext }
        return null
    }
}
