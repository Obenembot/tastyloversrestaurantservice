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

# Use Kubernetes to Build and Push to Docker Hub

### Build and Push Docker Image

```bash
docker build -t thembaembot/medicibeckend:latest .
docker login
docker push thembaembot/medicibeckend:latest
```
#### Connect to Running Instance of mysql running locally: Favor@123
```bash
mysql -h 127.0.0.1 -P 3306 -u root -p
```