/*
 * Copyright (c) 2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * you may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.carbon.connector.integration.test.slack;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.wso2.connector.integration.test.base.ConnectorIntegrationTestBase;
import org.wso2.connector.integration.test.base.RestResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Integration test for Slack connector.
 */
public class SlackConnectorIntegrationTest extends ConnectorIntegrationTestBase {

    private Map<String, String> eiRequestHeadersMap = new HashMap<String, String>();
    private Map<String, String> apiRequestHeadersMap = new HashMap<String, String>();

    private String apiUrlEndPoint;

    @BeforeClass(alwaysRun = true)
    public void setEnvironment() throws Exception {

        String connectorName = System.getProperty("connector_name") + "-connector-" +
                System.getProperty("connector_version") + ".zip";

        init(connectorName);

        eiRequestHeadersMap.put("Accept-Charset", "UTF-8");
        eiRequestHeadersMap.put("Content-Type", "application/json");
        apiRequestHeadersMap.put("Accept-Charset", "UTF-8");
        apiRequestHeadersMap.put("Content-Type", "application/x-www-form-urlencoded");

        getApiConfigProperties();
        String apiToken = connectorProperties.getProperty("apiToken");

        apiRequestHeadersMap.put("Authorization", "Bearer " + apiToken);
        apiUrlEndPoint = connectorProperties.getProperty("apiUrl") + "/api";

        Random rnd = new Random();
        char m = (char) (rnd.nextInt(26) + 'a');
        connectorProperties.setProperty("groupName", m + connectorProperties.getProperty("groupName"));
        Random rnd1 = new Random();
        char m1 = (char) (rnd1.nextInt(26) + 'a');
        connectorProperties.setProperty("channelNameApi1", m1 + connectorProperties.getProperty("channelNameApi1"));
        Random rnd2 = new Random();
        char m2 = (char) (rnd2.nextInt(26) + 'a');
        connectorProperties.setProperty("channelNameApi", m2 + connectorProperties.getProperty("channelNameApi"));
        Random rnd3 = new Random();
        char m3 = (char) (rnd3.nextInt(26) + 'a');
        connectorProperties.setProperty("groupNameOpt", m3 + connectorProperties.getProperty("groupNameOpt"));
        Random rnd4 = new Random();
        char m4 = (char) (rnd4.nextInt(26) + 'a');
        connectorProperties.setProperty("groupName3", m4 + connectorProperties.getProperty("groupName3"));
        Random rnd5 = new Random();
        char m5 = (char) (rnd5.nextInt(26) + 'a');
        connectorProperties.setProperty("channelName", m5 + connectorProperties.getProperty("channelName"));
        Random rnd6 = new Random();
        char m6 = (char) (rnd6.nextInt(26) + 'a');
        connectorProperties.setProperty("channelName1", m6 + connectorProperties.getProperty("channelName1"));
        Random rnd7 = new Random();
        char m7 = (char) (rnd7.nextInt(26) + 'a');
        connectorProperties.setProperty("groupName1", m7 + connectorProperties.getProperty("groupName1"));
        Random rnd8 = new Random();
        char m8 = (char) (rnd8.nextInt(26) + 'a');
        connectorProperties.setProperty("groupName2", m8 + connectorProperties.getProperty("groupName2"));

    }

