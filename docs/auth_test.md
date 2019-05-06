# Working with Slack authTest

[ [Overview](#overview) ]  [ [Operation details](#operation-details) ]  [ [Sample configuration](#sample-configuration)]


## Overview


This operation checks authentication & identity or tells "you" who you are. You can also use this operation to test whether Slack API authentication is functional. This operation returns corresponding user to the given apiToken together with the user details.

For a sample proxy service that illustrates how to work with authTest, see [Sample configuration](#sample-configuration).

 Operation | Description |
| ------------- |-------------|
| [authTest](#authTest) | Returns corresponding user to the given apiToken together with the user details. |


## Operation details
This section provides details on each of the operations.

##### authTest

```xml
   <slack.authTest/>
```

##### Sample request

Following is a sample JSON request that can be handled by the authTest operation.
```json
{
    "apiToken": "https://slack.com",
    "apiUrl": "xoxp-568453711959-56663574955a4c54abbf7ca858"
}
```
**Sample response**

Given below is a sample response for the authTest operation.

```json
{
    "ok": true,
    "url": "https://wso2-hq.slack.com/",
    "team": "wso2",
    "user": "ayodhya",
    "team_id": "TGQDBLXU7",
    "user_id": "UGNJPN1G8"
}
```

**Related Slack documentation**
https://api.slack.com/methods/auth.test

## Sample configuration

Following example illustrates how to connect to Slack  with the init operation and authTest operation.

1. Create a sample proxy as below :

   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <proxy xmlns="http://ws.apache.org/ns/synapse"
          name="slack_authTest"
          startOnLoad="true"
          statistics="disable"
          trace="disable"
          transports="http,https">
      <target>
         <inSequence>
            <property expression="json-eval($.apiToken)" name="apiToken"/>
            <property expression="json-eval($.apiUrl)" name="apiUrl"/>
            <slack.init>
               <apiUrl>{$ctx:apiUrl}</apiUrl>
               <apiToken>{$ctx:apiToken}</apiToken>
            </slack.init>
            <slack.authTest/>
            <respond/>
         </inSequence>
         <outSequence/>
         <faultSequence/>
      </target>
      <description/>
   </proxy>
       
   ```
2. Create a json file named authTest.json and copy the configurations given below to it:
 
     ```json
     {
        "apiUrl": "https://slack.com",
        "apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858"
     }
     ```
     
3. Replace the credentials with your values. 
 
4. Execute the following curl command:
    ```bash   
    curl -X POST http://localhost:8280/services/slack_authTest --header 'Content-Type: application/json' -d @authTest.json
    ``` 
                 
5. Slack returns a json response similar to the one shown below:
 
      ``````
    {"ok":true,"url":"https:\/\/wso2-hq.slack.com\/","team":"wso2","user":"ayodhya","team_id":"TGQDBLXU7","user_id":"UGNJPN1G8"}
