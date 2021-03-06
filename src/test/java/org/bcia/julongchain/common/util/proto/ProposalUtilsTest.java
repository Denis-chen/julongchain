package org.bcia.julongchain.common.util.proto;

import org.bcia.julongchain.common.exception.JulongChainException;
import org.junit.Test;

/**
 * 提案工具测试类
 *
 * @author zhouhui
 * @date 2018/3/28
 * @company Dingxuan
 */
public class ProposalUtilsTest {

    @Test
    public void buildSignedProposal() {
    }

    @Test
    public void buildSmartContractProposal() {
    }

    @Test
    public void buildProposalPayload() {
    }

    @Test
    public void computeProposalTxID() throws JulongChainException {
        long beginTime = System.currentTimeMillis();
        String txId = ProposalUtils.computeProposalTxID("zhouhui".getBytes(), "12345678907879887908".getBytes());
        long endTime = System.currentTimeMillis();

        System.out.println(txId + ",耗时"+ (endTime-beginTime) + "ms");
    }
}