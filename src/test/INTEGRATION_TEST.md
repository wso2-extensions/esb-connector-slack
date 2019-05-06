##Integration tests for WSO2 ESB Slack connector

### Pre-requisites:

    - Maven 3.x
    - Java 1.8 

### Tested Platforms:

    - Ubuntu 16.04
    - WSO2 EI 6.4.0
    - java 1.8

##### Steps to follow in setting the Slack workspace account.


1. To work with the Slack Web API connector, you need to have a Slack workspace account. If you do not have a Slack workspace account, go to https://slack.com/create#email and create a Slack workspace account.
   
 2. To access the Slack Web API [Register your application](https://api.slack.com/apps) with Slack workspace to obtain credentials to use with [OAuth 2.0](https://api.slack.com/docs/oauth) implementation, which allows you to negotiate a token on behalf of users and workspaces.Your app asks for specific [permission scopes](https://api.slack.com/scopes) and is rewarded with a token upon your approval.
### Steps to follow in setting integration test.

1.Download EI 6.4.0  by navigating to the following [URL](http://wso2.com/products/enterprise-service-bus/#).

2.Copy the EI 6.4.0 zip to the location `Connector_Home/repository/`.

3.Follow the below mentioned step for adding valid certificate to the file system to access Slack Web API

         Copy the certificate "slack.pem" by navigating to https://slack.com/create#email and place the certificate file in following location.
        "{SLACK_CONNECTOR_HOME}/src/repository"
        

4.Update the 'esb-connector-slack.properties' file at the location "{SLACK_CONNECTOR_HOME}/repository" as below

        i)    apiUrl                 -	Api URL to access the Slack Web API.
        ii)   apiToken               -	The token which is used to authenticate the Slack API. This token bears required scopes of the user.
        iii)  userId                 -	The id of the user to invite in to the channel.                                               

5.Update the slack properties file at location `<Connector_Home>/src/test/resources/artifacts/ESB/connector/config` as below.
<br/><br/>

| Property | Description |
| ------------- |-------------|
| channelName | Name of the channel to create|
| channelName1 | Name of the channel to create|
| channelNameApi | Name of the channel to create |
| channelNameApi1| Name of the channel to create |
| validate| Whether to return errors on invalid channel name instead of modifying it to meet the specified criteria |
| text| The content of the message to be send in to channel|
| text1| The content of the message to be send in to channel|
| text2| The content of the message to be send in to channel|
| asUser| Pass true to delete the message as the authed user with chat:write:user scope|
| groupName | Name of the group to create|
| groupName1 | Name of the group to create |
| groupName2| Name of the group to create |
| groupName3| Name of the group to create |
| groupNameOpt| Name of the group to create |
| purpose| The new purpose to set|
| time | When this reminder should happen: the Unix timestamp (up to five years from now), the number of seconds until the reminder (if within 24 hours), or a natural language description (Ex. "in 15 minutes)|
| includeLables| Include labels for each ID in custom profile fields|


6.Navigate to `{EI_Connector_Home}/` and run the following command.
             `$ mvn clean install -Dskip-tests=false`
