/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.otpp.annualstatement.service.integration;

import com.google.common.io.Resources;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import integration.com.otpp.annualstatement.service.integration.SftpOutboundTransferManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.file.remote.RemoteFileTemplate;
import org.springframework.integration.file.remote.session.CachingSessionFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.util.Assert;
import org.testng.annotations.Test;

@ContextConfiguration(classes = {CyberArkSerivceConfig.class})
@TestPropertySource("classpath:application-it.properties")
public class SftpOutboundTransferManagerIT extends AbstractTestNGSpringContextTests {

    @Autowired
    CachingSessionFactory sftpSessionFactory;


    @Autowired
    SftpOutboundTransferManager sftpOutboundTransferManager;

    private static final String SOURCE_FILE_NAME = "application-it.properties";

    private static final String DIRECTORY = "/PSOBDEV/sample";
    @Test
    public void send_byteArray_fileExists() throws Exception{


        @SuppressWarnings("unchecked")
        RemoteFileTemplate<LsEntry> template = new RemoteFileTemplate<LsEntry>(sftpSessionFactory);
        try {

            byte[] docText = Resources.toByteArray(Resources.getResource(SOURCE_FILE_NAME));

            sftpOutboundTransferManager.send(docText,SOURCE_FILE_NAME,DIRECTORY);

            Thread.sleep(2000);

           Assert.isTrue(SftpTestUtils.fileExists(DIRECTORY,template, SOURCE_FILE_NAME));
            logger.info(String.format("Successfully transferred '%s' file to a " +
                    "remote location under the name '%s'", SOURCE_FILE_NAME, SOURCE_FILE_NAME));
        }
        finally {
            SftpTestUtils.cleanUp(DIRECTORY,template, SOURCE_FILE_NAME);

        }


    }
}
