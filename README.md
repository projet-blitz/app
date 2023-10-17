# Blitz - Horaires de Bus en Temps Réel

Blitz est une application mobile développée en Kotlin avec Compose qui vous permet de consulter les horaires de bus en temps réel. De plus, elle affiche une carte Google Maps avec les arrêts de bus pour vous aider à planifier vos déplacements en toute simplicité.

## Prérequis

Avant de pouvoir exécuter l'application, assurez-vous de disposer des éléments suivants :

- Une clé d'API Google Maps. Vous pouvez obtenir une clé d'API Google Maps en suivant les étapes décrites dans la documentation officielle de Google : [Obtenir une clé d'API Google Maps](https://developers.google.com/maps/gmp-get-started).

- Le back-end de l'application. Le back-end est une API en C# disponible sur GitHub : [projet-blitz/api](https://github.com/projet-blitz/api).
Assurez-vous de cloner le back-end et de le configurer correctement avant de lancer l'application mobile.

## Installation

1. Clonez le projet sur votre machine :

```bash
git clone https://github.com/projet-blitz/app.git
```
2. Ouvrez le projet dans votre environnement de développement préféré (tel qu'Android Studio).

3. Créez un fichier local.properties à la racine du projet et ajoutez votre clé d'API Google Maps de la manière suivante :
```bash
google_maps_api_key=VOTRE_CLÉ_API_GOOGLE_MAPS
```

4. Assurez-vous que le back-end est en cours d'exécution et accessible.

5. Exécutez l'application sur votre émulateur ou appareil Android.

## Configuration

Assurez-vous de configurer l'URL du back-end dans le code de l'application pour qu'elle corresponde à l'emplacement de l'API. 

Par défaut, un TrustManager personnalisé acceptant tous les certificats SSL est utilisé pour accéder à l'API. 
Il est important de noter que cette approche ne doit être utilisée que pour des tests et des opérations de débogage, et elle n'est pas recommandée pour des environnements de production. 
