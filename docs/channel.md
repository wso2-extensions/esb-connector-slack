# Working with Slack channels

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 

The following operations allow you to work with slack channels. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with channels, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [createChannel](#Creating-a-channel)    | Creates a channel in the Slack workspace |
| [inviteUserToChannel](#Inviting-a-user-to-a-channel)      | Invites a user to a channel.The calling user must be a member of the channel |
| [joinToChannel](#Join-to-a-channel)      |  Joins a channel, creating it if needed |
| [leaveFromChannel](#Leave-from-a-channel)      |   Leaves a channel |
| [removeUserFromChannel](#Remove-user-from-a-channel)      |   Removes a user from a channel.This method allows a user to remove another member from a team channel |


### Operation details

This section provides more details on each of the operations.

#### Creating a channel
The createChannel operation creates a channel in the Slack workspace that you specify.

**createChannel**
```xml
<slack.createChannel>
    <channelName>{$ctx:channelName}</channelName>
    <validate>{$ctx:validate}</validate>
</slack.createChannel>
```

**Properties**
* channelName       : Required - The name of the channel to create.
* validate   : Optional - Whether to return errors on invalid channel name instead of modifying it to meet the specified criteria.


**Sample request**

Following is a sample request that can be handled by the createChannel operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"channelName": "mychannel",
	"validate": "true"
}
```
**Sample response**

Given below is a sample response for the createChannel operation.

```json
{
    "ok": true,
    "channel": {
        "id": "CHEHRN69L",
        "name": "mychannel",
        "is_channel": true,
        "created": 1553765296,
        "is_archived": false,
        "is_general": false,
        "unlinked": 0,
        "creator": "UGNJPN1G8",
        "name_normalized": "mychannel",
        "is_shared": false,
        "is_org_shared": false,
        "is_member": true,
        "is_private": false,
        "is_mpim": false,
        "last_read": "0000000000.000000",
        "latest": null,
        "unread_count": 0,
        "unread_count_display": 0,
        "members": [
            "UGNJPN1G8"
        ],
        "topic": {
            "value": "",
            "creator": "",
            "last_set": 0
        },
        "purpose": {
            "value": "",
            "creator": "",
            "last_set": 0
        },
        "previous_names": [],
        "priority": 0
    }
}

```

**Related Slack documentation**

https://api.slack.com/methods/channels.create

#### Inviting a user to a channel
The inviteUserToChannel operation invites a user to a channel. The calling user must be a member of the channel.


**inviteUserToChannel**
```xml
<slack.inviteUserToChannel>
    <channelId>{$ctx:channelId}</channelId>
    <userId>{$ctx:userId}</userId>
</slack.inviteUserToChannel>
```

**Properties**
* channelId       : Required - The id of the channel to invite user to.

* userId   : Required - The id of the user to invite to the channel.


**Sample request**

Following is a sample request that can be handled by the inviteUserToChannel operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"channelId": "CHJ2D9N95",
	"userId": "UGQLS7BRT"
}
```
**Sample response**

Given below is a sample response for the inviteUserToChannel operation.

```json
{
    "ok": true,
    "channel": {
        "id": "CHJ2D9N95",
        "name": "mychannel",
        "is_channel": true,
        "created": 1554100755,
        "is_archived": false,
        "is_general": false,
        "unlinked": 0,
        "creator": "UGNJPN1G8",
        "name_normalized": "mychannel",
        "is_shared": false,
        "is_org_shared": false,
        "is_member": true,
        "is_private": false,
        "is_mpim": false,
        "last_read": "1554100756.000200",
        "latest": {
            "user": "UGNJPN1G8",
            "type": "message",
            "subtype": "channel_join",
            "ts": "1554100756.000200",
            "text": "<@UGNJPN1G8> has joined the channel"
        },
        "unread_count": 0,
        "unread_count_display": 0,
        "members": [
            "UGNJPN1G8",
            "UGQLS7BRT"
        ],
        "topic": {
            "value": "",
            "creator": "",
            "last_set": 0
        },
        "purpose": {
            "value": "",
            "creator": "",
            "last_set": 0
        },
        "previous_names": [],
        "priority": 0
    }
}

```

**Related Slack documentation**

https://api.slack.com/methods/channels.invite

#### Join to a channel
The joinToChannel operation joins a channel, creating it if needed.

**joinToChannel**
```xml
<slack.joinToChannel>
    <channelName>{$ctx:channelName}</channelName>
    <validate>{$ctx:validate}</validate>
</slack.joinToChannel>
```

**Properties**
* channelName      : Required - The name of channel to join

* validate   : Optional - Whether to return errors on invalid channel name instead of modifying it to meet the specified criteria.

**Sample request**

Following is a sample request that can be handled by the joinToChannel operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"channelName": "busting",
	"validate": "true"
}
```
**Sample response**

Given below is a sample response for the joinToChannel operation.

```json
{
    "ok": true,
    "channel": {
        "id": "CH6NHMY74",
        "name": "busting",
        "is_channel": true,
        "created": 1553237388,
        "is_archived": false,
        "is_general": false,
        "unlinked": 0,
        "creator": "UGNJPN1G8",
        "name_normalized": "busting",
        "is_shared": false,
        "is_org_shared": false,
        "is_member": true,
        "is_private": false,
        "is_mpim": false,
        "members": [
            "UGNJPN1G8"
        ],
        "topic": {
            "value": "",
            "creator": "",
            "last_set": 0
        },
        "purpose": {
            "value": "",
            "creator": "",
            "last_set": 0
        },
        "previous_names": []
    },
    "already_in_channel": true
}

```

**Related Slack documentation**

https://api.slack.com/methods/channels.join

#### Leave from a channel
The leaveFromChannel operation leaves a channel.

**leaveFromChannel**
```xml
<slack.leaveFromChannel>
    <channelId>{$ctx:channelId}</channelId>
</slack.leaveFromChannel>
```

**Properties**
* channelId       : Required - The id of the channel to leave from.


**Sample request**

Following is a sample request that can be handled by the leaveFromChannel operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"channelId": "CH6NHMY74"
}
```
**Sample response**

Given below is a sample response for the leaveFromChannel operation.

```json
{
    "ok": true
}

```

**Related Slack documentation**

https://api.slack.com/methods/channels.leave

#### Remove user from a channel
The leaveFromChannel operation leaves a channel.

**removeUserFromChannel**
```xml
<slack.removeUserFromChannel>
    <channelId>{$ctx:channelId}</channelId>
    <userId>{$ctx:userId}</userId>
</slack.removeUserFromChannel>
```

**Properties**
* channelId       : Required - The id of the channel to remove user from.

* userId       : Required - The id of the user to remove from channel.


**Sample request**

Following is a sample request that can be handled by the leaveFromChannel operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"channelId": "CH6NHMY74",
	"userId": "UGQLS7BRT"
}
```
**Sample response**

Given below is a sample response for the leaveFromChannel operation.

```json
{
    "ok": true
}

```

**Related Slack documentation**

https://api.slack.com/methods/channels.kick



#### Sample configuration

Following is a sample proxy service that illustrates how to connect to Slack with the init operation, and then use the leaveFromChannel operation. The sample request for this proxy can be found in the leaveFromChannel sample request. You can use this sample as a template for using other operations in this category.

1. Create a sample proxy as below :

    **Sample Proxy**
    ```xml
   <proxy xmlns="http://ws.apache.org/ns/synapse"
          name="slack_leaveFromChannel"
          startOnLoad="true"
          statistics="disable"
          trace="disable"
          transports="http,https">
      <target>
         <inSequence>
            <property expression="json-eval($.channelId)" name="channelId"/>
            <property expression="json-eval($.apiUrl)" name="apiUrl"/>
            <property expression="json-eval($.apiToken)" name="apiToken"/>
            <slack.init>
               <apiToken>{$ctx:apiToken}</apiToken>
               <apiUrl>{$ctx:apiUrl}</apiUrl>
            </slack.init>
            <slack.leaveFromChannel>
               <channelId>{$ctx:channelId}</channelId>
            </slack.leaveFromChannel>
            <respond/>
         </inSequence>
         <outSequence/>
         <faultSequence/>
      </target>
      <description/>
   </proxy>
    ```

2. Create a json file named leaveFromChannel.json and copy the configurations given below to it:
 
     ```json
     {
     	"apiUrl": "https://slack.com",
     	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
     	"channelId": "CH6NHMY74"
     }
     ```
     
3. Replace the credentials with your values. 

4. Execute the following curl command:
     ```bash   
        curl -X POST http://localhost:8280/services/slack_leaveFromChannel --header 'Content-Type: application/json' -d @leaveFromChannel.json
     ``` 
       
 5. Slack returns a json response similar to the one shown below: 
 
     ``````
     {"ok":true}
    