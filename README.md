### TODO List

```angular2html
1. Add Opening and closing days for restaurants
2. Add Opening and closing times for restaurants

3. Add Opening and closing days for menu/meals
4. Add Opening and closing times for menu/meals

Notification(Contact Mr. Solomon to use his api.
5. Add WhatsApp/SMS functionality to send notify customers of available food
6. Also to notify customers for available discount
7. Notification for Account Creation and Update to the Restaurant owner



```

# Rest Service

### Docker Login Or Logout

```bash
docker login
docker logout
```

#### Connect to Running Instance of mysql running locally: Favor@123

```bash
mysql -h 127.0.0.1 -P 3306 -u root -p
```

## Docker Build and Push

### Remember to change the version number

```bash
docker build --platform=linux/amd64 -t thembaembot/restaurantservice:1.0.0.1 .
docker push thembaembot/restaurantservice:1.0.0.1
```

### Remove Old Container and Space

```bash
docker stop restaurantservice && docker rm restaurantservice
```

### Remember to change the version number

```bash
docker run -d --restart unless-stopped --name restaurantservice -p 10001:80 restaurantservice
```