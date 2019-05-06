# Working with Slack authTest

[ [Overview](#overview) ]  [ [Operation details](#operation-details) ]  [ [Sample configuration](#sample-configuration)]


## Overview

This operation retrieves a user's profile information.

 Operation        | Description |
| ------------- |-------------|
| [getUserProfile](#Getting-a-user-profile)    | Retrieves a user's profile information |

## Operation details

This section provides more details on each of the operations.

#### Getting a user profile
The getUserProfile operation retrieves a user's profile information.

**getUserProfile**
```xml
<slack.getUserProfile>
    <includeLables>{$ctx:includeLables}</includeLables>
    <userId>{$ctx:userId}</userId>
</slack.getUserProfile>
```

**Properties**
* includeLables      : Required - Include labels for each ID in custom profile fields.
* userId   : Required - The user to retrieve profile info for.

##### Sample request

Following is a sample JSON request that can be handled by the getUserProfile operation.
```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"includeLables": "true",
	"userId": "UGQLS7BRT"
}
```
**Sample response**

Given below is a sample response for the getUserProfile operation.

```json

{
    "ok": true,
    "profile": {
        "title": "",
        "phone": "",
        "skype": "",
        "real_name": "dhanushka",
        "real_name_normalized": "dhanushka",
        "display_name": "",
        "display_name_normalized": "",
        "fields": null,
        "status_text": "",
        "status_emoji": "",
        "status_expiration": 0,
        "avatar_hash": "gc36bb54a072",
        "email": "dhanuhmd80@gmail.com",
        "image_24": "https://secure.gravatar.com/avatar/c36bb54a072f4b9779c916d790a89395.jpg?s=24&d=https%3A%2F%2Fa.slack-edge.com%2F00b63%2Fimg%2Favatars%2Fava_0019-24.png",
        "image_32": "https://secure.gravatar.com/avatar/c36bb54a072f4b9779c916d790a89395.jpg?s=32&d=https%3A%2F%2Fa.slack-edge.com%2F00b63%2Fimg%2Favatars%2Fava_0019-32.png",
        "image_48": "https://secure.gravatar.com/avatar/c36bb54a072f4b9779c916d790a89395.jpg?s=48&d=https%3A%2F%2Fa.slack-edge.com%2F00b63%2Fimg%2Favatars%2Fava_0019-48.png",
        "image_72": "https://secure.gravatar.com/avatar/c36bb54a072f4b9779c916d790a89395.jpg?s=72&d=https%3A%2F%2Fa.slack-edge.com%2F00b63%2Fimg%2Favatars%2Fava_0019-72.png",
        "image_192": "https://secure.gravatar.com/avatar/c36bb54a072f4b9779c916d790a89395.jpg?s=192&d=https%3A%2F%2Fa.slack-edge.com%2F00b63%2Fimg%2Favatars%2Fava_0019-192.png",
        "image_512": "https://secure.gravatar.com/avatar/c36bb54a072f4b9779c916d790a89395.jpg?s=512&d=https%3A%2F%2Fa.slack-edge.com%2F00b63%2Fimg%2Favatars%2Fava_0019-512.png",
        "status_text_canonical": ""
    }
}
```

**Related Slack documentation**
https://api.slack.com/methods/users.profile.get

## Sample configuration

Following example illustrates how to connect to Slack  with the init operation and getUserProfile operation.

1. Create a sample proxy as below :

     Sample proxy
    
      ````
    <proxy xmlns="http://ws.apache.org/ns/synapse"
           name="slack_getUserProfile"
           startOnLoad="true"
           statistics="disable"
           trace="disable"
           transports="http,https">
       <target>
          <inSequence>
             <property expression="json-eval($.includeLables)" name="includeLables"/>
             <property expression="json-eval($.userId)" name="userId"/>
             <property expression="json-eval($.apiToken)" name="apiToken"/>
             <property expression="json-eval($.apiUrl)" name="apiUrl"/>
             <slack.init>
                <apiUrl>{$ctx:apiUrl}</apiUrl>
                <apiToken>{$ctx:apiToken}</apiToken>
             </slack.init>
             <slack.getUserProfile>
                <userId>{$ctx:userId}</userId>
                <includeLables>{$ctx:includeLables}</includeLables>
             </slack.getUserProfile>
             <respond/>
          </inSequence>
          <outSequence/>
          <faultSequence/>
       </target>
       <description/>
    </proxy>
     ```` 
 
 2. Create a json file named getUserProfile.json and copy the configurations given below to it:
  
      ```json
      {
      	"apiUrl": "https://slack.com",
      	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
      	"includeLables": "true",
      	"userId": "UGQLS7BRT"
      }
      ```
      
 3. Replace the credentials with your values.
 
 4. Execute the following curl command:
     ```bash   
     curl -X POST http://localhost:8280/services/slack_getUserProfile--header 'Content-Type: application/json' -d @getUserProfile.json
     ``` 
     
 5. Slack returns a json response similar to the one shown below:
  
     ``````
     {"ok": true,"profile": {"title": "","phone": "","skype": "","real_name": "dhanushka","real_name_normalized": "dhanushka","display_name": "","display_name_normalized": "","fields": null,"status_text": "","status_emoji": "","status_expiration": 0,"avatar_hash": "gc36bb54a072","email": "dhanuhmd80@gmail.com","image_24": "https://secure.gravatar.com/avatar/c36bb54a072f4b9779c916d790a89395.jpg?s=24&d=https%3A%2F%2Fa.slack-edge.com%2F00b63%2Fimg%2Favatars%2Fava_0019-24.png","image_32": "https://secure.gravatar.com/avatar/c36bb54a072f4b9779c916d790a89395.jpg?s=32&d=https%3A%2F%2Fa.slack-edge.com%2F00b63%2Fimg%2Favatars%2Fava_0019-32.png","image_48": "https://secure.gravatar.com/avatar/c36bb54a072f4b9779c916d790a89395.jpg?s=48&d=https%3A%2F%2Fa.slack-edge.com%2F00b63%2Fimg%2Favatars%2Fava_0019-48.png","image_72": "https://secure.gravatar.com/avatar/c36bb54a072f4b9779c916d790a89395.jpg?s=72&d=https%3A%2F%2Fa.slack-edge.com%2F00b63%2Fimg%2Favatars%2Fava_0019-72.png","image_192": "https://secure.gravatar.com/avatar/c36bb54a072f4b9779c916d790a89395.jpg?s=192&d=https%3A%2F%2Fa.slack-edge.com%2F00b63%2Fimg%2Favatars%2Fava_0019-192.png","image_512": "https://secure.gravatar.com/avatar/c36bb54a072f4b9779c916d790a89395.jpg?s=512&d=https%3A%2F%2Fa.slack-edge.com%2F00b63%2Fimg%2Favatars%2Fava_0019-512.png","status_text_canonical": ""}}