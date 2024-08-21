FROM node:21-alpine as node
WORKDIR /app
COPY . .

FROM nginx:alpine
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=node /app/dist/ecmp-frontend /usr/share/nginx/html
