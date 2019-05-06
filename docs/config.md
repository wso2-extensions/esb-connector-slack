# Configuring Slack Operations

[[Prerequisites]](#Prerequisites) [[Initializing the connector]](#initializing-the-connector)

## Prerequisites

> NOTE: To work with the Slack Web API connector, you need to have a Slack workspace account. If you do not have a Slack workspace account, go to https://slack.com/create#email and create a Slack workspace account.

To access the Slack Web API [Register your application](https://api.slack.com/apps) with Slack workspace to obtain credentials to use with [OAuth 2.0](https://api.slack.com/docs/oauth) implementation, which allows you to negotiate a token on behalf of users and workspaces.Your app asks for specific [permission scopes](https://api.slack.com/scopes) and is rewarded with a token upon your approval.

To use the Slack connector, add the <slack.init> element in your configuration before carrying out any other Slack operations.

## Initializing the Connector
Specify the init method as follows:

**init**
```xml
<slack.init>
    <apiToken>{$ctx:apiToken}</apiToken>
    <apiUrl>{$ctx:apiUrl}</apiUrl>
</slack.init>
```
**Properties**
* apiToken: The token which is used to authenticate the Slack API. This token bears required scopes of the user.
* apiUrl: The application URL to access the slack Web API.

Now that you have connected to Slack, use the information in the following topics to perform various operations with the connector:

* To work with Slack authTest, See [Working with slack authTest](auth_test.md)
* To work with Slack channels, See [Working with slack channels](channel.md)
* To work with Slack messages, See [Working with slack messages](chat.md)
* To work with Slack groups, See [Working with slack groups](group.md)
* To work with Slack reminders, See [Working with slack reminders](reminder.md)
* To work with Slack users, See [Working with slack users](user.md)
