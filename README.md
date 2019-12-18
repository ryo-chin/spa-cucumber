# Setup & Launch
## Front
```bash
cd frontend-app
brew install nvm # if need
nvm use
npm install -g @angular/cli # if need
npm install
ng serve
```
## API
```bash
./gradlew bootRun 
```
## ATDD Test
```bash
cd frontend-app
nvm use
npm install
cd ..
./gradlew api-server:bootJar
cd docker
docker-compose up -d --build
cd ..
./gradlew api-server:test --tests com.hakiba.spacucumber.RunCucumber --info
```

