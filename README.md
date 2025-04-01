API de Conversion de Devises - Guide Complet

Table des Matières

    Prérequis

    Configuration Initiale

    Structure du Projet

    Exécution de l'API

    Utilisation via Swagger

    Exemples de Requêtes

    Gestion des Erreurs

    Tests Automatisés

    Dépannage

    Détails Techniques

1. Prérequis

    Java 17+ (vérifiez avec java -version).

    Maven (vérifiez avec mvn -version).

    Clé API gratuite depuis Exchangerate-API.

    IDE (IntelliJ, Eclipse, ou VS Code).

2. Configuration Initiale
   
Étape 1 : Cloner le Dépôt
bash
``git clone https://github.com/ulrichchedjou/convertisseur-devise-api.git
cd currency-converter-api``

Étape 2 : Configurer la Clé API

    Créez un compte sur Exchangerate-API.

    Obtenez votre clé API.

    Ouvrez src/main/resources/application.properties et ajoutez :

properties

``api.key=VOTRE_CLE_API_ICI
exchange.api.url=https://v6.exchangerate-api.com/v6``

3. Structure du Projet
   
``Convertisseur_de_Devises/
├── src/
│   ├── main/
│   │   ├── java/com/KFOKAM48/Convertisseur_de_Devises
│   │   │   ├── config/                # Configuration WebClient et Swagger
│   │   │   ├── controller/            # Endpoints API
│   │   │   ├── DTOs/                   # Objets de transfert de données
│   │   │   ├── exception/             # Gestion des erreurs
│   │   │   ├── service/               # Logique métier
│   │   │   └── CurrencyConverterApplication.java
│   │   └── resources/
│   │       └── application.properties # Configuration
│   └── test/                          # Tests unitaires
└── pom.xml                            # Dépendances Maven``

5. Exécution de l'API


Étape 1 : Compiler avec Maven
bash
``mvn clean install``

Étape 2 : Démarrer l'Application
bash
``mvn spring-boot:run``

L'API sera accessible sur ``http://localhost:8081``.



5. Utilisation via Swagger

Accédez à l'interface Swagger :
``http://localhost:8081/swagger-ui.html``

Endpoint Disponible :

    GET /api/convert
    Convertit un montant entre deux devises.

Paramètres :
Nom	Exemple	Description
Source	USD	Devise source (3 lettres)
Target	EUR	Devise cible (3 lettres)
amount	100	Montant à convertir


6. Exemples de Requêtes
## Requête Valide :
``http://localhost:8081/api/convert?source=USD&target=EUR&amount=100``

# Response body

``{
  "source": "USD",
  "target": "EUR",
  "amount": 100,
  "convertedAmount": 92.46,
  "rate": 0.9246,
  "targetCurrency": "EUR"
}``

# Response headers

``connection: keep-alive  
content-type: application/json  
date: Tue,01 Apr 2025 08:07:37 GMT  
keep-alive: timeout=60  
transfer-encoding: chunked``

## Cas d'Erreur : Devise Invalide
http
``GET /api/convert?from=USD&to=XYZ&amount=100``

## Réponse :
	

``Error: response status is 503``
#Response body

``{
  "status": 503,
  "message": "Erreur inattendue lors de la conversion: Devise cible non trouvée ou invalide: XYZ"
}``

#Response headers

 ``connection: close  
 content-type: application/json  
 date: Tue,01 Apr 2025 08:21:26 GMT  
 transfer-encoding: chunked`` 
 
Cas d'Erreur : API Externe Indisponible
json

``{
  "status": 503,
  "message": "Erreur de l'API externe : Connexion refusée"
}``

7. Gestion des Erreurs

## Code Statut	Scénario
400	Devise source/cible invalide
503	Échec de connexion à l'API externe
500	Erreur interne du serveur

8. Dépannage
    
Problème : Clé API Invalide

    Solution : Vérifiez la clé dans application.properties.

Problème : Aucune Réponse de l'API

    Solution : Vérifiez la connexion Internet ou les logs Spring Boot.
