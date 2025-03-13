# Rest Service

### Some Checks that are made

1. Checks For fraudulent activities when updating a user. (By Id and Email)
2. Email Validation to avoid Duplicates
3. Audit History on User is kept (To See what happened on every user And Who made the changes)
4. Also password is Based64 encoded
5. Added a new api to update user password.
6. Email Validation added as well to check for proper email format. 

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