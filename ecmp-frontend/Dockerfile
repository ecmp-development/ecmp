FROM node:21-alpine as node
WORKDIR /app
COPY . .
RUN npm i -g npm
RUN npm i -g angular/cli
RUN npm run build --prod

FROM nginx:alpine
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=node /app/dist/ecmp-frontend /usr/share/nginx/html
