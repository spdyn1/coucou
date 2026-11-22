# aide — bouton « Aide à distance » des applis spdyn1

Module Android (sans Compose), inclus comme sous-module git, comme `maj`. Il lance l'appli **Aide**
(`fr.legift.aide`, contrôle à distance) ou, si elle n'est pas installée, propose d'ouvrir sa page de téléchargement
(`https://spdyn1.github.io/aide/`).

Intégration :
1. `git submodule add https://github.com/spdyn1/aide.git aide`
2. `settings.gradle.kts` : `include(":aide")` ; racine : plugin `com.android.library` (apply false) ;
   appli : `implementation(project(":aide"))`.
3. Bouton ou ligne de menu : texte `getString(com.spdyn1.aide.R.string.aide_libelle)` (fr/en),
   action `AideDistance.lancer(context)`. `AideDistance.installee(context)` dit si Aide est présente.
4. Facultatif : redéfinir `aide_site` dans `res/values` de l'appli pour changer l'adresse de téléchargement.

Le manifeste du module déclare la visibilité du paquet `fr.legift.aide` (`<queries>`) : rien à ajouter.
