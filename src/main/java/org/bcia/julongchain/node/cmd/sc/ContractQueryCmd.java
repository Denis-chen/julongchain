/**
 * Copyright Dingxuan. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.bcia.julongchain.node.cmd.sc;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.protobuf.ByteString;
import org.apache.commons.cli.*;
import org.apache.commons.lang3.StringUtils;
import org.bcia.julongchain.common.exception.NodeException;
import org.bcia.julongchain.common.log.JulongChainLog;
import org.bcia.julongchain.common.log.JulongChainLogFactory;
import org.bcia.julongchain.common.util.NetAddress;
import org.bcia.julongchain.node.Node;
import org.bcia.julongchain.node.common.util.NodeConstant;
import org.bcia.julongchain.protos.node.ProposalResponsePackage;
import org.bcia.julongchain.protos.node.SmartContractPackage;

/**
 * 完成节点查询智能合约的解析
 * node contract query -t 127.0.0.1:7051 -g $group_id -n mycc -i "{'Args':['query','a']}"
 *
 * @author zhouhui
 * @date 2018/3/16
 * @company Dingxuan
 */
public class ContractQueryCmd extends AbstractNodeContractCmd {
    private static JulongChainLog log = JulongChainLogFactory.getLog(ContractQueryCmd.class);
    /**
     * Target地址(Node)
     */
    private static final String ARG_TARGET = "t";

    /**
     * 参数：groupId
     */
    private static final String ARG_GROUP_ID = "g";
    /**
     * 参数：智能合约的名称
     */
    private static final String ARG_SC_NAME = "n";
    /**
     * 参数：智能合约的Input
     */
    private static final String ARG_INPUT = "i";
    /**
     * 参数
     */
    private static final String KEY_ARGS = "args";

    public ContractQueryCmd(Node node) {
        super(node);
    }

    @Override
    public void execCmd(String[] args) throws ParseException, NodeException {
        Options options = new Options();
        options.addOption(ARG_TARGET, true, "Input target address");
        options.addOption(ARG_GROUP_ID, true, "Input group id");
        options.addOption(ARG_SC_NAME, true, "Input smart contract id");
        options.addOption(ARG_INPUT, true, "");

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = parser.parse(options, args);

        String defaultValue = "";

        //-----------------------------------解析参数值-------------------------------//
        String targetAddress = null;
        if (cmd.hasOption(ARG_TARGET)) {
            targetAddress = cmd.getOptionValue(ARG_TARGET, defaultValue);
            log.info("TargetAddress: " + targetAddress);
        }

        //解析出群组ID
        String groupId = null;
        if (cmd.hasOption(ARG_GROUP_ID)) {
            groupId = cmd.getOptionValue(ARG_GROUP_ID, defaultValue);
            log.info("GroupId: " + groupId);
        }

        //解析出智能合约名称
        String scName = null;
        if (cmd.hasOption(ARG_SC_NAME)) {
            scName = cmd.getOptionValue(ARG_SC_NAME, defaultValue);
            log.info("Smart contract id: " + scName);
        }

        //解析出智能合约执行参数
        SmartContractPackage.SmartContractInput input = getSmartContractInput(cmd, ARG_INPUT, defaultValue);

        //-----------------------------------校验入参--------------------------------//
        if (StringUtils.isBlank(groupId)) {
            log.error("GroupId should not be null, Please input it");
            return;
        }

        if (StringUtils.isBlank(scName)) {
            log.error("SmartContractName should not be null, Please input it");
            return;
        }

        ProposalResponsePackage.Response response = null;

        if (StringUtils.isBlank(targetAddress)) {
            log.info("TargetAddress is empty, use 127.0.0.1:7051");
            response = nodeSmartContract.query(NodeConstant.DEFAULT_NODE_HOST, NodeConstant.DEFAULT_NODE_PORT,
                    groupId, scName, input);
        } else {
            try {
                NetAddress targetNetAddress = new NetAddress(targetAddress);
                response = nodeSmartContract.query(targetNetAddress.getHost(), targetNetAddress.getPort(), groupId,
                        scName, input);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
            }
        }

        if (response != null) {
            log.info("Query result: " + response.getMessage() + ", " + response.getPayload());
        } else {
            log.info("No query result");
        }
    }

}
