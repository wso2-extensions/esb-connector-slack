# Working with Slack messages

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 

The following operations allow you to work with slack messages. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with slack messages, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [sendMessage](#Send-message-to-a-channel)    |  Sends a message into a channel. |
| [deleteMessage](#Deletes-a-message-sent-to-a-channel)      | Deletes a message sent to a channel. |

### Operation details

This section provides more details on each of the operations.

#### Send message to a channel
The sendMessage operation sends a message into a channel.

**createSearchJob**
```xml
<slack.sendMessage>
    <channelId>{$ctx:channelId}</channelId>
    <text>{$ctx:text}</text>
</slack.sendMessage>
```

**Properties**
* channelId       : Required - The id of the channel which can be an encoded ID, or a name to send a message to. The channel can be a public channel, private group or IM channel. 

* text       : Required - Text of the message to send.

 
**Sample request**

Following is a sample request that can be handled by the sendMessage operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"channelId": "CHASH5VD5",
	"text": "Hello"
}
```


**Sample Response**

```json
{
    "channel": "CHASH5VD5",
    "ts": "1553842587.000200",
    "ok": true
}
```

**Related Slack documentation**
https://api.slack.com/methods/chat.meMessage

####  Deletes a message sent to a channel

The deleteMessage operation deletes a message that is sent to a slack channel.

**deleteMessage**
```xml
<slack.deleteMessage>
    <channelId>{$ctx:channelId}</channelId>
    <timeStamp>{$ctx:timeStamp}</timeStamp>
    <asUser>{$ctx:asUser}</asUser>
</slack.deleteMessage>
```

**Properties**
* channelId          : Required - The id of the channel containing the message to be deleted.

* timeStamp          : Required - Timestamp of the message to be deleted.

* asUser          : Optional - Pass true to delete the message as the authed user with chat:write:user scope


**Sample request**

Following is a sample request that can be handled by the deleteMessage operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"channelId": "CHASH5VD5",
	"timeStamp": "1553664080.000100",
	"asUser": "true"
}
```

**Sample Response**

```json
{
    "ok": true,
    "channel": "CHASH5VD5",
    "ts": "1553664080.000100"
}
```

**Related Slack documentation**
https://api.slack.com/methods/chat.delete

#### Sample configuration

Following is a sample proxy service that illustrates how to connect to Slack with the init operation, and then use the sendMessage operation. The sample request for this proxy can be found in the sendMessage sample request. You can use this sample as a template for using other operations in this category.

1. Create a sample proxy as below :

    **Sample Proxy**
    ```xml
   <?xml version="1.0" encoding="UTF-8"?>
    <proxy xmlns="http://ws.apache.org/ns/synapse"
           name="slack_sendMessage"
           startOnLoad="true"
           statistics="disable"
           trace="disable"
           transports="http,https">
       <target>
          <inSequence>
             <property expression="json-eval($.channelId)" name="channelId"/>
             <property expression="json-eval($.text)" name="text"/>
             <property expression="json-eval($.apiUrl)" name="apiUrl"/>
             <property expression="json-eval($.apiToken)" name="apiToken"/>
             <slack.init>
                <apiToken>{$ctx:apiToken}</apiToken>
                <apiUrl>{$ctx:apiUrl}</apiUrl>
             </slack.init>
             <slack.sendMessage>
                <text>{$ctx:text}</text>
                <channelId>{$ctx:channelId}</channelId>
             </slack.sendMessage>
             <respond/>
          </inSequence>
          <outSequence/>
          <faultSequence/>
       </target>
       <description/>
    </proxy>
    ```

2. Create a json file named sendMessage.json and copy the configurations given below to it:
 
     ```json
     {
     	"apiUrl": "https://slack.com",
     	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
     	"channelId": "CHASH5VD5",
     	"text": "Hello"
     }
     ```
     
 3. Replace the credentials with your values. 
 
 4. Execute the following curl command:
     ```bash   
     curl -X POST http://localhost:8280/services/slack_sendMessage --header 'Content-Type: application/json' -d @sendMessage.json
     ```  
                
 5. Slack returns a json response similar to the one shown below:
  
  ``````
 {"channel": "CHASH5VD5","ts": "1553842587.000200","ok": true}
       