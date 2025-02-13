# DVF Explorer

DVF Explorer est une application full-stack permettant d'explorer et d'analyser les données des ventes immobilières en France grâce aux données DVF (Demande de Valeur Foncière). Elle fournit une API REST et une interface utilisateur interactive pour visualiser les transactions immobilières.

## 📌 Fonctionnalités

- 🔍 **Recherche de ventes** par ville, type de bien, prix, surface, date.
- 📊 **Statistiques avancées** : prix moyen au m², évolution des prix.
- 🗺 **Carte interactive** : visualisation géographique des transactions.
- ⚡ **API REST** documentée avec Swagger.
- 🏗 **Kafka** pour la gestion des logs et événements.
- 🐳 **Dockerisation complète** avec PostgreSQL et Kafka.
- ✅ **Tests unitaires et d'intégration** avec JUnit, Mockito et Cypress.
- 🚀 **CI/CD** via GitHub Actions.

## 🏗 Stack Technique

### Backend
- **Langage** : Java 11/17
- **Framework** : Spring Boot (Spring MVC, Hibernate, Spring Data JPA)
- **Base de données** : PostgreSQL
- **Message Broker** : Apache Kafka
- **Tests** : JUnit 5, Mockito, Testcontainers
- **Documentation API** : Swagger

### Frontend
- **Framework** : Angular ou React
- **UI** : Bootstrap, Chart.js/D3.js, Leaflet.js
- **Tests E2E** : Cypress

### DevOps
- **Conteneurisation** : Docker, Docker Compose
- **CI/CD** : GitHub Actions
- **Surveillance** : Spring Boot Actuator, Prometheus/Grafana (optionnel)

## 🚀 Installation & Déploiement

### Prérequis
- Docker et Docker Compose installés
- Java 11/17 et Maven pour le backend
- Node.js et npm/yarn pour le frontend

### Démarrer le projet (en local)
```bash
# Cloner le repo
git clone https://github.com/ton_github/dvf-explorer.git
cd dvf-explorer

# Lancer les services Docker
docker-compose up -d

# Lancer le backend
cd backend
mvn spring-boot:run

# Lancer le frontend
cd ../frontend
npm install
npm start
```

### Exécuter les tests
```bash
# Tests backend
cd backend
mvn test

# Tests frontend
cd ../frontend
npm test

# Tests E2E
npm run cypress:open
```

## 📌 API REST Documentation
L'API REST est documentée via Swagger et accessible ici après lancement :
[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## 🛠 Dockerisation
Pour exécuter toute l'application avec PostgreSQL et Kafka :
```bash
docker-compose up --build
```

## 📜 Licence
Ce projet est sous licence MIT. Tu peux l'utiliser librement et contribuer.

---
🚀 **Démo et code source :** [GitHub](https://github.com/ton_github/dvf-explorer)
