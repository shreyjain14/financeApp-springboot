# Finance App Springboot Application
  

## Auth:

  
### REGISTER:

ENDPOINT:
[POST] `api/auth/register`

AUTHORIZATION REQUIRED: False

BODY:
```
{
  "username": <String>,
  "email": <String>,
  "password": <String>,
  "role": <String?>
}
```

RESPONSE:
```
{
  "id": <String>,
  "username": <String>,
  "email": <String>,
  "role": <String>,
  "verified": <Boolean>
}
```

### LOGIN

ENDPOINT:
[POST] `api/auth/login`

AUTHORIZATION REQUIRED: False

BODY:
```
{
  "username": <String>,
  "password": <String>
}
```

RESPONSE:
```
{
  "accessToken": <String>,
  "refreshToken": <String>
}
```

### REFRESH

ENDPOINT:
[POST] `api/auth/refresh`

AUTHORIZATION REQUIRED: False

BODY:
```
{
  "token": <String>
}
```

RESPONSE:
```
{
  "token": <String>
}
```

### LOGOUT

ENDPOINT:
[GET] `api/auth/logout`

AUTHORIZATION REQUIRED: True

## PAYMENT

### GET PAYMENTS

ENDPOINT:
[GET] `api/payment`

AUTHORIZATION REQUIRED: True

PARAMETERS:
```
"page": <Integer?>:1
```

RESPONSE:
```
<List<{
  "id": <String>,
  "amount": <Double>,
  "currency": <String>,
  "date": <String>,
  "payedFrom": <String>,
  "payedTo": <String>,
  "userId": <String>
}>>
```

### SAVE PAYMENT

ENDPOINT:
[GET] `api/payment`

AUTHORIZATION REQUIRED: True

BODY:
```
{
  "amount": <Double>,
  "currency": <String>,
  "payedFrom": <String>,
  "payedTo": <String>
}
```

RESPONSE:
```
{
  "id": <String>,
  "amount": <Double>,
  "currency": <String>,
  "date": <String>,
  "payedFrom": <String>,
  "payedTo": <String>,
  "userId": <String>
}
```

### DELETE PAYMENT

ENDPOINT:
[GET] `api/payment`

AUTHORIZATION REQUIRED: True

PARAMETERS:
```
"paymentId": <String>
```

BODY:
```
{
  "amount": <Double>,
  "currency": <String>,
  "payedFrom": <String>,
  "payedTo": <String>
}
```

RESPONSE:
```
<Boolean>
```

## DEFAULTS

### GET ALL DEFAULTS

ENDPOINT:
[GET] `api/defaults`

AUTHORIZATION REQUIRED: True

RESPONSE:
```
{
  "email": <String>,
  "payedTo": <List<String>>,
  "payedFrom": <List<String>>
}?
```

### ADD PAYED TO DEFAULT

ENDPOINT:
[POST] `api/defaults/payedTo`

AUTHORIZATION REQUIRED: True

BODY:
```
{
 "pay": <String> 
}
```

RESPONSE:
```
<List<String>>
```

### GET PAYED TO DEFAULTS

ENDPOINT:
[GET] `api/defaults/payedTo`

AUTHORIZATION REQUIRED: True

RESPONSE:
```
<List<String>>
```

### DELETE PAYED TO DEFAULT

ENDPOINT:
[DELETE] `api/defaults/payedTo`

AUTHORIZATION REQUIRED: True

BODY:
```
{
 "pay": <String> 
}
```

RESPONSE:
```
<List<String>>
```

### ADD PAYED FROM DEFAULT

ENDPOINT:
[POST] `api/defaults/payedFrom`

AUTHORIZATION REQUIRED: True

BODY:
```
{
 "pay": <String> 
}
```

RESPONSE:
```
<List<String>>
```

### GET PAYED FROM DEFAULTS

ENDPOINT:
[GET] `api/defaults/payedFrom`

AUTHORIZATION REQUIRED: True

RESPONSE:
```
<List<String>>
```

### DELETE PAYED FROM DEFAULT

ENDPOINT:
[DELETE] `api/defaults/payedFrom`

AUTHORIZATION REQUIRED: True

BODY:
```
{
 "pay": <String> 
}
```

RESPONSE:
```
<List<String>>
```

## SHARE

### GET SHARED USERS

ENDPOINT:
[GET] `api/share`

AUTHORIZATION REQUIRED: True

RESPONSE:
```
<List<String>>
```

### ADD SHARED USER

ENDPOINT:
[POST] `api/share`

AUTHORIZATION REQUIRED: True

PARAMETERS:
```
"email": <String>
```

RESPONSE:
```
<List<String>>
```

### REMOVE SHAREd USER

ENDPOINT:
[DELETE] `api/share`

AUTHORIZATION REQUIRED: True

PARAMETERS:
```
"email": <String>
```

RESPONSE:
```
<List<String>>
```

### CHECK SHARED USERS POST

ENDPOINT:
[POST] `api/share/check`

AUTHORIZATION REQUIRED: True

PARAMETERS:
```
"email": <String>,
"page": <Integer?>:1
```

RESPONSE:
```
<List<{
  "id": <String>,
  "amount": <Double>,
  "currency": <String>,
  "date": <String>,
  "payedFrom": <String>,
  "payedTo": <String>,
  "userId": <String>
}>>
```
