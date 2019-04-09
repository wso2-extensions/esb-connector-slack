# Working with Slack groups

[[  Overview ]](#overview)  [[ Operation details ]](#operation-details)  [[  Sample configuration  ]](#sample-configuration)

### Overview 

The following operations allow you to work with slack groups. Click an operation name to see details on how to use it.
For a sample proxy service that illustrates how to work with groups, see [Sample configuration](#sample-configuration).

| Operation        | Description |
| ------------- |-------------|
| [createGroup](#Creating-a-group)    | Creates a private channel in the Slack workspace. |
| [inviteUserToGroup](#Inviting-a-user-to-a-group)      | Invites a user to a private channel. |
| [renameGroup](#Rename-a-group)      |  Renames a private channel. |
| [setPurposeForGroup](#Set-purpose-for-a-group)      | Sets the purpose for a private channel.|


### Operation details

This section provides more details on each of the operations.

#### Creating a group
The createGroup operation creates a group in the Slack workspace that you specify.

**createGroup**
```xml
<slack.createChannel>
    <channelName>{$ctx:channelName}</channelName>
    <validate>{$ctx:validate}</validate>
</slack.createChannel>
```

**Properties**
* channelName       : Required - The name of the private channel to create.
* validate   : Optional - Whether to return errors on invalid channel name instead of modifying it to meet the specified criteria.


**Sample request**

Following is a sample request that can be handled by the createGroup operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"channelName": "secretplans",
	"validate": "true"
}
```
**Sample response**

Given below is a sample response for the createGroup operation.

```json
{
    "ok": true,
    "group": {
        "id": "GHDB24153",
        "name": "secretplans",
        "is_group": true,
        "created": 1553757202,
        "creator": "UGNJPN1G8",
        "is_archived": false,
        "name_normalized": "secretplans",
        "is_mpim": false,
        "is_open": true,
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
        "priority": 0
    }
}

```

**Related Slack documentation**

https://api.slack.com/methods/groups.create

#### Inviting a user to a group
The inviteUserToGroup operation invites a user to a private channel.


**inviteUserToGroup**
```xml
<slack.inviteUserToGroup>
    <channelId>{$ctx:channelId}</channelId>
    <userId>{$ctx:userId}</userId>
</slack.inviteUserToGroup>
```

**Properties**
* channelId       : Required - The id of the private channel to invite user to.

* userId   : Required - The id of the user to invite to the private channel.


**Sample request**

Following is a sample request that can be handled by the inviteUserToGroup operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"channelId": "GH72WK5AR",
	"userId": "UGQLS7BRT"
}
```
**Sample response**

Given below is a sample response for the inviteUserToGroup operation.

```json
{
    "ok": true,
    "group": {
        "id": "G024BE91L",
        "name": "secretplans",
        "is_group": "true",
        "created": 1360782804,
        "creator": "U024BE7LH",
        "is_archived": false,
        "is_open": true,
        "last_read": "0000000000.000000",
        "latest": null,
        "unread_count": 0,
        "unread_count_display": 0,
        "members": [
            "U024BE7LH"
        ],
        "topic": {
            "value": "Secret plans on hold",
            "creator": "U024BE7LV",
            "last_set": 1369677212
        },
        "purpose": {
            "value": "Discuss secret plans that no-one else should know",
            "creator": "U024BE7LH",
            "last_set": 1360782804
        }
    }
}

```

**Related Slack documentation**
https://api.slack.com/methods/groups.invite

#### Rename a group
Renames a private channel.

**renameGroup**
```xml
<slack.renameGroup>
    <channelId>{$ctx:channelId}</channelId>
    <channelName>{$ctx:channelName}</channelName>
    <validate>{$ctx:validate}</validate>
</slack.renameGroup>
```

**Properties**
* channelId     : Required - The id of the private channel to rename.

* channelName      : Required - New name for private channe.

* validate   : Optional - Whether to return errors on invalid channel name instead of modifying it to meet the specified criteria.

**Sample request**

Following is a sample request that can be handled by the renameGroup operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"channelId": "GHCQTJX1C",
	"channelName": "newchannel",
	"validate": "true"
}
```
**Sample response**

Given below is a sample response for the renameGroup operation.

```json
{
    "channel": {
        "id": "GHCQTJX1C",
        "name": "newchannel",
        "is_group": true,
        "created": 1554111243,
        "creator": "UGNJPN1G8",
        "is_archived": false,
        "name_normalized": "newchannel",
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
        }
    },
    "ok": true
}

```

**Related Slack documentation**

https://api.slack.com/methods/groups.rename

#### Set purpose for a group
Sets the purpose for a private channel.

**setPurposeForGroup*
```xml
<slack.setPurposeForGroup>
    <channelId>{$ctx:channelId}</channelId>
    <purpose>{$ctx:purpose</purpose>
</slack.setPurposeForGroup>
```

**Properties**
* channelId       : Required - The id of the private channel to set the purpose for.

* purpose       : Required - The new purpose to set for the private channel.


**Sample request**

Following is a sample request that can be handled by the setPurposeForGroup operation.

```json
{
	"apiUrl": "https://slack.com",
	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
	"channelId": "GH72WK5AR",
	"purpose": "Intern discussions"
}
```
**Sample response**

Given below is a sample response for the setPurposeForGroup operation.

```json
{
    "ok": true,
    "purpose": "Intern discussions"
}

```

**Related Slack documentation**

https://api.slack.com/methods/groups.setPurpose


#### Sample configuration

Following is a sample proxy service that illustrates how to connect to Slack with the init operation, and then use the setPurposeForGroup operation. The sample request for this proxy can be found in the setPurposeForGroup sample request. You can use this sample as a template for using other operations in this category.

1. Create a sample proxy as below :

    **Sample Proxy**
    ```xml
    <proxy xmlns="http://ws.apache.org/ns/synapse"
           name="slack_setPurposeForGroup"
           startOnLoad="true"
           statistics="disable"
           trace="disable"
           transports="http,https">
       <target>
          <inSequence>
             <property expression="json-eval($.purpose)" name="purpose"/>
             <property expression="json-eval($.channelId)" name="channelId"/>
             <property expression="json-eval($.apiUrl)" name="apiUrl"/>
             <property expression="json-eval($.apiToken)" name="apiToken"/>
             <slack.init>
                <apiToken>{$ctx:apiToken}</apiToken>
                <apiUrl>{$ctx:apiUrl}</apiUrl>
             </slack.init>
             <slack.setPurposeForGroup>
                <channelId>{$ctx:channelId}</channelId>
                <purpose>{$ctx:purpose}</purpose>
             </slack.setPurposeForGroup>
             <respond/>
          </inSequence>
          <outSequence/>
          <faultSequence/>
       </target>
       <description/>
    </proxy
    ```
2. Create a json file named setPurposeForGroup.json and copy the configurations given below to it:
 
     ```json
     {
     	"apiUrl": "https://slack.com",
     	"apiToken": "xoxp-568453711959-56663574955a4c54abbf7ca858",
     	"channelId": "GH72WK5AR",
     	"purpose": "Intern discussions"
     }
     ```
     
3. Replace the credentials with your values. 

4. Execute the following curl command:
    ```bash   
    curl -X POST http://localhost:8280/services/slack_setPurposeForGroup --header 'Content-Type: application/json' -d @setPurposeForGroup.json
    ``` 
5. Slack returns a json response similar to the one shown below:
 
      ``````
    {"ok": true,"purpose": "Intern discussions"}