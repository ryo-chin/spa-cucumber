./gradlew build -x test
cd frontend-app
npm install
cd ../docker
docker-compose restart
