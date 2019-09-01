FROM node:10.9.0-stretch

RUN npm install -g @angular/cli

# 1. --host 0.0.0.0 to access from host machine and selenium docker container
#  ref: https://dev.classmethod.jp/ria/angular-js/docker-for-macでangular8の実行環境を構築/
# 2. --disableHostCheck to access from selenium docker container
#  ref: https://tackeyy.com/blog/posts/how-to-access-app-through-the-ip-with-webpack-dev-server
CMD ng serve --host 0.0.0.0 --disableHostCheck
