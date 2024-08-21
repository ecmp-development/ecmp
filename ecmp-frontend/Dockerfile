FROM node:21-alpine as node
WORKDIR /app
COPY . .
COPY package*.json ./
RUN npm install
RUN npm run build --prod

FROM nginx:alpine
COPY ./nginx.conf /etc/nginx/conf.d/default.conf
COPY --from=node /app/dist/ecmp-frontend /usr/share/nginx/html
