./gradlew build -x test
cd frontend-app
npm install
cd ../docker
docker-compose down
docker-compose build
docker-compose up -d