    /**
     * Positive test case for authTest method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {authTest} integration test with mandatory parameters.")
    public void authTestWithMandatoryParameters() throws Exception {

        String methodName = "authTest";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_authTest_mandatory.json");
        String apiEndPoint = apiUrlEndPoint + "/auth.test";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap, "api_authTest_mandatory.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("url"), apiRestResponse.getBody().getString("url")
        );
    }

    /**
     * Negative test case for authTest method.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {authTest} negative integration test with mandatory parameters.")
    public void authTestNegative() throws Exception {

        String methodName = "authTest";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_authTest_negative.json");
        Assert.assertTrue(esbRestResponse.getBody().has("error"));
    }

    /**
     * Positive test case for createChannel method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {createChannel} integration test with mandatory parameters.")
    public void createChannelWithMandatoryParameters() throws Exception {

        String methodName = "createChannel";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_createChannel_mandatory.json");
        String cId = esbRestResponse.getBody().getJSONObject("channel").getString("id");
        connectorProperties.setProperty("channelId", cId);
        String apiEndPoint = apiUrlEndPoint + "/channels.create";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_createChannel_mandatory.text");
        String cId1 = apiRestResponse.getBody().getJSONObject("channel").getString("id");
        connectorProperties.setProperty("channelId1", cId1);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("channel"));
        Assert.assertTrue(apiRestResponse.getBody().has("channel"));
    }

    /**
     * Positive test case for createChannel method with optional parameters.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {createChannel} integration test with optional parameters.")
    public void createChannelWithOptionalParameters() throws Exception {

        String methodName = "createChannel";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_createChannel_optional.json");
        String cId = esbRestResponse.getBody().getJSONObject("channel").getString("id");
        connectorProperties.setProperty("channelIdOpt", cId);
        String apiEndPoint = apiUrlEndPoint + "/channels.create";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_createChannel_optional.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("channel"));
        Assert.assertTrue(apiRestResponse.getBody().has("channel"));
    }

    /**
     * Negative test case for createChannel method.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {createChannel} negative integration test.")
    public void createChannelNegative() throws Exception {

        String methodName = "createChannel";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_createChannel_negative.json");
        String apiEndPoint = apiUrlEndPoint + "/channels.create";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_createChannel_negative.text");
        Assert.assertEquals(esbRestResponse.getBody().has("error"), apiRestResponse.getBody().has("error"));
    }

    /**
     * Positive test case for inviteUserToChannel method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"createChannelWithMandatoryParameters",
            "createChannelWithOptionalParameters"}, description = "slack {inviteUserToChannel} integration test with " +
            "mandatory parameters.")
    public void inviteUserToChannelWithMandatoryParameters() throws Exception {

        String methodName = "inviteUserToChannel";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_inviteUserToChannel_mandatory.json");
        String cId = esbRestResponse.getBody().getJSONObject("channel").getString("id");
        connectorProperties.setProperty("channelId", cId);
        String apiEndPoint = apiUrlEndPoint + "/channels.invite";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_inviteUserToChannel_mandatory.text");
        String cId1 = apiRestResponse.getBody().getJSONObject("channel").getString("id");
        connectorProperties.setProperty("channelIdn", cId1);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("channel"));
        Assert.assertTrue(apiRestResponse.getBody().has("channel"));

    }

    /**
     * Negative test case for inviteUserToChannel method .
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"createChannelWithMandatoryParameters"}, description =
            "slack {inviteUserToChannel} negative integration test.")
    public void inviteUserToChannelNegative() throws Exception {

        String methodName = "inviteUserToChannel";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_inviteUserToChannel_negative.json");
        String apiEndPoint = apiUrlEndPoint + "/channels.invite";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_inviteUserToChannel_negative.text");
        Assert.assertEquals(esbRestResponse.getBody().has("error"), apiRestResponse.getBody().has("error"));
    }

    /**
     * Positive test case for joinToChannel method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {joinToChannel} integration test with mandatory parameters.")
    public void joinToChannelWithMandatoryParameters() throws Exception {

        String methodName = "joinToChannel";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_joinToChannel_mandatory.json");
        String apiEndPoint = apiUrlEndPoint + "/channels.join";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_joinToChannel_mandatory.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("channel"));
        Assert.assertTrue(apiRestResponse.getBody().has("channel"));
    }

    /**
     * Positive test case for joinToChannel method with optional parameters.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {joinToChannel} integration test with optional parameters.")
    public void joinToChannelWithOptionalParameters() throws Exception {

        String methodName = "joinToChannel";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_joinToChannel_optional.json");
        String apiEndPoint = apiUrlEndPoint + "/channels.join";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_joinToChannel_optional.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("channel"));
        Assert.assertTrue(apiRestResponse.getBody().has("channel"));
    }

    /**
     * Negative test case for joinToChannel method.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {joinToChannel} negative integration test.")
    public void joinToChannelNegative() throws Exception {

        String methodName = "joinToChannel";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_joinToChannel_negative.json");
        String apiEndPoint = apiUrlEndPoint + "/channels.join";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_joinToChannel_negative.text");
        Assert.assertEquals(esbRestResponse.getBody().has("error"), apiRestResponse.getBody().has("error"));
    }

    /**
     * Positive test case for leaveFromChannel method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {leaveFromChannel} integration test with mandatory parameters.")
    public void leaveFromChannelMandatory() throws Exception {

        String methodName = "leaveFromChannel";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_leaveFromChannel_mandatory.json");
        String apiEndPoint = apiUrlEndPoint + "/channels.leave";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_leaveFromChannel_mandatory.text");
        Assert.assertTrue(esbRestResponse.getBody().has("ok"));
        Assert.assertTrue(apiRestResponse.getBody().has("ok"));
    }

    /**
     * Negative test case for leaveFromChannel method.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {leaveFromChannel} negative integration test.")
    public void leaveFromChannelNegative() throws Exception {

        String methodName = "leaveFromChannel";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_leaveFromChannel_negative.json");
        String apiEndPoint = apiUrlEndPoint + "/channels.leave";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_leaveFromChannel_negative.text");
        Assert.assertEquals(esbRestResponse.getBody().has("error"), apiRestResponse.getBody().has("error"));
    }

    /**
     * Positive test case for sendMessage method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"createChannelWithMandatoryParameters"}, description =
            "slack {sendMessage} integration test with mandatory parameters.")
    public void sendMessageWithMandatoryParameters() throws Exception {

        String methodName = "sendMessage";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_sendMessage_mandatory.json");
        String tsmp = esbRestResponse.getBody().getString("ts");
        connectorProperties.setProperty("timeStamp", tsmp);
        String apiEndPoint = apiUrlEndPoint + "/chat.meMessage";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_sendMessage_mandatory.text");
        String tsmp1 = apiRestResponse.getBody().getString("ts");
        connectorProperties.setProperty("timeStamp1", tsmp1);
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("channel"));
        Assert.assertTrue(apiRestResponse.getBody().has("channel"));
    }

    /**
     * Negative test case for sendMessage method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"createChannelWithMandatoryParameters"}, description =
            "slack {sendMessage} negative integration test.")
    public void sendMessageNegative() throws Exception {

        String methodName = "sendMessage";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_sendMessage_negative.json");
        String apiEndPoint = apiUrlEndPoint + "/chat.meMessage";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_sendMessage_negative.text");
        Assert.assertEquals(esbRestResponse.getBody().has("error"), apiRestResponse.getBody().has("error"));
    }

    /**
     * Positive test case for deleteMessage method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"sendMessageWithMandatoryParameters"}, description = "slack " +
            "{deleteMessage} integration test with mandatory parameters.")
    public void deleteMessageMandatory() throws Exception {

        String methodName = "deleteMessage";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_deleteMessage_mandatory.json");
        String apiEndPoint = apiUrlEndPoint + "/chat.delete";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_deleteMessage_mandatory.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("channel"));
        Assert.assertTrue(apiRestResponse.getBody().has("channel"));
    }

    /**
     * Negative test case for deleteMessage method.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"sendMessageWithMandatoryParameters"}, description =
            "slack {deleteMessage}negative integration test.")
    public void deleteMessageNegative() throws Exception {

        String methodName = "deleteMessage";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_deleteMessage_negative.json");
        String apiEndPoint = apiUrlEndPoint + "/chat.delete";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_deleteMessage_negative.text");
        Assert.assertEquals(esbRestResponse.getBody().has("error"), apiRestResponse.getBody().has("error"));
    }

    /**
     * Positive test case for createGroup method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {createGroup} integration test with mandatory parameters.")
    public void createGroupWithMandatoryParameters() throws Exception {

        String methodName = "createGroup";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_createGroup_mandatory.json");
        String cId = esbRestResponse.getBody().getJSONObject("group").getString("id");
        connectorProperties.setProperty("channelId", cId);
        String apiEndPoint = apiUrlEndPoint + "/groups.create";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_createGroup_mandatory.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("group"));
        Assert.assertTrue(apiRestResponse.getBody().has("group"));
    }

    /**
     * Positive test case for createGroup method with optional parameters.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {createGroup} integration test with optional parameters.")
    public void createGroupOptional() throws Exception {

        String methodName = "createGroup";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_createGroup_optional.json");
        String cId = esbRestResponse.getBody().getJSONObject("group").getString("id");
        connectorProperties.setProperty("channelIdOpt", cId);
        String apiEndPoint = apiUrlEndPoint + "/groups.create";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_createGroup_optional.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("group"));
        Assert.assertTrue(apiRestResponse.getBody().has("group"));
    }

    /**
     * Negative test case for createGroup method.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {createGroup} negative integration test.")
    public void createGroupNegative() throws Exception {

        String methodName = "createGroup";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_createChannel_negative.json");
        String apiEndPoint = apiUrlEndPoint + "/groups.create";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_createChannel_negative.text");
        Assert.assertEquals(esbRestResponse.getBody().has("error"), apiRestResponse.getBody().has("error"));
    }

    /**
     * Positive test case for inviteUserToGroup method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"createGroupWithMandatoryParameters", "createGroupOptional"},
            description = "slack {inviteUserToGroup} integration test with mandatory parameters.")
    public void inviteUserToGroupWithMandatoryParameters() throws Exception {

        String methodName = "inviteUserToGroup";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_inviteUserToGroup_mandatory.json");
        String cId = esbRestResponse.getBody().getJSONObject("group").getString("id");
        connectorProperties.setProperty("channelId", cId);
        String apiEndPoint = apiUrlEndPoint + "/groups.invite";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_inviteUserToGroup_mandatory.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("group"));
        Assert.assertTrue(apiRestResponse.getBody().has("group"));
    }

    /**
     * Negative test case for inviteUserToGroup method .
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"createGroupWithMandatoryParameters"}, description =
            "slack {inviteUserToGroup} negative integration test.")
    public void inviteUserToGroupNegative() throws Exception {

        String methodName = "inviteUserToGroup";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_inviteUserToGroup_negative.json");
        String apiEndPoint = apiUrlEndPoint + "/groups.invite";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_inviteUserToGroup_negative.text");
        Assert.assertEquals(esbRestResponse.getBody().has("error"), apiRestResponse.getBody().has("error"));
    }

    /**
     * Positive test case for renameGroup method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"createGroupWithMandatoryParameters"}, description =
            "slack {renameGroup} integration test with mandatory parameters.")
    public void renameGroupWithMandatoryParameters() throws Exception {

        String methodName = "renameGroup";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_renameGroup_mandatory.json");
        String apiEndPoint = apiUrlEndPoint + "/groups.rename";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_renameGroup_mandatory.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("channel"));
        Assert.assertTrue(apiRestResponse.getBody().has("channel"));
    }

    /**
     * Positive test case for renameGroup method with optional parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"createGroupWithMandatoryParameters"}, description =
            "slack {renameGroup} integration test with optional parameters.")
    public void renameGroupOptional() throws Exception {

        String methodName = "renameGroup";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_renameGroup_optional.json");
        String apiEndPoint = apiUrlEndPoint + "/groups.rename";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_renameGroup_optional.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("channel"));
        Assert.assertTrue(apiRestResponse.getBody().has("channel"));
    }

    /**
     * Negative test case for renameGroup method.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"createGroupWithMandatoryParameters"}, description = "slack " +
            "{renameGroup} negative integration test.")
    public void renameGroupNegative() throws Exception {

        String methodName = "renameGroup";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_renameGroup_negative.json");
        String apiEndPoint = apiUrlEndPoint + "/groups.rename";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_renameGroup_negative.text");
        Assert.assertEquals(esbRestResponse.getBody().has("error"), apiRestResponse.getBody().has("error"));
    }

    /**
     * Positive test case for setPurposeForGroup method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"createGroupWithMandatoryParameters"}, description = "slack " +
            "{setPurposeForGroup} integration test with mandatory parameters.")
    public void setPurposeForGroupMandatory() throws Exception {

        String methodName = "setPurposeForGroup";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_setPurposeForGroup_mandatory.json");
        String apiEndPoint = apiUrlEndPoint + "/groups.setPurpose";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_setPurposeForGroup_mandatory.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("purpose"));
        Assert.assertTrue(apiRestResponse.getBody().has("purpose"));
    }

    /**
     * Negative test case for setPurposeForGroup method.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"createGroupWithMandatoryParameters"}, description = "slack " +
            "{setPurposeForGroup} negative integration test.")
    public void setPurposeForGroupNegative() throws Exception {

        String methodName = "setPurposeForGroup";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_setPurposeForGroup_negative.json");
        String apiEndPoint = apiUrlEndPoint + "/groups.setPurpose";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_setPurposeForGroup_negative.text");
        Assert.assertEquals(esbRestResponse.getBody().getString("error"), apiRestResponse.getBody().getString
                ("error"));
    }

    /**
     * Positive test case for addReminder method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {addReminder} integration test with mandatory parameters.")
    public void addReminderWithMandatoryParameters() throws Exception {

        String methodName = "addReminder";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_addReminder_mandatory.json");
        String cId = esbRestResponse.getBody().getJSONObject("reminder").getString("id");
        connectorProperties.setProperty("reminderId", cId);
        String apiEndPoint = apiUrlEndPoint + "/reminders.add";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_addReminder_mandatory.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("reminder"));
        Assert.assertTrue(apiRestResponse.getBody().has("reminder"));
    }

    /**
     * Positive test case for addReminder method with optional parameters.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {addReminder} integration test with optional parameters.")
    public void addReminderOptional() throws Exception {

        String methodName = "addReminder";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_addReminder_optional.json");
        String apiEndPoint = apiUrlEndPoint + "/reminders.add";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_addReminder_optional.text");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertTrue(esbRestResponse.getBody().has("reminder"));
        Assert.assertTrue(apiRestResponse.getBody().has("reminder"));
    }

    /**
     * Negative test case for addReminder method.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {addReminder} negative integration test.")
    public void addReminderNegative() throws Exception {

        String methodName = "addReminder";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_addReminder_negative.json");
        String apiEndPoint = apiUrlEndPoint + "/reminders.add";
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "POST",
                apiRequestHeadersMap, "api_addReminder_negative.text");
        Assert.assertEquals(esbRestResponse.getBody().has("error"), apiRestResponse.getBody().has("error"));
    }

    /**
     * Positive test case for getReminderInfo method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, dependsOnMethods = {"addReminderWithMandatoryParameters"}, description = "slack " +
            "{getReminderInfo} integration test with mandatory parameters.")
    public void getReminderInfoMandatory() throws Exception {

        String methodName = "getReminderInfo";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_getReminderInfo_mandatory.json");
        String apiEndPoint = apiUrlEndPoint + "/reminders.info?reminder=" + connectorProperties.getProperty
                ("reminderId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap, "api_getReminderInfo_mandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("reminder"), apiRestResponse.getBody().getString
                ("reminder"));
    }

    /**
     * Positive test case for getUserProfile method with mandatory parameters.
     */
    @Test(groups = {"wso2.ei"}, description = "slack {getUserProfile} integration test with mandatory parameters.")
    public void getUserProfileMandatory() throws Exception {

        String methodName = "getUserProfile";
        RestResponse<JSONObject> esbRestResponse = sendJsonRestRequest(getProxyServiceURLHttp(methodName),
                "POST", eiRequestHeadersMap, "esb_getUserProfile_mandatory.json");
        String apiEndPoint = apiUrlEndPoint + "/users.profile.get?include_labels=" + connectorProperties.getProperty
                ("includeLables") + "&user=" + connectorProperties.getProperty("userId");
        RestResponse<JSONObject> apiRestResponse = sendJsonRestRequest(apiEndPoint, "GET",
                apiRequestHeadersMap, "api_getUserProfile_mandatory.json");
        Assert.assertEquals(esbRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(apiRestResponse.getHttpStatusCode(), 200);
        Assert.assertEquals(esbRestResponse.getBody().getString("profile"), apiRestResponse.getBody()
                .getString("profile"));
    }
}
