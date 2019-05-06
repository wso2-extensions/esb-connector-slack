# Slack EI Connector

The Slack [Connector](https://docs.wso2.com/display/EI640/Working+with+Connectors) provides a quick and easy way to communicate with your teammates in slack. It allows you to  access the  [Slack Web API](https://api.slack.com/web#authentication) which is an interface for querying information from and enacting change in a Slack workspace.
## Compatibility

| Connector version | Supported Slack API version | Supported WSO2 ESB/EI version |
| ------------- | ------------- | ------------- |
| [1.0.0](https://github.com/wso2-extensions/esb-connector-slack/tree/org.wso2.carbon.esb.connector.slack-1.0.0) | V1 |  EI 6.4.0    |

## Getting started

#### Download and install the connector

1. Download the connector from the [WSO2 Store](https://store.wso2.com/store/assets/esbconnector/details/ffb3980c-0399-4a6a-8632-8a59ce199079) by clicking the Download Connector button.
2. Then you can follow this [Documentation](https://docs.wso2.com/display/EI640/Working+with+Connectors+via+the+Management+Console) to add and enable the connector via the Management Console in your EI instance.
3. For more information on using connectors and their operations in your EI configurations, see [Using a Connector](https://docs.wso2.com/display/EI640/Using+a+Connector).
4. If you want to work with connectors via EI tooling, see [Working with Connectors via Tooling](https://docs.wso2.com/display/EI640/Working+with+Connectors+via+Tooling).

#### Configuring the connector operations

To get started with Slack connector and their operations, see [Configuring Slack Operations](docs/config.md).


## Building From the Source

Follow the steps given below to build the Slack connector from the source code:

1. Get a clone or download the source from [Github](https://github.com/wso2-extensions/esb-connector-slack).
2. Run the following Maven command from the `esb-connector-slack` directory: `mvn clean install`.
3. The Slack connector zip file is created in the `esb-connector-slack/target` directory

## How You Can Contribute

As an open source project, WSO2 extensions welcome contributions from the community.
Check the [issue tracker](https://github.com/wso2-extensions/esb-connector-splunk/issues) for open issues that interest you. We look forward to receiving your contributions.
