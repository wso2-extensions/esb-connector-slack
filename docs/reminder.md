# Working with Slack reminder

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 

The following operations allow you to work with slack reminder. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with slack reminder, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [addReminder](#Add-reminder)    |   Creates a reminder. |
| [getReminderInfo](#Gets-informations-about-a-reminder)      | Gets information about a reminder. |

### Operation details

This section provides more details on each of the operations.

#### Add reminder
Creates a reminder.

**addReminder**
```xml
<slack.addReminder>
    <text>{$ctx:text}</text>
    <time>{$ctx:time}</time>
    <userId>{$ctx:userId}</userId>
</slack.addReminder>
```

**Properties**
* text      : Required - The content of the reminder.

* time       : Required - When this reminder should happen: the Unix timestamp (up to five years from now), the number of seconds until the reminder (if within 24 hours), or a natural language description (Ex. "in 15 minutes)

* userId      : Optional - The user who will receive the reminder. If no user is specified, the reminder will go to user who created it.


 
**Sample request**

Following is a sample request that can be handled by the sendMessage operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"text": "Ann's Birthday",
	"time": "1602288000",
	"userId": "UGQLS7BRT"
}
```


**Sample Response**

```json
{
    "ok": true,
    "reminder": {
        "id": "RmHEPSLA3S",
        "creator": "UGNJPN1G8",
        "user": "UGNJPN1G8",
        "text": "Ann's Birthday",
        "recurring": false,
        "time": 1602288000,
        "complete_ts": 0
    }
}
```

**Related Slack documentation**

https://api.slack.com/methods/reminders.add

####  Gets information about a reminder

Gets information about a reminder.

**getReminderInfo**
```xml
<slack.getReminderInfo>
    <reminderId>{$ctx:reminderId}</reminderId>
</slack.getReminderInfo>
```

**Properties**
* reminderId          : Required - The id of the reminder to retrieve the information from.


**Sample request**

Following is a sample request that can be handled by the getReminderInfo operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"reminderId": "RmHEPSLA3S"
}
```

**Sample Response**

```json
{
    "ok": true,
    "reminder": {
        "id": "RmHEPSLA3S",
        "creator": "UGNJPN1G8",
        "user": "UGNJPN1G8",
        "text": "Ann's Birthday",
        "recurring": false,
        "time": 1602288000,
        "complete_ts": 0
    }
}
```

**Related Slack documentation**

https://api.slack.com/methods/reminders.info

#### Sample configuration

Following is a sample proxy service that illustrates how to connect to Slack with the init operation, and then use the addReminder operation. The sample request for this proxy can be found in the addReminder sample request. You can use this sample as a template for using other operations in this category.

1. Create a sample proxy as below :

    **Sample Proxy**
    ```xml
    <proxy xmlns="http://ws.apache.org/ns/synapse"
           name="slack_addReminder"
           startOnLoad="true"
           statistics="disable"
           trace="disable"
           transports="http,https">
       <target>
          <inSequence>
             <property expression="json-eval($.time)" name="time"/>
             <property expression="json-eval($.user)" name="user"/>
             <property expression="json-eval($.text)" name="text"/>
             <property expression="json-eval($.apiToken)" name="apiToken"/>
             <property expression="json-eval($.apiUrl)" name="apiUrl"/>
             <slack.init>
                <apiUrl>{$ctx:apiUrl}</apiUrl>
                <apiToken>{$ctx:apiToken}</apiToken>
             </slack.init>
             <slack.addReminder>
                <text>{$ctx:text}</text>
                <user>{$ctx:user}</user>
                <time>{$ctx:time}</time>
             </slack.addReminder>
             <respond/>
          </inSequence>
          <outSequence/>
          <faultSequence/>
       </target>
       <description/>
    </proxy>
    ```

2. Create a json file named addReminder.json and copy the configurations given below to it:
 
     ```json
     {
     	"apiUrl": "https://slack.com",
     	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
     	"text": "Ann's Birthday",
     	"time": "1602288000",
     	"userId": "UGQLS7BRT"
     }
     ```
     
3. Replace the credentials with your values.

4. Execute the following curl command:
    ```bash   
    curl -X POST http://localhost:8280/services/slack_addReminder --header 'Content-Type: application/json' -d @addReminder.json
    ``` 
    
5. Slack returns a json response similar to the one shown below:
 
  ``````
  {"ok": true,"reminder": {"id": "RmHEPSLA3S","creator": "UGNJPN1G8","user": "UGNJPN1G8","text": "Ann's Birthday","recurring": false,"time": 1602288000,"complete_ts": 0}}