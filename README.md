# coucou — bouton « Aide à distance » des applis spdyn1

Module Android (sans Compose), inclus comme sous-module git, comme `maj`. Il lance l'appli **Coucou**
(`fr.legift.coucou`, contrôle à distance) ou, si elle n'est pas installée, propose d'ouvrir sa page de téléchargement
(`https://spdyn1.github.io/coucou/`).

Intégration :
1. `git submodule add https://github.com/spdyn1/coucou.git coucou`
2. `settings.gradle.kts` : `include(":coucou")` ; racine : plugin `com.android.library` (apply false) ;
   appli : `implementation(project(":coucou"))`.
3. Bouton ou ligne de menu : texte `getString(com.spdyn1.coucou.R.string.coucou_libelle)` (fr/en),
   action `CoucouDistance.lancer(context)`. `CoucouDistance.installee(context)` dit si Coucou est présente.
4. Facultatif : redéfinir `coucou_site` dans `res/values` de l'appli pour changer l'adresse de téléchargement.

Le manifeste du module déclare la visibilité du paquet `fr.legift.coucou` (`<queries>`) : rien à ajouter.
